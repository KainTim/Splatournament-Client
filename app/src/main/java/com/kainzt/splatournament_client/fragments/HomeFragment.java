package com.kainzt.splatournament_client.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kainzt.splatournament_client.R;
import com.kainzt.splatournament_client.databinding.FragmentHomeBinding;
import com.kainzt.splatournament_client.services.TournamentService;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

public class HomeFragment extends Fragment implements View.OnClickListener, Toolbar.OnMenuItemClickListener {
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
            binding.cvMenuCreateTeam.setOnClickListener(this);
            binding.cvMenuJoinTeam.setOnClickListener(this);
            binding.tbHome.setOnMenuItemClickListener(this);

        viewModel.tournamentsState.observe(requireActivity(),integer -> {
            if (integer == TournamentService.STATE_SUCCESS){
                viewModel.showTournamentList();
            }
            if (integer ==TournamentService.STATE_INVALID){
                Toast toast = new Toast(requireContext());
                toast.setText("There was a problem accessing the Tournaments, Please Try again Later!");
                toast.show();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cvMenuTournament){
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("savedLogin", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("username", "");
            String password = sharedPreferences.getString("password", "");
            viewModel.getCurrentTournaments(username,password,requireActivity());
        }
        if (v.getId() == R.id.cvMenuCreateTeam){

        }
        if (v.getId() == R.id.cvMenuJoinTeam){

        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.mit_Profile){
            SharedPreferences saveLogin = getContext().getSharedPreferences("saveLogin", Context.MODE_PRIVATE);
            saveLogin.edit().clear().apply();
            viewModel.logout();
            viewModel.showLogin();
            return true;
        }
        return false;
    }
}