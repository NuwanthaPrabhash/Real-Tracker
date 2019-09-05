package com.example.mainactivity.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mainactivity.R;
import com.prihanofficial.rabbit.logics.Rabbit;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button farmarButton, customerButton, changeLanguage;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocal();
        setContentView(R.layout.activity_main);

        imageview = findViewById(R.id.imageview);
        farmarButton = findViewById(R.id.farmarButton);
        customerButton = findViewById(R.id.customerButton);
        changeLanguage =  findViewById(R.id.changeLanguage);

        /// for bluer image

        Bitmap bitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.scan);
        Rabbit.setContext(this)
                .setBitMap(bitmap1)
                .setScale(1.1f)
                .setRaius(24.0f)
                .setConstarst(0.0f)
                .setBrightnes(1.0f)
                .build();

        Rabbit.getModifiedRabbitBitmap();
        imageview.setImageBitmap(Rabbit.getModifiedRabbitBitmap());
        ///


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
                Intent scan = new Intent(MainActivity.this, QrCodeScannerActivity.class);
                startActivity(scan);
            }
        });

        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }
        });



    }

    private void showChangeLanguageDialog() {

        final String[] languageList = {"English", "Irish", "සිංහල", "தமிழ்"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Select Language");
        mBuilder.setSingleChoiceItems(languageList, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {
                    setLocal("en"); // English
                    recreate();
                } else if (which == 1) {
                    setLocal("ga");  //Irish
                    recreate();
                } else if (which == 2) {
                    setLocal("si");  // Sinhala
                    recreate();
                } else if (which == 3) {
                    setLocal("ta");  // Tamil
                    recreate();
                }

                dialog.dismiss();  // dismiss dialog box after clicked
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show(); // show dialog box
    }

    private void setLocal(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();    // save data to shared preferences
        editor.putString("my_language", language);
        editor.apply();
    }


    //load language saved in shared preferences
    public void loadLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String language2 = sharedPreferences.getString("my_language", "");
        setLocal(language2);
    }




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
                .setNegativeButton("cancle", null).setCancelable(false); // setCancelable() use to not cancel the alert box when
        //clicked other areas

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
