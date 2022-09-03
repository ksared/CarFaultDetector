package com.example.carfaultdetector.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.carfaultdetector.R;

public class FaultIssueCorrelationActivity extends AppCompatActivity {

    private CheckBox halasSilnika;
    private CheckBox halasZawieszenia;
    private CheckBox czarnyDym;
    private CheckBox Szarpanie;
    private CheckBox bicie;
    private CheckBox sciaganieHamowanie;
    private CheckBox sciaganieJazda;
    private CheckBox wibracje;
    private CheckBox halasHamowanie;
    private CheckBox wydech;
    Button buttonSzukaj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_issue_correlation);

        halasSilnika = findViewById(R.id.halasSilnikaWyszukiwanie);
        halasZawieszenia = findViewById(R.id.halasZawieszeniaWyszukiwanie);
        czarnyDym = findViewById(R.id.czarnyDymWyszukiwanie);
        Szarpanie = findViewById(R.id.szarpanieWyszukiwanie);
        bicie = findViewById(R.id.bicieKierownicyWyszukiwanie);
        sciaganieHamowanie = findViewById(R.id.sciaganiePodczasHamowaniaWyszukiwanie);
        sciaganieJazda = findViewById(R.id.sciaganiePodczasJazdyWyszukiwanie);
        wibracje = findViewById(R.id.wibracjeSilnikaWyszukiwanie);
        halasHamowanie = findViewById(R.id.halasPodczasHamowaniaWyszukiwanie);
        wydech = findViewById(R.id.glosnaPracaUkladuWydWyszukiwanie);
        buttonSzukaj = findViewById(R.id.findFaultButton);

        buttonSzukaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });




    }

    private void search(){
        int[] issues = {boolToInt(halasSilnika.isChecked()), boolToInt(halasZawieszenia.isChecked()),
                boolToInt(czarnyDym.isChecked()), boolToInt(Szarpanie.isChecked()), boolToInt(bicie.isChecked()),
                boolToInt(sciaganieHamowanie.isChecked()), boolToInt(sciaganieJazda.isChecked()), boolToInt(wibracje.isChecked()),
                boolToInt(halasHamowanie.isChecked()), boolToInt(wydech.isChecked())};
        Intent intent = new Intent(FaultIssueCorrelationActivity.this, PresentedFaults.class);
        intent.putExtra("IssueTable", issues);
        startActivity(intent);
    }

    private int boolToInt(Boolean bool){
        if(bool){
            return 1;
        }
        return 0;
    }
}