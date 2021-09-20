package com.example.appnumbershapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public class checkNumber {

        int c = 2, d,e=0;
        int check;
        int b;

        public int checkNumber() {
            for (int i = 1; i <= b; c++)
            {
                if (b == i)
                {
                    check= 1;
                    e++;
                    break;
                }
                else
                    {
                    i += c;
                }
            }

            for (int j = 1; j <= b / 2; j++) {
                d = j * j;
                if (d == b) {
                    check = 2;
                    e++;
                    break;
                }
            }
            if(e==2)
            {
                check=3;
            }
            return check;
        }
    }
        public void check(View view)
        {
            checkNumber a=new checkNumber();
            EditText b=(EditText) findViewById(R.id.number);
            int c = Integer.parseInt(b.getText().toString());
            a.b=c;
            int checknum = a.checkNumber();
            if(checknum==1)
            {
                Toast.makeText(this, c+" is a triangle number", Toast.LENGTH_LONG).show();
            }
            else if(checknum==2)
            {
                Toast.makeText(this, c+" is a square number", Toast.LENGTH_LONG).show();
            }
            else if(checknum==3)
            {
                Toast.makeText(this, c+ " is both triangle and square number", Toast.LENGTH_LONG).show();
            }
            else
            {
                    Toast.makeText(this, c+" is neither triangle nor square number", Toast.LENGTH_LONG).show();
            }

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
