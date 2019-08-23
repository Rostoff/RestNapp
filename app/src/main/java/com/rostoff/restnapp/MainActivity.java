package com.rostoff.restnapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView temps;
    private Button btn_dodo;
    //private Button parameters;
    private int duree_recup;
    private String choix_music;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        this.seekBar = (SeekBar)findViewById(R.id.seekbar);
        seekBar.setProgress(10);
        this.temps = (TextView)findViewById(R.id.timer);
        this.btn_dodo = (Button)findViewById(R.id.go_dodo);

        //Recuperation du choix de musique dans les paramètres
        Intent intent = getIntent();

        this.choix_music = intent.getStringExtra("choix_music");
        if(this.choix_music == null) {
            this.choix_music = "jungle";
        }



        //System.out.println(choix_music);
        //System.out.println("Contenu de la shared "+sharedPreferences.getString("sauvegarde_choix_music", choix_music));

        //Configuration de la toolbar
        this.configureToolbar();

        this.temps.setText(getResources().getString(R.string.timer) +' '+ seekBar.getProgress() + getResources().getString(R.string.minute));


        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                temps.setText(getResources().getString(R.string.timer) +' '+ seekBar.getProgress() + getResources().getString(R.string.minute));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn_dodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                duree_recup = (int)(seekBar.getProgress()); //temps en minutes

                Intent intent = new Intent(getApplicationContext(), NapActivity.class);

                intent.putExtra("duree_recup", duree_recup);
                intent.putExtra("choix_music", choix_music);

                startActivity(intent);
                //finish();
            }
        });

    } //fin de onCreate



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.parameters:
                Intent intent = new Intent(getApplicationContext(), ParametersActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate le menu et l'ajouter à la toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    private void configureToolbar(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }
}
