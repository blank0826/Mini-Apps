package com.example.higherorlower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public void generateRandomNumber()
    {
        Random rand = new Random();
        n=rand.nextInt(20)+1;

    }
    int n;

    public void guess(View view){

        EditText editText=(EditText) findViewById(R.id.number);
        int i = Integer.parseInt(editText.getText().toString());
        String message;
        if(i>n)
        {
            message="Lower!";
        }
        else if(i<n)
        {
         message="Higher";
        }
        else
        {
            message="You got it right! Guess again";
            generateRandomNumber();
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
generateRandomNumber();

    }
}
