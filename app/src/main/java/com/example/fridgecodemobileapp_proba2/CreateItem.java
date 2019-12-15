package com.example.fridgecodemobileapp_proba2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CreateItem extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final String TAG = "TAG LOGCAT";
    private ZXingScannerView scannerView;
    private Fragment fragment;
    private Button dontHaveBarcodeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        scannerView = findViewById(R.id.zxscanner);

        //Request permission
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        scannerView.setResultHandler(CreateItem.this);
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(CreateItem.this, "You must accept this permission", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

        dontHaveBarcodeBtn = findViewById(R.id.dontHaveBarcodeBtn);
        dontHaveBarcodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                fragment = fragmentManager.findFragmentByTag(CreateItemFragment.TAG);

                if(fragment == null) {
                    fragment = new CreateItemFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("barcode", "0");
                    fragment.setArguments(bundle);
                }
                transaction.replace(R.id.container, fragment, CreateItemFragment.TAG).commit();
                dontHaveBarcodeBtn.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        scannerView.stopCamera();
        super.onDestroy();
    }

    @Override
    public void handleResult(Result rawResult) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragment = fragmentManager.findFragmentByTag(CreateItemFragment.TAG);

        if(fragment == null) {
            fragment = new CreateItemFragment();
            Bundle bundle = new Bundle();
            bundle.putString("barcode", rawResult.getText());
            fragment.setArguments(bundle);
        }
        transaction.replace(R.id.container, fragment, CreateItemFragment.TAG).commit();
        dontHaveBarcodeBtn.setVisibility(View.INVISIBLE);
    }
}
