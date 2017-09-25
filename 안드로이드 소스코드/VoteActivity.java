package com.example.gandi.votingapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.gandi.votingapp.R.layout.activity_vote;

public class VoteActivity extends Activity {

    TextView textView;


    String myJSON;

    public static final String USER_NAME = "USERNAME";
    public static final String CANDI_NUMBER = "CANDINUMBER";
    private static final String TAG_RESULTS="result";
    private static final String TAG_NAME = "name";
    private static final String TAG_SDATE = "sdate";
    private static final String TAG_EDATE ="edate";
    private String data = null;

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;

    String user_name;

    String candi_number;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_vote);
        textView = (TextView) findViewById(R.id.time);
        Time_Check();

        Intent intent = getIntent();
        user_name = intent.getStringExtra(MenuActivity.USER_NAME);

        Button b1 = (Button) findViewById(R.id.votebutton);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Vote2Activity.class);
                intent.putExtra(USER_NAME,user_name);
                startActivity(intent);
            }
        });

        list = (ListView) findViewById(R.id.ListView);
        personList = new ArrayList<HashMap<String, String>>();
        getData("http://voteapp.iptime.org/getlistview_vote.php");
    }


    private void Time_Check() {

        class timecheck extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(VoteActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {



                try{

                    String link="http://voteapp.iptime.org/time_check.php";

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    String result = null;

                    // Read Server Response
                    while((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    result = sb.toString();

                    return result;
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());

                }
            }

            @Override
            protected void onPostExecute(String result) {
                //super.onPostExecute(result);
                loading.dismiss();

                String s = result.trim();
                System.out.println(s);
                textView.setText(s);


                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }


        }
        timecheck task = new timecheck();
        task.execute();
    }




    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String name = c.getString(TAG_NAME);
                String sdate = c.getString(TAG_SDATE);
                String edate = c.getString(TAG_EDATE);

                HashMap<String,String> persons = new HashMap<String,String>();

                persons.put(TAG_NAME, name);
                persons.put(TAG_SDATE, sdate);
                persons.put(TAG_EDATE, edate);

                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    VoteActivity.this, personList, R.layout.list_item,
                    new String[]{TAG_NAME,TAG_SDATE,TAG_EDATE},
                    new int[]{R.id.name, R.id.sdate, R.id.edate}
            );

            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if(position == 0) {
                        Intent intent = new Intent(
                            getApplicationContext(),
                            Gongyak1Activity.class);
                        intent.putExtra(CANDI_NUMBER,"1번");
                        startActivity(intent);
                    }
                    if(position == 1) {
                        Intent intent = new Intent(
                            getApplicationContext(),
                            Gongyak1Activity.class);
                        intent.putExtra(CANDI_NUMBER,"2번");
                        startActivity(intent);
                    }
                    if(position == 2) {
                        Intent intent = new Intent(
                                getApplicationContext(),
                                Gongyak1Activity.class);
                        intent.putExtra(CANDI_NUMBER,"3번");
                        startActivity(intent);
                    }
                    if(position == 3) {
                        Intent intent = new Intent(
                                getApplicationContext(),
                                Gongyak1Activity.class);
                        intent.putExtra(CANDI_NUMBER,"4번");
                        startActivity(intent);
                    }
                    if(position == 4) {
                        Intent intent = new Intent(
                                getApplicationContext(),
                                Gongyak1Activity.class);
                        intent.putExtra(CANDI_NUMBER,"5번");
                        startActivity(intent);
                    }
                }
            });

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