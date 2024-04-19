package com.kainzt.splatournament_client.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kainzt.splatournament_client.models.Tournament;
import com.kainzt.splatournament_client.services.UserService;

import java.util.ArrayList;
import java.util.List;


public class MainViewModel extends ViewModel {
    public static final int SHOW_LOGIN = 0;
    public static final int SHOW_REGISTER = 1;
    public static final int SHOW_MENU = 2;
    public static final int SHOW_TOURNAMENT_LIST = 3;
    public static final String SERVER_IP = "http://192.168.60.21:4711";
    private final MutableLiveData<Integer> _state = new MutableLiveData<>(SHOW_LOGIN);
    public LiveData<Integer> state = _state;
    private final UserService userService = new UserService(SERVER_IP);
    public LiveData<Boolean> registerDone = userService.registerDone;
    public LiveData<Boolean> verified = userService.verified;

    public void showLogin(){
        _state.postValue(SHOW_LOGIN);
    }
    public void showRegister(){
        _state.postValue(SHOW_REGISTER);
    }
    public void showMenu(){
        _state.postValue(SHOW_MENU);
    }
    public void showTournamentList(){
        _state.postValue(SHOW_TOURNAMENT_LIST);
    }

    public void verifyLogin(String username, String password, Context context){
        userService.verifyLogin(username,password,context);
    }
    public void addUser(String username, String password, Context context) {
        userService.addUser(username,password,context);
    }

    public List<Tournament> getTournaments() {
        return new ArrayList<>();
    }
}
