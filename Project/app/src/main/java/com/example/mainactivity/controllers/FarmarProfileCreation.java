package com.example.mainactivity.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mainactivity.R;

public class FarmarProfileCreation extends AppCompatActivity {

    Button saveFarmarProfileCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmar_profile_creation);

        saveFarmarProfileCreation = (Button) findViewById(R.id.saveFarmarProfileCreation);

        saveFarmarProfileCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saveFarmarProfileCreationIntent = new Intent(FarmarProfileCreation.this, EnterProductDetails.class);
                startActivity(saveFarmarProfileCreationIntent);
            }
        });
    }
}
