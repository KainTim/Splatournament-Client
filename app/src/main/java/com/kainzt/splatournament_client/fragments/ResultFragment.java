package com.kainzt.splatournament_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kainzt.splatournament_client.R;
import com.kainzt.splatournament_client.databinding.FragmentResultBinding;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

public class ResultFragment extends Fragment {
    private FragmentResultBinding binding;
    private MainViewModel viewModel;
    public ResultFragment() {
        // Required empty public constructor
    }
    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        return binding.getRoot();
    }
}