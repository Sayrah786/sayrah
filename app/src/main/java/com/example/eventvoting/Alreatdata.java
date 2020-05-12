package com.example.eventvoting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by SAYEEDUR on 15-02-2018.
 */

public class Alreatdata extends AsyncTask<Void,Integer,Integer> {

    Context c;
    ListView lv;
    String data;

    ArrayList<String> players=new ArrayList<>();

    ProgressDialog pd;
    FloatingActionButton fab;
    public Alreatdata(Context c, String data, ListView lv,FloatingActionButton fab) {
        this.c = c;
        this.data = data;
        this.lv = lv;
        this.fab=fab;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

       /* pd=new ProgressDialog(c);
        pd.setTitle("Parser");
        pd.setMessage("Parsing ....Please wait");
        pd.show();*/
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

            if(players.get(0).equals("no")){
                lv.setVisibility(View.INVISIBLE);
                fab.setVisibility(View.INVISIBLE);
            }




        }else
        {

            Toast.makeText(c,"Unable to Parse",Toast.LENGTH_SHORT).show();
        }

       // pd.dismiss();
    }

    private int parse()
    {
        try
        {
            //ADD THAT DATA TO JSON ARRAY FIRST


            JSONArray ja=new JSONArray(data);

            //CREATE JO OBJ TO HOLD A SINGLE ITEM
            JSONObject jo=null;



            players.clear();

            //LOOP THRU ARRAY
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                //RETRIOEVE NAME
                String name=jo.getString("request");




                //ADD IT TO OUR ARRAYLIST
                players.add(name);

            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}