package com.example.eventvoting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class AllEventActivity extends AppCompatActivity {
    String url="http://192.168.1.100:10080/eventvoting/vote.php";
    ListView lv;
    String user;
    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_event);
        user=getIntent().getExtras().getString("user");
        lv=findViewById(R.id.pb_lv);
        final Publiclist d=new Publiclist(this,url,lv,user);
        d.execute();
        this.mHandler = new Handler();
        m_Runnable.run();
    }
    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {

            sa();
            AllEventActivity.this.mHandler.postDelayed(m_Runnable, 5000);
        }

    };
    public void sa(){
        final Publiclist d=new Publiclist(this,url,lv,user);
        d.execute();
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder a_builder=new AlertDialog.Builder(AllEventActivity.this);
        a_builder.setMessage("Do you want exit app?");
        a_builder.setCancelable(false);
        a_builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
}
