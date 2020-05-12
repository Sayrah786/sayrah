package com.example.eventvoting;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
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
 * Created by SAYEEDUR on 18-02-2018.
 */

public class takeresparti extends AsyncTask<String,Integer,String> {

    Context c;
    String address;
    TextView txt1,txt2,txt3,txt4,txt5,tx1,tx2,tx3,txt6,txt7,txt8,txt9,txt10,tx4,tx5,tx6,tx7,tx8;

    ProgressDialog pd;

    public takeresparti(Context c, String address, TextView txt1,TextView txt2,TextView txt3,TextView txt4, TextView txt5,TextView txt6,TextView txt7,TextView txt8,TextView txt9, TextView txt10,TextView tx1,TextView tx2,TextView tx3,TextView tx4,TextView tx5,TextView tx6,TextView tx7,TextView tx8) {
        this.c = c;
        this.address = address;
        this.txt1=txt1;
        this.txt2=txt2;
        this.txt3=txt3;
        this.txt4=txt4;
        this.txt5=txt5;
        this.tx1=tx1;
        this.tx2=tx2;
        this.tx3=tx3;
        this.txt6=txt6;
        this.txt7=txt7;
        this.txt8=txt8;
        this.txt9=txt9;
        this.txt10=txt10;
        this.tx4=tx4;
        this.tx5=tx5;
        this.tx6=tx6;
        this.tx7=tx7;
        this.tx8=tx8;

    }

    //B4 JOB STARTS
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Fetch Data");
        pd.setMessage("Fetching Data...Please wait");
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String po=params[0];
        String nam=params[1];
        String data=downloadData(po,nam);
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        pd.dismiss();

        if(s != null)
        {
            takeresdata p=new takeresdata(c,s,txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,tx1,tx2,tx3,tx4,tx5,tx6,tx7,tx8);

            p.execute();

        }
        else {
            Toast.makeText(c,"Unable to downland",Toast.LENGTH_SHORT).show();
        }
    }

    private String downloadData(String po,String nam)
    {
        //connect and get a stream
        InputStream is=null;
        String line =null;
        String pos=po;
        String name=nam;

        try {
            URL url=new URL(address);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            //httpURLConnection.setDoInput(true);
            OutputStream OS = con.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
            String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                    URLEncoder.encode("pos", "UTF-8") + "=" + URLEncoder.encode(pos, "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            is=new BufferedInputStream(con.getInputStream());

            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            StringBuffer sb=new StringBuffer();

            if(br != null) {

                while ((line=br.readLine()) != null) {
                    sb.append(line+"\n");
                }

            }else {
                return null;
            }

            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null)
            {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}

