package com.rostoff.restnapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NapActivity extends AppCompatActivity {

    private TextView textView;
    private MediaPlayer mediaPlayer;
    private int source ;
    private int duree_recup;
    private TextView countDown;
    private CountDownTimer countDownTimer;
    private String choix_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nap);

        Intent intent = getIntent();
        this.choix_music = intent.getStringExtra("choix_music");
        this.configureToolbar();
        //System.out.println("test 1"+ choix_music);
        if(this.choix_music == "jungle") {
            this.source = R.raw.jungle;
        } else{
            this.source = R.raw.ocean;
        }


        this.countDown = (TextView)findViewById(R.id.countDown);

        this.mediaPlayer = MediaPlayer.create(getApplicationContext(), source );

        this.textView = (TextView)findViewById(R.id.duree_recup);

        //Intent intent = getIntent();
        //this.choix_music = intent.getStringExtra("choix_music");
        //System.out.println("test 2"+ choix_music);

        if(intent != null){
            this.duree_recup = intent.getIntExtra("duree_recup", 0);

            textView.setText("C'est parti pour une sieste de : " + duree_recup/60 + " min !");
        }


        MainActivity ma = new MainActivity();



        //Compte à rebours
        this.countDownTimer = new CountDownTimer(duree_recup*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                int minutes = (int) ((millisUntilFinished/1000)/60);
                int secondes = (int)((millisUntilFinished/1000)%60);
                countDown.setText(minutes+" : "+secondes);
            }


            @Override
            public void onFinish() {
                countDown.setText("Fin !!!");
            }
        };


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mediaPlayer.stop();
            }
        };

        new Handler().postDelayed(runnable, duree_recup*60*1000);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mediaPlayer.start();
        countDownTimer.start();
    }

    private void configureToolbar(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void playSound(View view) {
        Button button = (Button) view;

        if(mediaPlayer.isPlaying()){

            mediaPlayer.pause();
            countDownTimer.cancel();

            button.setText(getString(R.string.play_music_button));

        } else{
            mediaPlayer.start();
            countDownTimer.start();
            button.setText(getString(R.string.stop_music_button));
        }

    }
}
