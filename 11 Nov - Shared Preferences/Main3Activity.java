package com.bmpl.sharedpreference5;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView textView = findViewById(R.id.textView);

        SharedPreferences sharedPreferences = getSharedPreferences("userdata", MODE_PRIVATE);
        textView.setText("Welcome " + sharedPreferences.getString("name", ""));
    }
}
