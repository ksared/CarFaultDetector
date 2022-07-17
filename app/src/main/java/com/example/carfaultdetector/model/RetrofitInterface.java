package com.example.carfaultdetector.model;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/login")
    Call<User> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executeSignup (@Body HashMap<String, String> map);

    @POST("/addworkshop")
    Call<Void> executeAddWorkshop (@Body HashMap<String, String> map);
}
