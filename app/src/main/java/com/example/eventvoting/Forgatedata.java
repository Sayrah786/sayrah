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
 * Created by SAYEEDUR on 11-02-2018.
 */

public class Forgatedata extends AsyncTask<Void,Integer,Integer> {

    Context c;

    String data;
    String type;

    ArrayList<String> admin=new ArrayList<>();

    ArrayList<String> players=new ArrayList<>();
    ArrayList<String> sec_ans=new ArrayList<>();

    ProgressDialog pd;

    public Forgatedata(Context c, String data,String type) {
        this.c = c;
        this.data = data;
        this.type=type;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

      /*  pd=new ProgressDialog(c);
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
            if (type.equals("admin")) {
                Intent intent = new Intent(c, FPassActivity.class);
                intent.putExtra("admin", admin.get(0));
                intent.putExtra("sec_que", players.get(0));
                intent.putExtra("sec_ans", sec_ans.get(0));
                c.startActivity(intent);
            }
            else {
                Intent intent = new Intent(c, User_FPass_Activity.class);
                intent.putExtra("admin", admin.get(0));
                intent.putExtra("sec_que", players.get(0));
                intent.putExtra("sec_ans", sec_ans.get(0));
                c.startActivity(intent);
            }

        }else
        {

            Toast.makeText(c,"Unable to Parse",Toast.LENGTH_SHORT).show();
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
                String user=jo.getString("user_name");
                String name=jo.getString("sec_que");
                String s_a=jo.getString("sec_ans");



                //ADD IT TO OUR ARRAYLIST
                players.add(name);
                sec_ans.add(s_a);
                admin.add(user);


            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
