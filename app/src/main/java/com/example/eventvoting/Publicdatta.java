package com.example.eventvoting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
 * Created by SAYEEDUR on 08-02-2018.
 */

public class Publicdatta extends AsyncTask<Void,Integer,Integer> {

    Context c;
    ListView lv;
    String data;
    String user;

    ArrayList<String> players=new ArrayList<>();
    ArrayList<String> admin=new ArrayList<>();
    ArrayList<String> title=new ArrayList<>();
    ArrayList<String> st_time=new ArrayList<>();
    ArrayList<String> end_time=new ArrayList<>();
    ProgressDialog pd;

    public Publicdatta(Context c, String data, ListView lv,String user) {
        this.c = c;
        this.data = data;
        this.lv = lv;
        this.user=user;

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
            //ADAPTER
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,players);


            //ADAPT TO LISTVIEW

            lv.setAdapter(adapter);


            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(c, VoteActivity.class);
                        intent.putExtra("user", user);
                        intent.putExtra("admin", admin.get(position));
                        intent.putExtra("name", players.get(position));
                        intent.putExtra("s_t", st_time.get(position));
                        intent.putExtra("e_t", end_time.get(position));
                        intent.putExtra("tit", title.get(position));
                        c.startActivity(intent);

                }
            });

        }else
        {
            lv.setAdapter(null);
        }

      //  pd.dismiss();
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
                String name=jo.getString("name");
                String adm=jo.getString("admin_name");
                String tit=jo.getString("title");
                String s_t=jo.getString("start_time");
                String e_t=jo.getString("end_time");



                //ADD IT TO OUR ARRAYLIST
                players.add(name);
                admin.add(adm);
                title.add(tit);
                st_time.add(s_t);
                end_time.add(e_t);


            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
