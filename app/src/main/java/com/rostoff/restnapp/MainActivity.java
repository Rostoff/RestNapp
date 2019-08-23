package com.rostoff.restnapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
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

    // VARIABLES
    private SeekBar seekBar;
    private TextView temps;
    private Button btn_dodo;
    private int duree_recup;
    private SharedPreferences sharedPreferences;


    //region CUSTOM LISTENERS
    private final SeekBar.OnSeekBarChangeListener seekBarCustomListner =
            new SeekBar.OnSeekBarChangeListener() {

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
            };

    private final View.OnClickListener viewOnClickListenerCustom =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //sharedPreferences = getSharedPreferences("Datas", Context.MODE_PRIVATE);
                    duree_recup = seekBar.getProgress(); //temps en minutes

                    sharedPreferences.edit().putInt("sauvegarde_duree_music", duree_recup).apply();
                    sharedPreferences.edit().commit();

                    Intent intent = new Intent(getApplicationContext(), NapActivity.class);
                    startActivity(intent);

                }
            };
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.seekBar = (SeekBar)findViewById(R.id.seekbar);
        this.temps = (TextView)findViewById(R.id.timer);
        this.btn_dodo = (Button)findViewById(R.id.go_dodo);

        sharedPreferences = getSharedPreferences("Datas", Context.MODE_PRIVATE);
        duree_recup = sharedPreferences.getInt("sauvegarde_duree_music", 0);

        this.configureToolbar();
        this.configureUI();

        this.seekBar.setOnSeekBarChangeListener(seekBarCustomListner);
        btn_dodo.setOnClickListener(viewOnClickListenerCustom);
    }

    //region FUNCTIONS
    private void configureToolbar(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

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

    private void configureUI(){
        seekBar.setProgress(duree_recup);
        this.temps.setText(getResources().getString(R.string.timer) +' '+ seekBar.getProgress() + getResources().getString(R.string.minute));

    }

    //endregion
}
