package com.example.carfaultdetector.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carfaultdetector.R;
import com.example.carfaultdetector.model.Fault;

public class FaultAdapter extends BaseAdapter {

    private Context ctx;
    private Fault[] faults;
    private LayoutInflater inflater;

    public FaultAdapter(Context ctx, Fault[] faults){
        this.ctx = ctx;
        this.faults = faults;
        inflater = LayoutInflater.from(ctx);

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
        System.out.println("Get view w faultadapter");
        if(view==null){
            System.out.println("View to null to robie listview");
            view = inflater.inflate(R.layout.fault_issue_item, null);
            TextView faultNameTextView = view.findViewById(R.id.faultItem);
            faultNameTextView.setText(faults[i].getName());
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
