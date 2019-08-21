package com.example.mainactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

//    // Show the items
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        return true;
//    }
//
//    // execute the item
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()){
//            case R.id.settings : Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.aboutUs : Toast.makeText(this, " About us Clicked", Toast.LENGTH_SHORT).show() ;
//                return true;
//            case R.id.contactUs : Toast.makeText(this, " Contact us Clicked", Toast.LENGTH_SHORT).show();
//                return true;
//
//        }
//
//        return false;
//    }

    //////////  show Alert box when click back /////////

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Really Exit ?")
                .setMessage("are you sure?")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("cancle",null).setCancelable(false); // setCancelable() use to not cancel the alert box when
        //clicked other areas

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
