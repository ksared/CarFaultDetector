package com.example.carfaultdetector.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/login")
    Call<User> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executeSignup (@Body HashMap<String, String> map);

    @POST("/addworkshop")
    Call<Void> executeAddWorkshop (@Body HashMap<String, String> map);

    @GET("/getAllWorkshops")
    Call<Object> executeGetAllWorkshops();

    @POST("/rateWorkshop")
    Call<Void> rateWorkshop(@Body HashMap<String, String> map);

    @POST("/deleteWorkshop")
    Call<Void> deleteWorkshop(@Body HashMap<String, String> map);

    @POST("/addFault")
    Call<Void> addFault(@Body HashMap<String, String> map);

    @GET("/getAllFaults")
    Call<Object> executeGetAllFaluts();

    @POST("/modifyFaultIssue")
    Call<Void> modifyFaultIssue(@Body HashMap<String, String> map);

    @POST("/addNoise")
    Call<Void> addNoise(@Body HashMap<String, String> map);

    @GET("/getNoise")
    Call<Object> getNoise(@Body HashMap<String, String> map);
}
