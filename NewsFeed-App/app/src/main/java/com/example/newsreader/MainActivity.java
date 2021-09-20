package com.example.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.mbms.DownloadProgressListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

ArrayList<String> titles = new ArrayList<>();
ArrayList<String> content = new ArrayList<>();
ArrayAdapter arrayAdapter;

SQLiteDatabase articlesDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        articlesDB = this.openOrCreateDatabase("Articles", MODE_PRIVATE, null);

        articlesDB.execSQL("CREATE TABLE IF NOT EXISTS articles (id INTEGER PRIMARY KEY, articleId, INTEGER, articleTitle VARCHAR, articleContent VARCHAR)");


        DownloadTask task = new DownloadTask();
        try {

           //task.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");

        } catch (Exception e) {

        }


        ListView listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), webViewActivity.class);
                intent.putExtra("content", content.get(i));

                startActivity(intent);
            }
        });

        updateListView();

    }

    public void updateListView()
    {
        Cursor c = articlesDB.rawQuery("SELECT * FROM articles", null);

        int contentIndex = c.getColumnIndex("articleContent");
        int titleIndex = c.getColumnIndex("articleTitle");

        if (c.moveToFirst()) {
            titles.clear();
            content.clear();

            do {

                titles.add(c.getString(titleIndex));
                content.add(c.getString(contentIndex));

            } while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
        }

    }

    public class DownloadTask extends AsyncTask<String, Void,String>
    {

        @Override
        protected String doInBackground(String... urls) {
            String result="";
            URL url;
            HttpURLConnection connection= null;
            try{
                url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream in =connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while(data!=-1)
                {
                    char current = (char) data;
                    result+= current;
                    data=reader.read();
                }

                JSONArray jsonArray = new JSONArray(result);
                int numberOfItems = 20;

                if(jsonArray.length()<20)
                {
                    numberOfItems = jsonArray.length();
                }

                articlesDB.execSQL("DELETE FROM articles");

                for(int i=0;i<numberOfItems;i++)
                {
                    String articleId = jsonArray.getString(i);
                    url = new URL("https://hacker-news.firebaseio.com/v0/item/"+articleId+".json?print=pretty");
                    connection = (HttpURLConnection) url.openConnection();
                    in =connection.getInputStream();
                    reader = new InputStreamReader(in);
                    data = reader.read();
                    String articleInfo="";
                    while(data!=-1)
                    {
                        char current = (char) data;
                        articleInfo += current;
                        data=reader.read();
                    }
                    JSONObject jsonObject = new JSONObject(articleInfo);
                    if(!jsonObject.isNull("title")&&!jsonObject.isNull("url"))
                    {
                        String articleTitle=jsonObject.getString("title");
                        String Url=jsonObject.getString("url");

                        url = new URL(Url);
                        connection = (HttpURLConnection) url.openConnection();
                        in = connection.getInputStream();
                        reader = new InputStreamReader(in);
                        data = reader.read();
                        String articleContent = "";
                        while(data!=-1)
                        {
                            char current = (char) data;
                            articleContent+= current;
                            data = reader.read();
                        }
                        String sql = "INSERT INTO articles(articleId,articleTitle,articleContent) VALUES (?,?,?)";
                        SQLiteStatement statement = articlesDB.compileStatement(sql);
                        statement.bindString(1, articleId);
                        statement.bindString(2, articleTitle);
                        statement.bindString(3, articleContent);

                        statement.execute();
                    }

                }
                return result;
            }catch(Exception e)
            {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            updateListView();
        }
    }
}
