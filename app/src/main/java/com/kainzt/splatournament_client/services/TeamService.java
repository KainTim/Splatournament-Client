package com.kainzt.splatournament_client.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kainzt.splatournament_client.callbacks.MyOnTeamCreatedCallback;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import javax.security.auth.callback.Callback;

public class TeamService {
    private final String SERVER_IP = MainViewModel.SERVER_IP;
    private static TeamService instance;
    private TeamService() {
    }
    public static TeamService getInstance() {
        if (instance == null) return (instance = new TeamService());
        return instance;
    }

    public static int STATE_INITIAL = 0;
    public static int STATE_INVALID = -1;
    public static int STATE_SUCCESS = 1;

    private RequestQueue requestQueue;

    private void initQueue(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
    }
    public void createTeam(String teamName, String username, String password, Context context, MyOnTeamCreatedCallback callback) {
        initQueue(context);
        JSONObject data = new JSONObject();
        try {
            data.put("teamname",teamName);
            data.put("username",(username));
            data.put("password",password);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String url = SERVER_IP + "/api/teams/add";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,
                data,
                jsonObject -> {
                    try {
                        if (!jsonObject.getString("username").equals("null")) {
                            callback.onCalled(true);
                            return;
                        }
                    } catch (JSONException e) {
                        callback.onCalled(false);
                    }
                    callback.onCalled(false);
                },
                volleyError -> {
                    callback.onCalled(false);
                });
        requestQueue.add(request);
    }
}
