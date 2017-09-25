package com.example.gandi.votingapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by gandi on 2017-06-11.
 */



public class SuggestActivity extends Activity {
    private EditText suggest_content;
    public static final String USER_NAME = "USERNAME";

    String studentid;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);

        Intent intent = getIntent();
        final String username = intent.getStringExtra(MenuActivity.USER_NAME);
        studentid = username;

        suggest_content = (EditText) findViewById(R.id.suggest_content);

    }

    public void suggest_insert(View view) {
        new AlertDialog.Builder(this)
                .setTitle("경고!").setMessage("정말 건의하시겠습니까?.")
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast.makeText(SuggestActivity.this, "건의가 취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String suggest_contents  = suggest_content.getText().toString();
                        insertToDatabase(studentid, suggest_contents);
                    }
                })
                .show();

    }

    private void insertToDatabase(String studentid, String suggest_content) {

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            AlertDialog warning;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SuggestActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String studentid = (String)params[0];
                    String suggest_content = (String)params[1];

                    String link="http://voteapp.iptime.org/suggest.php";
                    String data  = URLEncoder.encode("studentid", "UTF-8") + "=" + URLEncoder.encode(studentid, "UTF-8");
                    data += "&" + URLEncoder.encode("suggest_content", "UTF-8") + "=" + URLEncoder.encode(suggest_content, "UTF-8");

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

                    return sb.toString();
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }

            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                loading.dismiss();

                String s = result.trim();

                if(s.equalsIgnoreCase("success")) {
                    new AlertDialog.Builder(SuggestActivity.this)
                            .setTitle("확인").setMessage("건의가 완료되었습니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intent = new Intent(SuggestActivity.this, MenuActivity.class);
                                    finish();
                                    startActivity(intent);
                                }
                            })
                            .show();
                }
                else if(s.equalsIgnoreCase("failed")) {
                    new AlertDialog.Builder(SuggestActivity.this)
                            .setTitle("확인").setMessage("건의 전송에 실패하였습니다..\n 관리자에게 문의바랍니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                }
                            })
                            .show();
                }
            }
        }

        InsertData task = new InsertData();
        task.execute(studentid, suggest_content);
    }

}
