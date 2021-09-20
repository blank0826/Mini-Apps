package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

     Button go;
     int locationOfCorrectAnswer;
ArrayList<Integer>answers=new ArrayList<Integer>();
TextView tv,sum,timer;
TextView scoreTextView;
int score=0,attempt=0;
Random rand;
Button playAgain;
Button opt1,opt2,opt3,opt4;
ConstraintLayout gameLayout;


    public void chooseAnswer(View view)
    {
        tv.setVisibility(View.VISIBLE);
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString()))
        {
        tv.setText("Correct!! :)");
        score++;
        }
        else{
            tv.setText("Incorrect!! :(");
        }
        attempt++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(attempt));
        newQuestion();
    }
    public void start(View view){
        go.setVisibility(View.INVISIBLE);

        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgain));
    }

    public void playAgain(View view)
    {
        score=0;
        attempt=0;
        timer.setText("30s");
        playAgain.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);
        opt1.setClickable(true);
        opt2.setClickable(true);
        opt3.setClickable(true);
        opt4.setClickable(true);
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(attempt));
        newQuestion();
        new CountDownTimer(30100,100){

            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                tv.setText("Done!");
                playAgain.setVisibility(View.VISIBLE);
                opt1.setClickable(false);
                opt2.setClickable(false);
                opt3.setClickable(false);
                opt4.setClickable(false);

            }
        }.start();
    }

    public void newQuestion()
    {
        int a = rand.nextInt(21);
        int b= rand.nextInt(21);

        sum.setText(Integer.toString(a)+" + "+Integer.toString(b)+"?");
        locationOfCorrectAnswer=rand.nextInt(4);
        answers.clear();

        for(int i=0;i<4;i++) {
            if(i==locationOfCorrectAnswer)
            {
                answers.add(a+b);
            }
            else
            {
                int wrongAnswer=rand.nextInt(41);
                while(wrongAnswer==(a+b))
                {
                    wrongAnswer=rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        opt1.setText(Integer.toString(answers.get(0)));
        opt2.setText(Integer.toString(answers.get(1)));
        opt3.setText(Integer.toString(answers.get(2)));
        opt4.setText(Integer.toString(answers.get(3)));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go=findViewById(R.id.start);
        rand= new Random();
        sum=findViewById(R.id.sum);
        opt1=findViewById(R.id.opt1);
        opt2=findViewById(R.id.opt2);
        opt3=findViewById(R.id.opt3);
        opt4=findViewById(R.id.opt4);
        tv=findViewById(R.id.startLabel);
        scoreTextView=findViewById(R.id.score);
        timer=findViewById(R.id.timer);
        playAgain=findViewById(R.id.playAgain);
        go.setVisibility(View.VISIBLE);
        gameLayout=findViewById(R.id.gameLayout);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}
