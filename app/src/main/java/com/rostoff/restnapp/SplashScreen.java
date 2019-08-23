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

    //region CUSTOM RUNNABLE

    private final Runnable runnableCustome =
            new Runnable() {
                @Override
                public void run() {
                    //demarrer une page
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            };

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedPreferences = getSharedPreferences("Datas", Context.MODE_PRIVATE);

        //Verifier que les valeurs music et temps ne sont pas nulles
        musicVerifcation();
        timeVerification();

        //Retarder le delai de 3 secondes
        new Handler().postDelayed(runnableCustome, SPLASH_SCREEN_TIME_OUT);
    }


    //region FUNCTIONS

    private void musicVerifcation() {
        String music = sharedPreferences.getString("sauvegarde_choix_music", "");
        if(music.equals("")) {
            music = "jungle";
            sharedPreferences.edit().putString("sauvegarde_choix_music", music).apply();
            sharedPreferences.edit().commit();
        }
    }


    private void timeVerification() {
        int duree_music = sharedPreferences.getInt("sauvegarde_duree_music", 0);
        if(duree_music == 0){
            duree_music = 5;
            sharedPreferences.edit().putInt("sauvegarde_duree_music", duree_music).apply();
            sharedPreferences.edit().commit();
        }
    }

    //endregion
}
