package com.kainzt.splatournament_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kainzt.splatournament_client.R;
import com.kainzt.splatournament_client.databinding.FragmentRegisterBinding;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    FragmentRegisterBinding binding;
    MainViewModel viewModel;
    public RegisterFragment() {
        // Required empty public constructor
    }
    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding  = FragmentRegisterBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding.btnRegister.setOnClickListener(this);
        binding.btnRegisterLogin.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnRegister){
            if (!validateInput())return;
        }
        if (v.getId()==R.id.btnRegisterLogin){
            viewModel.showLogin();
        }
    }

    private boolean validateInput() {
        boolean validInput = true;
        if (binding.txtRegisterUserName.getEditText().getText().toString().isEmpty()){
            binding.txtRegisterUserName.setError("Enter a Username!");
            validInput = false;
        }else {
            binding.txtRegisterUserName.setErrorEnabled(false);
        }
        if (binding.txtRegisterPassword.getEditText().getText().toString().isEmpty()){
            binding.txtRegisterPassword.setError("Enter a Password!");
            validInput = false;
        }else {
            binding.txtRegisterPassword.setErrorEnabled(false);
        }
        if (binding.txtRegisterRepeatPassword.getEditText().getText().toString().isEmpty()||
            !binding.txtRegisterRepeatPassword.getEditText().getText().toString()
                .equals(binding.txtRegisterPassword.getEditText().getText().toString())){
            if (binding.txtRegisterRepeatPassword.getEditText().getText().toString().isEmpty()){
                binding.txtRegisterRepeatPassword.setError("Enter a Password!");
            }else if (!binding.txtRegisterPassword.getEditText().getText().toString().isEmpty()){
                binding.txtRegisterRepeatPassword.setError("Password is not the same!");
            }
            validInput = false;
        }else {
            binding.txtRegisterRepeatPassword.setErrorEnabled(false);
        }
        return validInput;
    }
}