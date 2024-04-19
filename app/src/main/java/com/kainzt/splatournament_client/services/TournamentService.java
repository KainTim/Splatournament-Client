package com.kainzt.splatournament_client.services;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kainzt.splatournament_client.models.Tournament;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

import java.util.List;

public class TournamentService {
    private final String SERVER_IP = MainViewModel.SERVER_IP;
    private final UserService userService;
    private static TournamentService instance;

    private TournamentService() {
        userService = UserService.getInstance();
    }
    public static TournamentService getInstance(){
        if (instance==null) return (instance = new TournamentService());
        return instance;
    }
    private RequestQueue requestQueue;
    private final MutableLiveData<List<Tournament>> _tournaments = new MutableLiveData<>();
    public LiveData<List<Tournament>> tournaments = _tournaments;

    public void getCurrentTournaments(String username,String password,Context context){

        initQueue(context);
        String url = SERVER_IP+"/api/tournaments?username="+username+"&password="+password;

        /*
        JsonArrayRequest stringRequest = new JsonArrayRequest
                (Request.Method.GET, url,
                        response -> tournaments.postValue(),
                        volleyError -> System.out.println(volleyError.toString())
                );


        requestQueue.add(stringRequest);

        */
    }




    private void initQueue(Context context) {
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(context);
        }
    }

}
