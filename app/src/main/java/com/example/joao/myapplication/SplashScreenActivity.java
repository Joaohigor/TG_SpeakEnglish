package com.example.joao.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Joao on 10/16/15.
 */
public class SplashScreenActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Timer().schedule(new TimerTask(){
            @Override
            public void run(){
                finish();

                Intent intent = new Intent();
                intent.setClass(SplashScreenActivity.this, Tela_Principal.class);
                startActivity(intent);
            }
        },3000);
    }
}
