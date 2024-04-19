package com.kainzt.splatournament_client.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kainzt.splatournament_client.dtos.UserDTO;

import org.json.JSONException;
import org.json.JSONObject;


public class MainViewModel extends ViewModel {
    public static final int SHOW_LOGIN = 0;
    public static final int SHOW_REGISTER = 1;
    public static final int SHOW_MENU = 2;
    public static final String SERVER_IP = "http://192.168.60.21:4711";
    private final MutableLiveData<Integer> _state = new MutableLiveData<>(SHOW_LOGIN);
    public LiveData<Integer> state = _state;
    private final MutableLiveData<Boolean> _verified = new MutableLiveData<>();
    public LiveData<Boolean> verified = _verified;
    private final MutableLiveData<Boolean> _registerDone = new MutableLiveData<>();
    public LiveData<Boolean> registerDone = _registerDone;

    public void showLogin(){
        _state.postValue(SHOW_LOGIN);
    }
    public void showRegister(){
        _state.postValue(SHOW_REGISTER);
    }
    public void showMenu(){
        _state.postValue(SHOW_MENU);
    }
    private RequestQueue requestQueue;

    public void verifyLogin(String username, String password, Context context){
        initQueue(context);
        String url = SERVER_IP+"/api/users/verify-login?username="+username+"&password="+password;

        StringRequest stringRequest = new StringRequest
                (Request.Method.GET, url, response -> {
                    _verified.postValue(response.equals("true"));
                }, volleyError -> {
                    System.out.println(volleyError.toString());
                });
        requestQueue.add(stringRequest);
    }
    public void addUser(String username, String password, Context context) {
        initQueue(context);
        String url = SERVER_IP+"/api/users/add-user?username="+username+"&password="+password;
        StringRequest stringRequest = new StringRequest
                (Request.Method.POST, url, response -> {
                    _registerDone.postValue(response.equals("true"));
                }, volleyError -> {
                    System.out.println(volleyError.toString());
                });
        requestQueue.add(stringRequest);
    }

    private void initQueue(Context context) {
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(context);
        }
    }

}
