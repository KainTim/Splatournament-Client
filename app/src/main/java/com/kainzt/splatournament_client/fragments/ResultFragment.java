package com.kainzt.splatournament_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kainzt.splatournament_client.R;
import com.kainzt.splatournament_client.databinding.FragmentResultBinding;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

import java.util.List;

public class ResultFragment extends Fragment implements View.OnClickListener {
    private FragmentResultBinding binding;
    private MainViewModel viewModel;
    private List<Boolean> wins;
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
        wins = viewModel.wins;
        double count = (double) wins.stream().filter(aBoolean -> aBoolean).count();
        binding.txtResultWinRate.setText(String.format("Win-Rate: %.1f",count/wins.size()*100)+"%");
        binding.btnResultsShowHome.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnResultsShowHome){
            viewModel.showMenu();
            FragmentManager fm = requireActivity().getSupportFragmentManager();
            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
        }
    }
}