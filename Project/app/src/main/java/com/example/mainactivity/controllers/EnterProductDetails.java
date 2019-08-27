package com.example.mainactivity.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mainactivity.R;
import com.example.mainactivity.modal.ProductDetails;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnterProductDetails extends AppCompatActivity {

    EditText productNameEdit, temperatureEdit, humidityEdit, fertilizerEdit, farmNameEdit, transportMediaEdit, weedingTypeEdit;
    Button productDetailsSaveButton;
    ProductDetails productDetails;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_product_details);

        productNameEdit = (EditText) findViewById(R.id.productNameEdit);
        temperatureEdit = (EditText) findViewById(R.id.temperatureEdit);
        humidityEdit = (EditText) findViewById(R.id.humidityEdit);
        fertilizerEdit = (EditText) findViewById(R.id.fertilizerEdit);
        farmNameEdit = (EditText) findViewById(R.id.farmNameEdit);
        transportMediaEdit = (EditText) findViewById(R.id.transportMediaEdit);
        weedingTypeEdit = (EditText) findViewById(R.id.weedingTypeEdit);
        productDetailsSaveButton = (Button) findViewById(R.id.productDetailsSaveButton);

        productDetails = new ProductDetails();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("ProductDetails");  // all these together to connect the database

        productDetailsSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = databaseReference.push().getKey();   // create a new primery key

                productDetails.setProductNameEdit(productNameEdit.getText().toString().trim());
                productDetails.setTemperatureEdit(Integer.valueOf(temperatureEdit.getText().toString().trim()));
                productDetails.setHumidityEdit(Integer.valueOf(humidityEdit.getText().toString().trim()));
                productDetails.setFertilizerEdit(fertilizerEdit.getText().toString().trim());
                productDetails.setFarmNameEdit(farmNameEdit.getText().toString().trim());
                productDetails.setTransportMediaEdit(transportMediaEdit.getText().toString().trim());
                productDetails.setWeedingTypeEdit(weedingTypeEdit.getText().toString().trim());

                databaseReference.push().setValue(productDetails);
            }
        });

    }
}
