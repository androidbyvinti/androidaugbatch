package com.bmpl.mediafiles;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button playButton, pauseButton, stopButton;
    MediaPlayer mediaPlayer;

    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        playButton = (Button)findViewById(R.id.playButton);
        pauseButton = (Button)findViewById(R.id.pauseButton);
        stopButton = (Button)findViewById(R.id.stopButton);

        mediaPlayer = MediaPlayer.create(this, R.raw.sample);
        mediaPlayer.getDuration();
        mediaPlayer.getCurrentPosition();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()){
            case R.id.playButton:
                if(mediaPlayer==null)
                {
                    playButton.setContentDescription("data");

                    Toast.makeText(this, playButton.getContentDescription(), Toast.LENGTH_LONG).show();

                    mediaPlayer = MediaPlayer.create(this, R.raw.sample);
                    mediaPlayer.start();
                } else {
                    mediaPlayer.start();// start play
                }

                break;

            case R.id.pauseButton:
                mediaPlayer.pause();
                break;

            case R.id.stopButton:
                mediaPlayer.stop();
                mediaPlayer = null;
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        } else if(mediaPlayer == null){

        }
    }
}