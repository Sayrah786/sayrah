package com.example.eventvoting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ch_Op_Activity extends AppCompatActivity {
    Button adm,usr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch__op);
        adm=findViewById(R.id.btn_admin);
        usr=findViewById(R.id.btn_user);

    }
    public void but_click(View v)
    {
        Intent in=new Intent(Ch_Op_Activity.this,loginActivity.class);
        startActivity(in);
    }
    public void btn_usr_click(View v)
    {
        Intent in=new Intent(Ch_Op_Activity.this,User_Login_Activity.class);
        startActivity(in);
    }
}
