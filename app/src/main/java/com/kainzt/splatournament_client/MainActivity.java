package com.kainzt.splatournament_client;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.kainzt.splatournament_client.fragments.CreateTournamentFragment;
import com.kainzt.splatournament_client.fragments.HomeFragment;
import com.kainzt.splatournament_client.fragments.LoginFragment;
import com.kainzt.splatournament_client.fragments.NextSetFragment;
import com.kainzt.splatournament_client.fragments.RegisterFragment;
import com.kainzt.splatournament_client.fragments.TournamentFragment;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.clMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.state.observe(this,state ->{
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            switch (state){
                case MainViewModel.SHOW_LOGIN: fragmentTransaction.replace(R.id.clMain, LoginFragment.newInstance());
                    break;
                case MainViewModel.SHOW_REGISTER: fragmentTransaction.replace(R.id.clMain, RegisterFragment.newInstance());
                    break;
                case MainViewModel.SHOW_MENU: fragmentTransaction.replace(R.id.clMain, HomeFragment.newInstance());
                    break;
                case MainViewModel.SHOW_TOURNAMENT_LIST: fragmentTransaction.replace(R.id.clMain, TournamentFragment.newInstance(1))
                        .addToBackStack(null);
                    break;
                case MainViewModel.SHOW_CREATE_TOURNAMENT: fragmentTransaction.replace(R.id.clMain, CreateTournamentFragment.newInstance())
                        .addToBackStack(null);
                case MainViewModel.SHOW_NEXT_SET: fragmentTransaction.replace(R.id.clMain, NextSetFragment.newInstance())
                        .addToBackStack(null);
                    break;
            }
            fragmentTransaction.commit();
        });
    }
}