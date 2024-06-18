package com.kainzt.splatournament_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kainzt.splatournament_client.R;
import com.kainzt.splatournament_client.databinding.FragmentNextSetBinding;
import com.kainzt.splatournament_client.models.Tournament;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class NextSetFragment extends Fragment implements View.OnClickListener {
    private FragmentNextSetBinding binding;
    private MainViewModel viewModel;
    private int setIndex;
    private int gameIndex = 0;
    private boolean isWin = true;

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
        Tournament tournament = viewModel.tournaments.stream()
                .filter(tournament1 -> tournament1.getId()== viewModel.currentTournamentId)
                .findFirst().get();
        tournament.getSets().add(new ArrayList<>());
        setIndex = tournament.getSets().size() - 1;
        binding.tbNextSet.setTitle(tournament.getName());
        binding.tbNextSet.setSubtitle("Your Team: " + viewModel.currentTeam);
        //viewModel.enterTournament(viewModel.tournaments.get(viewModel.currentTournamentIndex).getId(),viewModel.currentTeamId,requireActivity());
        binding.imgNextSet.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnNextSetGoNext) {

        }
        if (v.getId() == R.id.btnNextSetEditPrevious) {

        }
        if (v.getId() == R.id.imgNextSet) {
            Tournament tournament = viewModel.tournaments.stream()
                    .filter(tournament1 -> tournament1.getId()== viewModel.currentTournamentId)
                    .findFirst().get();
            List<String> strings = tournament.getSets().get(setIndex);
            if (isWin) {
                strings.set(gameIndex, viewModel.currentTeam);
            } else {
                strings.set(gameIndex, viewModel.otherTeam);
            }
        }
    }
}