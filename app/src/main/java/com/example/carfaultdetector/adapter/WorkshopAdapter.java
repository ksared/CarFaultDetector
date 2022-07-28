package com.example.carfaultdetector.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import com.example.carfaultdetector.R;
import com.example.carfaultdetector.ViewModel.WorkshopsViewModel;
import com.example.carfaultdetector.model.Workshop;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class WorkshopAdapter extends BaseAdapter {

    private Context ctx;
    private Workshop[] workshops;
    private LayoutInflater layoutInflater;

    ButtonListener ratebuttonListener;
    DeleteButtonListener deleteButtonListener;

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;
    private Button rate5;
    private Button rate4;
    private Button rate3;
    private Button rate2;
    private Button rate1;



    public WorkshopAdapter(Context ctx, Workshop[] workshops){
        this.ctx = ctx;
        this.workshops = workshops;
        layoutInflater = LayoutInflater.from(ctx);
    }

    public void setRateButtonListener(ButtonListener listener){
        this.ratebuttonListener = listener;
    }
    public void setDeleteButtonListener(DeleteButtonListener listener){
        this.deleteButtonListener = listener;
    }
    @Override
    public int getCount() {
        return workshops.length;
    }

    @Override
    public Object getItem(int i) {
        return workshops[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        System.out.println("Get view w workshopadapter");
        ViewHolder viewHolder;
        if(view==null){
            view = layoutInflater.inflate(R.layout.workshoplist_item, null);
            viewHolder = new ViewHolder();
            viewHolder.addressTextView = view.findViewById(R.id.addressOfWorkshop);
            viewHolder.nameTextView = view.findViewById(R.id.nameOfWorkshop);
            viewHolder.rateTextView = view.findViewById(R.id.rateOfWorkshop);
            viewHolder.buttonRate = view.findViewById(R.id.buttonRate);


            TextView nameTextView = view.findViewById(R.id.nameOfWorkshop);
            TextView addressTextView = view.findViewById(R.id.addressOfWorkshop);
            TextView rateTextView = view.findViewById(R.id.rateOfWorkshop);
            Button buttonRate = view.findViewById(R.id.buttonRate);
            Button buttonDelete = view.findViewById(R.id.buttonDelete);
            nameTextView.setText(workshops[i].getName());
            addressTextView.setText(workshops[i].getAddress());

            NumberFormat formatter = new DecimalFormat("#0.0");
            rateTextView.setText(String.valueOf(formatter.format(workshops[i].getRate())));

        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        final Object temp = getItem(i);
        Button buttonRate = view.findViewById(R.id.buttonRate);
        buttonRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ratebuttonListener != null){
                    ratebuttonListener.onButtonClickListener(i, temp);
                }

            }
        });

        Button buttonDelete = view.findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Nacisnalem przycisk usuwania");
                if(deleteButtonListener != null){
                    System.out.println("Usuwanie");
                    deleteButtonListener.onButtonDeleteClickListener(i, temp);
                }
            }
        });
        return view;
    }

    private static class ViewHolder{
        Button buttonRate;
        TextView nameTextView;
        TextView addressTextView;
        TextView rateTextView;
    }

    public void createRateContactDialog(){
        alertDialogBuilder = new AlertDialog.Builder(ctx);
        final View ratePopupView = layoutInflater.inflate(R.layout.rate_workshop_popup, null);
        rate5 = ratePopupView.findViewById(R.id.ocena5);
        rate4 = ratePopupView.findViewById(R.id.ocena4);
        rate3 = ratePopupView.findViewById(R.id.ocena3);
        rate2 = ratePopupView.findViewById(R.id.ocena2);
        rate1 = ratePopupView.findViewById(R.id.ocena1);

        alertDialogBuilder.setView(ratePopupView);
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        rate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Ocena 5");
            }
        });
        rate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }


}
