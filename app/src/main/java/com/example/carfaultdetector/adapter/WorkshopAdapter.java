package com.example.carfaultdetector.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carfaultdetector.R;
import com.example.carfaultdetector.model.Workshop;

import org.w3c.dom.Text;

public class WorkshopAdapter extends BaseAdapter {

    private Context ctx;
    private Workshop[] workshops;
    private LayoutInflater layoutInflater;


    public WorkshopAdapter(Context ctx, Workshop[] workshops){
        this.ctx = ctx;
        this.workshops = workshops;
        layoutInflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return workshops.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.workshoplist_item, null);
        TextView nameTextView = view.findViewById(R.id.nameOfWorkshop);
        TextView addressTextView = view.findViewById(R.id.addressOfWorkshop);
        TextView rateTextView = view.findViewById(R.id.rateOfWorkshop);

        nameTextView.setText(workshops[i].getName());
        addressTextView.setText(workshops[i].getAddress());
        rateTextView.setText(String.valueOf(workshops[i].getRate()));

        return view;
    }
}
