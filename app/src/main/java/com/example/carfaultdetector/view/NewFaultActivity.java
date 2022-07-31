package com.example.carfaultdetector.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carfaultdetector.R;
import com.example.carfaultdetector.ViewModel.NewFaultViewModel;

public class NewFaultActivity extends AppCompatActivity {

    private Button addFaultButton;
    private EditText editTextNameofFault;
    private NewFaultViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault);
        editTextNameofFault = findViewById(R.id.newFaultName);
        addFaultButton = findViewById(R.id.newFaultButton);
        viewModel = new ViewModelProvider(this).get(NewFaultViewModel.class);

        addFaultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.addFault(editTextNameofFault.getText().toString());

            }
        });
        viewModel.mutableLiveDataNewFault.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged( Integer integer) {
                if(integer == 200){
                    Toast.makeText(getApplicationContext(),"Udalo sie dodac usterke", Toast.LENGTH_LONG).show();
                }
                else if(integer == 400){
                    Toast.makeText(getApplicationContext(),"Istnieje już taka usterka!", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(getApplicationContext(),"Nieznany błąd", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}