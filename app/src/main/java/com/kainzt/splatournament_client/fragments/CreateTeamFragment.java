package com.kainzt.splatournament_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kainzt.splatournament_client.R;
import com.kainzt.splatournament_client.databinding.FragmentCreateTeamBinding;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

public class CreateTeamFragment extends Fragment implements View.OnClickListener {
    private FragmentCreateTeamBinding binding;
    private MainViewModel viewModel;

    public CreateTeamFragment() {
        // Required empty public constructor
    }

    public static CreateTeamFragment newInstance() {
        return new CreateTeamFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateTeamBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        if (viewModel.isCreateTeam){
            binding.tbCreateTeam.setTitle(R.string.create_team);
            binding.btnCreateTeam.setText(R.string.create_and_join);
        }else {
            binding.tbCreateTeam.setTitle(R.string.join_a_team);
            binding.btnCreateTeam.setText(R.string.join);
        }
        binding.btnCreateTeam.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnCreateTeam){
            if (viewModel.isCreateTeam){
            }
        }
    }
}