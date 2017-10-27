package com.bmpl.firebaseservice_nisha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class WelcomeActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private Button uploadButton, downdloadButton;
    private File pathOfStorage;
    private ProgressDialog progressDialog;
    private StorageReference mStorageRef;
    private String nameOfFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        mStorageRef = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        pathOfStorage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

        Log.d("WelcomeActivity","Path of Storage=" +pathOfStorage);
        uploadButton = (Button)findViewById(R.id.uploadButton);

        downdloadButton = (Button)findViewById(R.id.downloadButton);

        String userId = firebaseUser.getUid();
        Log.d("WelcomeActivity", "Id = " + userId);
        welcomeTextView = (TextView)findViewById(R.id.welcomeTextView);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("userdetails");

        databaseReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                progressDialog.dismiss();
                //enhanced for loop
                welcomeTextView.setText("Welcome " + dataSnapshot.child("Name").getValue());
                Log.d("WelcomeActivity" , "Name = " +dataSnapshot.child("Name").getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                progressDialog.dismiss();
                Log.d("WelcomeActivity","Error Occurred"+ databaseError.getMessage());
            }
        });

        downdloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File localFile = null;
                try {
                    localFile = File.createTempFile(nameOfFile,".pdf", pathOfStorage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mStorageRef.child(nameOfFile).getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                Toast.makeText(WelcomeActivity.this,  "Downloaded = " + taskSnapshot.getStorage().getName() , Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(WelcomeActivity.this, "Error " + exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);//available from API:19
                    intent.setType("application/pdf");
                    startActivityForResult(intent, 1001);
                } catch (Exception e){
                    Log.d("WelcomeActivity","upload error = " + e.getMessage());
                    Toast.makeText(WelcomeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1001 && resultCode == RESULT_OK){

            Uri uri = data.getData();
            nameOfFile = uri.getPath();
            int path = nameOfFile.lastIndexOf("/");
            nameOfFile = nameOfFile.substring(path + 1);

            Log.d("WelcomeActivity","name = " + nameOfFile + "uri data= " + uri);

            Uri file = uri;
            StorageReference riversRef = mStorageRef.child(nameOfFile);

            riversRef.putFile(file)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(WelcomeActivity.this, "Uploaded", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(WelcomeActivity.this, "Error Occurred" + exception.getMessage(), Toast.LENGTH_LONG).show();
                            Log.d("WelcomeActivity", exception.getMessage());

                        }
                    });
        }
    }


}