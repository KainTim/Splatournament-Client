package com.kainzt.splatournament_client.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kainzt.splatournament_client.R;
import com.kainzt.splatournament_client.databinding.FragmentTournamentBinding;
import com.kainzt.splatournament_client.databinding.FragmentTournamentListBinding;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

public class TournamentFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private MainViewModel viewModel;
    private FragmentTournamentListBinding binding;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TournamentFragment() {
    }

    @SuppressWarnings("unused")
    public static TournamentFragment newInstance(int columnCount) {
        TournamentFragment fragment = new TournamentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTournamentListBinding.inflate(inflater, container, false);
        RecyclerView view = binding.lstTournament;
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        if (viewModel.tournaments.isEmpty()){
            binding.txtNoCurrentTournaments.setVisibility(View.VISIBLE);
        }else {
            binding.txtNoCurrentTournaments.setVisibility(View.INVISIBLE);
        }

        //viewModel.joiningTournamentState.removeObservers(requireActivity());
        viewModel.joiningTournamentState.observe(requireActivity(),integer ->
                {
                    Log.d("enter",integer+"");
                    if (integer == -1||integer ==-2) {
                        return;
                    }
                    viewModel.showNextSetFragment(1/*viewModel.tournaments.indexOf(
                            viewModel.tournaments.stream()
                                    .filter(tournament -> tournament.getId()==integer)
                                    .findFirst().get())*/);
                });
        // Set the adapter
        Context context = view.getContext();
        if (mColumnCount <= 1) {
            view.setLayoutManager(new LinearLayoutManager(context));
        } else {
            view.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        view.setAdapter(new MyTournamentRecyclerViewAdapter(viewModel.tournaments,position -> viewModel.enterTournament(viewModel.tournaments.get(position).getId(),viewModel.currentTeamId,requireActivity())));
        binding.fabCreateTournament.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fabCreateTournament){
            viewModel.showCreateTournament();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);;
    }
}