package com.kainzt.splatournament_client.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kainzt.splatournament_client.R;
import com.kainzt.splatournament_client.databinding.FragmentLoginBinding;
import com.kainzt.splatournament_client.services.UserService;
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
        viewModel.verified.removeObservers(requireActivity());

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("savedLogin", Context.MODE_PRIVATE);

        viewModel.verified.observe(requireActivity(), verified -> {
            if (verified == UserService.STATE_VERIFIED) {
                viewModel.username = binding.txtUsername.getEditText().getText().toString();
                sharedPreferences.edit().putString("username", binding.txtUsername.getEditText().getText().toString()).apply();
                sharedPreferences.edit().putString("password", binding.txtPassword.getEditText().getText().toString()).apply();
                viewModel.showMenu();
                viewModel.verified.removeObservers(requireActivity());
            } else if (verified == UserService.STATE_INVALID) {
                binding.txtPassword.getEditText().setText("");
                displayInvalidPassword();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            if (!validateInput()) return;
            Log.d("Clicklistener", "got called");
            viewModel.verifyLogin(binding.txtUsername.getEditText().getText().toString(),
                    binding.txtPassword.getEditText().getText().toString(), getContext());
        } else if (v.getId() == R.id.btnLoginRegister) {
            viewModel.showRegister();
        }
    }

    private void displayInvalidPassword() {
        binding.tvInvalidPassorUsrName.setVisibility(View.VISIBLE);
        new CountDownTimer(5000, 5000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                binding.tvInvalidPassorUsrName.setVisibility(View.GONE);
            }
        }.start();
    }

    private boolean validateInput() {
        boolean validInput = true;
        if (binding.txtUsername.getEditText().getText().toString().isEmpty()) {
            binding.txtUsername.setError("Enter a Username!");
            validInput = false;
        } else {
            binding.txtUsername.setErrorEnabled(false);
        }
        if (binding.txtPassword.getEditText().getText().toString().isEmpty()) {
            binding.txtPassword.setError("Enter a Password!");
            validInput = false;
        } else {
            binding.txtPassword.setErrorEnabled(false);
        }
        return validInput;
    }
}