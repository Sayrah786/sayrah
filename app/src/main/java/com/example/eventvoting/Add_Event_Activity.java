package com.example.eventvoting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Add_Event_Activity extends AppCompatActivity {
FloatingActionButton fab;
String admin;
    String date_time = "";
    int mYear;
    int mMonth;
    int mDay;
    EditText et_show_date_time,end,name,title;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    String st_t,et_t,nam,tit;
    int mHour;
    int mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__event_);
        fab=findViewById(R.id.addpart);
       admin=getIntent().getExtras().getString("admin");
        et_show_date_time =  findViewById(R.id.start_time);
        et_show_date_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datePicker();

            }
        });
        name=findViewById(R.id.event_name);
        end=findViewById(R.id.end_time);

        title=findViewById(R.id.event_title);
        end.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datePicker1();

            }
        });
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

                        et_show_date_time.setText(date_time+" "+hourOfDay + ":" + minute);
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

                        end.setText(date_time+" "+hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
    public void addevent(View view) throws ParseException {
        Date date,date1,date2;
        st_t=et_show_date_time.getText().toString();
        et_t=end.getText().toString();
        nam=name.getText().toString();
        tit=title.getText().toString();
        String datetime = dateformat.format(c.getTime());




        if (st_t.equals("")||et_t.equals("")||nam.equals("")||tit.equals(""))
        {
            //Toast.makeText(Add_Event_Activity.this, "Field should not be empty", Toast.LENGTH_SHORT).show();
            if (nam.equals("")) {
                name.setError("Enter the Event Name");
                name.requestFocus();
                name.setText("");
            }
            else if(tit.equals("")){
                title.setError("Enter the Title");
                title.requestFocus();
                title.setText("");
            }
            else if(st_t.equals("")){
                et_show_date_time.setError("Enter the Starting Time");
                et_show_date_time.requestFocus();
                et_show_date_time.setText("");

            }
            else if(et_t.equals("")){
                end.setError("Enter the End Time");
                end.requestFocus();
                end.setText("");
            }
        }
        else {
            date=dateformat.parse(datetime);
            date1=dateformat.parse(st_t);
            date2=dateformat.parse(et_t);
            if (date1.compareTo(date) < 0 || date2.compareTo(date) < 0) {

                Toast.makeText(Add_Event_Activity.this, "Select date is less Current date", Toast.LENGTH_SHORT).show();
                if (date1.compareTo(date) < 0)
                {
                    et_show_date_time.setError("Select date is less Current date");
                    et_show_date_time.requestFocus();
                    et_show_date_time.setText("");

                }
                if (date2.compareTo(date) < 0)
                {
                    end.setError("Select date is less Current date");
                    end.requestFocus();
                    end.setText("");

                }
            } else {
                if (date1.compareTo(date2) < 0) {
                    String method = "addevent";
                    RegLog backgroundTask = new RegLog(this);
                    backgroundTask.execute(method, nam, tit, st_t, et_t, admin);
                }
                else {
                    //Toast.makeText(Add_Event_Activity.this, "End date is less Start date", Toast.LENGTH_SHORT).show();
                    end.setError("End date is less Start date");
                    end.requestFocus();
                    end.setText("");
                }
                
            }
        }
    }
}
