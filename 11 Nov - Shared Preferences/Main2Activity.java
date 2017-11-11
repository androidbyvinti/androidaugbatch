package com.bmpl.sharedpreference5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class Main2Activity extends AppCompatActivity {

    EditText nameEditText, passwordEditText;
    Button saveButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nameEditText = findViewById(R.id.nameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        saveButton = findViewById(R.id.saveButton);
        textView = findViewById(R.id.textView);

        readData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                SharedPreferences.Editor editor = getSharedPreferences("userdata", MODE_PRIVATE).edit();
                editor.putString("name", name);
                editor.putString("password", password);
                editor.commit();    //

                startActivity(new Intent(Main2Activity.this, Main3Activity.class));
            }
        });
    }

    void readData(){

        SharedPreferences sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);
        textView.setText(sharedPreferences.getString("name", "")
                .concat("\n")
                .concat(sharedPreferences.getString("password", "")));

        File file =  new File("userdata");
        Log.i("File path = " , " " +file.getPath());
        //textView.setText("\n");
        //textView.setText(sharedPreferences.getString("password", ""));

    }

}
