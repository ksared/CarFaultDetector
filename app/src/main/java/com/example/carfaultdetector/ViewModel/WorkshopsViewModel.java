package com.example.carfaultdetector.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carfaultdetector.model.RetrofitInterface;
import com.example.carfaultdetector.model.Workshop;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WorkshopsViewModel extends ViewModel {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BaseURL = "http://10.0.2.2:3000";
    HashMap<String, String> map;
    public MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
    private int httpCode;
    public static Workshop workshop;


    public int addWorkshop(HashMap<String, String> map){
        retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<Void> call = retrofitInterface.executeAddWorkshop(map);
        System.out.println("Dodaje warsztat");
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200){
                    httpCode = 200;
                    System.out.println("warsztat dodany");
                    mutableLiveData.setValue(200);
                }
                else if(response.code()==400){
                    System.out.println("blad");
                    httpCode = 400;
                    mutableLiveData.setValue(400);
                }
                else if(response.code()==403){
                    System.out.println("blad");
                    httpCode = 403;
                    mutableLiveData.setValue(403);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Błąd: ", t.getMessage());
                httpCode = 405;
                mutableLiveData.setValue(405);
            }
        });
        return httpCode;
    }

}
