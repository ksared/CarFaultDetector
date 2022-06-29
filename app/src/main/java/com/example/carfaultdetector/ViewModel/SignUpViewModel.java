package com.example.carfaultdetector.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carfaultdetector.model.LoginResult;
import com.example.carfaultdetector.model.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Retrofit;

public class SignUpViewModel extends ViewModel {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BaseURL = "http://10.0.2.2:3000";
    private static LoginResult result;
    HashMap<String, String> map;
    public MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();

}
