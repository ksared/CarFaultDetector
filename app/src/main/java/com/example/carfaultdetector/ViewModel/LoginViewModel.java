package com.example.carfaultdetector.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carfaultdetector.model.Global;
import com.example.carfaultdetector.model.RetrofitInterface;

import com.example.carfaultdetector.model.User;

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
    private String BaseURL = Global.url;
    private static User result;
    //LiveData<String> liveData;
    HashMap<String, String> map;
    public MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();

    private User getLogin(HashMap<String, String> map){

        retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        // System.out.println("Wchodzę do getLogin");
        //Log.d("mapa", map.get("email") + " " + map.get("password"));
        Call<User> call = retrofitInterface.executeLogin(map);

        //System.out.println("Wykonany call");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
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
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("blad", "Failure");
                result = null;
                mutableLiveData.setValue(false);
            }
        });
        //Log.d("result: " , result.getEmail() + " " + result.getName());
        return result;
    }

    public void getLoginName(HashMap<String, String> map){
        User login = getLogin(map);
    }

    public String getEmail(){
        return result.getEmail();
    }
    public String getName(){
        return result.getName();
    }




}
