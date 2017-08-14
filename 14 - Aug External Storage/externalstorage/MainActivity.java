package com.bmpl.externalstorage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nameEditText, passwordEditText;
    Button submitButton, readButton;

    //path specify where to write data
    File path;
    File fileDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = (EditText)findViewById(R.id.nameEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);

        submitButton = (Button)findViewById(R.id.submitButton);
        readButton = (Button)findViewById(R.id.readButton);

        //sd card root folder path
        path = Environment.getExternalStorageDirectory();

        //for internal directory
        //path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        fileDetails = new File(path, "data.txt");

        submitButton.setOnClickListener(this);
        readButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.submitButton:

                String name = nameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if(fileDetails!=null){

                    try {
                        FileWriter fileWriter = new FileWriter(fileDetails);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                        bufferedWriter.write(name + "\n");
                        bufferedWriter.write(password + "\n");

                        Toast.makeText(this, "Data written", Toast.LENGTH_LONG).show();

                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(this, "Unable to write data", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.readButton:

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setCancelable(false);

        alertDialog.setTitle("Exit");
        alertDialog.setMessage("Do you want to exit?");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        //3 ways --> Positive button, Negative button, Neutral button
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                //onDestroy();
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onResume();
            }
        });
        alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        //make alert dialog visible on screen
        alertDialog.show();
    }
}