package com.example.yogesh.mediahome;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 MediaPlayer md;
 SeekBar sb;
 boolean ispause=false,played=false;
 int songlength;
 int t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       md= new MediaPlayer();
       sb=(SeekBar)findViewById(R.id.sb);
       sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              t=progress;
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {
          md.seekTo(t);
           }
       });
    }

    public void playbtn(View view) {
     if(ispause==false&&played==false){
        played=true;
        md= MediaPlayer.create(this,R.raw.sorry);
        songlength=md.getDuration();
        sb.setMax(songlength);
         Toast.m akeText(this, "duration:"+songlength, Toast.LENGTH_SHORT).show();
     }
    md.start();
        CountDownTimer cd=new CountDownTimer(100000000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int dur=md.getCurrentPosition();
                sb.setProgress(dur);
            }

            @Override
            public void onFinish() {

            }
        };

       cd.start();



    }

    public void pausebtn(View view) {
        md.pause();
        ispause=true;
        played=false;
    }

    public void stopbtn(View view) {
        md.stop();

        ispause=false;
        played=false;
    }

    public void plylistbtn(View view) {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        intent.setType("*/*");
        Intent ob= Intent.createChooser(intent,"choose audio");
        startActivityForResult(ob,1);
    }
    @Override
    public void onActivityResult(int requestcode,int resultcode,Intent data){

    if(resultcode==RESULT_OK){
         
         md.start();
    }

    }




}
