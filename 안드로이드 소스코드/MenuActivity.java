package com.example.gandi.votingapp;

/**
 * Created by gandi on 2017-03-19.
 */

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class MenuActivity extends ActionBarActivity {


    String username2;
    public static final String USER_NAME = "USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        final String username = intent.getStringExtra(MainActivity.USER_NAME);
        username2 = username;


        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText("login ID : " + username2);


        Button b1 = (Button) findViewById(R.id.notice);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
                startActivity(intent);
            }
        });


        Button b2 = (Button) findViewById(R.id.vote);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Vote_check(username2);
            }
        });

        Button b3 = (Button) findViewById(R.id.survey);
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SurveyActivity.class);
                startActivity(intent);
            }
        });

        Button b4 = (Button) findViewById(R.id.suggest);
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SuggestActivity.class);
                intent.putExtra(USER_NAME, username2);
                startActivity(intent);
            }
        });
}

    public void onBackPressed(){

    }

    private void Vote_check(final String user_name) {

                class votecheck extends AsyncTask<String, Void, String> {
                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(MenuActivity.this, "잠시만 기다려 주세요..", "Loading...");
                    }

                    @Override
                    protected String doInBackground(String... params) {
                        try{
                            String user_name = (String)params[0];

                            String link="http://voteapp.iptime.org/vote_check.php";
                            String data  = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8");

                            URL url = new URL(link);
                            URLConnection conn = url.openConnection();
                            conn.setDoOutput(true);
                            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                            wr.write( data );
                            wr.flush();

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
                if(s.equalsIgnoreCase("vote_no")){
                    Intent intent = new Intent(MenuActivity.this, VoteActivity.class);
                    intent.putExtra(USER_NAME, user_name);
                    startActivity(intent);
                } else if(s.equalsIgnoreCase("overtime")) {
                    new AlertDialog.Builder(MenuActivity.this)
                            .setTitle("경고").setMessage("투표 가능한 시간이 아닙니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                }
                            })
                            .show();

                }
                else {
                    Intent intent = new Intent(MenuActivity.this, VotecomActivity.class);
                    intent.putExtra(USER_NAME, user_name);
                    startActivity(intent);
                }
                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }


        }
        votecheck task = new votecheck();
        task.execute(user_name);
    }

    public void btn_logout(View v) {
        new AlertDialog.Builder(this)
                .setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent i = new Intent(MenuActivity.this, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(i);
                        finish();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                })
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}