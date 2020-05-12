package com.example.eventvoting;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
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
 * Created by SAYEEDUR on 08-02-2018.
 */

public class Takevoteparti extends AsyncTask<String,Integer,String> {

    Context c;
    String address;
    Button txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10;

    ProgressDialog pd;

    public Takevoteparti(Context c, String address, Button txt1, Button txt2, Button txt3, Button txt4, Button txt5, Button txt6, Button txt7, Button txt8, Button txt9, Button txt10) {
        this.c = c;
        this.address = address;
        this.txt1=txt1;
        this.txt2=txt2;
        this.txt3=txt3;
        this.txt4=txt4;
        this.txt5=txt5;
        this.txt6=txt6;
        this.txt7=txt7;
        this.txt8=txt8;
        this.txt9=txt9;
        this.txt10=txt10;

    }

    //B4 JOB STARTS
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

     /*   pd=new ProgressDialog(c);
        pd.setTitle("Fetch Data");
        pd.setMessage("Fetching Data...Please wait");
        pd.show();*/
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

      //  pd.dismiss();

        if(s != null)
        {
            Takevotepartidata p=new Takevotepartidata(c,s,txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10);

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

