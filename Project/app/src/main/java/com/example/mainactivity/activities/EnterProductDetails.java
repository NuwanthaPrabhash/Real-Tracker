package com.example.mainactivity.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mainactivity.QRCodeGenerator;
import com.example.mainactivity.R;
import com.example.mainactivity.modal.ProductDetails;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnterProductDetails extends AppCompatActivity {

    EditText productNameEdit, temperatureEdit, humidityEdit, fertilizerEdit, farmNameEdit, transportMediaEdit, weedingTypeEdit;
    Button productDetailsSaveButton, generate;
    ProductDetails productDetails;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_product_details);

        productNameEdit = findViewById(R.id.productNameEdit);
        temperatureEdit = findViewById(R.id.temperatureEdit);
        humidityEdit = findViewById(R.id.humidityEdit);
        fertilizerEdit = findViewById(R.id.fertilizerEdit);
        farmNameEdit = findViewById(R.id.farmNameEdit);
        transportMediaEdit = findViewById(R.id.transportMediaEdit);
        weedingTypeEdit = findViewById(R.id.weedingTypeEdit);
        productDetailsSaveButton = findViewById(R.id.productDetailsSaveButton);
        generate = findViewById(R.id.generate);

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

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EnterProductDetails.this, QRCodeGenerator.class);
                startActivity(intent);

            }
        });


    }
}
