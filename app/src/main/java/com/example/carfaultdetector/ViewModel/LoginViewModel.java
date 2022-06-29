package com.example.carfaultdetector.ViewModel;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carfaultdetector.model.RetrofitInterface;

import com.example.carfaultdetector.model.LoginResult;
import com.example.carfaultdetector.view.LoginActivity;

import java.util.HashMap;

//import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginViewModel extends ViewModel {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BaseURL = "http://10.0.2.2:3000";
    private static LoginResult result;
    //LiveData<String> liveData;
    HashMap<String, String> map;
    public MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();

    private LoginResult getLogin(HashMap<String, String> map){

        retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        // System.out.println("Wchodzę do getLogin");
        //Log.d("mapa", map.get("email") + " " + map.get("password"));
        Call<LoginResult> call = retrofitInterface.executeLogin(map);

        //System.out.println("Wykonany call");
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.code()==200){
                    Log.d("ok", "kod 200");
                    result = response.body();
                    mutableLiveData.setValue(true);
                    //Log.d("response", response.body().getName() + " " + response.body().getEmail());
                    //loginTextView.setText("Login");
                }
                else if(response.code()==404){
                    Log.d("blad", "kod 404");
                    result = null;
                    mutableLiveData.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.d("blad", "Failure");
                result = null;
                mutableLiveData.setValue(false);
            }
        });
        //Log.d("result: " , result.getEmail() + " " + result.getName());
        return result;
    }

    public void getLoginName(HashMap<String, String> map){
        LoginResult login = getLogin(map);
    }

    public String getEmail(){
        return result.getEmail();
    }
    public String getName(){
        return result.getName();
    }




}
