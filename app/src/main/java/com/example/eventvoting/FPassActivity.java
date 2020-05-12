package com.example.eventvoting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FPassActivity extends AppCompatActivity {
TextView txt;
EditText edt;
String sec_que,sec_ans,admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fpass);
        txt=findViewById(R.id.link_for_pass);
        edt=findViewById(R.id.edt_pass_sa);
        admin=getIntent().getExtras().getString("admin");
        sec_que=getIntent().getExtras().getString("sec_que");
        sec_ans=getIntent().getExtras().getString("sec_ans");
        txt.setText(sec_que);
    }
    public void btn_for(View view){
        String s_a=edt.getText().toString();
        if(s_a.equals("")){
            Toast.makeText(FPassActivity.this,"You should enter Security Answer",Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (s_a.equals(sec_ans)){
                Intent intent=new Intent(FPassActivity.this,NewPassActivity.class);
                intent.putExtra("admin", admin);
                startActivity(intent);
            }
            else {
                Toast.makeText(FPassActivity.this,"Security answer wrong",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
