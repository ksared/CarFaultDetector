package com.example.carfaultdetector.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.carfaultdetector.R;
import com.example.carfaultdetector.ViewModel.FaultDecisionTreeViewModel;

public class FaultDecisionTree extends AppCompatActivity {

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;
    private Intent intent;

    Button halasButton;
    Button odpalanieButton;
    Button inneButton;
    Button kontrolkiButton;

    FaultDecisionTreeViewModel faultDecisionTreeViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision_tree);

        halasButton = findViewById(R.id.halasTreeButton);
        odpalanieButton = findViewById(R.id.odpalanieTreeButton);
        inneButton = findViewById(R.id.othersTreeButton);
        kontrolkiButton = findViewById(R.id.controlTreeButton);
        faultDecisionTreeViewModel = new ViewModelProvider(this).get(FaultDecisionTreeViewModel.class);


        halasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.tree_halas);
                CheckBox halasSilnika = findViewById(R.id.halastreeHalasSilnika);
                CheckBox halasKola = findViewById(R.id.halastreeHalasKola);
                CheckBox halasWydechu = findViewById(R.id.halastreeHalasWydechu);
                CheckBox halasHamowanie = findViewById(R.id.halastreeHalasPodczasHamowania);
                CheckBox bicieKierownicy = findViewById(R.id.halastreeBicieKierownicy);
                CheckBox checkEngine = findViewById(R.id.controlTreeCheckEngine);
                CheckBox sciaganie = findViewById(R.id.halastreeSciaganiePodczasJazdyWyszukiwanie);
                CheckBox wibracje = findViewById(R.id.halastreeWibracjeSilnikaWyszukiwanie);
                CheckBox halasSkrecanie = findViewById(R.id.halastreeHalasPodczasSkrecania);
                CheckBox halasObroty = findViewById(R.id.halastreeHalasNaObrotach);
                CheckBox szarpanie = findViewById(R.id.halastreeSzarpanie);


            }
        });

        odpalanieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.tree_odpalanie);
            }
        });

        inneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.tree_inne);
            }
        });

        kontrolkiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.tree_kontrolki);
            }
        });
    }
}