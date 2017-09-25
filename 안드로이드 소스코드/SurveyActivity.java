package com.example.gandi.votingapp;

import android.app.Activity;
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

import static com.example.gandi.votingapp.R.layout.activity_survey;


/**
 * Created by gandi on 2017-05-25.
 */

public class SurveyActivity extends Activity{

    String myJSON;

    private static final String TAG_RESULTS="result";
    public static final String SURVEY_NUMBER_2 = "SURVEYNUMBER";
    private static final String SURVEY_NUMBER = "survey_id";
    private static final String SURVEY_NAME = "survey_name";
    private static final String SURVEY_SHORT_CONTENT ="survey_short_content";

    JSONArray surveys = null;

    ArrayList<HashMap<String, String>> surveyList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(activity_survey);

        list = (ListView) findViewById(R.id.surveylist);
        surveyList = new ArrayList<HashMap<String,String>>();
        getData("http://voteapp.iptime.org/getlistview_survey.php");
    }

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            surveys = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<surveys.length();i++){
                JSONObject c = surveys.getJSONObject(i);
                String number = c.getString(SURVEY_NUMBER);
                String name = c.getString(SURVEY_NAME);
                String content = c.getString(SURVEY_SHORT_CONTENT);

                HashMap<String,String> surveys = new HashMap<String,String>();

                surveys.put(SURVEY_NUMBER, number);
                surveys.put(SURVEY_NAME, name);
                surveys.put(SURVEY_SHORT_CONTENT, content);

                surveyList.add(surveys);
            }

            ListAdapter adapter = new SimpleAdapter(
                    SurveyActivity.this, surveyList, R.layout.list_item_survey,
                    new String[]{SURVEY_NUMBER,SURVEY_NAME,SURVEY_SHORT_CONTENT},
                    new int[]{R.id.surveynumber, R.id.surveyname, R.id.surveycontent}
            );

            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if(position == 0) {
                        Intent intent = new Intent(
                                getApplicationContext(),
                                SurveylistActivity.class);
                        intent.putExtra(SURVEY_NUMBER_2,"1");
                        startActivity(intent);
                    }
                    if(position == 1) {
                        Intent intent = new Intent(
                                getApplicationContext(),
                                SurveylistActivity.class);
                        intent.putExtra(SURVEY_NUMBER_2,"2");
                        startActivity(intent);
                    }
                    if(position == 2) {
                        Intent intent = new Intent(
                                getApplicationContext(),
                                SurveylistActivity.class);
                        intent.putExtra(SURVEY_NUMBER_2,"3");
                        startActivity(intent);
                    }
                    if(position == 3) {
                        Intent intent = new Intent(
                                getApplicationContext(),
                                SurveylistActivity.class);
                        intent.putExtra(SURVEY_NUMBER_2,"4");
                        startActivity(intent);
                    }
                    if(position == 4) {
                        Intent intent = new Intent(
                                getApplicationContext(),
                                SurveylistActivity.class);
                        intent.putExtra(SURVEY_NUMBER_2,"5");
                        startActivity(intent);
                    }
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String> {

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
