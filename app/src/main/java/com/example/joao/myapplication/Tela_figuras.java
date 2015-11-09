package com.example.joao.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;

public class Tela_figuras extends Activity {

    private final int REQ_CODE_SPEECH_INPUT = 100;
    private ImageButton btnSpeak;
    private ImageAdapter adapter;
    private int lastPage;
    private String atual;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_figuras);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new ImageAdapter(this, getImages("an_"));
        viewPager.setAdapter(adapter);
        this.pos = 0;
        this.atual = adapter.drawables.get(pos).name;
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                if (lastPage > arg0) {
                    pos--;
                    atual = adapter.drawables.get(pos).name;
                    Log.i("Script", atual + " " + pos);
                } else if (lastPage < arg0) {
                    pos++;
                    atual = adapter.drawables.get(pos).name;
                    Log.i("Script", atual + " " + pos);
                }
                lastPage = arg0;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
    }

    public void btnSpeak(View v) {
        promptSpeechInput();
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String text;
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    text = result.get(0);
                    if (answerIsCorrect(text.toLowerCase())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Congratulations!!")
                                .setCancelable(true);
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Errouuuuuu!!").setCancelable(true);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
                break;
            }
        }
    }

    public ArrayList<Image> getImages(String prefix) {
        Field[] drawables = R.drawable.class.getFields();
        ArrayList<Image> imgs = new ArrayList<>();
        for (Field f : drawables) {
            try {
                if (f.getName().startsWith(prefix)) {
                    int id = this.getBaseContext().getResources().getIdentifier(f.getName(), "drawable", this.getBaseContext().getPackageName());
                    imgs.add(new Image(id, f.getName().substring(3)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return imgs;
    }

    public boolean answerIsCorrect(String name) {
        return this.atual.equals(name);
    }

}