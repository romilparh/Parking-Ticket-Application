package com.romilparh.shadybond.shadyparkingsystem.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.romilparh.shadybond.shadyparkingsystem.R;
import com.romilparh.shadybond.shadyparkingsystem.database.model.PaymentInfoDBModel;

import java.util.List;

/**
 * Created by shadybond on 2018-04-24.
 */

public class RVAdapterPayment extends RecyclerView.Adapter<RVAdapterPayment.ViewHolder>{
    private List<PaymentInfoDBModel> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView cardNumber, cardExpiry, cardCVV,cardType;


        public ViewHolder(View v) {
            super(v);

            this.cardNumber = v.findViewById(R.id.cardNumberTV);
            this.cardCVV = v.findViewById(R.id.cardCVVTV);
            this.cardType = v.findViewById(R.id.cardTypeTV);
            this.cardExpiry = v.findViewById(R.id.expiryDateTV);

        }
    }

    public RVAdapterPayment(List<PaymentInfoDBModel> myDataset) {
        this.mDataset = myDataset;
    }

    @Override
    public RVAdapterPayment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_payment, parent, false);
        RVAdapterPayment.ViewHolder vh = new RVAdapterPayment.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RVAdapterPayment.ViewHolder holder, int position) {
        holder.cardNumber.setText(mDataset.get(position).getCard_number());
        holder.cardCVV.setText(mDataset.get(position).getCvv());
        holder.cardExpiry.setText(mDataset.get(position).getDateExpiry());
        holder.cardType.setText(mDataset.get(position).getCard_Type());
        }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
