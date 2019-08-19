package com.example.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginAndSignIn extends AppCompatActivity {

    EditText userNameEdit, passwordEdit;
    Button logIn, signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_sign_in);

        logIn = (Button) findViewById(R.id.logIn);
        signIn = (Button) findViewById(R.id.signIn);
        userNameEdit = (EditText) findViewById(R.id.userNameEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logInIntent = new Intent(LoginAndSignIn.this, EnterProductDetails.class);
                startActivity(logInIntent);
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
