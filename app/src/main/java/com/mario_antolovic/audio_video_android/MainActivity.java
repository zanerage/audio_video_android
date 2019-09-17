package com.mario_antolovic.audio_video_android;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    private VideoView myVideoView;
    private Button btnPlay;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myVideoView = findViewById(R.id.myvideo);
        btnPlay = findViewById(R.id.btn_play);

        btnPlay.setOnClickListener(MainActivity.this);

        mediaController = new MediaController(MainActivity.this);

    }

    @Override
    public void onClick(View v) {

        Uri videoUri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.butterfly);

        myVideoView.setVideoURI(videoUri);

        myVideoView.setMediaController(mediaController);
        mediaController.setAnchorView(myVideoView);
        myVideoView.start();

    }
}
