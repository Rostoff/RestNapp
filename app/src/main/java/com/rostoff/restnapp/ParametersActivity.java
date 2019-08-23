package com.rostoff.restnapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class ParametersActivity extends AppCompatActivity {

    private Button ocean;
    private Button jungle;
    private String choix_music;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);

        this.ocean = (Button)findViewById(R.id.ocean_choice);
        this.jungle = (Button)findViewById(R.id.jungle_choice);

        //sauvegarde du choix de musique
        sharedPreferences = getSharedPreferences("Datas", Context.MODE_PRIVATE);

        //Configurer et utiliser la fonction configureToolbar
        this.configureToolbar();


        ocean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                choix_music = "ocean";
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("choix_music", choix_music);
                startActivity(intent);
                sharedPreferences.edit().putString("sauvegarde_choix_music", choix_music).apply();
                sharedPreferences.edit().commit();

            }
        });

        jungle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choix_music = "jungle";
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("choix_music", choix_music);
                startActivity(intent);
                sharedPreferences.edit().putString("sauvegarde_choix_music", choix_music).apply();
                sharedPreferences.edit().commit();
            }
        });


        sharedPreferences = getSharedPreferences("Datas", Context.MODE_PRIVATE);
        String music = sharedPreferences.getString("sauvegarde_choix_music", "vide");
        System.out.println("parametres sauvegardé: " + music);


        if(music.equals("jungle")){
            this.jungle.setPressed(true);
        }

        if(music.equals("ocean")){
            this.ocean.setPressed(true);
        }
    }


    private void configureToolbar(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);

        //définir la toolbar
        setSupportActionBar(toolbar);

        //Obtenez un support ActionBar correspondant à cette barre d'outils
        ActionBar actionBar = getSupportActionBar();

        //Activer la flèche de retour
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

}
