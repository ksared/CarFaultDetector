package com.example.carfaultdetector.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carfaultdetector.R;
import com.example.carfaultdetector.ViewModel.FaultIssueViewModel;
import com.example.carfaultdetector.adapter.FaultAdapter;
import com.example.carfaultdetector.model.Fault;

import java.util.ArrayList;
import java.util.List;

public class AddFaultIssues extends AppCompatActivity {

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;
    private FaultIssueViewModel faultIssueViewModel;
    private ListView faultListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faultissue);

        faultListView = findViewById(R.id.faultIssueListView);

        faultIssueViewModel = new ViewModelProvider(this).get(FaultIssueViewModel.class);
        faultIssueViewModel.getFaults();

        faultIssueViewModel.mutableLiveDataFaults.observe(this, new Observer<Fault[]>() {
            @Override
            public void onChanged(Fault[] faults) {
                //System.out.println("zmiana listviewIssue " + faults[0].getName());
                FaultAdapter faultAdapter = new FaultAdapter(getApplicationContext(), faults);
                faultListView.setAdapter(faultAdapter);

                faultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        createConnectDialog(faults[i]);
                    }
                });

            }
        });

        faultIssueViewModel.mutableLiveDataAddIssue.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer == 200){
                    Toast.makeText(getApplicationContext(), "Udalo się dodać objawy usterki", Toast.LENGTH_LONG).show();
                }
                else if(integer == 404){
                    Toast.makeText(getApplicationContext(), "Nie udało się znaleźć takiej usterki", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Nieznany błąd", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void createConnectDialog(Fault fault){
        alertDialogBuilder = new AlertDialog.Builder(this);
        final View connectPupup = getLayoutInflater().inflate(R.layout.fault_issue_popup, null);
        TextView faultNameTextView = connectPupup.findViewById(R.id.nameOfFaultPopup);
        Button addButton = connectPupup.findViewById(R.id.addIssuePopupButton);

        CheckBox halasSilnikaCheckbox = connectPupup.findViewById(R.id.halasSilnika);
        CheckBox halasPodczasHamowania = connectPupup.findViewById(R.id.halasPodczasHamowania);
        CheckBox halasZawieszenia = connectPupup.findViewById(R.id.halasZawieszenia);
        CheckBox bicie = connectPupup.findViewById(R.id.bicieKierownicy);
        CheckBox wibracje = connectPupup.findViewById(R.id.wibracjeSilnika);
        CheckBox czarnyDym = connectPupup.findViewById(R.id.czarnyDym);
        CheckBox sciaganiePodczasHamowania = connectPupup.findViewById(R.id.sciaganiePodczasHamowania);
        CheckBox sciaganiePodczasJazdy = connectPupup.findViewById(R.id.sciaganiePodczasJazdy);
        CheckBox glosnaPracaWydech = connectPupup.findViewById(R.id.glosnaPracaUkladuWyd);
        CheckBox szarpanie = connectPupup.findViewById(R.id.szarpanie);

        faultNameTextView.setText(fault.getName());

        alertDialogBuilder.setView(connectPupup);
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        faultIssueViewModel = new ViewModelProvider(this).get(FaultIssueViewModel.class);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tablica[] = {boolToint(halasSilnikaCheckbox.isChecked()),
                        boolToint(halasZawieszenia.isChecked()),boolToint(czarnyDym.isChecked()),
                        boolToint(szarpanie.isChecked()),boolToint(bicie.isChecked()),
                        boolToint(sciaganiePodczasHamowania.isChecked()),boolToint(sciaganiePodczasJazdy.isChecked()),
                        boolToint(wibracje.isChecked()),boolToint(halasPodczasHamowania.isChecked()),
                        boolToint(glosnaPracaWydech.isChecked())};
                faultIssueViewModel.addIssue(fault, tablica);
                alertDialog.dismiss();

            }
        });

    }

    public int boolToint(boolean bool){
        if(bool) return 1;
        else return 0;
    }
}