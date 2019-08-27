package com.example.mainactivity.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mainactivity.R;
import com.example.mainactivity.modal.FarmarProfileCreationModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FarmarProfileCreation extends AppCompatActivity {

    EditText firstNameEdit, secoundNameEdit, emailEdit, contactNumberEdit;
    Button saveFarmarProfileCreation;
    FarmarProfileCreationModel farmarProfileCreationModel;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmar_profile_creation);

        firstNameEdit = (EditText) findViewById(R.id.firstNameEdit);
        secoundNameEdit = (EditText) findViewById(R.id.secoundNameEdit);
        emailEdit = (EditText) findViewById(R.id.emailEdit);
        contactNumberEdit = (EditText) findViewById(R.id.contactNumberEdit);
        saveFarmarProfileCreation = (Button) findViewById(R.id.saveFarmarProfileCreation);

        farmarProfileCreationModel = new FarmarProfileCreationModel();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("FarmerProfiles");  // all these together to connect the database

        saveFarmarProfileCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                farmarProfileCreationModel.setFirstNameEdit(firstNameEdit.getText().toString().trim());
                farmarProfileCreationModel.setSecoundNameEdit(secoundNameEdit.getText().toString().trim());
                farmarProfileCreationModel.setEmailEdit(emailEdit.getText().toString().trim());
                farmarProfileCreationModel.setContactNumberEdit(Integer.valueOf(contactNumberEdit.getText().toString().trim()));

                databaseReference.push().setValue(farmarProfileCreationModel);

                Intent saveFarmarProfileCreationIntent = new Intent(FarmarProfileCreation.this, EnterProductDetails.class);
                startActivity(saveFarmarProfileCreationIntent);


            }
        });
    }
}
