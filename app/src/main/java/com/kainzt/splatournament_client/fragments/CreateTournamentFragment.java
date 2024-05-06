package com.kainzt.splatournament_client.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.android.material.snackbar.Snackbar;
import com.kainzt.splatournament_client.R;
import com.kainzt.splatournament_client.databinding.FragmentCreateTournamentBinding;
import com.kainzt.splatournament_client.enums.TournamentStyle;
import com.kainzt.splatournament_client.services.TournamentService;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

import java.util.Arrays;

public class CreateTournamentFragment extends Fragment implements View.OnClickListener {
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
        binding = FragmentCreateTournamentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(),
                android.R.layout.simple_dropdown_item_1line,
                Arrays.stream(TournamentStyle.values()).map(Enum::toString).toArray(String[]::new));
        binding.txtAutoComplete.setAdapter(adapter);
        binding.btnCreateTournament.setOnClickListener(this);
        viewModel.tournamentCreationState.observe(requireActivity(),
                state -> {
            if (state == TournamentService.STATE_SUCCESS){
                    Snackbar snackbar = Snackbar.make(binding.getRoot(), "Tournament created successfully", Snackbar.LENGTH_LONG);
                    snackbar.show();
            }
            if (state == TournamentService.STATE_INVALID){
                Snackbar snackbar = Snackbar.make(binding.getRoot(), "Error during Tournament Creation!",Snackbar.LENGTH_INDEFINITE);
                snackbar.show();

            }
        });
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCreateTournament) {
            validateInput();
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("savedLogin", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("username", "");
            viewModel.createTournament(
                    binding.txtTournamentName.getEditText().getText().toString(),
                    Integer.parseInt(binding.txtTournamentMaxTeams.getEditText().getText().toString()),
                    Integer.parseInt(binding.txtTournamentBestOf.getEditText().getText().toString()),
                    binding.txtAutoTournamentStyle.getEditText().getText().toString(),
                    username,
                    requireActivity()
            );
        }
    }

    private void validateInput() {
    }
}