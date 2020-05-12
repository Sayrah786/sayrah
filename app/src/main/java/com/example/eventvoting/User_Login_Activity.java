package com.example.eventvoting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class User_Login_Activity extends AppCompatActivity {
TextView txt,txt1;
    EditText ET_NAME,ET_PASS;
    String login_name,login_pass;
    String url="http://192.168.1.100:10080/eventvoting/userfargetpass.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__login);
        txt=findViewById(R.id.usr_reg);
        txt1=findViewById(R.id.link_user_for_pass);
        ET_NAME=findViewById(R.id.edt_user_name);
        ET_PASS=findViewById(R.id.edt_pass_word);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(User_Login_Activity.this,User_Register_Activity.class);
                startActivity(in);
            }
        });


    }
    public void logbtn(View view)
    {
        login_name = ET_NAME.getText().toString();
        login_pass = ET_PASS.getText().toString();
        String method = "login";
        Userreg regLog = new Userreg(this);
        regLog.execute(method,login_name,login_pass);
        loginCheck();


    }
    public void btn_user_forget(View view){
        String admin=ET_NAME.getText().toString();
        if (admin.equals("")){
            Toast.makeText(User_Login_Activity.this,"You should enter admin name",Toast.LENGTH_SHORT).show();
        }
        else {
            String type="user";
            final Forgetlist d1=new Forgetlist(this,url,type);
            d1.execute(admin);
        }
    }
    void loginCheck(){
        login_name = ET_NAME.getText().toString();
        login_pass = ET_PASS.getText().toString();
        //check username and password are correct and then add them to SharedPreferences
        SharedPreferences sp=getSharedPreferences("user_login", Context.MODE_PRIVATE);


        SharedPreferences.Editor e = sp.edit();
        e.clear();
        e.putString("username", login_name);
        e.putString("password", login_pass);

        //Set name and email in global/application context

        //  session_id=login_name;
        e.commit();


    }

}
