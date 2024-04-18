package com.kainzt.splatournament_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kainzt.splatournament_client.R;
import com.kainzt.splatournament_client.databinding.FragmentLoginBinding;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private FragmentLoginBinding binding;
    private MainViewModel viewModel;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding.btnLogin.setOnClickListener(this);
        binding.btnLoginRegister.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin){
            if (binding.txtUsername.getEditText().getText().toString().isEmpty()){
                binding.txtPassword.setError("Enter a Username!");
            }
            if (binding.txtPassword.getEditText().getText().toString().isEmpty()){
                binding.txtPassword.setError("Enter a Password!");
            }
            if (viewModel.verifyLogin(binding.txtUsername.getEditText().getText().toString(),
                    binding.txtPassword.getEditText().getText().toString())) {
                viewModel.showMenu();
            }
        }
        if (v.getId() == R.id.btnLoginRegister){
            viewModel.showRegister();
        }
    }
}