package com.kainzt.splatournament_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kainzt.splatournament_client.R;
import com.kainzt.splatournament_client.databinding.FragmentHomeBinding;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private FragmentHomeBinding binding;
    private MainViewModel viewModel;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
            binding.cvMenuTournament.setOnClickListener(this);
            binding.cvMenuLeaderboards.setOnClickListener(this);
            binding.cvMenuWeapons.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cvMenuTournament){
            viewModel.showTournamentList();
        }
        if (v.getId() == R.id.cvMenuLeaderboards){

        }
        if (v.getId() == R.id.cvMenuWeapons){

        }
    }
}