package com.romilparh.shadybond.shadyparkingsystem.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.romilparh.shadybond.shadyparkingsystem.R;
import com.romilparh.shadybond.shadyparkingsystem.database.DBHelper;
import com.romilparh.shadybond.shadyparkingsystem.database.model.ParkingTicketDBModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by shadybond on 2018-04-20.
 */

// Adapter for Home Screen for Ticket List

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ParkingTicketDBModel> mDataset;
    DateFormat df;
    Date today;
    String reportDate;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView price,
                carMake,
                carColor,
                ticketTime,
                parkingLane,
                parkingNumber,
                carNumber,
                cardNumber,
                dateNow;


        public ViewHolder(View v) {
            super(v);


            this.price = v.findViewById(R.id.priceTV);
            this.carMake = v.findViewById(R.id.carMakeTV);
            this.carColor = v.findViewById(R.id.carColorTV);
            this.ticketTime = v.findViewById(R.id.timeTicketTV);
            this.parkingLane = v.findViewById(R.id.parkingLaneTV);
            this.parkingNumber = v.findViewById(R.id.parkingNumberTV);
            this.carNumber = v.findViewById(R.id.carNumberTV);
            this.cardNumber = v.findViewById(R.id.cardNumberTV);
            this.dateNow = v.findViewById(R.id.dateView);

        }
    }

    public MyAdapter(List<ParkingTicketDBModel> myDataset) {
        this.mDataset = myDataset;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

        this.df = new SimpleDateFormat("MM/dd/yyyy");
        this.today = Calendar.getInstance().getTime();
        reportDate = df.format(today);

        holder.ticketTime.setText(mDataset.get(position).getTime_ticket());
        holder.carNumber.setText(mDataset.get(position).getCar_number());
        holder.carColor.setText(mDataset.get(position).getCar_color());
        holder.carMake.setText(String.valueOf(mDataset.get(position).getCar_make()));
        holder.parkingLane.setText(mDataset.get(position).getParking_lane());
        holder.parkingNumber.setText(String.valueOf(mDataset.get(position).getParking_number()));
        holder.price.setText(String.valueOf(mDataset.get(position).getPrice()));
        holder.cardNumber.setText(mDataset.get(position).getCard_number());
        holder.dateNow.setText(reportDate);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

