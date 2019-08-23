package com.rostoff.restnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private final int SPLASH_SCREEN_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences("Datas", Context.MODE_PRIVATE);
        String music = sharedPreferences.getString("sauvegarde_choix_music", "");

        if(music == null){
            music = "jungle";
        }

        System.out.println("Le choix en memoire est: "+music);


        //Rediriger vers la page principale après 3 secondes
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //demarrer une page
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        //Retarder le delai de 3 secondes
        new Handler().postDelayed(runnable, SPLASH_SCREEN_TIME_OUT);
    }
}
