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
                CheckBox checkEngine = findViewById(R.id.halastreeCheckEngine);
                CheckBox sciaganie = findViewById(R.id.halastreeSciaganiePodczasJazdyWyszukiwanie);
                CheckBox wibracje = findViewById(R.id.halastreeWibracjeSilnikaWyszukiwanie);
                CheckBox halasSkrecanie = findViewById(R.id.halastreeHalasPodczasSkrecania);
                CheckBox halasObroty = findViewById(R.id.halastreeHalasNaObrotach);
                CheckBox szarpanie = findViewById(R.id.halastreeSzarpanie);
                Button halasButton = findViewById(R.id.halasTreefindFaultButton);



                halasButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Boolean[] bool = {halasSilnika.isChecked(), halasKola.isChecked(),
                                halasWydechu.isChecked(), halasHamowanie.isChecked(), bicieKierownicy.isChecked(),
                                checkEngine.isChecked(), sciaganie.isChecked(), wibracje.isChecked(),
                                halasSkrecanie.isChecked(), halasObroty.isChecked(), szarpanie.isChecked()};
                        String wynik = faultDecisionTreeViewModel.wynikHalas(bool);
                        System.out.println("wynik: " + wynik);
                        AlertDialog.Builder builder = new AlertDialog.Builder(FaultDecisionTree.this);
                        builder.setTitle("Usterka");
                        builder.setMessage(wynik);
                        builder.show();
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
            }
        });
    }
}