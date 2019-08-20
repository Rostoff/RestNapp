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
    private int current_time;
    private int milli_current_time;
    private int temps_ecoule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nap);

        //Recupération de la valeur "choix_music" et securisation au cas où valeur nulle
        Intent intent = getIntent();
        if(intent != null) {
            this.choix_music = intent.getStringExtra("choix_music");
        }

        //Initialisation de la toolbar
        this.configureToolbar();

        //Condition musique par défaut
        if(this.choix_music.equals("jungle")) {
            this.source = R.raw.jungle;
        } else{
            this.source = R.raw.ocean;
        }

        this.countDown = (TextView)findViewById(R.id.countDown);
        this.mediaPlayer = MediaPlayer.create(getApplicationContext(), source );
        this.textView = (TextView)findViewById(R.id.duree_recup);

        if(intent != null){

            this.duree_recup = intent.getIntExtra("duree_recup", 0);
            textView.setText("C'est parti pour une sieste de : " + duree_recup + " min !");
        }

            this.current_time = duree_recup; //temps récupéré en minutes

            this.milli_current_time = current_time * 1000 * 60;

        this.Timer();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mediaPlayer.start();
        countDownTimer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
        countDownTimer.cancel();
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
            System.out.println("Temps sauvegardé avant: "+ milli_current_time);
            countDownTimer.cancel();
            System.out.println("Temps sauvegardé après: "+ milli_current_time);
            //this.temps_ecoule = (duree_recup*1000*60) - milli_current_time;

            button.setText(getString(R.string.play_music_button));

        } else {
            this.Timer();
            mediaPlayer.start();
            countDownTimer.start();
            button.setText(getString(R.string.stop_music_button));
        }

    }


    public void Timer() {

        //Compte à rebours
        this.countDownTimer = new CountDownTimer(milli_current_time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                System.out.println("depart" +milli_current_time);


                    milli_current_time = (int) millisUntilFinished;

                    //milli_current_time = (int) millisUntilFinished - (temps_ecoule);



                int minutes = (int) ((milli_current_time / 1000) / 60);
                int secondes = (int) ((milli_current_time / 1000) % 60);
                System.out.println("millisuntil" + millisUntilFinished);
                System.out.println("Milli_current_time = " + milli_current_time);

                countDown.setText(String.format("%02d:%02d", minutes, secondes));

            }


            @Override
            public void onFinish() {

                mediaPlayer.stop();
                countDown.setText("Fin !!");
            }
        };
    }

}
