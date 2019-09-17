package com.mario_antolovic.audio_video_android;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener,SeekBar.OnSeekBarChangeListener{
//UI COMPONENTS
    private VideoView myVideoView;
    private Button btnPlay;
    private MediaController mediaController;
    private Button btnplaymusic,btnpausemusic;
    private MediaPlayer mediaPlayer;
    private SeekBar volumeseekbar;
    private AudioManager audioManager;
    private SeekBar moveseekbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myVideoView = findViewById(R.id.myvideo);
        btnPlay = findViewById(R.id.btn_play);
        btnpausemusic = findViewById(R.id.btn_pause);
        btnplaymusic = findViewById(R.id.btn_music);

        btnPlay.setOnClickListener(MainActivity.this);
        btnplaymusic.setOnClickListener(MainActivity.this);
        btnpausemusic.setOnClickListener(MainActivity.this);

        mediaController = new MediaController(MainActivity.this);

        mediaPlayer = MediaPlayer.create(this,R.raw.music);
        volumeseekbar = findViewById(R.id.seekBar);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        moveseekbar = findViewById(R.id.seekbarmove);

        int maximumVolumeForUsers = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolumeForUsers = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volumeseekbar.setMax(maximumVolumeForUsers);
        volumeseekbar.setProgress(currentVolumeForUsers);

        volumeseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    //Toast.makeText(MainActivity.this,Integer.toString(progress),Toast.LENGTH_SHORT).show();
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        moveseekbar.setOnSeekBarChangeListener(MainActivity.this);



    }

    @Override
    public void onClick(View buttonView) {

        switch (buttonView.getId()) {
            case R.id.btn_play:
                Uri videoUri = Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.butterfly);

                myVideoView.setVideoURI(videoUri);

                myVideoView.setMediaController(mediaController);
                mediaController.setAnchorView(myVideoView);
                myVideoView.start();

                break;

            case R.id.btn_music:
                mediaPlayer.start();

                break;
            case R.id.btn_pause:

                mediaPlayer.pause();
                break;
        }



    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
