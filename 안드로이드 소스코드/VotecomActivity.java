package com.example.gandi.votingapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;


/**
 * Created by gandi on 2017-04-28.
 */


public class VotecomActivity extends Activity {

    TextView textView;
    TextView textView2;
    TextView textView3;

    int all_vote_people;
    String candi1VotePeople = null;

    double pie1;
    int pie_candi1;
    double pie2_1;
    double pie3;

    int pie_candi2;
    double pie2_2;
    double pie3_2;

    int pie_candi3;
    double pie2_3;
    double pie3_3;

    int pie_candi4;
    double pie2_4;
    double pie3_4;

    int pie_candi5;
    double pie2_5;
    double pie3_5;

    int pie_candif;
    double pie2_f;
    double pie3_f;

    double[] pieChartValues = new double[6];
    public static final String TYPE = "type";
    //각 계열(Series)의 색상
    private static int[] COLORS = new int[]{Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.CYAN, Color.DKGRAY};



    //각 계열의 타이틀
    String[] mSeriesTitle = new String[] {"기호 1번", "기호 2번", "기호 3번", "기호 4번", "기호 5번", "기권" };


    private CategorySeries mSeries = new CategorySeries("계열");

    private DefaultRenderer mRenderer = new DefaultRenderer();

    private GraphicalView mChartView;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votecom);
        textView = (TextView) findViewById(R.id.text1);
        textView2 = (TextView) findViewById(R.id.text2);
        textView3 = (TextView) findViewById(R.id.text3);
        setVotePeople();
        setVotePeople2();
        setVotePeople3();

        setVotePeople_Number();
        setVotePeople2_Number();
        setVotePeople3_Number();
        setVotePeople4_Number();
        setVotePeople5_Number();
        setVotePeopleF_Number();

        // 웹으로부터 데이터를 받아오는 함수들

        //double fir = Double.parseDouble(AllVotePeople);
        //System.out.println(fir);

        /*
        int allpeople = Integer.parseInt(AllVotePeople);
        int candi1people = Integer.parseInt(candi1VotePeople);
        int rest = allpeople - candi1people;
        pie1 = (allpeople/candi1people) * 100;
        pie2 = (allpeople/rest) * 100;
        */

        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.argb(100, 50, 50, 50));
        mRenderer.setChartTitleTextSize(20);
        mRenderer.setLabelsTextSize(30);
        mRenderer.setLegendTextSize(30);
        mRenderer.setMargins(new int[]{20, 30, 15, 0});
        mRenderer.setZoomButtonsVisible(true);
        mRenderer.setStartAngle(90);

        if (mChartView == null) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.chart_pie);
            mChartView = ChartFactory.getPieChartView(this, mSeries, mRenderer);
            mRenderer.setClickEnabled(true);
            mRenderer.setSelectableBuffer(10);
            layout.addView(mChartView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT));
        } else {
            mChartView.repaint();
        }

    }

    public void fillPieChart() {

        for (int i = 0; i < pieChartValues.length; i++) {

            mSeries.add(mSeriesTitle[i] + "_" + (String.valueOf(pieChartValues[i])), pieChartValues[i]);
            //Chart에서 사용할 값, 색깔, 텍스트등을 DefaultRenderer객체에 설정

            SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();

            renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);

            mRenderer.addSeriesRenderer(renderer);
            if (mChartView != null)
                mChartView.repaint();
        }

    }

    private void setVotePeople() {

        class VotePeople extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(VotecomActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/votecom1.php";

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

                String s = result;
                textView.setText(s);

                System.out.println(s);

                if (s.startsWith("\uFEFF")) {
                    s = s.substring(1);
                }
                pie1 = Double.parseDouble(s);


                try {

                } catch (NumberFormatException e) {
                }

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }


        }
        VotePeople task = new VotePeople();
        task.execute();
    }

    private void setVotePeople2() {

        class VotePeople extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(VotecomActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/votecom1_1.php";

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
                    candi1VotePeople = result.trim();
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

                textView2.setText(s);

                if (s.startsWith("\uFEFF")) {
                    s = s.substring(1);
                }

                try {

                } catch (NumberFormatException e) {

                }
            }

        }
        VotePeople task = new VotePeople();
        task.execute();
    }

    private void setVotePeople3() {

        class VotePeople extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(VotecomActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/votecom1_2.php";

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
                textView3.setText(s);

                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }
        VotePeople task = new VotePeople();
        task.execute();
    }

    private void setVotePeople_Number() {

        class VotePeople extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(VotecomActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/votecomcandi1.php";

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



                if (s.startsWith("\uFEFF")) {
                    s = s.substring(1);
                }


                pie_candi1 = Integer.parseInt(s);
                pie3 = pie1 - pie_candi1;

                pie2_1 = (pie_candi1/pie1) * 100;

                pie2_1 = Math.round(pie2_1);

                pieChartValues[0] = pie2_1;



                try {

                } catch (NumberFormatException e) {

                }
            }

        }
        VotePeople task = new VotePeople();
        task.execute();
    }

    private void setVotePeople2_Number() {

        class VotePeople extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(VotecomActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/votecomcandi2.php";

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

                if (s.startsWith("\uFEFF")) {
                    s = s.substring(1);
                }


                pie_candi2 = Integer.parseInt(s);
                pie3_2 = pie1 - pie_candi2;

                pie2_2 = (pie_candi2/pie1) * 100;

                pie2_2 = Math.round(pie2_2);
                pieChartValues[1] = pie2_2;


                try {

                } catch (NumberFormatException e) {

                }
            }

        }
        VotePeople task = new VotePeople();
        task.execute();
    }

    private void setVotePeople3_Number() {

        class VotePeople extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(VotecomActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/votecomcandi3.php";

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

                if (s.startsWith("\uFEFF")) {
                    s = s.substring(1);
                }


                pie_candi3 = Integer.parseInt(s);
                pie3_3 = pie1 - pie_candi3;

                pie2_3 = (pie_candi3/pie1) * 100;

                pie2_3 = Math.round(pie2_3);
                pieChartValues[2] = pie2_3;

                try {

                } catch (NumberFormatException e) {

                }
            }

        }
        VotePeople task = new VotePeople();
        task.execute();
    }

    private void setVotePeople4_Number() {

        class VotePeople extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(VotecomActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/votecomcandi4.php";

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

                if (s.startsWith("\uFEFF")) {
                    s = s.substring(1);
                }


                pie_candi4 = Integer.parseInt(s);
                pie3_4 = pie1 - pie_candi4;

                pie2_4 = (pie_candi4/pie1) * 100;

                pie2_4 = Math.round(pie2_4);
                pieChartValues[3] = pie2_4;
                try {

                } catch (NumberFormatException e) {

                }
            }

        }
        VotePeople task = new VotePeople();
        task.execute();
    }

    private void setVotePeople5_Number() {

        class VotePeople extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(VotecomActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/votecomcandi5.php";

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

                if (s.startsWith("\uFEFF")) {
                    s = s.substring(1);
                }


                pie_candi5 = Integer.parseInt(s);
                pie3_5 = pie1 - pie_candi5;

                pie2_5 = (pie_candi5/pie1) * 100;

                pie2_5 = Math.round(pie2_5);
                pieChartValues[4] = pie2_5;

                try {

                } catch (NumberFormatException e) {

                }
            }

        }
        VotePeople task = new VotePeople();
        task.execute();
    }

    private void setVotePeopleF_Number() {

        class VotePeople extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(VotecomActivity.this, "잠시만 기다려 주세요..", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String link="http://voteapp.iptime.org/votecomcandi_forgive.php";

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

                if (s.startsWith("\uFEFF")) {
                    s = s.substring(1);
                }


                pie_candif = Integer.parseInt(s);
                pie3_f = pie1 - pie_candif;



                pie2_f = Math.round(pie2_f);


                all_vote_people = pie_candi1 + pie_candi2 + pie_candi3 + pie_candi4 + pie_candi5 + pie_candif;

                pie2_f = (pie_candif/pie1) * 100;

                pieChartValues[5] = pie2_f;

                String allv = Double.toString(all_vote_people);

                textView2.setText(allv);

                fillPieChart();

                try {

                } catch (NumberFormatException e) {

                }
            }

        }
        VotePeople task = new VotePeople();
        task.execute();
    }
}



