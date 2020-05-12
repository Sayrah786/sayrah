package com.example.eventvoting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class HomeActivity extends AppCompatActivity {
FloatingActionButton fab;
ListView lv;
String admin;
    String url="http://192.168.1.100:10080/eventvoting/adminevent.php";
    String url1="http://192.168.1.100:10080/eventvoting/alreat.php";
    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        admin=getIntent().getExtras().getString("admin");
        fab=findViewById(R.id.add_event);
        lv=findViewById(R.id.admin_event);
        String met="comp";
       // Resultreg resultreg=new Resultreg(this);
      //  resultreg.execute(met,admin);

        final EventLode d=new EventLode(this,url,lv);
        d.execute(admin);
        Alreatlist alreatlist=new Alreatlist(this,url1,lv,fab);
        alreatlist.execute(admin);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,Add_Event_Activity.class);
                intent.putExtra("admin",admin);
                startActivity(intent);
            }
        });
        this.mHandler = new Handler();
        m_Runnable.run();
    }
    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {

           sa();
            HomeActivity.this.mHandler.postDelayed(m_Runnable, 5000);
        }

    };
    public void sa(){
        final EventLode d=new EventLode(this,url,lv);
        d.execute(admin);
        Alreatlist alreatlist=new Alreatlist(this,url1,lv,fab);
        alreatlist.execute(admin);
    }
/*
            a_builder.setCancelable(false);
            a_builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
finish();

                }
            });
            a_builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.cancel();
                }
            });
            AlertDialog alertDialog=a_builder.create();
            alertDialog.setTitle("Alert !!!");
            alertDialog.show();

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_, menu);
        return true;
    }
*/
    @Override
    public void onBackPressed() {
        AlertDialog.Builder a_builder=new AlertDialog.Builder(HomeActivity.this);
        a_builder.setMessage("Do you want exit app?");
        a_builder.setCancelable(false);
        a_builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             //HomeActivity.super.finish();
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity(a);
            }
        });
        a_builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        AlertDialog alertDialog=a_builder.create();
        alertDialog.setTitle("Alert !!!");
        alertDialog.show();

    }
    /*void exit1(){

        //check username and password are correct and then add them to SharedPreferences
        SharedPreferences sp=getSharedPreferences("exit", Context.MODE_PRIVATE);

        String exit="exit";
        SharedPreferences.Editor e = sp.edit();
        e.clear();
        e.putString("exit", exit);


        //Set name and email in global/application context

        //  session_id=login_name;
        e.commit();


    }*/
}
