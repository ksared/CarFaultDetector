package com.example.carfaultdetector.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.carfaultdetector.R;

public class FaultDecisionTree extends AppCompatActivity {

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;

    Button halasButton;
    Button odpalanieButton;
    Button inneButton;
    Button kontrolkiButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision_tree);

        halasButton = findViewById(R.id.halasTreeButton);
        odpalanieButton = findViewById(R.id.odpalanieTreeButton);
        inneButton = findViewById(R.id.othersTreeButton);
        kontrolkiButton = findViewById(R.id.controlTreeButton);
    }
}