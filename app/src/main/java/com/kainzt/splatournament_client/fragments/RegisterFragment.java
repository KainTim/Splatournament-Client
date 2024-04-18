package com.kainzt.splatournament_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kainzt.splatournament_client.R;
import com.kainzt.splatournament_client.databinding.FragmentLoginFragmentBinding;
import com.kainzt.splatournament_client.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {
    FragmentRegisterBinding binding;
    public RegisterFragment() {
        // Required empty public constructor
    }
    public static RegisterFragment newInstance(String param1, String param2) {
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

        return binding.getRoot();
    }
}