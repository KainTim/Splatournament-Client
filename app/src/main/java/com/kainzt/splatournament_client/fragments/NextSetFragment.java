package com.kainzt.splatournament_client.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.kainzt.splatournament_client.R;
import com.kainzt.splatournament_client.databinding.FragmentNextSetBinding;
import com.kainzt.splatournament_client.models.Tournament;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class NextSetFragment extends Fragment implements View.OnClickListener, Toolbar.OnMenuItemClickListener {
    private FragmentNextSetBinding binding;
    private MainViewModel viewModel;
    private int setIndex = 0;
    private Long gameIndex = 0L;
    private boolean isWin = true;
    private Tournament tournament;
    private boolean showOwnTeamName = true;
    private List<Boolean> wins = new ArrayList<>();

    public NextSetFragment() {
        // Required empty public constructor
    }

    public static NextSetFragment newInstance() {
        return new NextSetFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNextSetBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
         tournament = viewModel.tournaments.stream()
                .filter(tournament1 -> tournament1.getId()== viewModel.currentTournamentId)
                .findFirst().get();

        // TODO: 19.06.24 GENERALIZE AND GET FROM SERVER
        ArrayList<List<Long>> sets = new ArrayList<>();
        ArrayList<Long> set1 = new ArrayList<>();
        set1.add(viewModel.currentTeamId);
        set1.add(12L);
        sets.add(set1);
        tournament.setSets(sets);
        viewModel.otherTeam = "TestOpponent";
        List<Long> set = tournament.getSets().get(setIndex);
        if (wins.size()<=0){
            for (int i = 0; i < tournament.getBestOf(); i++) {
                wins.add(true);
            }
        }
        tournament.getSets().add(new ArrayList<>());
        binding.txtNextSetGameIndex.setText(String.format("Game %d/%d", gameIndex+1,tournament.getBestOf()));
        binding.tbNextSet.setTitle(tournament.getName());
        binding.tbNextSet.setSubtitle("Your Team: " + viewModel.currentTeam);
        binding.tbNextSet.setOnClickListener(this);
        binding.btnNextSetGoNext.setOnClickListener(this);
        binding.btnNextSetEditPrevious.setOnClickListener(this);
        binding.imgNextSet.setOnClickListener(this);
        binding.tbNextSet.setOnMenuItemClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.tbNextSet){
            if (showOwnTeamName){
                binding.tbNextSet.setSubtitle("Your Opponent: " + viewModel.otherTeam);
            }else {
                binding.tbNextSet.setSubtitle("Your Team: " + viewModel.currentTeam);
            }
            showOwnTeamName = !showOwnTeamName;
        }
        if (v.getId() == R.id.btnNextSetGoNext) {
            gameIndex++;
            if (gameIndex>= tournament.getBestOf()){
                gameIndex = tournament.getBestOf()-1;
            }
            binding.txtNextSetGameIndex.setText(String.format("Game %d/%d", gameIndex+1,tournament.getBestOf()));
            binding.imgNextSet.setImageResource(getDrawableId(wins.get(Math.toIntExact(gameIndex))));
        }
        if (v.getId() == R.id.btnNextSetEditPrevious) {
            gameIndex--;
            if (gameIndex<0) gameIndex = 0L;
            binding.txtNextSetGameIndex.setText(String.format("Game %d/%d", gameIndex+1,tournament.getBestOf()));
            binding.imgNextSet.setImageResource(getDrawableId(wins.get(Math.toIntExact(gameIndex))));
        }
        if (v.getId() == R.id.imgNextSet) {
            wins.set(Math.toIntExact(gameIndex),!wins.get(Math.toIntExact(gameIndex)));
            binding.imgNextSet.setImageResource(getDrawableId(wins.get(Math.toIntExact(gameIndex))));
        }
    }
    private int getDrawableId(boolean win) {
        try {
            Class res = R.drawable.class;
            Field field = res.getField(win?"victory":"defeat");
            int drawableId = field.getInt(null);
            return drawableId;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId()==R.id.mitNextSetFinish){
            viewModel.wins = wins;
            viewModel.showResult();
            return true;
        }
        return false;
    }
}