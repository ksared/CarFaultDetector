package com.example.carfaultdetector.ViewModel;

import android.util.JsonReader;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carfaultdetector.model.Global;
import com.example.carfaultdetector.model.RetrofitInterface;
import com.example.carfaultdetector.model.Workshop;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WorkshopsViewModel extends ViewModel {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BaseURL = Global.url;
    HashMap<String, String> map;
    public MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Workshop[]> mutableLiveData2 = new MutableLiveData<>();
    public MutableLiveData<Integer> mutableLiveDataRateReturn = new MutableLiveData<>();
    public MutableLiveData<Integer> mutableLiveDataDelete = new MutableLiveData<>();
    private int httpCode;
    private Workshop[] workshops;
    //public static Workshop workshop;


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

    public void rateWorkshop(Workshop workshop, String rate){
        retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("name", workshop.getName());
        map.put("rate", rate);

        Call<Void> call = retrofitInterface.rateWorkshop(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Oceniono");
                if(response.code() == 200){
                    mutableLiveDataRateReturn.setValue(200);
                }
                else if(response.code() == 400){
                    mutableLiveDataRateReturn.setValue(400);
                }
                else{
                    mutableLiveDataRateReturn.setValue(404);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Blad");
            }
        });
    }

    public void getWorkshops(){
        retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<Object> call = retrofitInterface.executeGetAllWorkshops();
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if(response.code()==200){
                    System.out.println(response.body());
                    List<Object> list = (List<Object>) response.body();
                    System.out.println(list.get(0).toString());
                    String name;
                    String address;
                    double numberofRates;
                    double rate;
                    System.out.println("wielkosc listy: " + list.size());
                    workshops = new Workshop[list.size()];

                    String pomocnik;
                    for(int i = 0; i<list.size(); i++){
                        workshops[i] = new Workshop();
                        pomocnik = list.get(i).toString();
                        name = pomocnik.substring(pomocnik.indexOf("name") + 5, pomocnik.indexOf("address") - 2);
                        address = pomocnik.substring(pomocnik.indexOf("address") + 8, pomocnik.indexOf("number") - 2);
                        numberofRates = Double.parseDouble(pomocnik.substring(pomocnik.indexOf("Rates") + 6, pomocnik.indexOf("rate") - 2)) ;
                        rate = Double.parseDouble( pomocnik.substring(pomocnik.indexOf("rate") + 5, pomocnik.length()-1) );
                        workshops[i].setName(name);
                        workshops[i].setAddress(address);
                        if(numberofRates==0){
                            workshops[i].setRate(0);
                        }
                        else{
                            workshops[i].setRate(rate/numberofRates);
                        }
                        System.out.println("Name: " + workshops[i].getName() + " address: " + workshops[i].getAddress()
                                + " rate: " + workshops[i].getRate());
                    }
                    mutableLiveData2.setValue(workshops);
                }
                else{

                }


            }
            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                System.out.println("Nie udalo sie " + t);
            }
        });
    }

    public void deleteWorkshop(Workshop workshop){
        retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("name", workshop.getName());

        Call<Void> call = retrofitInterface.deleteWorkshop(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Usuniety");
                if(response.code() == 200){
                    mutableLiveDataDelete.setValue(200);
                }
                else if(response.code() == 400){
                    mutableLiveDataDelete.setValue(400);
                }
                else{
                    mutableLiveDataDelete.setValue(404);
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Blad");
                mutableLiveDataDelete.setValue(404);
            }
        });
    }

    public Workshop[] getTableOfWorkshops(){
        return workshops;
    }



}
