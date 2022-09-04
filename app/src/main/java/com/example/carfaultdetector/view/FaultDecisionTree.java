package com.example.carfaultdetector.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.carfaultdetector.R;
import com.example.carfaultdetector.ViewModel.FaultDecisionTreeViewModel;
import com.example.carfaultdetector.model.Global;

import java.util.HashMap;

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
                CheckBox checkEngine = findViewById(R.id.halastreeCheckEngine);
                CheckBox sciaganie = findViewById(R.id.halastreeSciaganiePodczasJazdyWyszukiwanie);
                CheckBox wibracje = findViewById(R.id.halastreeWibracjeSilnikaWyszukiwanie);
                CheckBox halasSkrecanie = findViewById(R.id.halastreeHalasPodczasSkrecania);
                CheckBox halasObroty = findViewById(R.id.halastreeHalasNaObrotach);
                CheckBox szarpanie = findViewById(R.id.halastreeSzarpanie);
                Button halasButton = findViewById(R.id.halasTreefindFaultButton);
                Button addButton = findViewById(R.id.halasTreeAddFaultButton);

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("issue1", String.valueOf(Global.boolToInt(halasSilnika.isChecked())));
                        map.put("issue2", String.valueOf(Global.boolToInt(halasKola.isChecked())));
                        map.put("issue3", String.valueOf(Global.boolToInt(halasWydechu.isChecked())));
                        map.put("issue4", String.valueOf(Global.boolToInt(halasHamowanie.isChecked())));
                        map.put("issue5", String.valueOf(Global.boolToInt(bicieKierownicy.isChecked())));
                        map.put("issue6", String.valueOf(Global.boolToInt(checkEngine.isChecked())));
                        map.put("issue7", String.valueOf(Global.boolToInt(sciaganie.isChecked())));
                        map.put("issue8", String.valueOf(Global.boolToInt(wibracje.isChecked())));
                        map.put("issue9", String.valueOf(Global.boolToInt(halasSkrecanie.isChecked())));
                        map.put("issue10", String.valueOf(Global.boolToInt(halasObroty.isChecked())));
                        map.put("issue11", String.valueOf(Global.boolToInt(szarpanie.isChecked())));

                        setContentView(R.layout.add_noise_decission_tree);
                        EditText noiseNameEditText = findViewById(R.id.noiseFaultEditText);
                        Button addNoiseButton = findViewById(R.id.addNoiseFaultButton);

                        addNoiseButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                map.put("name", noiseNameEditText.getText().toString());
                                faultDecisionTreeViewModel.addNoise(map);
                                faultDecisionTreeViewModel.mutableLiveDataAdd.observe(FaultDecisionTree.this, new Observer<Boolean>() {
                                    @Override
                                    public void onChanged(Boolean aBoolean) {
                                        if(aBoolean){
                                            Toast.makeText(getApplicationContext(),"Udalo sie dodac usterke", Toast.LENGTH_LONG).show();
                                        }

                                    }
                                });
                            }
                        });


                    }
                });

                halasButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Boolean[] bool = {halasSilnika.isChecked(), halasKola.isChecked(),
                                halasWydechu.isChecked(), halasHamowanie.isChecked(), bicieKierownicy.isChecked(),
                                checkEngine.isChecked(), sciaganie.isChecked(), wibracje.isChecked(),
                                halasSkrecanie.isChecked(), halasObroty.isChecked(), szarpanie.isChecked()};
                        faultDecisionTreeViewModel.wynikHalas(bool);
                        faultDecisionTreeViewModel.mutableLiveDataHalas.observe(FaultDecisionTree.this, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                System.out.println("wynik: " + s);
                                AlertDialog.Builder builder = new AlertDialog.Builder(FaultDecisionTree.this);
                                builder.setTitle("Usterka");
                                builder.setMessage(s);
                                builder.show();
                            }
                        });

                    }
                });
            }
        });

        odpalanieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.tree_odpalanie);

                CheckBox odpalKluczyk = findViewById(R.id.odpalTreeNieReagujeKluczyk);
                CheckBox odpalPrad = findViewById(R.id.odpalTreeBrakPradu);
                CheckBox odpalBrakReakcjiStart = findViewById(R.id.odpalTreeNieKreci);
                CheckBox odpalKreci = findViewById(R.id.odpalTreeKreci);
                CheckBox odpalKreciGasnie = findViewById(R.id.odpalTreeKreciGasnie);
                CheckBox odpalPaliwo = findViewById(R.id.odpalTreeNiskiPoziomPaliwa);
                CheckBox odpalOlej = findViewById(R.id.odpalTreeKontrolkaOleju);
                CheckBox odpalTemperatura = findViewById(R.id.odpalTreeNiskaTemperatura);
                Button odpalButton = findViewById(R.id.odpalTreefindFaultButton);

                odpalButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Boolean[] bool = {odpalKluczyk.isChecked(), odpalPrad.isChecked(),
                                odpalBrakReakcjiStart.isChecked(), odpalKreci.isChecked(),
                                odpalKreciGasnie.isChecked(), odpalPaliwo.isChecked(),
                                odpalOlej.isChecked(), odpalTemperatura.isChecked()};
                        String wynik = faultDecisionTreeViewModel.wynikOdpalanie(bool);
                        System.out.println("wynik: " + wynik );
                        AlertDialog.Builder builder = new AlertDialog.Builder(FaultDecisionTree.this);
                        builder.setTitle("Usterka");
                        builder.setMessage(wynik);
                        builder.show();
                    }
                });


            }
        });

        inneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.tree_inne);

                CheckBox bialyDym = findViewById(R.id.innetreeBialyDym);
                CheckBox czarnyDym = findViewById(R.id.innetreeCzarnyDym);
                CheckBox szarpanie = findViewById(R.id.inneTreeSzarpanie);
                CheckBox brakMocy = findViewById(R.id.inneTreeMoc);
                CheckBox paliwo = findViewById(R.id.inneTreePaliwo);
                CheckBox ladowanie = findViewById(R.id.inneTreeBrakLadowania);
                CheckBox slabeHamulce = findViewById(R.id.inneTreeHamulce);
                CheckBox sciaganieJazda = findViewById(R.id.inneTreeSciaganieJazda);
                CheckBox sciaganieHamowanie = findViewById(R.id.inneTreeSciaganieHamowanie);
                Button inneButton = findViewById(R.id.inneTreefindFaultButton);

                inneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Boolean[] booleans = {
                                bialyDym.isChecked(), czarnyDym.isChecked(),
                                szarpanie.isChecked(), brakMocy.isChecked(),
                                paliwo.isChecked(), ladowanie.isChecked(),
                                slabeHamulce.isChecked(), sciaganieJazda.isChecked(),
                                sciaganieHamowanie.isChecked()
                        };
                        String wynik = faultDecisionTreeViewModel.wynikInne(booleans);
                        System.out.println(wynik);
                        AlertDialog.Builder builder = new AlertDialog.Builder(FaultDecisionTree.this);
                        builder.setTitle("Usterka");
                        builder.setMessage(wynik);
                        builder.show();
                    }
                });
            }
        });

        kontrolkiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setContentView(R.layout.tree_kontrolki);

                ImageButton absImageButton = findViewById(R.id.absIndicator);
                ImageButton accumulatorImageButton = findViewById(R.id.accumulator);
                ImageButton oilImageButton = findViewById(R.id.oil);
                ImageButton checkEngineImageButton = findViewById(R.id.checkEngine);
                ImageButton tpmsImageButton = findViewById(R.id.tpms);

                absImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FaultDecisionTree.this);
                        builder.setTitle("Opis kontrolki");
                        builder.setMessage("Awaria układu hamulcowego");
                        builder.show();
                    }
                });
                accumulatorImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FaultDecisionTree.this);
                        builder.setTitle("Opis kontrolki");
                        builder.setMessage("Brak ładowania akumulatora");
                        builder.show();
                    }
                });
                oilImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FaultDecisionTree.this);
                        builder.setTitle("Opis kontrolki");
                        builder.setMessage("Niskie ciśnienie oleju");
                        builder.show();
                    }
                });
                checkEngineImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FaultDecisionTree.this);
                        builder.setTitle("Opis kontrolki");
                        builder.setMessage("Check engine - awaria silnika");
                        builder.show();
                    }
                });
                tpmsImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FaultDecisionTree.this);
                        builder.setTitle("Opis kontrolki");
                        builder.setMessage("Niskie ciśnienie w oponach");
                        builder.show();
                    }
                });
            }
        });
    }
}