package com.example.eventvoting;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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
 * Created by SAYEEDUR on 09-02-2018.
 */

public class Resultdata extends AsyncTask<String,Integer,String> {

    Context c;
    String address;
    String p1,p2,p3,p4,p5,e_t,p6,p7,p8,p9,p10;

    ProgressDialog pd;

    public Resultdata(Context c, String address,String p1,String p2,String p3,String p4,String p5,String p6,String p7,String p8,String p9,String p10,String e_t) {
        this.c = c;
        this.address = address;
        this.p1=p1;
        this.p2=p2;
        this.p3=p3;
        this.p4=p4;
        this.p5=p5;
        this.e_t=e_t;
        this.p6=p6;
        this.p7=p7;
        this.p8=p8;
        this.p9=p9;
        this.p10=p10;

    }

    //B4 JOB STARTS
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

       /* pd=new ProgressDialog(c);
        pd.setTitle("Fetch Data");
        pd.setMessage("Fetching Data...Please wait");
        pd.show();*/
    }

    @Override
    protected String doInBackground(String... params) {
        String po=params[0];
        String data=downloadData(po);
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        //pd.dismiss();

        if(s != null)
        {
            Resultload p=new Resultload(c,s,p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,e_t);

            p.execute();

        }else
        {
            Toast.makeText(c,"Unable to download data",Toast.LENGTH_SHORT).show();
        }
    }

    private String downloadData(String po)
    {
        //connect and get a stream
        InputStream is=null;
        String line =null;
        String pos=po;

        try {
            URL url=new URL(address);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            //httpURLConnection.setDoInput(true);
            OutputStream OS = con.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
            String data = URLEncoder.encode("event_name", "UTF-8") + "=" + URLEncoder.encode(pos, "UTF-8");
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

