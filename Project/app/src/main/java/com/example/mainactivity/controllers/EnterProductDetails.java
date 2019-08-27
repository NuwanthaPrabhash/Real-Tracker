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

    EditText productNameEdit;
    Button productDetailsSaveButton;
    ProductDetails productDetails;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_product_details);

        productNameEdit = (EditText) findViewById(R.id.productNameEdit);
        productDetailsSaveButton = (Button) findViewById(R.id.productDetailsSaveButton);

        productDetails = new ProductDetails();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("ProductDetails");  // all these together to connect the database

        productDetailsSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = databaseReference.push().getKey();   // create a new primery key
                productDetails.setProductNameEdit(productNameEdit.getText().toString().trim());
                databaseReference.push().setValue(productDetails);
            }
        });

    }
}
