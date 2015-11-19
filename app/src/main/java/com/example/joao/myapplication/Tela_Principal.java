package com.example.joao.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Tela_Principal extends Activity {

    MediaPlayer mp;
    MediaPlayer bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);
        mp = MediaPlayer.create(Tela_Principal.this, R.raw.play_sound);
//        bg = MediaPlayer.create(Tela_Principal.this, R.raw.intro);
//        bg.start();
//        bg.setLooping(true);
//        bg.setVolume(100,100);
//        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
//        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void botaoPlay(View v){
        Intent i = new Intent();
        i.setClass(this, Tela_opcoes.class);
        startActivity(i);
        mp.start();
    }
}
