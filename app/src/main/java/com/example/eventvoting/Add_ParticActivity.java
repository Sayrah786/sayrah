package com.example.eventvoting;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Add_ParticActivity extends AppCompatActivity {
EditText pa1,pa2,pa3,pa4,pa5,pa6,pa7,pa8,pa9,pa10;
String title,par1,par2,par3,par4,par5,par6,par7,par8,par9,par10,admin,name;
TextView txt;
FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__partic);
        pa1=findViewById(R.id.part1);
        pa2=findViewById(R.id.part2);
        pa3=findViewById(R.id.part3);
        pa4=findViewById(R.id.part4);
        pa5=findViewById(R.id.part5);
        pa6=findViewById(R.id.part6);
        pa7=findViewById(R.id.part7);
        pa8=findViewById(R.id.part8);
        pa9=findViewById(R.id.part9);
        pa10=findViewById(R.id.part10);
        title=getIntent().getExtras().getString("title");
        admin=getIntent().getExtras().getString("admin");
        name=getIntent().getExtras().getString("name");
        txt=findViewById(R.id.title_p);
        txt.setText(title);
        fab=findViewById(R.id.addp);
    }
    public void ed2(View view)
    {
        pa3.setVisibility(View.VISIBLE);
    }
    public void ed3(View view)
    {
        pa4.setVisibility(View.VISIBLE);
    }
    public void ed4(View view)
    {
        pa5.setVisibility(View.VISIBLE);
    }
    public void ed5(View view)
    {
        pa6.setVisibility(View.VISIBLE);
    }
    public void ed6(View view)
    {
        pa7.setVisibility(View.VISIBLE);
    }
    public void ed7(View view)
    {
        pa8.setVisibility(View.VISIBLE);
    }
    public void ed8(View view)
    {
        pa9.setVisibility(View.VISIBLE);
    }
    public void ed9(View view)
    {
        pa10.setVisibility(View.VISIBLE);
    }

    public void addparti(View view)
    {
        par1=pa1.getText().toString();
        par2=pa2.getText().toString();
        par3=pa3.getText().toString();
        par4=pa4.getText().toString();
        par5=pa5.getText().toString();
        par6=pa6.getText().toString();
        par7=pa7.getText().toString();
        par8=pa8.getText().toString();
        par9=pa9.getText().toString();
        par10=pa10.getText().toString();
        if(par1.equals("")||par2.equals(""))
        {
            Toast.makeText(Add_ParticActivity.this, "Field should not be empty", Toast.LENGTH_SHORT).show();

        }
        else
        {
            String method="addparti";
            Addparticipant backgroundTask=new Addparticipant(this);
            backgroundTask.execute(method,par1,par2,par3,par4,par5,par6,par7,par8,par9,par10,admin,name);
        }
    }

    @Override
    public void onBackPressed() {

    }
}
