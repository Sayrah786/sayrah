package com.example.eventvoting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

public class WinActivity extends AppCompatActivity {
    int a,b,c,d,e,f,g,h,i1,j;
    BarChart barChart;

    String name,admin;
    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,tx1,tx2,tx3,tx4,tx5,tx6,tx7,tx8;
    String url="http://192.168.1.100:10080/eventvoting/partidata.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
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
        name = getIntent().getExtras().getString("name");
        admin = getIntent().getExtras().getString("admin");
        tx1=findViewById(R.id.p3);
        tx2=findViewById(R.id.p4);
        tx3=findViewById(R.id.p5);
        tx4=findViewById(R.id.textView22);
        tx5=findViewById(R.id.textView24);
        tx6=findViewById(R.id.textView26);
        tx7=findViewById(R.id.textView28);
        tx8=findViewById(R.id.textView30);

        txt1=findViewById(R.id.pw1);
        txt2=findViewById(R.id.pw2);
        txt3=findViewById(R.id.pw3);
        txt4=findViewById(R.id.pw4);
        txt5=findViewById(R.id.pw5);
        txt6=findViewById(R.id.textView23);
        txt7=findViewById(R.id.textView25);
        txt8=findViewById(R.id.textView27);
        txt9=findViewById(R.id.textView29);
        txt10=findViewById(R.id.textView31);
        final takeresparti sa=new takeresparti(this,url,txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,tx1,tx2,tx3,tx4,tx5,tx6,tx7,tx8);
        sa.execute(admin,name);
        barChart = findViewById(R.id.user_bar);
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
        BarDataSet barDataSet = new BarDataSet(barEntries, "Result");
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.7f);
        barChart.setData(barData);
        String[] parti = new String[]{"","P1","P2","P3","P4","P5","P6","P7","P8","P9","P10"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new myXaxs(parti));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);


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
