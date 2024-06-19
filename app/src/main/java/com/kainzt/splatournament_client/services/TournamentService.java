package com.kainzt.splatournament_client.services;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.kainzt.splatournament_client.dtos.TournamentDTO;
import com.kainzt.splatournament_client.enums.TournamentStyle;
import com.kainzt.splatournament_client.models.Tournament;
import com.kainzt.splatournament_client.viewmodels.MainViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TournamentService {
    private final String SERVER_IP = MainViewModel.SERVER_IP;
    private final UserService userService;
    private static TournamentService instance;

    private TournamentService() {
        userService = UserService.getInstance();
    }

    public static TournamentService getInstance() {
        if (instance == null) return (instance = new TournamentService());
        return instance;
    }

    private RequestQueue requestQueue;
    public static int STATE_INITIAL = 0;
    public static int STATE_INVALID = -1;
    public static int STATE_SUCCESS = 1;

    private final MutableLiveData<Integer> _tournamentsState = new MutableLiveData<>(STATE_INITIAL);
    public final LiveData<Integer> tournamentsState = _tournamentsState;
    public List<Tournament> tournaments = new ArrayList<>();

    private final MutableLiveData<Integer> _tournamentCreationState = new MutableLiveData<>(STATE_INITIAL);
    public final LiveData<Integer> tournamentCreationState = _tournamentCreationState;

    private final MutableLiveData<Integer> _joiningTournamentState = new MutableLiveData<>(-2);
    public final LiveData<Integer> joiningTournamentState = _joiningTournamentState;
    public void getCurrentTournaments(String username, String password, Context context) {

        initQueue(context);
        String url = SERVER_IP + "/api/tournaments?username=" + username + "&password=" + password;

        JsonArrayRequest request = new JsonArrayRequest(url, jsonArray -> {
            tournaments.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    Log.d("Tournaments", jsonArray.getJSONObject(i).toString());
                    JSONObject object = jsonArray.getJSONObject(i);
                    int id = object.getInt("id");
                    String name = object.getString("name");
                    String createdBy = object.getString("createdBy");
                    Long bestOf = object.getLong("bestOf");
                    TournamentStyle style = TournamentStyle.INVALID;
                    if (!object.getString("style").equals("null")) {
                        style = TournamentStyle.valueOf(object.getString("style"));
                    }
                    int teamCount = object.getInt("teamCount");
                    tournaments.add(new Tournament(id, name, style, teamCount, createdBy,bestOf));
                } catch (JSONException e) {
                    Log.e("Tournaments", "Parsing error" + jsonArray);
                    _tournamentsState.setValue(STATE_INVALID);
                    _tournamentsState.setValue(STATE_INITIAL);
                }
            }
            _tournamentsState.setValue(STATE_SUCCESS);
            _tournamentsState.setValue(STATE_INITIAL);
        }, volleyError -> {
        });


        requestQueue.add(request);

    }

    public void createTournament(String tournamentName, int maxTeams, int bestOf, String tournamentStyle, String username, Context context) {
        initQueue(context);
        JSONObject data = new JSONObject();
        try {
            data.put("name",tournamentName);
            data.put("maxTeams",maxTeams);
            data.put("bestOf",bestOf);
            data.put("style",tournamentStyle);
            data.put("createdBy",username);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String url = SERVER_IP + "/api/tournaments/add";
        
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,
                data,
                jsonObject -> {
                    if (jsonObject == null) {
                        _tournamentCreationState.setValue(STATE_INVALID);
                        _tournamentCreationState.setValue(STATE_INITIAL);
                        return;
                    }
                    _tournamentCreationState.setValue(STATE_SUCCESS);
                    _tournamentCreationState.setValue(STATE_INITIAL);
                },
                volleyError -> {
                });
        requestQueue.add(request);
    }


    private void initQueue(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
    }

    public void enterTournament(int tournamentId, Long currentTeamId, Context context) {
        initQueue(context);
        JSONObject data = new JSONObject();
        try {
            data.put("tournamentId",tournamentId);
            data.put("teamId",currentTeamId);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String url = SERVER_IP + "/api/teams/joinTournament";
        Log.d("enter",data.toString());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,
                data,
                jsonObject -> {
                    if (jsonObject == null) {
                        _joiningTournamentState.setValue(STATE_INVALID);
                        return;
                    }
                    Log.d("enter",jsonObject.toString());
                    try {
                        _joiningTournamentState.setValue(jsonObject.getInt("id"));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    _joiningTournamentState.setValue(-2);
                },
                volleyError -> {
                });
        requestQueue.add(request);
    }
}
