package com.example.gandi.votingapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SurveylistActivity extends AppCompatActivity {

    TextView short_content;
    TextView content;
    RadioButton choose1;
    RadioButton choose2;
    RadioButton choose3;
    RadioButton choose4;
    public static final String SURVEY_NUMBER2 = "SURVEYNUMBER";

    String surveynumber;
    String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveylist);

        choose1 = (RadioButton) findViewById(R.id.sub1);
        choose2 = (RadioButton) findViewById(R.id.sub2);
        choose3 = (RadioButton) findViewById(R.id.sub3);
        choose4 = (RadioButton) findViewById(R.id.sub4);

        Intent intent = getIntent();
        surveynumber = intent.getStringExtra(SurveyActivity.SURVEY_NUMBER_2);

        setSurvey1_1(surveynumber);
        setSurvey1_2(surveynumber);
        setSurvey1_3(surveynumber);
        setSurvey1_4(surveynumber);
        setSurvey1_5(surveynumber);
        setSurvey1_6(surveynumber);



        short_content = (TextView) findViewById(R.id.shortcontent);
        content = (TextView) findViewById(R.id.longcontent);
        Button b1 = (Button) findViewById(R.id.success);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(choose1.isChecked()) {
                    result = choose1.getText().toString();
                    insertToDatabase(result, surveynumber);
                }
                else if(choose2.isChecked()) {
                    result = choose2.getText().toString();
                    insertToDatabase(result, surveynumber);
                }
                else if(choose3.isChecked()) {
                    result = choose3.getText().toString();
                    insertToDatabase(result, surveynumber);
                }
                else if(choose4.isChecked()) {
                    result = choose4.getText().toString();
                    insertToDatabase(result, surveynumber);
                }
                else {
                    new AlertDialog.Builder(SurveylistActivity.this)
                            .setTitle("경고!!").setMessage("항목이 체크되지 않았습니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                }
                            })
                            .show();
                }
            }
        });


        Button b2 = (Button) findViewById(R.id.back);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void insertToDatabase(String data,String surveynumber) {

        class InsertData extends AsyncTask<String, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SurveylistActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }


            @Override
            protected String doInBackground(String... params) {

                try{
                    String surveydata = (String)params[0];
                    String survey_number = (String)params[1];

                    String link="http://voteapp.iptime.org/surveydata_insert.php";
                    String data  = URLEncoder.encode("survey_data", "UTF-8") + "=" + URLEncoder.encode(surveydata, "UTF-8");
                    data += "&" + URLEncoder.encode("survey_number", "UTF-8") + "=" + URLEncoder.encode(survey_number, "UTF-8");

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
                //super.onPostExecute(s);
                loading.dismiss();

                String s = result.trim();

                if(s.equalsIgnoreCase("success")) {
                    new AlertDialog.Builder(SurveylistActivity.this)
                            .setTitle("확인").setMessage("설문참여가 완료되었습니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intent = new Intent(SurveylistActivity.this, MenuActivity.class);
                                    finish();
                                    startActivity(intent);
                                }
                            })
                            .show();
                }
                else if(s.equalsIgnoreCase("fail")) {
                    new AlertDialog.Builder(SurveylistActivity.this)
                            .setTitle("확인").setMessage("설문참여에 실패하였습니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                }
                            })
                            .show();
                }
            }
        }

        InsertData task = new InsertData();
        task.execute(data,surveynumber);
    }

    private void setSurvey1_1(String survey_number) {

        class SurveyInfo extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SurveylistActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String survey_number = (String)params[0];

                    String link="http://voteapp.iptime.org/survey1_1.php";
                    String data  = URLEncoder.encode("survey_number", "UTF-8") + "=" + URLEncoder.encode(survey_number, "UTF-8");


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

                short_content.setText(s);

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        SurveyInfo task = new SurveyInfo();
        task.execute(survey_number);
    }

    private void setSurvey1_2(String survey_number) {

        class SurveyInfo extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SurveylistActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String survey_number = (String)params[0];

                    String link="http://voteapp.iptime.org/survey1_2.php";
                    String data  = URLEncoder.encode("survey_number", "UTF-8") + "=" + URLEncoder.encode(survey_number, "UTF-8");


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

                content.setText(s);

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        SurveyInfo task = new SurveyInfo();
        task.execute(survey_number);
    }

    private void setSurvey1_3(String survey_number) {

        class SurveyInfo extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SurveylistActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String survey_number = (String)params[0];

                    String link="http://voteapp.iptime.org/survey1_3.php";
                    String data  = URLEncoder.encode("survey_number", "UTF-8") + "=" + URLEncoder.encode(survey_number, "UTF-8");


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

                choose1.setText(s);

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        SurveyInfo task = new SurveyInfo();
        task.execute(survey_number);


    }

    private void setSurvey1_4(String survey_number) {

        class SurveyInfo extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SurveylistActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String survey_number = (String)params[0];

                    String link="http://voteapp.iptime.org/survey1_4.php";
                    String data  = URLEncoder.encode("survey_number", "UTF-8") + "=" + URLEncoder.encode(survey_number, "UTF-8");


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

                choose2.setText(s);

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        SurveyInfo task = new SurveyInfo();
        task.execute(survey_number);
    }

    private void setSurvey1_5(String survey_number) {

        class SurveyInfo extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SurveylistActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String survey_number = (String)params[0];

                    String link="http://voteapp.iptime.org/survey1_5.php";
                    String data  = URLEncoder.encode("survey_number", "UTF-8") + "=" + URLEncoder.encode(survey_number, "UTF-8");


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

                choose3.setText(s);

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        SurveyInfo task = new SurveyInfo();
        task.execute(survey_number);
    }

    private void setSurvey1_6(String survey_number) {

        class SurveyInfo extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SurveylistActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String survey_number = (String)params[0];

                    String link="http://voteapp.iptime.org/survey1_6.php";
                    String data  = URLEncoder.encode("survey_number", "UTF-8") + "=" + URLEncoder.encode(survey_number, "UTF-8");


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

                choose4.setText(s);

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        SurveyInfo task = new SurveyInfo();
        task.execute(survey_number);
    }


}