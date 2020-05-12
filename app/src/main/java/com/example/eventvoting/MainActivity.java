package com.example.eventvoting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
public static int S_T_Uut=4000;
    SharedPreferences sharedprefrance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /* sharedprefrance =getApplicationContext().getSharedPreferences("exit", Context.MODE_PRIVATE);
        String exit=sharedprefrance.getString("exit","");
        if(exit.equals("exit"))
        {
            SharedPreferences.Editor e = sharedprefrance.edit();
            e.clear();
            e.commit();
          finish();

        }*/

        new Handler().postDelayed(new Runnable() {
     
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, Nav_Activity.class);
                startActivity(i);
                finish();
            }
        },S_T_Uut);

    }
}

