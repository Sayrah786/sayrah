package com.example.eventvoting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by SAYEEDUR on 06-02-2018.
 */

public class RegLog extends AsyncTask<String,Void,String> {
    Context ctx;
    AlertDialog alertDialog;
    String login_name;
    String name,title,start_time,end_time;
    String admin;

    RegLog(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information....");
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://192.168.1.100:10080/eventvoting/reg.php";
        String login_url = "http://192.168.1.100:10080/eventvoting/login.php";
        String addevent = "http://192.168.1.100:10080/eventvoting/addevent.php";
        String addpart = "http://192.168.1.100:10080/eventvoting/addpart.php";
        String method = params[0];
        if (method.equals("register")) {
            String name = params[1];
            String password = params[2];
            String sq = params[3];
            String sec_ans=params[4];
            String email=params[5];
            String org=params[6];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                        URLEncoder.encode("sec_que", "UTF-8") + "=" + URLEncoder.encode(sq, "UTF-8") + "&" +
                        URLEncoder.encode("sec_ans", "UTF-8") + "=" + URLEncoder.encode(sec_ans, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("org", "UTF-8") + "=" + URLEncoder.encode(org, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();




                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
                String response = "";
                String line;
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();

                return response;



                //return "Registration Success...";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if(method.equals("login"))
        {
            login_name = params[1];
            String login_pass = params[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("login_pass","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line;
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else if (method.equals("addevent")) {
            name = params[1];
            title = params[2];
            start_time = params[3];
            end_time=params[4];
            admin=params[5];

            try {
                URL url = new URL(addevent);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8") + "&" +
                        URLEncoder.encode("start", "UTF-8") + "=" + URLEncoder.encode(start_time, "UTF-8") + "&" +
                        URLEncoder.encode("end_t", "UTF-8") + "=" + URLEncoder.encode(end_time, "UTF-8") + "&" +
                        URLEncoder.encode("admin", "UTF-8") + "=" + URLEncoder.encode(admin, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();


                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
                String response = "";
                String line;
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();

                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "error";

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Registration Success..."))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(ctx, loginActivity.class);
            ctx.startActivity(myIntent);
        }
        else if (result.equals("Login Success...    "))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            Intent log = new Intent(ctx, HomeActivity.class);
            log.putExtra("admin",login_name);
            ctx.startActivity(log);
        }
        else if (result.equals("Event Add..  ")){
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(ctx, Add_ParticActivity.class);
            myIntent.putExtra("title",title);
            myIntent.putExtra("admin",admin);
            myIntent.putExtra("name",name);
            ctx.startActivity(myIntent);
        }
        else if (result.equals("Participant add Success")){
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }

    }


}