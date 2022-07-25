package com.example.carfaultdetector.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.carfaultdetector.R;
import com.example.carfaultdetector.ViewModel.WorkshopsViewModel;
import com.example.carfaultdetector.adapter.WorkshopAdapter;
import com.example.carfaultdetector.model.Workshop;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

public class WorkshopsActivity extends AppCompatActivity {

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;
    private EditText nameEditText, addressEditText;
    private Button addWorkshopButton;
    private FloatingActionButton viewDialogButton;
    private ListView workshopsListView;
    private Workshop workshops;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshops);

        WorkshopsViewModel workshopsViewModel = new ViewModelProvider(this).get(WorkshopsViewModel.class);
        workshopsViewModel.getWorkshops();

        Workshop testWorkshop1 = new Workshop("pierwszy", "Pierwszy adres", 3);
        Workshop testWorkshop2 = new Workshop("drugi", "Drugi adres", 3.9);
        //Workshop[] workshops = new Workshop[2];
        //workshops[0] = testWorkshop1;
        //workshops[1] = testWorkshop2;



        addListen();

        workshopsViewModel.mutableLiveData2.observe(this, new Observer<Workshop[]>() {
            @Override
            public void onChanged(Workshop[] worksh) {
                workshopsListView = findViewById(R.id.workshopsListView);
                WorkshopAdapter workshopAdapter = new WorkshopAdapter(getApplicationContext(), worksh);
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

}