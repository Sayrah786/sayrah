package com.example.eventvoting;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class VoteActivity extends AppCompatActivity {
    String name,title,start_time,end_time,admin,user;
    FloatingActionButton btn;
    EditText edt;
    String comment;
    String url="http://192.168.1.100:10080/eventvoting/discomment.php";
    String url1="http://192.168.1.100:10080/eventvoting/partidata.php";
    String url2="http://192.168.1.100:10080/eventvoting/result.php";
    String p1,p2,p3,p4,p5;
    ListView list;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10;
    TextView txt,txt1;
    String partid,method;
    SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        user=getIntent().getExtras().getString("user");
        name=getIntent().getExtras().getString("name");
        title=getIntent().getExtras().getString("tit");
        start_time=getIntent().getExtras().getString("s_t");
        end_time=getIntent().getExtras().getString("e_t");
        admin=getIntent().getExtras().getString("admin");
        View bottomSheet = findViewById(R.id.design_bottom_sheet);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_SETTLING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_HIDDEN");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i("BottomSheetCallback", "slideOffset: " + slideOffset);
            }
        });
        txt=findViewById(R.id.e_name);
        btn1=findViewById(R.id.bt_p1);
        btn2=findViewById(R.id.bt_p2);
        btn3=findViewById(R.id.bt_p3);
        btn4=findViewById(R.id.bt_p4);
        btn5=findViewById(R.id.bt_p5);
        btn6=findViewById(R.id.bt_p6);
        btn7=findViewById(R.id.bt_p7);
        btn8=findViewById(R.id.bt_p8);
        btn9=findViewById(R.id.bt_p9);
        btn10=findViewById(R.id.bt_p10);
        btn=findViewById(R.id.comment_btn);
        edt=findViewById(R.id.comment);
        list=findViewById(R.id.cmt);
        txt.setText(name);
        txt1=findViewById(R.id.res_u);




        final Takevoteparti d=new Takevoteparti(this,url1,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10);
        d.execute(admin,name);
        final Commentlist d1=new Commentlist(this,url,list);
        d1.execute(name);

        try {
            date_com();
            date_com1();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public void usercomment(View view)
    {
        comment=edt.getText().toString();

        if(comment.equals(""))
        {
            Toast.makeText(VoteActivity.this, "Plz enter the fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String method="comment";
            Commenttask backgroundTask = new Commenttask(this);
            backgroundTask.execute(method,comment,name,user);
            final Commentlist d=new Commentlist(this,url,list);
            d.execute(name);
            edt.setText("");
        }


    }
    public void btn_one(View view){
        partid="100";
        method="vote";

        Votereg backgroundTask=new Votereg(this,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10);
        backgroundTask.execute(method,partid,name,user);
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
        btn10.setEnabled(false);

    }
    public void btn_two(View view){
        partid="200";
        method="vote";

        Votereg backgroundTask=new Votereg(this,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10);
        backgroundTask.execute(method,partid,name,user);
        btn1.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn2.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
        btn10.setEnabled(false);
    }
    public void btn_three(View view){
        partid="300";
        method="vote";

        Votereg backgroundTask=new Votereg(this,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10);
        backgroundTask.execute(method,partid,name,user);
        btn2.setEnabled(false);
        btn1.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn3.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
        btn10.setEnabled(false);
    }
    public void btn_four(View view){
        partid="400";
        method="vote";

        Votereg backgroundTask=new Votereg(this,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10);
        backgroundTask.execute(method,partid,name,user);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn1.setEnabled(false);
        btn5.setEnabled(false);
        btn4.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
        btn10.setEnabled(false);
    }
    public void btn_five(View view){
        partid="500";
        method="vote";

        Votereg backgroundTask=new Votereg(this,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10);
        backgroundTask.execute(method,partid,name,user);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn1.setEnabled(false);
        btn5.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
        btn10.setEnabled(false);
    }
    public void btn_six(View view){
        partid="600";
        method="vote";

        Votereg backgroundTask=new Votereg(this,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10);
        backgroundTask.execute(method,partid,name,user);

        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn1.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
        btn10.setEnabled(false);
    }
    public void btn_seven(View view){
        partid="700";
        method="vote";

        Votereg backgroundTask=new Votereg(this,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10);
        backgroundTask.execute(method,partid,name,user);
        btn1.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn2.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
        btn10.setEnabled(false);
    }
    public void btn_eight(View view){
        partid="800";
        method="vote";

        Votereg backgroundTask=new Votereg(this,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10);
        backgroundTask.execute(method,partid,name,user);
        btn2.setEnabled(false);
        btn1.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn3.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
        btn10.setEnabled(false);
    }
    public void btn_nine(View view){
        partid="900";
        method="vote";

        Votereg backgroundTask=new Votereg(this,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10);
        backgroundTask.execute(method,partid,name,user);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn1.setEnabled(false);
        btn5.setEnabled(false);
        btn4.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
        btn10.setEnabled(false);
    }
    public void btn_ten(View view){
        partid="1000";
        method="vote";

        Votereg backgroundTask=new Votereg(this,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10);
        backgroundTask.execute(method,partid,name,user);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn1.setEnabled(false);
        btn5.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
        btn10.setEnabled(false);
    }
    public void date_com() throws ParseException {
        String datetime = dateformat.format(c.getTime());
        Date date=dateformat.parse(datetime);
        Date date1=dateformat.parse(start_time);
        if(date.compareTo(date1)<=0){
            btn1.setVisibility(View.INVISIBLE);
            btn2.setVisibility(View.INVISIBLE);
            btn3.setVisibility(View.INVISIBLE);
            btn4.setVisibility(View.INVISIBLE);
            btn5.setVisibility(View.INVISIBLE);
            btn6.setVisibility(View.INVISIBLE);
            btn7.setVisibility(View.INVISIBLE);
            btn8.setVisibility(View.INVISIBLE);
            btn9.setVisibility(View.INVISIBLE);
            btn10.setVisibility(View.INVISIBLE);
            Toast.makeText(VoteActivity.this, "Event Start on "+start_time, Toast.LENGTH_SHORT).show();
        }

    }
    public void date_com1() throws ParseException {
        String datetime = dateformat.format(c.getTime());
        Date date=dateformat.parse(datetime);
        Date date1=dateformat.parse(end_time);
        if(date1.compareTo(date)<=0){
            btn1.setVisibility(View.INVISIBLE);
            btn2.setVisibility(View.INVISIBLE);
            btn3.setVisibility(View.INVISIBLE);
            btn4.setVisibility(View.INVISIBLE);
            btn5.setVisibility(View.INVISIBLE);
           txt1.setVisibility(View.VISIBLE);
            btn6.setVisibility(View.INVISIBLE);
            btn7.setVisibility(View.INVISIBLE);
            btn8.setVisibility(View.INVISIBLE);
            btn9.setVisibility(View.INVISIBLE);
            btn10.setVisibility(View.INVISIBLE);

            Toast.makeText(VoteActivity.this, "Event End on "+end_time, Toast.LENGTH_SHORT).show();
        }


    }
    public void btn_res_uesr(View view){

        final Windata d=new Windata(this,url2,name,admin);
        d.execute(name);
    }

}
