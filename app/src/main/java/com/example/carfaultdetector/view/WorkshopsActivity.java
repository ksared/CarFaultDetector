package com.example.carfaultdetector.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.carfaultdetector.R;
import com.example.carfaultdetector.ViewModel.WorkshopsViewModel;
import com.example.carfaultdetector.adapter.ButtonListener;
import com.example.carfaultdetector.adapter.WorkshopAdapter;
import com.example.carfaultdetector.model.Workshop;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

public class WorkshopsActivity extends AppCompatActivity implements ButtonListener {

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;
    private EditText nameEditText, addressEditText;
    private Button addWorkshopButton;
    private FloatingActionButton viewDialogButton;
    private ListView workshopsListView;
    private Workshop workshops;

    private Button rate5;
    private Button rate4;
    private Button rate3;
    private Button rate2;
    private Button rate1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshops);
        workshopsListView = findViewById(R.id.workshopsListView);

        WorkshopsViewModel workshopsViewModel = new ViewModelProvider(this).get(WorkshopsViewModel.class);
        workshopsViewModel.getWorkshops();
        addListen();

        workshopsViewModel.mutableLiveData2.observe(this, new Observer<Workshop[]>() {
            @Override
            public void onChanged(Workshop[] worksh) {

                WorkshopAdapter workshopAdapter = new WorkshopAdapter(getApplicationContext(), worksh);
                workshopAdapter.setButtonListener(WorkshopsActivity.this);
                workshopsListView.setAdapter(workshopAdapter);


            }
        });







    }

    public void addListen(){
        viewDialogButton = findViewById(R.id.floatingAddWorkshopButton);
        viewDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewContactDialog();
            }
        });
    }

    public void createNewContactDialog(){
        alertDialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.new_workshop_popup, null);
        nameEditText = contactPopupView.findViewById(R.id.workshopNameEdit);
        addressEditText = contactPopupView.findViewById(R.id.workshopAddressEdit);

        addWorkshopButton = contactPopupView.findViewById(R.id.addWorkshopButton);

        alertDialogBuilder.setView(contactPopupView);
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        WorkshopsViewModel workshopsViewModel = new ViewModelProvider(this).get(WorkshopsViewModel.class);
        addWorkshopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();

                map.put("name", nameEditText.getText().toString());
                map.put("address", addressEditText.getText().toString());

                Log.d("Workshop details: ", nameEditText.getText().toString() + "  " + addressEditText.getText().toString());

                int result = workshopsViewModel.addWorkshop(map);
                System.out.println("Result: " + result);
                alertDialog.dismiss();
            }
        });
    }
    public void createRateContactDialog(){
        alertDialogBuilder = new AlertDialog.Builder(this);
        final View ratePopupView = getLayoutInflater().inflate(R.layout.rate_workshop_popup, null);
        rate5 = ratePopupView.findViewById(R.id.ocena5);
        rate4 = ratePopupView.findViewById(R.id.ocena4);
        rate3 = ratePopupView.findViewById(R.id.ocena3);
        rate2 = ratePopupView.findViewById(R.id.ocena2);
        rate1 = ratePopupView.findViewById(R.id.ocena1);

        alertDialogBuilder.setView(ratePopupView);
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        WorkshopsViewModel workshopsViewModel = new ViewModelProvider(this).get(WorkshopsViewModel.class);
        rate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Ocena 5");
            }
        });
        rate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    @Override
    public void onButtonClickListener(int position, Object value) {
        System.out.println("Kliknales przycisk nr " + position);
        createRateContactDialog();
    }
}