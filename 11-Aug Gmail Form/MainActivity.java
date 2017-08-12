package com.bmpl.gmailform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText firstEditText, lastEditText, userNameEditText,
            passwordEditText, dayEditText, yearEditText;

    private Button submitButton;

    private Spinner monthSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downcast();

    }

    private void downcast(){

        firstEditText = (EditText)findViewById(R.id.firstEditText);
        lastEditText = (EditText)findViewById(R.id.lastEditText);
        userNameEditText = (EditText)findViewById(R.id.userNameEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        dayEditText = (EditText)findViewById(R.id.dayEditText);
        yearEditText = (EditText)findViewById(R.id.yearEditText);

        submitButton = (Button)findViewById(R.id.submitButton);

        monthSpinner = (Spinner)findViewById(R.id.spinnerMonth);

        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        //Explicit intent
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
