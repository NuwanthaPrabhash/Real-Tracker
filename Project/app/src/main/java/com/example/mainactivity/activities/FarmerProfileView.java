package com.example.mainactivity.activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class FarmerProfileView extends AppCompatActivity {

    EditText firstNameEdit, secoundNameEdit, emailEdit, contactNumberEdit, createUsernameEdit, createPasswordEdit;
    Button profilePicSelelctButton, profilePicUploadButton;
    DatabaseReference databaseReference;
    ImageView imageView, profilePicUploadImageView;
    StorageReference storageReference;
    FirebaseStorage firebaseStorage;
    Uri imgUri;
    private StorageTask uploadTask;
    FarmarProfileCreationModel farmarProfileCreationModel;
    String userNameEdit;
    String passEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_farmer_profile_view);
        setContentView(R.layout.activity_farmar_profile_view_and_update);

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

        Intent myprof = getIntent();
        userNameEdit = myprof.getStringExtra("user");
        passEdit = myprof.getStringExtra("pass");

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = rootRef.child("FarmerProfiles").child(userNameEdit);
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReferenceFromUrl("gs://project-ded5a.appspot.com").child("Images");

        userNameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fname = dataSnapshot.child("firstNameEdit").getValue().toString();
                String sname = dataSnapshot.child("secoundNameEdit").getValue().toString();
                String email = dataSnapshot.child("emailEdit").getValue().toString();
                String contact = dataSnapshot.child("contactNumberEdit").getValue().toString();
                final String image = dataSnapshot.child("imageId").getValue().toString();

                firstNameEdit.setText(fname);
                secoundNameEdit.setText(sname);
                emailEdit.setText(email);
                contactNumberEdit.setText(contact);
                createUsernameEdit.setText(userNameEdit);
                createPasswordEdit.setText(passEdit);

                try {
                    final File file = File.createTempFile("image", "jpg");
                    storageReference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            profilePicUploadImageView.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(FarmerProfileView.this, "Image falier", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        farmarProfileCreationModel = new FarmarProfileCreationModel();
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
                    Toast.makeText(FarmerProfileView.this, "Upload in Progress", Toast.LENGTH_LONG).show();
                } else {
                    fileUploader();
                }

                Intent intentProductDetails = new Intent(FarmerProfileView.this, EnterProductDetails.class);
                startActivity(intentProductDetails);
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

//        -------------------------------------------------------------------------------------------------

//        if (TextUtils.isEmpty(firstNameEdit.getText())){
//            firstNameEdit.setError("First Name is Required!");
//        }
//        else {
//        }

//        ---------------------------------------------------------------------------------------------------
        databaseReference = FirebaseDatabase.getInstance().getReference().child("FarmerProfiles");  // all these together to connect the database
        storageReference = FirebaseStorage.getInstance().getReference().child("Images");

        farmarProfileCreationModel = new FarmarProfileCreationModel();

        farmarProfileCreationModel.setFirstNameEdit(firstNameEdit.getText().toString().trim());
        farmarProfileCreationModel.setSecoundNameEdit(secoundNameEdit.getText().toString().trim());
        farmarProfileCreationModel.setEmailEdit(emailEdit.getText().toString().trim());
        farmarProfileCreationModel.setContactNumberEdit(Integer.valueOf(contactNumberEdit.getText().toString().trim()));
        farmarProfileCreationModel.setCreateUsernameEdit(createUsernameEdit.getText().toString().trim());
        farmarProfileCreationModel.setCreatePasswordEdit(Integer.valueOf(createPasswordEdit.getText().toString().trim()));
        farmarProfileCreationModel.setImageId(imageId);

        databaseReference.child(userNameEdit).setValue(farmarProfileCreationModel);


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
