package com.example.fridgecodemobileapp_proba2;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateItemFragment extends Fragment {

    public static String TAG = "CREATE_ITEM_FRAGMENT";

    private EditText rowBarcode, rowName, rowBrand, rowExpir, rowNum;
    private Spinner rowLoc, rowUnit;
    private DatabaseReference databaseReference;
    private Button saveData;

    public CreateItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        String userReferenceTest = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users/"+userReferenceTest+"/product");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_item, container, false);

        rowBarcode = view.findViewById(R.id.tableBarcodeET);
        rowName = view.findViewById(R.id.tableNameET);
        rowBrand = view.findViewById(R.id.tableBrandET);
        rowExpir = view.findViewById(R.id.tableExpirationET);
        rowLoc = view.findViewById(R.id.tableLocationSpinner);
        rowNum = view.findViewById(R.id.tableNumOfItemET);
        rowUnit = view.findViewById(R.id.tableUnitSpinner);

        rowBarcode.setText(getArguments().getString("barcode"));
        if(Long.valueOf(rowBarcode.getText().toString().trim()) == 0)
        {
            rowBarcode.setEnabled(true);
        }

        String[] locArray = getResources().getStringArray(R.array.Location);
        String[] unitArray = getResources().getStringArray(R.array.Unit);
        setSpinnerAdapter(rowLoc, locArray);
        setSpinnerAdapter(rowUnit, unitArray);


        saveData = view.findViewById(R.id.saveButton);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
                getFragmentManager().beginTransaction().remove(CreateItemFragment.this).commit();
                getActivity().finish();
            }
        });

        return view;
    }


    private void addProduct() {
        long barcode = Long.valueOf(rowBarcode.getText().toString().trim());
        String brand = rowBrand.getText().toString().trim();
        String expiration = rowExpir.getText().toString().trim();
        String locations = rowLoc.getSelectedItem().toString().trim();
        String name = rowName.getText().toString().trim();
        int numberOfItem = Integer.valueOf(rowNum.getText().toString().trim());
        String unit = rowUnit.getSelectedItem().toString().trim();

        String id = databaseReference.push().getKey();
        ProductData productData = new ProductData(id, barcode, expiration, name, brand, locations, numberOfItem, unit);

        databaseReference.child(id).setValue(productData);
    }

    private void setSpinnerAdapter(Spinner spinner, String[] strings) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, strings);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(arrayAdapter);
    }
}
