package com.bmpl.internalstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nameEditText, passwordEditText;
    Button saveButton, readButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = (EditText)findViewById(R.id.nameEditText);
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

                String name = nameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                try {

                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(openFileOutput("user-details", MODE_APPEND)));
                    bufferedWriter.write(name + "\n");
                    bufferedWriter.write(password  + "\n");

                    Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();

                    bufferedWriter.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }


                break;

            case R.id.readButton:

                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput("user-details")));
                    String line = "";

                    while((line = bufferedReader.readLine())!=null){
                        Log.d("MainActivity","Data is= " + line);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
