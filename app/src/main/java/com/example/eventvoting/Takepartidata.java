package com.example.eventvoting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by SAYEEDUR on 07-02-2018.
 */

public class Takepartidata extends AsyncTask<Void,Integer,Integer> {

    Context c;
    String data;
    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10;
    ProgressDialog pd;
    ArrayList<String> pa1=new ArrayList<>();
    ArrayList<String> pa2=new ArrayList<>();
    ArrayList<String> pa3=new ArrayList<>();
    ArrayList<String> pa4=new ArrayList<>();
    ArrayList<String> pa5=new ArrayList<>();
    ArrayList<String> pa6=new ArrayList<>();
    ArrayList<String> pa7=new ArrayList<>();
    ArrayList<String> pa8=new ArrayList<>();
    ArrayList<String> pa9=new ArrayList<>();
    ArrayList<String> pa10=new ArrayList<>();



    public Takepartidata(Context c, String data, TextView txt1, TextView txt2, TextView txt3, TextView txt4, TextView txt5,TextView txt6,TextView txt7,TextView txt8,TextView txt9, TextView txt10) {
        this.c = c;
        this.data = data;
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

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parser");
        pd.setMessage("Parsing ....Please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {

        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if(integer == 1)
        {
           txt1.setText(pa1.get(0));
            txt2.setText(pa2.get(0));
            txt3.setText(pa3.get(0));
            txt4.setText(pa4.get(0));
            txt5.setText(pa5.get(0));
            txt6.setText(pa6.get(0));
            txt7.setText(pa7.get(0));
            txt8.setText(pa8.get(0));
            txt9.setText(pa9.get(0));
            txt10.setText(pa10.get(0));


        }else
        {

            Toast.makeText(c,"Unable to Parse",Toast.LENGTH_SHORT).show();
        }

        pd.dismiss();
    }

    //PARSE RECEIVED DATA
    private int parse() {
        try {
            //ADD THAT DATA TO JSON ARRAY FIRST


            JSONArray ja = new JSONArray(data);

            //CREATE JO OBJ TO HOLD A SINGLE ITEM
            JSONObject jo = null;


           pa1.clear();
            pa2.clear();
            pa3.clear();
            pa4.clear();
            pa5.clear();

            //LOOP THRU ARRAY
            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);

                //RETRIOEVE NAME
                String p1=jo.getString("parti1");
                String p2=jo.getString("parti2");
                String p3=jo.getString("parti3");
                String p4=jo.getString("parti4");
                String p5=jo.getString("parti5");
                String p6=jo.getString("parti6");
                String p7=jo.getString("parti7");
                String p8=jo.getString("parti8");
                String p9=jo.getString("parti9");
                String p10=jo.getString("parti10");


                //ADD IT TO OUR ARRAYLIST
                pa1.add(p1);
                pa2.add(p2);
                pa3.add(p3);
                pa4.add(p4);
                pa5.add(p5);
                pa6.add(p6);
                pa7.add(p7);
                pa8.add(p8);
                pa9.add(p9);
                pa10.add(p10);

            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
