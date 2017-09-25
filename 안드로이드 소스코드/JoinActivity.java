package com.example.gandi.votingapp;

/**
 * Created by gandi on 2017-03-19.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//import net.simplifiedcoding.androidloginapp.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.regex.Pattern;

public class JoinActivity extends Activity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextStudentID;
    private EditText editTextPassword_check;
    private EditText editTextemail;

    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        editTextUsername = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextStudentID = (EditText) findViewById(R.id.editTextStudentID);
        editTextPassword_check = (EditText) findViewById(R.id.editTextPassword_check);
        editTextemail = (EditText) findViewById(R.id.editTextemail);
    }

    public void checkDB(View view) {
        String studentid = editTextStudentID.getText().toString();
        String username = editTextUsername.getText().toString();
        String email = editTextemail.getText().toString();

        if(studentid.length() == 0 || username.length() == 0 || email.length() == 0) {
            if(studentid.length() == 0) {
                new AlertDialog.Builder(this)
                        .setTitle("경고!").setMessage("학번이 입력되지 않았습니다.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();
            }
            else if(username.length() == 0) {
                new AlertDialog.Builder(this)
                        .setTitle("경고!").setMessage("이름이 입력되지 않았습니다.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();
            }
            else if(email.length() == 0) {
                new AlertDialog.Builder(this)
                        .setTitle("경고!").setMessage("이메일이 입력되지 않았습니다.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();
            }
        }
        else if(check > 0) {
            new AlertDialog.Builder(this)
                    .setTitle("경고!").setMessage("이미 인증하셨습니다.")
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    })
                    .show();
        }

        else {
            CheckDatabase(studentid, username, email);
        }

    }

    private void CheckDatabase(String studentid, String username, String email) {

        class CheckData extends AsyncTask<String, Void, String>{
            ProgressDialog loading;
            AlertDialog warning;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(JoinActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String studentid = (String)params[0];
                    String username = (String)params[1];
                    String email = (String)params[2];

                    String link="http://voteapp.iptime.org/app/app_join/check_db.php";
                    String data  = URLEncoder.encode("studentid", "UTF-8") + "=" + URLEncoder.encode(studentid, "UTF-8");
                    data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                    data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

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
                    new AlertDialog.Builder(JoinActivity.this)
                            .setTitle("확인").setMessage("인증되었습니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    check++;
                                }
                            })
                            .show();
                }
                else if(s.equalsIgnoreCase("failed")) {
                    new AlertDialog.Builder(JoinActivity.this)
                            .setTitle("경고!").setMessage("등록되지 않은 정보입니다.\n 다시 확인해보시거나, 관리자에게 문의바랍니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                }
                            })
                            .show();
                }
            }
        }

        CheckData task = new CheckData();
        task.execute(studentid, username, email);
    }

    public void insert(View view){
        String studentid = editTextStudentID.getText().toString();
        String username = editTextUsername.getText().toString();
        String email = editTextemail.getText().toString();
        String password = editTextPassword.getText().toString();
        String password_check = editTextPassword_check.getText().toString();

        if(check == 0 )
        {
            new AlertDialog.Builder(this)
                    .setTitle("경고!").setMessage("학생 인증을 먼저 해주세요!!")
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    })
                    .show();
        }

        else {
            if(!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", password)) {
                new AlertDialog.Builder(this)
                        .setTitle("경고!").setMessage("비밀번호 형식을 맞춰주세요.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();
            }

            else {
                if(password.equalsIgnoreCase(password_check)) {
                    insertToDatabase(studentid, username, password);
                }
                else {
                    new AlertDialog.Builder(this)
                            .setTitle("경고!").setMessage("비밀번호륻 다시 확인해주세요.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                }
                            })
                            .show();
                }
            }


        }



    }



    private void insertToDatabase(String studentid, String username, String password) {

        class InsertData extends AsyncTask<String, Void, String>{
            ProgressDialog loading;
            AlertDialog warning;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(JoinActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String studentid = (String)params[0];
                    String username = (String)params[1];
                    String password = (String)params[2];

                    String link="http://voteapp.iptime.org/app/app_join/join.php";
                    String data  = URLEncoder.encode("studentid", "UTF-8") + "=" + URLEncoder.encode(studentid, "UTF-8");
                    data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

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
                    new AlertDialog.Builder(JoinActivity.this)
                            .setTitle("확인").setMessage("회원가입이 완료되었습니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intent = new Intent(JoinActivity.this, MainActivity.class);
                                    finish();
                                    startActivity(intent);
                                }
                            })
                            .show();
                }
                else if(s.equalsIgnoreCase("failed")) {
                    new AlertDialog.Builder(JoinActivity.this)
                            .setTitle("확인").setMessage("회원가입에 실패하셨습니다.\n 관리자에게 문의바랍니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                }
                            })
                            .show();
                }
            }
        }

        InsertData task = new InsertData();
        task.execute(studentid, username, password);
    }
}
