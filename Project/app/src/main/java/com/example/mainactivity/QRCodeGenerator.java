package com.example.mainactivity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mainactivity.activities.EnterProductDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QRCodeGenerator extends AppCompatActivity {

    TextView dbName, dbTemperature, dbHumidity, dbFertilizer, dbFarmName, dbTransportMedia, dbWeedingType;
    Button qrGeneratorButton, dataView;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_generator);

        qrGeneratorButton = findViewById(R.id.qrGeneratorButton);
        dbName = findViewById(R.id.dbName);
        dbTemperature = findViewById(R.id.dbTemperature);
        dbHumidity = findViewById(R.id.dbHumidity);
        dbFertilizer = findViewById(R.id.dbFertilizer);
        dbFarmName = findViewById(R.id.dbFarmName);
        dbTransportMedia = findViewById(R.id.dbTransportMedia);
        dbWeedingType = findViewById(R.id.dbWeedingType);

        dataView = findViewById(R.id.dataView);

        qrGeneratorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference().child("ProductDetails").child("-LnrCEzM_L6qbksuMmk1");
//                databaseReference = FirebaseDatabase.getInstance().getReference().child("ProductDetails");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String productName = dataSnapshot.child("productNameEdit").getValue().toString();
                        String temperature = dataSnapshot.child("temperatureEdit").getValue().toString();
                        String humidity = dataSnapshot.child("humidityEdit").getValue().toString();
                        String fertilizer = dataSnapshot.child("fertilizerEdit").getValue().toString();
                        String farmName = dataSnapshot.child("farmNameEdit").getValue().toString();
                        String transportMedia = dataSnapshot.child("transportMediaEdit").getValue().toString();
                        String weedingType = dataSnapshot.child("weedingTypeEdit").getValue().toString();

                        dbName.setText(productName);
                        dbTemperature.setText(temperature);
                        dbHumidity.setText(humidity);
                        dbFertilizer.setText(fertilizer);
                        dbFarmName.setText(farmName);
                        dbTransportMedia.setText(transportMedia);
                        dbWeedingType.setText(weedingType);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
