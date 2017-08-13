package com.bmpl.activitylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("onCreate", "Inside onCreate()");

        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onStart", "Inside onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop", "Inside onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy", "Inside onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause", "Inside onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "Inside onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("onRestart", "Inside onRestart()");
    }
}
