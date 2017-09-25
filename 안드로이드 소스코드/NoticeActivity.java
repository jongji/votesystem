package com.example.gandi.votingapp;

import android.app.Activity;

/**
 * Created by gandi on 2017-03-23.
 */


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import android.widget.SimpleAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


import static com.example.gandi.votingapp.R.layout.activity_notice;

public class NoticeActivity extends Activity {


    String myJSON;

    private static final String TAG_RESULTS="result";
    private static final String NOTICE_NUMBER = "notice_id";
    private static final String NOTICE_NAME = "notice_name";
    private static final String NOTICE_CONTENT ="notice_content";

    JSONArray notices = null;

    ArrayList<HashMap<String, String>> noticeList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_notice);

        list = (ListView) findViewById(R.id.ListView);
        noticeList = new ArrayList<HashMap<String,String>>();
        getData("http://voteapp.iptime.org/getlistview_notice.php");
    }

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            notices = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<notices.length();i++){
                JSONObject c = notices.getJSONObject(i);
                String number = c.getString(NOTICE_NUMBER);
                String name = c.getString(NOTICE_NAME);
                String content = c.getString(NOTICE_CONTENT);

                HashMap<String,String> notices = new HashMap<String,String>();

                notices.put(NOTICE_NUMBER, number);
                notices.put(NOTICE_NAME, name);
                notices.put(NOTICE_CONTENT, content);

                noticeList.add(notices);
            }

            ListAdapter adapter = new SimpleAdapter(
                    NoticeActivity.this, noticeList, R.layout.list_item_notice,
                    new String[]{NOTICE_NUMBER,NOTICE_NAME,NOTICE_CONTENT},
                    new int[]{R.id.number, R.id.name, R.id.content}
            );

            list.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String>{

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }



            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}
