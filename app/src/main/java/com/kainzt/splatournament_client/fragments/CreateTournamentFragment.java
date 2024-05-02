package com.kainzt.splatournament_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.kainzt.splatournament_client.R;
import com.kainzt.splatournament_client.databinding.FragmentCreateTournamentBinding;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

public class CreateTournamentFragment extends Fragment {
    private FragmentCreateTournamentBinding binding;
    private MainViewModel viewModel;
    public CreateTournamentFragment() {
        // Required empty public constructor
    }
    public static CreateTournamentFragment newInstance() {
        return new CreateTournamentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateTournamentBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
                 ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireActivity(),
                 android.R.layout.simple_dropdown_item_1line, COUNTRIES);
                 binding.txtAutoComplete.setAdapter(adapter);
        return binding.getRoot();
    }
    private static final String[] COUNTRIES = new String[] {
     };

}