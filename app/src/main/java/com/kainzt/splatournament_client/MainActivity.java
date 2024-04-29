package com.kainzt.splatournament_client;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.kainzt.splatournament_client.fragments.HomeFragment;
import com.kainzt.splatournament_client.fragments.LoginFragment;
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
                case 0: fragmentTransaction.replace(R.id.clMain, LoginFragment.newInstance());
                    break;
                case 1: fragmentTransaction.replace(R.id.clMain, RegisterFragment.newInstance());
                    break;
                case 2: fragmentTransaction.replace(R.id.clMain, HomeFragment.newInstance());
                    break;
                case 3: fragmentTransaction.replace(R.id.clMain, TournamentFragment.newInstance(1))
                        .addToBackStack(null);
                    break;
            }
            fragmentTransaction.commit();
        });
    }
}