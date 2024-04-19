package com.kainzt.splatournament_client.services;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

public class TournamentService {
    private final String SERVER_IP = MainViewModel.SERVER_IP;
    private final UserService userService;

    public TournamentService() {
        userService = UserService.getInstance();
    }
    private RequestQueue requestQueue;






    private void initQueue(Context context) {
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(context);
        }
    }

}
