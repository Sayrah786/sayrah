package com.example.eventvoting;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

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

public class Publiclist extends AsyncTask<String,Integer,String> {

    Context c;
    String address;
    ListView lv;
    String user;


    ProgressDialog pd;

    public Publiclist(Context c, String address, ListView lv,String user) {
        this.c = c;
        this.address = address;
        this.lv = lv;
        this.user=user;

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

        String data=downloadData();
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

       // pd.dismiss();

        if(s != null)
        {
            Publicdatta p=new Publicdatta(c,s,lv,user);

            p.execute();

        }
    }

    private String downloadData( )
    {
        //connect and get a stream
        InputStream is=null;
        String line =null;


        try {
            URL url=new URL(address);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();

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
