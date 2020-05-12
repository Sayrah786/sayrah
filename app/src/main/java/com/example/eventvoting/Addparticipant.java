package com.example.eventvoting;

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
 * Created by SAYEEDUR on 07-02-2018.
 */

public class Addparticipant extends AsyncTask<String,Void,String> {
    Context ctx;
    String admin;

    Addparticipant(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String addpart = "http://192.168.1.100:10080/eventvoting/addpart.php";

        String method = params[0];

        if (method.equals("addparti")) {
            String par1 = params[1];
            String par2 = params[2];
            String par3 = params[3];
            String par4 = params[4];
            String par5 = params[5];
            String par6 = params[6];
            String par7 = params[7];
            String par8 = params[8];
            String par9 = params[9];
            String par10 = params[10];
            admin = params[11];
            String name=params[12];


            try {
                URL url = new URL(addpart);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("par1", "UTF-8") + "=" + URLEncoder.encode(par1, "UTF-8") + "&" +
                        URLEncoder.encode("par2", "UTF-8") + "=" + URLEncoder.encode(par2, "UTF-8") + "&" +
                        URLEncoder.encode("par3", "UTF-8") + "=" + URLEncoder.encode(par3, "UTF-8") + "&" +
                        URLEncoder.encode("par4", "UTF-8") + "=" + URLEncoder.encode(par4, "UTF-8") + "&" +
                        URLEncoder.encode("par5", "UTF-8") + "=" + URLEncoder.encode(par5, "UTF-8") + "&" +
                        URLEncoder.encode("admin", "UTF-8") + "=" + URLEncoder.encode(admin, "UTF-8") + "&" +
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("par6", "UTF-8") + "=" + URLEncoder.encode(par6, "UTF-8") + "&" +
                        URLEncoder.encode("par7", "UTF-8") + "=" + URLEncoder.encode(par7, "UTF-8") + "&" +
                        URLEncoder.encode("par8", "UTF-8") + "=" + URLEncoder.encode(par8, "UTF-8") + "&" +
                        URLEncoder.encode("par9", "UTF-8") + "=" + URLEncoder.encode(par9, "UTF-8") + "&" +
                        URLEncoder.encode("par10", "UTF-8") + "=" + URLEncoder.encode(par10, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                //httpURLConnection.connect();
                httpURLConnection.disconnect();
                return "Event request send ADMIN";

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
        if (result.equals("Event request send ADMIN")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            Intent intent=new Intent(ctx,HomeActivity.class);
            intent.putExtra("admin",admin);
            ctx.startActivity(intent);

        }
        else {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }
}


