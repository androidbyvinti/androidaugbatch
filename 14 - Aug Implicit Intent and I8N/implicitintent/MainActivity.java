package com.bmpl.implicitintent;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //I8N--> Internationalization

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void buttonClicked(View view){


        try {

            //Implicit Intent
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            //intent.setType("audio");//data type check-->
            intent.setType("text/plain");

            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");

            //Body--> Extra text
            intent.setPackage("com.whatsapp");

            intent.putExtra(Intent.EXTRA_TEXT, "This is my data");

            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"abc@gmail.com"});

            startActivity(Intent.createChooser(intent, "Select AnyOne"));
            /*            //Implicit Intent
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            //intent.setType("audio*//*");//data type check-->
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "This is my data");
            startActivity(Intent.createChooser(intent, "Select AnyOne"));*/
        }
        catch (ActivityNotFoundException e){

            Toast.makeText(this, "Activity not found", Toast.LENGTH_LONG).show();

        }

    }
}
