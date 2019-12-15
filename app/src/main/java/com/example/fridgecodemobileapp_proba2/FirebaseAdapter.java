package com.example.fridgecodemobileapp_proba2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FirebaseAdapter extends FirebaseRecyclerAdapter<ProductData, ProdDataViewHolder> {
    /**
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private Context context;

    public FirebaseAdapter(@NonNull FirebaseRecyclerOptions<ProductData> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ProdDataViewHolder holder, int position, @NonNull final ProductData model) {
        holder.prodNameTV.setText(model.getName());
        holder.barcodeTV.setText(String.valueOf(model.getBarcode()));
        holder.expirTV.setText(model.getExpiration());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("idDetail",model.getId());
                intent.putExtra("nameDetail", model.getName());
                intent.putExtra("barcodeDetail", String.valueOf(model.getBarcode()));
                intent.putExtra("expirationDetail", model.getExpiration());
                intent.putExtra("locationDetail", model.getLocations());
                intent.putExtra("brandDetail", model.getBrand());
                intent.putExtra("numberOfItem", String.valueOf(model.getNumberOfItem()));
                intent.putExtra("unitDetail", model.getUnit());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ProdDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProdDataViewHolder(LayoutInflater.from(context).inflate(R.layout.row, parent, false));
    }
}
