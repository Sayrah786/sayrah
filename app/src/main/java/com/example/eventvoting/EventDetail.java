package com.example.eventvoting;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EventDetail extends AppCompatActivity {
    String name,title,start_time,end_time,admin;
    String url="http://192.168.1.100:10080/eventvoting/partidata.php";
    TextView txt1,txt2,txt3,txt4,txt5,txt,tx1,txt8,txt9,tx11,tx2,tx3,tx4,tx5;
    FloatingActionButton fab1,fab2,fab3;
    String url1="http://192.168.1.100:10080/eventvoting/result.php";
    String p1,p2,p3,p4,p5,p6,p7,p8,p9,p10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        name=getIntent().getExtras().getString("name");
        title=getIntent().getExtras().getString("tit");
        start_time=getIntent().getExtras().getString("s_t");
        end_time=getIntent().getExtras().getString("e_t");
        admin=getIntent().getExtras().getString("admin");
        txt1=findViewById(R.id.paa1);
        txt2=findViewById(R.id.paa2);
        txt3=findViewById(R.id.paa3);
        txt4=findViewById(R.id.paa4);
        txt5=findViewById(R.id.paa5);
        tx11=findViewById(R.id.paa6);
        tx2=findViewById(R.id.paa7);
        tx3=findViewById(R.id.paa8);
        tx4=findViewById(R.id.paa9);
        tx5=findViewById(R.id.paa10);
        fab1=findViewById(R.id.mod_e);
        fab2=findViewById(R.id.del_e);
        txt=findViewById(R.id.mod);
        tx1=findViewById(R.id.del);
        txt8=findViewById(R.id.evt_nam);
        txt8.setText(name);
        txt9=findViewById(R.id.res);
        fab3=findViewById(R.id.res_e);
       final Takeparti d=new Takeparti(this,url,txt1,txt2,txt3,txt4,txt5,tx11,tx2,tx3,tx4,tx5);
        d.execute(admin,name);
    }
    public void add_btn(View view){
        if(fab1.getVisibility() == View.INVISIBLE) {
            fab1.setVisibility(View.VISIBLE);
            fab2.setVisibility(View.VISIBLE);
            fab3.setVisibility(View.VISIBLE);
            txt.setVisibility(View.VISIBLE);
            txt9.setVisibility(View.VISIBLE);
            tx1.setVisibility(View.VISIBLE);
        }
        else {
            fab1.setVisibility(View.INVISIBLE);
            fab2.setVisibility(View.INVISIBLE);
            txt.setVisibility(View.INVISIBLE);
            fab3.setVisibility(View.INVISIBLE);
            txt9.setVisibility(View.INVISIBLE);
            tx1.setVisibility(View.INVISIBLE);
        }
    }
    public void mod_btn(View view){
        p1=txt1.getText().toString();
        p2=txt2.getText().toString();
        p3=txt3.getText().toString();
        p4=txt4.getText().toString();
        p5=txt5.getText().toString();
        p6=tx11.getText().toString();
        p7=tx2.getText().toString();
        p8=tx3.getText().toString();
        p9=tx4.getText().toString();
        p10=tx5.getText().toString();
        Intent intent=new Intent(EventDetail.this,ModifyActivity.class);
        intent.putExtra("admin",admin);
        intent.putExtra("name",name);
        intent.putExtra("title",title);
        intent.putExtra("start_time",start_time);
        intent.putExtra("end_time",end_time);
        intent.putExtra("part1",p1);
        intent.putExtra("part2",p2);
        intent.putExtra("part3",p3);
        intent.putExtra("part4",p4);
        intent.putExtra("part5",p5);
        intent.putExtra("part6",p6);
        intent.putExtra("part7",p7);
        intent.putExtra("part8",p8);
        intent.putExtra("part9",p9);
        intent.putExtra("part10",p10);
        startActivity(intent);

    }
    public void del_btn(View view){
        AlertDialog.Builder a_builder=new AlertDialog.Builder(EventDetail.this);
        a_builder.setMessage("Do you want delete the event")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

               del();
            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog=a_builder.create();
        alertDialog.setTitle("Alert !!!");
        alertDialog.show();

    }
    public void res_btn(View view){
        p1=txt1.getText().toString();
        p2=txt2.getText().toString();
        p3=txt3.getText().toString();
        p4=txt4.getText().toString();
        p5=txt5.getText().toString();
        p6=tx11.getText().toString();
        p7=tx2.getText().toString();
        p8=tx3.getText().toString();
        p9=tx4.getText().toString();
        p10=tx5.getText().toString();
        final Resultdata d=new Resultdata(this,url1,p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,end_time);
        d.execute(name);
    }
    public void del(){
        String method="delete";
        DeleteTask deleteTask=new DeleteTask(this);
        deleteTask.execute(method,admin,name);
    }
}
