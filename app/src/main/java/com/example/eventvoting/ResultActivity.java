package com.example.eventvoting;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {
    int a, b, c, d, e,f,g,h,i1,j;
    BarChart barChart;
    String part1, part2, part3, part4, part5, end_time,part6, part7, part8, part9, part10;
    TextView txt1, txt2, txt3, txt4, txt5,txt6, txt7, txt8, txt9, txt10, resu,tx1,tx2,tx3,tx4,tx5,tx6,tx7,tx8;
    FloatingActionButton fab;
    SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    Calendar c1 = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        a = getIntent().getExtras().getInt("a");
        b = getIntent().getExtras().getInt("b");
        c = getIntent().getExtras().getInt("c");
        d = getIntent().getExtras().getInt("d");
        e = getIntent().getExtras().getInt("e");
        f = getIntent().getExtras().getInt("f");
        g = getIntent().getExtras().getInt("g");
        h = getIntent().getExtras().getInt("h");
        i1 = getIntent().getExtras().getInt("i");
        j = getIntent().getExtras().getInt("j");
        part1 = getIntent().getExtras().getString("part1");
        part2 = getIntent().getExtras().getString("part2");
        part3 = getIntent().getExtras().getString("part3");
        part4 = getIntent().getExtras().getString("part4");
        part5 = getIntent().getExtras().getString("part5");
        part6 = getIntent().getExtras().getString("part6");
        part7 = getIntent().getExtras().getString("part7");
        part8 = getIntent().getExtras().getString("part8");
        part9 = getIntent().getExtras().getString("part9");
        part10 = getIntent().getExtras().getString("part10");
        end_time = getIntent().getExtras().getString("end_time");
        txt1 = findViewById(R.id.textView3);
        txt2 = findViewById(R.id.textView5);
        txt3 = findViewById(R.id.textView7);
        txt4 = findViewById(R.id.textView9);
        txt5 = findViewById(R.id.textView11);
        txt6 = findViewById(R.id.textView13);
        txt7 = findViewById(R.id.textView15);
        txt8 = findViewById(R.id.textView17);
        txt9 = findViewById(R.id.textView19);
        txt10 = findViewById(R.id.textView21);
        tx1 = findViewById(R.id.textView6);
        tx2 = findViewById(R.id.textView8);
        tx3 = findViewById(R.id.textView10);
        tx4 = findViewById(R.id.textView12);
        tx5 = findViewById(R.id.textView14);
        tx6 = findViewById(R.id.textView16);
        tx7 = findViewById(R.id.textView18);
        tx8 = findViewById(R.id.textView20);
        barChart = findViewById(R.id.bar_chart);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, a));
        barEntries.add(new BarEntry(2, b));
        barEntries.add(new BarEntry(3, c));
        barEntries.add(new BarEntry(4, d));
        barEntries.add(new BarEntry(5, e));
        barEntries.add(new BarEntry(6, f));
        barEntries.add(new BarEntry(7, g));
        barEntries.add(new BarEntry(8, h));
        barEntries.add(new BarEntry(9, i1));
        barEntries.add(new BarEntry(10, j));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Data_set1");
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.7f);
        barChart.setData(barData);
        String[] parti = new String[]{"","P1","P2","P3","P4","P5","P6","P7","P8","P9","P10"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new myXaxs(parti));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        Toast.makeText(ResultActivity.this, "hello", Toast.LENGTH_SHORT);
        Toast.makeText(ResultActivity.this, part1, Toast.LENGTH_SHORT);
        txt1.setText(part1);
        txt2.setText(part2);
        txt3.setText(part3);
        txt4.setText(part4);
        txt5.setText(part5);
        txt6.setText(part6);
        txt7.setText(part7);
        txt8.setText(part8);
        txt9.setText(part9);
        txt10.setText(part10);

        if(part3.equals("")){
            tx1.setVisibility(View.INVISIBLE);
        }
        if(part4.equals("")){
            tx2.setVisibility(View.INVISIBLE);
        }
        if(part5.equals("")){
            tx3.setVisibility(View.INVISIBLE);
        }
        if(part6.equals("")){
            tx4.setVisibility(View.INVISIBLE);
        }
        if(part7.equals("")){
            tx5.setVisibility(View.INVISIBLE);
        }
        if(part8.equals("")){
            tx6.setVisibility(View.INVISIBLE);
        }
        if(part9.equals("")){
            tx7.setVisibility(View.INVISIBLE);
        }
        if(part10.equals("")){
            tx8.setVisibility(View.INVISIBLE);
        }

    }

    public class myXaxs implements IAxisValueFormatter {
        private String[] mValues;

        public myXaxs(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int) value];
        }
    }




}
