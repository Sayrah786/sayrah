package com.example.eventvoting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class User_FPass_Activity extends AppCompatActivity {
    TextView txt;
    EditText edt;
    String sec_que,sec_ans,admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__fpass);
        txt=findViewById(R.id.link_for_pass1);
        edt=findViewById(R.id.edt_user_pass1);
        admin=getIntent().getExtras().getString("admin");
        sec_que=getIntent().getExtras().getString("sec_que");
        sec_ans=getIntent().getExtras().getString("sec_ans");
        txt.setText(sec_que);
    }
    public void btn_for1(View view){
        String s_a=edt.getText().toString();
        if(s_a.equals("")){
            Toast.makeText(User_FPass_Activity.this,"You should enter Security Answer",Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (s_a.equals(sec_ans)){
                Intent intent=new Intent(User_FPass_Activity.this,UserPassActivity.class);
                intent.putExtra("admin", admin);
                startActivity(intent);
            }
            else {
                Toast.makeText(User_FPass_Activity.this,"Security answer wrong",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
