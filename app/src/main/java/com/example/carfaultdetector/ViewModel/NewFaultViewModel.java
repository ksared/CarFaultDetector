package com.example.carfaultdetector.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carfaultdetector.model.Fault;
import com.example.carfaultdetector.model.Global;
import com.example.carfaultdetector.model.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewFaultViewModel extends ViewModel {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BaseURL = Global.url;
    public MutableLiveData<Integer> mutableLiveDataNewFault = new MutableLiveData<>();

    public void addFault(String name){

        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
        retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<Void> call = retrofitInterface.addFault(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Udalo sie dodac usterke");
                if(response.code()==200){
                    mutableLiveDataNewFault.setValue(200);
                }
                else{
                    mutableLiveDataNewFault.setValue(400);
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Błąd podczas dodawania usterki");
                mutableLiveDataNewFault.setValue(400);

            }
        });
    }
}
