package com.bmpl.gmailform;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity{

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView)findViewById(R.id.textView);

        Intent intent = getIntent();
        //get
        String first = intent.getStringExtra("first");

        textView.setText(intent.getStringExtra("first") + "\n" +
                            intent.getStringExtra("last") + "\n" +
                            intent.getStringExtra("username") + "\n" +
                            intent.getStringExtra("password") + "\n" +
                            intent.getStringExtra("day") + "\n" +
                            intent.getStringExtra("month") + "\n" +
                            intent.getStringExtra("year"));

    }

}
