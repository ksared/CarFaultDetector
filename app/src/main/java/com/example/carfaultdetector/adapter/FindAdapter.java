package com.example.carfaultdetector.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carfaultdetector.R;
import com.example.carfaultdetector.model.Fault;

import java.util.List;

public class FindAdapter extends BaseAdapter {
    private Context ctx;
    private Fault[] faults;
    private LayoutInflater inflater;
    private int[] maxIssueValues;
    private int[] checked;

    public FindAdapter(Context ctx, Fault[] faults, int[] check){
        this.ctx = ctx;
        this.faults = faults;
        this.maxIssueValues = new int[10];
        int[] array = {0,0,0,0,0,0,0,0,0,0};
        this.maxIssueValues = array;
        this.checked = check;
        inflater = LayoutInflater.from(ctx);
        for(int i = 0; i<faults.length; i++){
            List<Double> issueList = faults[i].getIssueList();
            maxIssueValues[0] += issueList.get(0);
            maxIssueValues[1] += issueList.get(1);
            maxIssueValues[2] += issueList.get(2);
            maxIssueValues[3] += issueList.get(3);
            maxIssueValues[4] += issueList.get(4);
            maxIssueValues[5] += issueList.get(5);
            maxIssueValues[6] += issueList.get(6);
            maxIssueValues[7] += issueList.get(7);
            maxIssueValues[8] += issueList.get(8);
            maxIssueValues[9] += issueList.get(9);
        }

    }

    @Override
    public int getCount() {
        return faults.length;
    }

    @Override
    public Object getItem(int i) {
        return faults[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        System.out.println("Get view w findAdapter");
        if(view==null){
            System.out.println("View to null to robie listview");
            view = inflater.inflate(R.layout.find_issue_item, null);
            TextView faultNameTextView = view.findViewById(R.id.findIssueItem_NazwaUsterki);
            TextView faultProbabilityTextView = view.findViewById(R.id.findIssueItem_Prawdopodobieństwo);
            faultNameTextView.setText(faults[i].getName());

            //liczenie prawdopodobienstwa
            double probability = 0;
            for(int j=0; j<10; j++){
                if(checked[j] == 1){
                    double divideResult = faults[i].getIssueList().get(j) / maxIssueValues[j];
                    probability += divideResult;
                }
            }

            //wypisanie
            faultProbabilityTextView.setText(String.valueOf(probability));

        }
        else{
            System.out.println("View to NIE null Nie robie listview");
            // view = inflater.inflate(R.layout.fault_issue_item, null);
            //TextView faultNameTextView = view.findViewById(R.id.faultItem);
            //faultNameTextView.setText(faults[i].getName());
        }
        return view;
    }
}
