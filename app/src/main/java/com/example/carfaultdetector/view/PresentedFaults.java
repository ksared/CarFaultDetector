package com.example.carfaultdetector.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ListView;

import com.example.carfaultdetector.R;
import com.example.carfaultdetector.ViewModel.FaultIssueViewModel;
import com.example.carfaultdetector.adapter.FindAdapter;
import com.example.carfaultdetector.model.Fault;

public class PresentedFaults extends AppCompatActivity {

    private int[] issueTable;
    FaultIssueViewModel faultIssueViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_issue);
        Bundle extras = getIntent().getExtras();
        issueTable = extras.getIntArray("IssueTable");

        ListView listView = findViewById(R.id.findIssueListView);


        faultIssueViewModel = new ViewModelProvider(this).get(FaultIssueViewModel.class);
        faultIssueViewModel.getFaults();
        faultIssueViewModel.mutableLiveDataFaults.observe(this, new Observer<Fault[]>() {
            @Override
            public void onChanged(Fault[] faults) {
                FindAdapter findAdapter = new FindAdapter(getApplicationContext(), faults, issueTable);
                listView.setAdapter(findAdapter);
            }
        });
    }
}