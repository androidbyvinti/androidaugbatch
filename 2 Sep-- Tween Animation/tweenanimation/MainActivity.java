package com.bmpl.tweenanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button button;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        button = (Button)findViewById(R.id.button);

        animation = AnimationUtils.loadAnimation(this, R.anim.translate_animation);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageView.startAnimation(animation);

            }
        });

    }
}
