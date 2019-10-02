package com.example.mainactivity.activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class FarmarProfileCreation extends AppCompatActivity {

    EditText firstNameEdit, secoundNameEdit, emailEdit, contactNumberEdit, createUsernameEdit, createPasswordEdit;
    Button profilePicSelelctButton, profilePicUploadButton;
    FarmarProfileCreationModel farmarProfileCreationModel;
    DatabaseReference databaseReference;
    ImageView imageView, profilePicUploadImageView;
    StorageReference storageReference;
    Uri imgUri;
    private StorageTask uploadTask;

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


        farmarProfileCreationModel = new FarmarProfileCreationModel();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("FarmerProfiles");  // all these together to connect the database
        storageReference = FirebaseStorage.getInstance().getReference().child("Images");


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

//                Intent intentProductDetails = new Intent(FarmarProfileCreation.this, EnterProductDetails.class);
//                startActivity(intentProductDetails);
            }
        });

    }

    private String getExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void fileUploader() {

        final String imageId;
        imageId = System.currentTimeMillis() + "." + getExtension(imgUri);
        StorageReference reference = storageReference.child(imageId);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = rootRef.child("FarmerProfiles").child(createUsernameEdit.getText().toString());

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {  //dataSnapshot get the all data from userNameRef

                if (dataSnapshot.exists()) {
                    Toast.makeText(FarmarProfileCreation.this, "Username is Alredi Exist", Toast.LENGTH_LONG).show();
                } else {

                    if (TextUtils.isEmpty(firstNameEdit.getText())) {
                        firstNameEdit.setError("First Name is Required!");
                    } else {
                        farmarProfileCreationModel.setFirstNameEdit(firstNameEdit.getText().toString().trim());
                    }
                    if (TextUtils.isEmpty(secoundNameEdit.getText())) {
                        secoundNameEdit.setError("Second Name is Required!");
                    } else {
                        farmarProfileCreationModel.setSecoundNameEdit(secoundNameEdit.getText().toString().trim());
                    }
                    if (TextUtils.isEmpty(emailEdit.getText())) {
                        emailEdit.setError("Email is Required!");
                    } else {
                        farmarProfileCreationModel.setEmailEdit(emailEdit.getText().toString().trim());
                    }
                    if (TextUtils.isEmpty(contactNumberEdit.getText())) {
                        contactNumberEdit.setError("Contact Number is Required!");
                    } else {
                        farmarProfileCreationModel.setContactNumberEdit(Integer.valueOf(contactNumberEdit.getText().toString().trim()));
                    }
                    if (TextUtils.isEmpty(createUsernameEdit.getText())) {
                        createUsernameEdit.setError("Username is Required!");
                    } else {
                        farmarProfileCreationModel.setCreateUsernameEdit(createUsernameEdit.getText().toString().trim());
                    }
                    if (TextUtils.isEmpty(createPasswordEdit.getText())) {
                        createPasswordEdit.setError("Password is Required!");
                    } else {
                        farmarProfileCreationModel.setCreatePasswordEdit(Integer.valueOf(createPasswordEdit.getText().toString().trim()));
                    }

                    farmarProfileCreationModel.setImageId(imageId);

                    if (!firstNameEdit.getText().toString().trim().isEmpty() && !secoundNameEdit.getText().toString().trim().isEmpty() && !emailEdit.getText().toString().trim().isEmpty() && !contactNumberEdit.getText().toString().trim().isEmpty() && !createUsernameEdit.getText().toString().trim().isEmpty() && !createPasswordEdit.getText().toString().trim().isEmpty() && profilePicUploadImageView.getDrawable() != null) {
                        databaseReference.child(createUsernameEdit.getText().toString().trim()).setValue(farmarProfileCreationModel);
                        Toast.makeText(FarmarProfileCreation.this, "Profile Created Successfully", Toast.LENGTH_LONG).show();
                        Intent intentProductDetails = new Intent(FarmarProfileCreation.this, EnterProductDetails.class);
                        startActivity(intentProductDetails);
                    } else {
                        if(profilePicUploadImageView.getDrawable() == null){
                            Toast.makeText(FarmarProfileCreation.this, "Enter Profile Picture", Toast.LENGTH_LONG).show();
                        }
                        Toast.makeText(FarmarProfileCreation.this, "Enter Your Details", Toast.LENGTH_LONG).show();
                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        userNameRef.addListenerForSingleValueEvent(valueEventListener);


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

}
