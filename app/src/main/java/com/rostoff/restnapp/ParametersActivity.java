package com.rostoff.restnapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
    private SharedPreferences preferences_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);

        this.ocean = (Button)findViewById(R.id.ocean_choice);
        this.jungle = (Button)findViewById(R.id.jungle_choice);

        //sauvegarde du choix de musique
        preferences_music = getPreferences(MODE_PRIVATE);

        //Configurer et utiliser la fonction configureToolbar
        this.configureToolbar();


        ocean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                choix_music = "ocean";
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("choix_music", choix_music);
                startActivity(intent);
                preferences_music.edit().putString("sauvegarde_choix_music", choix_music).apply();
            }
        });

        jungle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choix_music = "jungle";
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("choix_music", choix_music);
                startActivity(intent);
                preferences_music.edit().putString("sauvegarde_choix_music", choix_music).apply();
            }
        });

        choix_music = getPreferences(MODE_PRIVATE).getString("sauvegarde_choix_music", null);
        //System.out.println("sauvegarde " + choix_music);

        if(this.choix_music == null) {
            this.choix_music = "jungle";
            this.jungle.setPressed(true);
        }

        if(this.choix_music =="jungle"){
            this.jungle.setPressed(true);
        }

        if(this.choix_music == "ocean"){
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
