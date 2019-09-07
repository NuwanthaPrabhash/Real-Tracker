package com.example.mainactivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FarmerProfileViewAndUpdate extends AppCompatActivity {

    TextView firstNamedb, secoundNamedb, emaildb, contactNumberdb, dbFarmName, dbTransportMedia, dbWeedingType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_profile_view_and_update);

        firstNamedb = findViewById(R.id.firstNamedb);
        secoundNamedb = findViewById(R.id.secoundNamedb);
        emaildb = findViewById(R.id.emaildb);
        contactNumberdb = findViewById(R.id.contactNumberdb);

        Intent myprof = getIntent();
        String userNameEdit = myprof.getStringExtra("user");
        String passEdit = myprof.getStringExtra("pass");

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = rootRef.child("FarmerProfiles").child(userNameEdit);

        userNameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fname = dataSnapshot.child("firstNameEdit").getValue().toString();
                String sname = dataSnapshot.child("secoundNameEdit").getValue().toString();
                String email = dataSnapshot.child("emailEdit").getValue().toString();
                String contact = dataSnapshot.child("contactNumberEdit").getValue().toString();

                firstNamedb.setText(fname);
                secoundNamedb.setText(sname);
                emaildb.setText(email);
                contactNumberdb.setText(contact);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
