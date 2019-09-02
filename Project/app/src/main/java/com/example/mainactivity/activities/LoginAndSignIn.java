package com.example.mainactivity.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mainactivity.R;

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

                String username = "nuwantha";      // set user name and password from the database here
                String password = "123";
                if (username.equals(userNameEdit.getText().toString()) && password.equals(passwordEdit.getText().toString())) {
                    Intent logInIntent = new Intent(LoginAndSignIn.this, EnterProductDetails.class);
                    startActivity(logInIntent);
                }

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
