package com.example.gandi.votingapp;

/**
 * Created by gandi on 2017-05-11.
 */

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;



public class Vote2Activity extends Activity {

    int can;
    String candi_1;
    String candi_2;
    String candi_2_1;
    String candi_2_2;
    String candi_3_1;
    String candi_3_2;
    String candi_4_1;
    String candi_4_2;
    String candi_5_1;
    String candi_5_2;


    public static final String USER_NAME = "USERNAME";

    String user_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote2);

        Intent intent = getIntent();
        user_name = intent.getStringExtra(VoteActivity.USER_NAME);
        setCandi1(); setCandi2();
        setCandi3(); setCandi4();
        setCandi5(); setCandi6();
        setCandi7(); setCandi8();
        setCandi9(); setCandi10();

        candi_check();

    }

    private void candi_list() {
        final LinearLayout lm = (LinearLayout) findViewById(R.id.ll);

        // linearLayout params 정의
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);


        for (int j = 0; j <= can; j++) {

            // LinearLayout 생성
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            TextView candi_number = new TextView(this);
            TextView candi1_1 = new TextView(this);
            TextView candi1_2 = new TextView(this);

            candi_number.setTextColor(Color.parseColor("#FF7200"));


            ll.addView(candi_number);

            switch(j) {
                case 0:
                    candi_number.setText("  기  권  표  ");
                    candi1_1.setText("   정후보 : 기  권  ");
                    ll.addView(candi1_1);
                    candi1_2.setText("     부후보 : 기  권  ");
                    ll.addView(candi1_2);
                    break;
                case 1:
                    candi_number.setText("  기호 " + j + " 번 ");
                    candi1_1.setText("   정후보 : " + candi_1 + "  ");
                    ll.addView(candi1_1);
                    candi1_2.setText("   부후보 : " + candi_2 + "  ");
                    ll.addView(candi1_2);
                    break;
                case 2 :
                    candi_number.setText("  기호 " + j + " 번 ");
                    candi1_1.setText("   정후보 : " + candi_2_1 + "  ");
                    ll.addView(candi1_1);
                    candi1_2.setText("   부후보 : " + candi_2_2 + "  ");
                    ll.addView(candi1_2);
                    break;
                case 3:
                    candi_number.setText("  기호 " + j + " 번 ");
                    candi1_1.setText("   정후보 : " + candi_3_1 + "  ");
                    ll.addView(candi1_1);
                    candi1_2.setText("   부후보 : " + candi_3_2 + "  ");
                    ll.addView(candi1_2);
                    break;
                case 4:
                    candi_number.setText("  기호 " + j + " 번 ");
                    candi1_1.setText("   정후보 : " + candi_4_1 + "  ");
                    ll.addView(candi1_1);
                    candi1_2.setText("   부후보 : " + candi_4_2 + "  ");
                    ll.addView(candi1_2);
                    break;
                case 5:
                    candi_number.setText("  기호 " + j + " 번 ");
                    candi1_1.setText("   정후보 : " + candi_5_1 + "  ");
                    ll.addView(candi1_1);
                    candi1_2.setText("   부후보 : " + candi_5_2 + "  ");
                    ll.addView(candi1_2);
                    break;
            }

            // 버튼 생성
            final Button btn = new Button(this);
            // setId 버튼에 대한 키값
            btn.setId(j + 1);
            btn.setText("투표");
            btn.setLayoutParams(params);

            final int position = j;


            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.d("log", "position :" + position);
                    AlertDialog.Builder alert = new AlertDialog.Builder(Vote2Activity.this);
                    alert.setTitle("경고!").setMessage("정말 투표하시겠습니까?")
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Toast.makeText(Vote2Activity.this, "투표가 취소되었습니다.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setPositiveButton("투표하기", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    switch(position) {
                                        case 0:
                                            insertToDatabase("기권", user_name);
                                            break;
                                        case 1:
                                            insertToDatabase("1번", user_name);
                                            break;
                                        case 2:
                                            insertToDatabase("2번", user_name);
                                            break;
                                        case 3:
                                            insertToDatabase("3번", user_name);
                                            break;
                                        case 4:
                                            insertToDatabase("4번", user_name);
                                            break;
                                        case 5:
                                            insertToDatabase("5번", user_name);
                                            break;
                                    }
                                    Intent i = new Intent(Vote2Activity.this, MenuActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    i.putExtra(USER_NAME, user_name);
                                    startActivity(i);
                                }
                            })

                            .show();

                    //Toast.makeText(getApplicationContext(), "클릭한 position:" + position, Toast.LENGTH_LONG).show();


                }
            });

            //버튼 add
            ll.addView(btn);
            //LinearLayout 정의된거 add
            lm.addView(ll);
        }


    }

    private void insertToDatabase(String data, String user_name) {

        class InsertData extends AsyncTask<String, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Vote2Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }


            @Override
            protected String doInBackground(String... params) {

                try{
                    String votedata = (String)params[0];
                    String user_name = (String)params[1];

                    String link="http://voteapp.iptime.org/votedata_insert.php";
                    String data  = URLEncoder.encode("data", "UTF-8") + "=" + URLEncoder.encode(votedata, "UTF-8");
                    data += "&" + URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write( data );
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }

            }
        }

        InsertData task = new InsertData();
        task.execute(data, user_name);
    }

    private void setCandi1() {

        class setCandi extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Vote2Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{


                    String link="http://voteapp.iptime.org/candi1.php";


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

                candi_1 = s;

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        setCandi task = new setCandi();
        task.execute();
    } //기호 1

    private void setCandi2() {

        class setCandi extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Vote2Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/candi2.php";


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

                candi_2 = s;




                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        setCandi task = new setCandi();
        task.execute();
    } //기호 1

    private void setCandi3() {

        class setCandi extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Vote2Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/candi3.php";

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

                candi_2_1 = s;

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        setCandi task = new setCandi();
        task.execute();
    } //기호 2

    private void setCandi4() {

        class setCandi extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Vote2Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/candi4.php";

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

                candi_2_2 = s;

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        setCandi task = new setCandi();
        task.execute();
    } //기호 2

    private void setCandi5() {

        class setCandi extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Vote2Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/candi5.php";

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

                candi_3_1 = s;

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        setCandi task = new setCandi();
        task.execute();
    } //기호 3

    private void setCandi6() {

        class setCandi extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Vote2Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/candi6.php";

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

                candi_3_2 = s;

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        setCandi task = new setCandi();
        task.execute();
    } //기호 3

    private void setCandi7() {

        class setCandi extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Vote2Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/candi7.php";

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

                candi_4_1 = s;

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        setCandi task = new setCandi();
        task.execute();
    } //기호 4

    private void setCandi8() {

        class setCandi extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Vote2Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/candi8.php";

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

                candi_4_2 = s;

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        setCandi task = new setCandi();
        task.execute();
    } //기호 4

    private void setCandi9() {

        class setCandi extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Vote2Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/candi9.php";

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

                candi_5_1 = s;

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        setCandi task = new setCandi();
        task.execute();
    } //기호 5

    private void setCandi10() {

        class setCandi extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Vote2Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/candi10.php";

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

                candi_5_2 = s;

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        setCandi task = new setCandi();
        task.execute();
    } //기호 5

    private void candi_check() {

        class Candi_check extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Vote2Activity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/candi_check.php";

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

                if (s.startsWith("\uFEFF")) {
                    s = s.substring(1);
                }

                can = Integer.parseInt(s);

                candi_list();



                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        Candi_check task = new Candi_check();
        task.execute();
    }
}
