package com.example.gandi.votingapp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.app.ProgressDialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;


public class Gongyak1Activity extends AppCompatActivity {


    private static final String TAG_RESULTS="result";
    private static final String TAG_NAME = "name";
    private static final String TAG_INFO = "info";
    private static final String TAG_PROMISE ="promise";
    public static final String CANDI_NUMBER = "CANDINUMBER";




    public static final String baseURL = "http://voteapp.iptime.org/1.png";
    private ProgressDialog progressDialog;

    ImageView imageView;
    TextView candi1_textview1;
    TextView candi1_textview2;
    TextView candi1_textview3;

    TextView candi2_textview1;
    TextView candi2_textview2;
    TextView candi2_textview3;

    TextView promiss;

    String candi_number;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gongyak1);
        imageView = (ImageView) findViewById(R.id.promiss);
        candi1_textview1 = (TextView) findViewById(R.id.candi1_name);
        candi1_textview2 = (TextView) findViewById(R.id.candi1_department);
        candi1_textview3 = (TextView) findViewById(R.id.candi1_info);

        candi2_textview1 = (TextView) findViewById(R.id.candi2_name);
        candi2_textview2 = (TextView) findViewById(R.id.candi2_department);
        candi2_textview3 = (TextView) findViewById(R.id.candi2_info);

        promiss = (TextView) findViewById(R.id.promi);

        Intent intent = getIntent();
        candi_number = intent.getStringExtra(VoteActivity.CANDI_NUMBER);

        setCandiInfo(candi_number);
        setCandiInfo2(candi_number);
        setCandiInfo3(candi_number);

        setCandiInfo4(candi_number);
        setCandiInfo5(candi_number);
        setCandiInfo6(candi_number);

        setCandiInfo7(candi_number);

        Thread mThread = new Thread() {
            public void run(){
                try {
                    URL url = new URL(baseURL);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);


                }
                catch(IOException ex) {

                }
            }
        };

        mThread.start();

        try {
            mThread.join();
            imageView.setImageBitmap(bitmap);
        } catch (InterruptedException e) {

        }
    }

    private void setCandiInfo(String candi_number) {

        class CandiInfo extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Gongyak1Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String candi_number = (String)params[0];

                    String link="http://voteapp.iptime.org/candi_1.php";
                    String data  = URLEncoder.encode("candi_number", "UTF-8") + "=" + URLEncoder.encode(candi_number, "UTF-8");


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
                candi1_textview1.setText(s);

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }


        }
        CandiInfo task = new CandiInfo();
        task.execute(candi_number);
    }

    private void setCandiInfo2(String candi_number) {

        class CandiInfo extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Gongyak1Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String candi_number = (String)params[0];

                    String link="http://voteapp.iptime.org/candi1_1.php";
                    String data  = URLEncoder.encode("candi_number", "UTF-8") + "=" + URLEncoder.encode(candi_number, "UTF-8");


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
                candi1_textview2.setText(s);

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }


        }
        CandiInfo task = new CandiInfo();
        task.execute(candi_number);
    }

    private void setCandiInfo3(String candi_number) {

        class CandiInfo extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Gongyak1Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String candi_number = (String)params[0];

                    String link="http://voteapp.iptime.org/candi1_2.php";
                    String data  = URLEncoder.encode("candi_number", "UTF-8") + "=" + URLEncoder.encode(candi_number, "UTF-8");


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
                candi1_textview3.setText(s);

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }


        }
        CandiInfo task = new CandiInfo();
        task.execute(candi_number);
    }

    private void setCandiInfo4(String candi_number) {

        class CandiInfo extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Gongyak1Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String candi_number = (String)params[0];

                    String link="http://voteapp.iptime.org/candi_2.php";
                    String data  = URLEncoder.encode("candi_number", "UTF-8") + "=" + URLEncoder.encode(candi_number, "UTF-8");


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
                candi2_textview1.setText(s);

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }


        }
        CandiInfo task = new CandiInfo();
        task.execute(candi_number);
    }

    private void setCandiInfo5(String candi_number) {

        class CandiInfo extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Gongyak1Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String candi_number = (String)params[0];

                    String link="http://voteapp.iptime.org/candi2_1.php";
                    String data  = URLEncoder.encode("candi_number", "UTF-8") + "=" + URLEncoder.encode(candi_number, "UTF-8");


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
                candi2_textview2.setText(s);

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }


        }
        CandiInfo task = new CandiInfo();
        task.execute(candi_number);
    }

    private void setCandiInfo6(String candi_number) {

        class CandiInfo extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Gongyak1Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String candi_number = (String)params[0];

                    String link="http://voteapp.iptime.org/candi2_2.php";
                    String data  = URLEncoder.encode("candi_number", "UTF-8") + "=" + URLEncoder.encode(candi_number, "UTF-8");


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
                candi2_textview3.setText(s);

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }


        }
        CandiInfo task = new CandiInfo();
        task.execute(candi_number);
    }

    private void setCandiInfo7(String candi_number) {

        class CandiInfo extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Gongyak1Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String candi_number = (String)params[0];

                    String link="http://voteapp.iptime.org/candi_promiss.php";
                    String data  = URLEncoder.encode("candi_number", "UTF-8") + "=" + URLEncoder.encode(candi_number, "UTF-8");


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
                promiss.setText(s);

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }


        }
        CandiInfo task = new CandiInfo();
        task.execute(candi_number);
    }





}
