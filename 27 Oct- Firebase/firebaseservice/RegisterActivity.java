package com.bmpl.firebaseservice_nisha;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nameEditText, passwordEditText, emailEditText;
    Button createAccountButton;
    private FirebaseAuth mAuth;
    private static int counter = 1;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    //private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = (EditText)findViewById(R.id.nameEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        emailEditText = (EditText)findViewById(R.id.emailEditText);

        //firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("userdetails");

        createAccountButton = (Button)findViewById(R.id.createAccount);

        mAuth = FirebaseAuth.getInstance();

        createAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        final String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            nameEditText.setError("Cannot be Empty");
            emailEditText.setError("Cannot be Empty");
            passwordEditText.setError("Cannot be Empty");
        } else if(password.length()<6){
            passwordEditText.setError("Minimum 6 characters required");
        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //account is successfully created
                            if(task.isSuccessful()){
                                firebaseUser = mAuth.getCurrentUser();
                                String uid = firebaseUser.getUid();
                                myRef.child(uid).child("Name").setValue(name);
                                //myRef.child(String.valueOf(counter)).child("Name").setValue(name);//create or overwrite
                                counter++;
                                Intent intent = new Intent(RegisterActivity.this, WelcomeActivity.class);
                                startActivity(intent);
                            } else {
                                //account is not created
                                Toast.makeText(RegisterActivity.this, "Error while Creating Account... Try later", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}
