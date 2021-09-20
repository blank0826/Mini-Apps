package com.example.timestables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView myListView=findViewById(R.id.myListView);
        final ArrayList<String> tables=new ArrayList<String>();
        final ArrayAdapter<String> myAraayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tables);

        final SeekBar numberSeekBar=(SeekBar)findViewById(R.id.numberSeekBar);
        numberSeekBar.setMax(20);
        numberSeekBar.setProgress(10);
        for(int a=1;a<=10;a++)
            tables.add(Integer.toString(a*(10)));
        myListView.setAdapter(myAraayAdapter);

        numberSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
int min=1;
                if(min>i)
                {
                    i=min;
                    numberSeekBar.setProgress(min);
                }
tables.clear();
                for(int a=1;a<=10;a++)
                    tables.add(Integer.toString(a*(i)));
                    myListView.setAdapter(myAraayAdapter);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
