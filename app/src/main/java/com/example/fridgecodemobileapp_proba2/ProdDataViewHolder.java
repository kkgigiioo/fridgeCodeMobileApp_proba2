package com.example.fridgecodemobileapp_proba2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProdDataViewHolder extends RecyclerView.ViewHolder {

    public TextView prodNameTV, barcodeTV, expirTV;

    public ProdDataViewHolder(@NonNull View itemView) {
        super(itemView);

        prodNameTV = (TextView) itemView.findViewById(R.id.prodNameTV);
        barcodeTV = (TextView) itemView.findViewById(R.id.barcodeTV);
        expirTV = (TextView) itemView.findViewById(R.id.expirationTV);

    }
}
