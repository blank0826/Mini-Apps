package com.example.basicphrases;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void speak (View view)
    {
        Button button=(Button)view;
        MediaPlayer media = MediaPlayer.create(this, getResources().getIdentifier(button.getTag().toString(), "raw",getPackageName()));
        media.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
