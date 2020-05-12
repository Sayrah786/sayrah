package com.example.eventvoting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by SAYEEDUR on 09-02-2018.
 */

public class UpdateTask extends AsyncTask<String,Void,String> {
    Context ctx;
    AlertDialog alertDialog;
    String admin;
    UpdateTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information....");
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://192.168.1.100:10080/eventvoting/update.php";
        String method = params[0];
        if (method.equals("modify")) {
            admin = params[1];
            String name=params[2];
            String title=params[3];
            String s_t=params[4];
            String e_t=params[5];
            String parti1=params[6];
            String parti2=params[7];
            String parti3=params[8];
            String parti4=params[9];
            String parti5=params[10];
            String parti6=params[11];
            String parti7=params[12];
            String parti8=params[13];
            String parti9=params[14];
            String parti10=params[15];

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("admin", "UTF-8") + "=" + URLEncoder.encode(admin, "UTF-8") + "&" +
                        URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8") + "&" +
                        URLEncoder.encode("start_time", "UTF-8") + "=" + URLEncoder.encode(s_t, "UTF-8") + "&" +
                        URLEncoder.encode("end_time", "UTF-8") + "=" + URLEncoder.encode(e_t, "UTF-8") + "&" +
                        URLEncoder.encode("part1", "UTF-8") + "=" + URLEncoder.encode(parti1, "UTF-8") + "&" +
                        URLEncoder.encode("part2", "UTF-8") + "=" + URLEncoder.encode(parti2, "UTF-8") + "&" +
                        URLEncoder.encode("part3", "UTF-8") + "=" + URLEncoder.encode(parti3, "UTF-8") + "&" +
                        URLEncoder.encode("part4", "UTF-8") + "=" + URLEncoder.encode(parti4, "UTF-8") + "&" +
                        URLEncoder.encode("part5", "UTF-8") + "=" + URLEncoder.encode(parti5, "UTF-8") + "&" +
                        URLEncoder.encode("part6", "UTF-8") + "=" + URLEncoder.encode(parti6, "UTF-8") + "&" +
                        URLEncoder.encode("part7", "UTF-8") + "=" + URLEncoder.encode(parti7, "UTF-8") + "&" +
                        URLEncoder.encode("part8", "UTF-8") + "=" + URLEncoder.encode(parti8, "UTF-8") + "&" +
                        URLEncoder.encode("part9", "UTF-8") + "=" + URLEncoder.encode(parti9, "UTF-8") + "&" +
                        URLEncoder.encode("part10", "UTF-8") + "=" + URLEncoder.encode(parti10, "UTF-8") + "&" +
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                //httpURLConnection.connect();
                httpURLConnection.disconnect();
                return "Update Event Success...";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("Update Event Success...")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            Intent log = new Intent(ctx, HomeActivity.class);
            log.putExtra("admin",admin);
            ctx.startActivity(log);
        }
        else
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }

    }



}
