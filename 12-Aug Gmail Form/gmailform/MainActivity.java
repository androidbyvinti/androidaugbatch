package com.bmpl.gmailform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private ArrayAdapter arrayAdapter;

    private EditText firstEditText, lastEditText, userNameEditText,
            passwordEditText, dayEditText, yearEditText;

    private Button submitButton;

    private Spinner monthSpinner, countryFlagSpinner;

    ArrayList arrayList;

    int flags[] = {R.drawable.ic_launcher, R.drawable.ic_launcher,
                    R.drawable.ic_launcher, R.drawable.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downcast();

        arrayList = new ArrayList();
        arrayList.add(R.drawable.ic_launcher);
        arrayList.add(R.drawable.ic_launcher);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, arrayList);

        countryFlagSpinner.setAdapter(arrayAdapter);
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
        countryFlagSpinner = (Spinner)findViewById(R.id.spinnerFlag);

        submitButton.setOnClickListener(this);
        firstEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                firstEditText.setBackgroundResource(R.drawable.background_effect);

/*                if(hasFocus){
                   firstEditText.setBackgroundResource(R.drawable.background_effect);
                } else{

                }*/

            }
        });
    }

    @Override
    public void onClick(View view) {

        //String first = firstEditText.getText().toString();

        //Explicit intent
        Intent intent = new Intent(this, SecondActivity.class);

        //edittext data
        intent.putExtra("first", firstEditText.getText().toString());  //when we need to send data--> putExtra()
        intent.putExtra("last", lastEditText.getText().toString());
        intent.putExtra("username", userNameEditText.getText().toString());
        intent.putExtra("password", passwordEditText.getText().toString());
        intent.putExtra("day", dayEditText.getText().toString());
        intent.putExtra("year", yearEditText.getText().toString());

        //spinner data
        intent.putExtra("month", monthSpinner.getSelectedItem().toString());

        startActivity(intent);
    }
}
