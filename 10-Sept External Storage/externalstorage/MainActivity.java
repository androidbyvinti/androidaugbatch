package com.bmpl.externalstorage;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emailEditText, passwordEditText;
    Button saveButton, readButton;

    File pathOfFile;

    File fileDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pathOfFile = Environment.getExternalStorageDirectory();//sd card path
        pathOfFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

        fileDetails = new File(pathOfFile, "user_details.doc");

        emailEditText = (EditText)findViewById(R.id.emailEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);

        saveButton = (Button)findViewById(R.id.saveButton);
        readButton = (Button)findViewById(R.id.readButton);

        saveButton.setOnClickListener(this);
        readButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.saveButton:

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                try {

                    if(pathOfFile.canWrite())
                    {
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileDetails));
                        bufferedWriter.write(email);
                        bufferedWriter.write(password);

                        bufferedWriter.close();
                        Toast.makeText(MainActivity.this, "Data written", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(MainActivity.this, "Cannot write data", Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.readButton:
                break;

        }
    }
}
