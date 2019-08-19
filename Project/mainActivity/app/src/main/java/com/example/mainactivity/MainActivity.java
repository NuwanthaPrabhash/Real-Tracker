package com.example.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button farmarButton, customerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        farmarButton = (Button) findViewById(R.id.farmarButton);
        customerButton = (Button) findViewById(R.id.customerButton);

        farmarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent farmarButtonIntent = new Intent(MainActivity.this, LoginAndSignIn.class);
                startActivity(farmarButtonIntent);
            }
        });

        customerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent customerButtonIntent = new Intent(MainActivity.this, CustomerScanner.class);
                startActivity(customerButtonIntent);
            }
        });

    }
}
