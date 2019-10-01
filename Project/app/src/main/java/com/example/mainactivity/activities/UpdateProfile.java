package com.example.mainactivity.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.mainactivity.R;
import com.example.mainactivity.modal.FarmarProfileCreationModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateProfile extends AppCompatActivity {

    FarmarProfileCreationModel farmarProfileCreationModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_farmar_profile_view_and_update);

        Intent unameAndPasswd = getIntent();
        final String username = unameAndPasswd.getStringExtra("username");
        final String password = unameAndPasswd.getStringExtra("password");

        EditText firstname, secoundName, email, contactNumber, usernameeeeeeeeeee, passworddddddddddddd;


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = rootRef.child("FarmerProfiles").child(username);



//        ValueEventListener eventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//
//                    String password = dataSnapshot.child("createPasswordEdit").getValue().toString();
//
//                    if (password.equals(passwordEdit.getText().toString())) {
//
//                        String username = dataSnapshot.child("createUsernameEdit").getValue().toString();
//
//
//
//
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
////                        Log.d(TAG, databaseError.getMessage()); //Don't ignore errors!
//            }
//        };
//        userNameRef.addListenerForSingleValueEvent(eventListener);


    }

}

