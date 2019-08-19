package com.rostoff.restnapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ParametersActivity extends AppCompatActivity {

    private Button ocean;
    private Button jungle;
    private String choix_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);

        this.ocean = (Button)findViewById(R.id.ocean_choice);
        this.jungle = (Button)findViewById(R.id.jungle_choice);

        //Configurer et utiliser la fonction configureToolbar
        this.configureToolbar();


        ocean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choix_music = "ocean";
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("choix_music", choix_music);
                startActivity(intent);
            }
        });

        jungle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choix_music = "jungle";
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("choix_music", choix_music);
                startActivity(intent);
            }
        });
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
