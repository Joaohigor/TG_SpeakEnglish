package com.example.joao.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class Tela_opcoes extends Activity {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_opcoes);
        mp = MediaPlayer.create(Tela_opcoes.this, R.raw.play_sound);
    }
    public void botaoVoltar(View v){
        finish();
//        Intent i = new Intent();
//        i.setClass(this, Tela_Principal.class);
//        startActivity(i);
        mp.start();
    }
    public void botaoAnimal(View v){
        Intent i = new Intent();
        i.setClass(this, Tela_figuras.class);
        i.putExtra("prefix", "an_");
        startActivity(i);
    }
    public void botaoFruta(View v){
        Intent i = new Intent();
        i.setClass(this, Tela_figuras.class);
        i.putExtra("prefix","fr_");
        startActivity(i);
    }
    public void botaoCor(View V){
        Intent i = new Intent();
        i.setClass(this, Tela_figuras.class);
        i.putExtra("prefix","co_");
        startActivity(i);
    }
}
