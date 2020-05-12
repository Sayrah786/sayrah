package com.example.eventvoting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ModifyActivity extends AppCompatActivity {
String admin,name,title,start_time,end_time,part1,part2,part3,part4,part5,part6,part7,part8,part9,part10;
TextView txt;
EditText edt,edt1,edt2,edt3,edt4,edt5,edt6,edt7,edt8,edt9,edt10,edt11,edt12;
    String date_time = "";
    int mYear;
    int mMonth;
    int mDay;
    int mHour;
    int mMinute;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        admin=getIntent().getExtras().getString("admin");
        name=getIntent().getExtras().getString("name");
        title=getIntent().getExtras().getString("title");
        start_time=getIntent().getExtras().getString("start_time");
        end_time=getIntent().getExtras().getString("end_time");
        part1=getIntent().getExtras().getString("part1");
        part2=getIntent().getExtras().getString("part2");
        part3=getIntent().getExtras().getString("part3");
        part4=getIntent().getExtras().getString("part4");
        part5=getIntent().getExtras().getString("part5");
        part6=getIntent().getExtras().getString("part6");
        part7=getIntent().getExtras().getString("part7");
        part8=getIntent().getExtras().getString("part8");
        part9=getIntent().getExtras().getString("part9");
        part10=getIntent().getExtras().getString("part10");
        txt=findViewById(R.id.ed1);
        edt=findViewById(R.id.ed2);
        edt1=findViewById(R.id.ed3);
        edt2=findViewById(R.id.ed4);
        edt3=findViewById(R.id.ed5);
        edt4=findViewById(R.id.ed6);
        edt5=findViewById(R.id.ed7);
        edt6=findViewById(R.id.ed8);
        edt7=findViewById(R.id.ed9);
        edt8=findViewById(R.id.ed10);
        edt9=findViewById(R.id.ed11);
        edt10=findViewById(R.id.ed12);
        edt11=findViewById(R.id.ed13);
        edt12=findViewById(R.id.ed14);
        txt.setText(name);
        edt.setText(title);
        edt1.setText(start_time);
        edt2.setText(end_time);
        edt3.setText(part1);
        edt4.setText(part2);
        edt5.setText(part3);
        edt6.setText(part4);
        edt7.setText(part5);
        edt8.setText(part6);
        edt9.setText(part7);
        edt10.setText(part8);
        edt11.setText(part9);
        edt12.setText(part10);
        edt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datePicker();

            }
        });
        edt2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datePicker1();

            }
        });
        if (part3.equals("")){
            edt5.setVisibility(View.INVISIBLE);
        }
        if (part4.equals("")){
            edt6.setVisibility(View.INVISIBLE);
        }
        if (part5.equals("")){
            edt7.setVisibility(View.INVISIBLE);
        }
        if (part6.equals("")){
            edt8.setVisibility(View.INVISIBLE);
        }
        if (part7.equals("")){
            edt9.setVisibility(View.INVISIBLE);
        }
        if (part8.equals("")){
            edt10.setVisibility(View.INVISIBLE);
        }
        if (part9.equals("")){
            edt11.setVisibility(View.INVISIBLE);
        }
        if (part10.equals("")){
            edt12.setVisibility(View.INVISIBLE);
        }


    }
    public void btn_update(View view) throws ParseException {
        Date date,date1,date2;
        String datetime = dateformat.format(c.getTime());
        date=dateformat.parse(datetime);
        date1=dateformat.parse(edt1.getText().toString());
        date2=dateformat.parse(edt2.getText().toString());
        if (date2.compareTo(date) < 0) {

            Toast.makeText(ModifyActivity.this, "Select date is less Current date", Toast.LENGTH_SHORT).show();
        } else {
            if (date1.compareTo(date2) < 0) {
                String method="modify";
                String tit,st,et,p1,p2,p3,p4,p5,p6,p7,p8,p9,p10;
                tit=edt.getText().toString();
                st=edt1.getText().toString();
                et=edt2.getText().toString();
                p1=edt3.getText().toString();
                p2=edt4.getText().toString();
                p3=edt5.getText().toString();
                p4=edt6.getText().toString();
                p5=edt7.getText().toString();
                p6=edt8.getText().toString();
                p7=edt9.getText().toString();
                p8=edt10.getText().toString();
                p9=edt11.getText().toString();
                p10=edt12.getText().toString();
                UpdateTask updateTask=new UpdateTask(this);
                updateTask.execute(method,admin,name,tit,st,et,p1,p2,p3,p4,p5,p6,p7,p8,p9,p10);
            }
            else {
                Toast.makeText(ModifyActivity.this, "End date is less Start date", Toast.LENGTH_SHORT).show();

            }

        }

    }
    private void datePicker(){

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        //*************Call Time Picker Here ********************
                        tiemPicker();

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    private void tiemPicker(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;

                        edt1.setText(date_time+" "+hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
    private void datePicker1(){

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        //*************Call Time Picker Here ********************
                        tiemPicker1();

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    private void tiemPicker1(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;

                        edt2.setText(date_time+" "+hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
    public void b2(View view){
        edt5.setVisibility(View.VISIBLE);
    }
    public void b3(View view){
        edt6.setVisibility(View.VISIBLE);
    }
    public void bb4(View view){
        edt7.setVisibility(View.VISIBLE);
    }
    public void b5(View view){
        edt8.setVisibility(View.VISIBLE);
    }
    public void b6(View view){
        edt9.setVisibility(View.VISIBLE);
    }
    public void b7(View view){
        edt10.setVisibility(View.VISIBLE);
    }
    public void b8(View view){
        edt11.setVisibility(View.VISIBLE);
    }
    public void b9(View view){
        edt12.setVisibility(View.VISIBLE);
    }

}
