package com.kainzt.splatournament_client.services;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class UserService {
    private final String SERVER_IP;

    public UserService(String SERVER_IP) {
        this.SERVER_IP = SERVER_IP;
    }

    private final MutableLiveData<Boolean> _verified = new MutableLiveData<>();
    public LiveData<Boolean> verified = _verified;
    private final MutableLiveData<Boolean> _registerDone = new MutableLiveData<>();
    public LiveData<Boolean> registerDone = _registerDone;
    private RequestQueue requestQueue;


    public void verifyLogin(String username, String password, Context context){
        initQueue(context);
        String url = SERVER_IP+"/api/users/verify-login?username="+username+"&password="+password;

        StringRequest stringRequest = new StringRequest
                (Request.Method.GET, url, response -> _verified.postValue(response.equals("true")),
                        volleyError -> System.out.println(volleyError.toString()));
        requestQueue.add(stringRequest);
    }

    public void addUser(String username, String password, Context context) {
        initQueue(context);
        String url = SERVER_IP+"/api/users/add-user?username="+username+"&password="+password;
        StringRequest stringRequest = new StringRequest
                (Request.Method.POST, url, response -> _registerDone.postValue(response.equals("true")),
                        volleyError -> System.out.println(volleyError.toString()));
        requestQueue.add(stringRequest);
    }


    private void initQueue(Context context) {
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(context);
        }
    }
}
