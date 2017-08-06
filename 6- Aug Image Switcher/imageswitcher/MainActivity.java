package com.bmpl.imageswitcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button imageOne, imageTwo;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageOne = (Button)findViewById(R.id.imageOneButton);
        imageTwo = (Button)findViewById(R.id.imageTwoButton);

        imageView = (ImageView)findViewById(R.id.imageView);

        //anonymous way
        //this--> current object/class reference
        imageOne.setOnClickListener(this);
        imageTwo.setOnClickListener(this);

    }
    //View--> View is predefined class--> represt widgets and listeners(interface)
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.imageOneButton:
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageResource(R.drawable.images1);
                break;

            case R.id.imageTwoButton:
                imageView.setImageResource(R.drawable.images2);
                break;
        }
    }
}