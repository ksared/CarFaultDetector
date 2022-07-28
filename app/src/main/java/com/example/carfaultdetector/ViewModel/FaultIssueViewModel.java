package com.example.carfaultdetector.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carfaultdetector.model.Fault;
import com.example.carfaultdetector.model.RetrofitInterface;
import com.example.carfaultdetector.model.Workshop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FaultIssueViewModel extends ViewModel {

    private Retrofit retrofit;
    private  RetrofitInterface retrofitInterface;
    private String BaseURL = "http://10.0.2.2:3000";
    private Fault[] faults;
    public MutableLiveData<Fault[]> mutableLiveDataFaults = new MutableLiveData<>();
    public MutableLiveData<Integer> mutableLiveDataAddIssue = new MutableLiveData<>();


    public void addIssue(Fault fault, int []boxes){
        retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();
        map.put("name", fault.getName());
        map.put("issue1", String.valueOf(boxes[0]));
        map.put("issue2", String.valueOf(boxes[1]));
        map.put("issue3", String.valueOf(boxes[2]));
        map.put("issue4", String.valueOf(boxes[3]));
        map.put("issue5", String.valueOf(boxes[4]));
        map.put("issue6", String.valueOf(boxes[5]));
        map.put("issue7", String.valueOf(boxes[6]));
        map.put("issue8", String.valueOf(boxes[7]));
        map.put("issue9", String.valueOf(boxes[8]));
        map.put("issue10", String.valueOf(boxes[9]));

        Call<Void> call = retrofitInterface.modifyFaultIssue(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code()==200){
                    System.out.println("Poszlo ok");
                }
                else{
                    System.out.println("Nie poszlo ok");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Nie poszlo ok");

            }
        });

    }

    public void getFaults(){
        System.out.println("Wchodze do viewmodel issue");
        retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<Object> call = retrofitInterface.executeGetAllFaluts();
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if(response.code()==200){
                    System.out.println(response.body());
                    List<Object> list = (List<Object>) response.body();
                    System.out.println(list.get(0).toString());
                    String name;
                    String symptomString;
                    System.out.println("wielkosc listy: " + list.size());
                    faults = new Fault[list.size()];

                    String pomocnik;
                    for(int i = 0; i<list.size(); i++){
                        faults[i] = new Fault();
                        pomocnik = list.get(i).toString();
                        name = pomocnik.substring(pomocnik.indexOf("name") + 5, pomocnik.indexOf("symptoms") - 2);
                        symptomString = pomocnik.substring(pomocnik.indexOf("symptoms=") + 10, pomocnik.indexOf("}") - 1);
                        List<String> symptomStringList = new ArrayList<String>(Arrays.asList(symptomString.split(",")));

                        List<Double> symptomIntegerList = new ArrayList<>();
                        for (String s : symptomStringList) {
                            symptomIntegerList.add(Double.parseDouble(s));
                        }



                        faults[i].setName(name);
                        faults[i].setIssueList(symptomIntegerList);

                        System.out.println("Name: " + faults[i].getName());
                    }
                    mutableLiveDataFaults.setValue(faults);
                }
                else{
                    System.out.println("Błąd w viewmodel issue");
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }
}
