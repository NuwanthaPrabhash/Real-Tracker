package com.example.mainactivity.activities;


import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mainactivity.R;
import com.example.mainactivity.modal.FarmarProfileCreationModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.prihanofficial.rabbit.logics.Rabbit;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class FarmarProfileCreation extends AppCompatActivity {

    EditText firstNameEdit, secoundNameEdit, emailEdit, contactNumberEdit, createUsernameEdit, createPasswordEdit;
    Button saveFarmarProfileCreation, profilePicSelelctButton, profilePicUploadButton;
    FarmarProfileCreationModel farmarProfileCreationModel;
    DatabaseReference databaseReference;
    ImageView imageView, profilePicUploadImageView;
    StorageReference storageReference;
    Uri imgUri;
    private StorageTask uploadTask;

    String firstName, secondName, email, username;
    Integer contactNumber, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmar_profile_creation);

        imageView = findViewById(R.id.imageViewProfileCreation);
        profilePicSelelctButton = findViewById(R.id.profilePicSelelctButton);
        profilePicUploadButton = findViewById(R.id.profilePicUploadButton);
        profilePicUploadImageView = findViewById(R.id.profilePicUploadImageView);
        firstNameEdit = findViewById(R.id.firstNameEdit);
        secoundNameEdit = findViewById(R.id.secoundNameEdit);
        emailEdit = findViewById(R.id.emailEdit);
        contactNumberEdit = findViewById(R.id.contactNumberEdit);
        createUsernameEdit = findViewById(R.id.createUsernameEdit);
        createPasswordEdit = findViewById(R.id.createPasswordEdit);
        saveFarmarProfileCreation = findViewById(R.id.saveFarmarProfileCreation);


        farmarProfileCreationModel = new FarmarProfileCreationModel();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("FarmerProfiles");  // all these together to connect the database
        storageReference = FirebaseStorage.getInstance().getReference().child("Images");

//        /// for bluer image
//
//        Bitmap bitmap1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.logo);
//        Rabbit.setContext(this)
//                .setBitMap(bitmap1)
//                .setScale(1.1f)
//                .setRaius(24.0f)
//                .setConstarst(0.0f)
//                .setBrightnes(1.0f)
//                .build();
//
//        Rabbit.getModifiedRabbitBitmap();
//        imageView.setImageBitmap(Rabbit.getModifiedRabbitBitmap());
//        ///
        saveFarmarProfileCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                setData();
//kkkkkkkkkk
////                farmarProfileCreationModel.setFirstNameEdit(firstNameEdit.getText().toString().trim());
////                farmarProfileCreationModel.setSecoundNameEdit(secoundNameEdit.getText().toString().trim());
////                farmarProfileCreationModel.setEmailEdit(emailEdit.getText().toString().trim());
////                farmarProfileCreationModel.setContactNumberEdit(Integer.valueOf(contactNumberEdit.getText().toString().trim()));
////                farmarProfileCreationModel.setCreateUsernameEdit(createUsernameEdit.getText().toString().trim());
////                farmarProfileCreationModel.setCreatePasswordEdit(Integer.valueOf(createPasswordEdit.getText().toString().trim()));
////
////                databaseReference.push().setValue(farmarProfileCreationModel);
//kkkkkkkkkkkkkkk
//                Intent saveFarmarProfileCreationIntent = new Intent(FarmarProfileCreation.this, EnterProductDetails.class);
//                startActivity(saveFarmarProfileCreationIntent);


            }
        });

        profilePicSelelctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fileChooser();
            }
        });

        profilePicUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (uploadTask != null && uploadTask.isInProgress()) {   // remove multiple uploading save image when clicking button several times
                    Toast.makeText(FarmarProfileCreation.this, "Upload in Progress", Toast.LENGTH_LONG).show();
                } else {
                    fileUploader();
                }
            }
        });

    }

    private String getExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void fileUploader() {

        String imageId;
        imageId = System.currentTimeMillis() + "." + getExtension(imgUri);
        StorageReference reference = storageReference.child(imageId);


        farmarProfileCreationModel.setFirstNameEdit(firstNameEdit.getText().toString().trim());
        farmarProfileCreationModel.setSecoundNameEdit(secoundNameEdit.getText().toString().trim());
        farmarProfileCreationModel.setEmailEdit(emailEdit.getText().toString().trim());
        farmarProfileCreationModel.setContactNumberEdit(Integer.valueOf(contactNumberEdit.getText().toString().trim()));
        farmarProfileCreationModel.setCreateUsernameEdit(createUsernameEdit.getText().toString().trim());
        farmarProfileCreationModel.setCreatePasswordEdit(Integer.valueOf(createPasswordEdit.getText().toString().trim()));
        farmarProfileCreationModel.setImageId(imageId);

        databaseReference.push().setValue(farmarProfileCreationModel);


        uploadTask = reference.putFile(imgUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
//                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }

    public void fileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUri = data.getData();
            profilePicUploadImageView.setImageURI(imgUri);
        }
    }

//    private void setData() {
//
//
//        firstName = firstNameEdit.getText().toString().trim();
//        secondName = secoundNameEdit.getText().toString().trim();
//        email = emailEdit.getText().toString().trim();
//        contactNumber = Integer.valueOf(contactNumberEdit.getText().toString().trim());
//        username = createUsernameEdit.getText().toString().trim();
//        password = Integer.valueOf(createPasswordEdit.getText().toString().trim());
//
//        if ((firstName != null && !firstName.isEmpty()) && (secondName != null && !secondName.isEmpty()) && (email != null && !email.isEmpty())
//                && (contactNumber != null) && (username != null && username.isEmpty()) && (password != null)) {
//            farmarProfileCreationModel.setFirstNameEdit(firstName);
//            farmarProfileCreationModel.setSecoundNameEdit(secondName);
//            farmarProfileCreationModel.setEmailEdit(email);
//            farmarProfileCreationModel.setContactNumberEdit(contactNumber);
//            farmarProfileCreationModel.setCreateUsernameEdit(username);
//            farmarProfileCreationModel.setCreatePasswordEdit(password);
//
//            DataInsertAsyc.execute((Runnable) farmarProfileCreationModel);
//        }
//    }
//
//
//    class DataInsertAsyc extends AsyncTask<FarmarProfileCreationModel, Void, Boolean> {
//
//        @Override
//        protected Boolean doInBackground(FarmarProfileCreationModel... farmarProfileCreationModels) {
//            databaseReference.push().setValue(farmarProfileCreationModels);
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean aBoolean) {
//            super.onPostExecute(aBoolean);
//            if (aBoolean) {
//                // succes toAST
//                Toast.makeText(getApplicationContext(), "Profile created successfully", Toast.LENGTH_SHORT).show();
//            } else {
//                //failed Toast
//                Toast.makeText(getApplicationContext(), "profile not created", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

}
