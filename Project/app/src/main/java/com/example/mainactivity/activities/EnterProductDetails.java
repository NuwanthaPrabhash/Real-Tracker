package com.example.mainactivity.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mainactivity.FarmerProfileViewAndUpdate;
import com.example.mainactivity.R;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

public class EnterProductDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    String TAG = "GenerateQRCode";
    EditText edtValue;
    ImageView qrImage;
    Button start, save;
    String inputValue, temperature, humidity, name, fertilizer, farmName, transportMedia, weedingType;
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    EditText productNameEdit, temperatureEdit, humidityEdit, fertilizerEdit, farmNameEdit, transportMediaEdit, weedingTypeEdit;
    String val, userNameEdit, passEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_product_details);

        Intent logInIntent = getIntent();
        userNameEdit = logInIntent.getStringExtra("usernamepass");
        passEdit = logInIntent.getStringExtra("passwordpass");

        qrImage = findViewById(R.id.QR_Image);
        productNameEdit = findViewById(R.id.productNameEdit);
        temperatureEdit = findViewById(R.id.temperatureEdit);
        humidityEdit = findViewById(R.id.humidityEdit);
        fertilizerEdit = findViewById(R.id.fertilizerEdit);
        farmNameEdit = findViewById(R.id.farmNameEdit);
        transportMediaEdit = findViewById(R.id.transportMediaEdit);
        weedingTypeEdit = findViewById(R.id.weedingTypeEdit);
        start = findViewById(R.id.start);
        save = findViewById(R.id.save);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String arr[] = new String[7];
                arr[0] = productNameEdit.getText().toString().trim();
                arr[1] = temperatureEdit.getText().toString().trim();
                arr[2] = humidityEdit.getText().toString().trim();
                arr[3] = fertilizerEdit.getText().toString().trim();
                arr[4] = farmNameEdit.getText().toString().trim();
                arr[5] = transportMediaEdit.getText().toString().trim();
                arr[6] = weedingTypeEdit.getText().toString().trim();

                val = "Product Name : " + arr[0] + "\n" + "\n" + "Stored Temperature : " + arr[1] + "\n" + "\n" + "Humidity : " + arr[2] + "\n" + "\n" + "Fertilizer : " + arr[3] + "\n" + "\n" + "Farm Name : "
                        + arr[4] + "\n" + "\n" + "Transport Media : " + arr[5] + "\n" + "\n" + "Weeding Type : " + arr[6] + "\n" + "\n";


                if (val.length() > 0) {
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int smallerDimension = width < height ? width : height;
                    smallerDimension = smallerDimension * 3 / 4;

                    qrgEncoder = new QRGEncoder(val, null, QRGContents.Type.TEXT, smallerDimension);
                    try {
                        bitmap = qrgEncoder.encodeAsBitmap();
                        qrImage.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        Log.v(TAG, e.toString());
                    }
                } else {
                    edtValue.setError("Required");
                }

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean save;
                String result;
                try {

//                    save = QRGSaver.save(savePath, edtValue.getText().toString().trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);
                    //                    save = QRGSaver.save(savePath, edtValue.getText().toString().trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);
                    save = QRGSaver.save(savePath, val, bitmap, QRGContents.ImageType.IMAGE_JPEG);
                    result = save ? "Image Saved" : "Image Not Saved";
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        String itemName = (String) menuItem.getTitle();
        Toast.makeText(this, itemName, Toast.LENGTH_SHORT).show();

        closeDrawer();

        switch (menuItem.getItemId()) {
            case R.id.myProfile:
                Intent myprof = new Intent(EnterProductDetails.this, FarmerProfileViewAndUpdate.class);
                myprof.putExtra("user", userNameEdit);
                myprof.putExtra("pass", passEdit);
                startActivity(myprof);
                break;
            case R.id.aboutUs:
                Intent web = new Intent(EnterProductDetails.this, AboutUs.class);
                startActivity(web);
                break;
        }

        return true;
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        }
        super.onBackPressed();
    }

}
