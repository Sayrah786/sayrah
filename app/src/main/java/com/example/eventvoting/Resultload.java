package com.example.eventvoting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by SAYEEDUR on 09-02-2018.
 */

public class Resultload extends AsyncTask<Void,Integer,Integer> {

    Context c;

    String data;
    int a=0,b=0,c1=0,d=0,e=0,f=0,g=0,h=0,i1=0,j=0;

    String p1,p2,p3,p4,p5,e_t,p6,p7,p8,p9,p10;


    ArrayList<String> players=new ArrayList<>();

    ProgressDialog pd;

    public Resultload(Context c, String data,String p1,String p2,String p3,String p4,String p5,String p6,String p7,String p8,String p9,String p10,String e_t) {
        this.c = c;
        this.data = data;
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

            for (int i=0; i<players.size();i++){

                if(players.get(i).matches("100")){
                    a=a+1;

                }
                if(players.get(i).matches("200")){
                    b=b+1;

                }

                if(players.get(i).matches("300")){
                    c1=c1+1;
                }
                if(players.get(i).matches("400")){
                    d=d+1;
                }
                if(players.get(i).matches("500")){
                    e=e+1;
                }
                if(players.get(i).matches("600")){
                    f=f+1;
                }
                if(players.get(i).matches("700")){
                    g=g+1;
                }
                if(players.get(i).matches("800")){
                   h=h+1;
                }
                if(players.get(i).matches("900")){
                    i1=i1+1;
                }
                if(players.get(i).matches("1000")){
                    j=j+1;
                }

            }


            Intent i=new Intent(c,ResultActivity.class);
            i.putExtra("a",a);
            i.putExtra("b",b);
            i.putExtra("c",c1);
            i.putExtra("d",d);
            i.putExtra("e",e);
            i.putExtra("f",f);
            i.putExtra("g",g);
            i.putExtra("h",h);
            i.putExtra("i",i1);
            i.putExtra("j",j);
            i.putExtra("part1",p1);
            i.putExtra("part2",p2);
            i.putExtra("part3",p3);
            i.putExtra("part4",p4);
            i.putExtra("part5",p5);
            i.putExtra("end_time",e_t);
            i.putExtra("part6",p6);
            i.putExtra("part7",p7);
            i.putExtra("part8",p8);
            i.putExtra("part9",p9);
            i.putExtra("part10",p10);
            c.startActivity(i);




        }else
        {
            Toast.makeText(c,"There is no vote for event",Toast.LENGTH_SHORT).show();
        }

       // pd.dismiss();
    }

    //PARSE RECEIVED DATA
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
                String participant_id=jo.getString("participant_id");


                //ADD IT TO OUR ARRAYLIST
                players.add(participant_id);

            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
