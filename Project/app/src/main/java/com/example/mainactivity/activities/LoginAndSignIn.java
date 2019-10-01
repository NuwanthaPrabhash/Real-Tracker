package com.example.mainactivity.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mainactivity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAndSignIn extends AppCompatActivity {

    EditText userNameEdit, passwordEdit;
    Button logIn, signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_sign_in);

        logIn = findViewById(R.id.logIn);
        signIn = findViewById(R.id.signIn);
        userNameEdit = findViewById(R.id.userNameEdit);
        passwordEdit = findViewById(R.id.passwordEdit);


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference userNameRef = rootRef.child("FarmerProfiles").child(userNameEdit.getText().toString());

                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            String password = dataSnapshot.child("createPasswordEdit").getValue().toString();

                            if (password.equals(passwordEdit.getText().toString())) {

                                String username = dataSnapshot.child("createUsernameEdit").getValue().toString();

                                Intent logInIntent = new Intent(LoginAndSignIn.this, EnterProductDetails.class);
                                logInIntent.putExtra("usernamepass", username);
                                logInIntent.putExtra("passwordpass", password);
                                startActivity(logInIntent);


                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        Log.d(TAG, databaseError.getMessage()); //Don't ignore errors!
                    }
                };
                userNameRef.addListenerForSingleValueEvent(eventListener);


            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = new Intent(LoginAndSignIn.this, FarmarProfileCreation.class);
                startActivity(signInIntent);
            }
        });
    }

}
