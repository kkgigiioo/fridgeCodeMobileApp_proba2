package com.example.fridgecodemobileapp_proba2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DetailActivity extends AppCompatActivity {

    private DatabaseReference databaseReference, deleteDbReference;
    private String productId, prodName, prodBarcode, prodExpir, prodLoc, prodBrand, prodNumOfItem, prodUnit, userReferenceTest;
    private Button deleteItem;
    private TextView nameTV, barcodeTV, expirTV, prodLocTV, prodBrandTV, prodNumOfItemTV, prodUnitTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //show product datas
        productId = getIntent().getStringExtra("idDetail");
        prodName = getIntent().getStringExtra("nameDetail");
        prodBarcode = getIntent().getStringExtra("barcodeDetail");
        prodExpir = getIntent().getStringExtra("expirationDetail");
        prodLoc = getIntent().getStringExtra("locationDetail");
        prodBrand = getIntent().getStringExtra("brandDetail");
        prodNumOfItem = getIntent().getStringExtra("numberOfItem");
        prodUnit = getIntent().getStringExtra("unitDetail");

        nameTV = findViewById(R.id.detailNameTV);
        barcodeTV = findViewById(R.id.barcodeTV);
        expirTV = findViewById(R.id.expirTV);
        prodLocTV = findViewById(R.id.locationTV);
        prodBrandTV = findViewById(R.id.detailBrandTV);
        prodNumOfItemTV = findViewById(R.id.numOfItemTV);
        prodUnitTV = findViewById(R.id.unitTV);

        nameTV.setText(prodName);
        barcodeTV.setText("Barcode:                 " + prodBarcode);
        expirTV.setText("Expiration day:            " + prodExpir);
        prodLocTV.setText("Location:                " + prodLoc);
        prodBrandTV.setText("Brand:                 " + prodBrand);
        prodNumOfItemTV.setText("Number of item:    " + prodNumOfItem);
        prodUnitTV.setText("Unit:                   " + prodUnit);


        //database reference
        userReferenceTest = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("users/"+userReferenceTest).child("product");

        //delete product
        deleteItem = findViewById(R.id.delItemDetailBtn);
        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProduct();
            }
        });
    }

    private void deleteProduct() {
        deleteDbReference = databaseReference.child(productId);

        deleteDbReference.removeValue()
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(DetailActivity.this, "Something wrong", Toast.LENGTH_LONG).show();
                }
            })
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(DetailActivity.this,"Product is deleted!", Toast.LENGTH_LONG).show();
                    finish();
                }
            });
    }
}
