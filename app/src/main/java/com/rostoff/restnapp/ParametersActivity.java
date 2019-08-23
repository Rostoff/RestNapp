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
import android.view.MotionEvent;
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


        ocean.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    save_music_choice("ocean");
                    setSeletedButton(false, true);

                       Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                       //intent.putExtra("choix_music", choix_music);
                       startActivity(intent);


                }
                return true;

            }
        });

        jungle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    save_music_choice("jungle");
                    setSeletedButton(true, false);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    //intent.putExtra("choix_music", choix_music);
                    startActivity(intent);

                }
                return true;

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        colorButtononPressed();
    }

    //Met en mémoire le choix de musique dans le SharedPreferences
    private void save_music_choice(String choixMusic) {
        sharedPreferences.edit().putString("sauvegarde_choix_music", choixMusic).apply();
        sharedPreferences.edit().commit();
    }


    //Chaque bouton sera considéré comme "appuyé"
    private boolean setSeletedButton(boolean jungleSelect, boolean oceanSelect) {
        this.jungle.setPressed(jungleSelect);
        this.ocean.setPressed(oceanSelect);
        return true;
    }

    //Recupération de la valeur enregistré et appel de la fonction setSelectedButton en fonction
    public void colorButtononPressed(){
        String music = sharedPreferences.getString("sauvegarde_choix_music", "vide");

        if(music.equals("jungle")){
            setSeletedButton(true, false);
        } else {
            setSeletedButton(false, true);
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
