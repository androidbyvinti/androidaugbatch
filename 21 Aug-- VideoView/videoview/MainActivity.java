package com.bmpl.videoview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;

    String pathOfVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView)findViewById(R.id.videoView);

        pathOfVideo = "android.resource://" + getPackageName() + "/" + R.raw.small;

        videoView.setVideoPath(pathOfVideo);
        videoView.setMediaController(new MediaController(this));
        videoView.start();

    }
}
