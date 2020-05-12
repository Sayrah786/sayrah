package com.example.eventvoting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;

public class loginActivity extends AppCompatActivity {
TextView txt,txt1;
Button btn;



    EditText ET_NAME,ET_PASS;
    String login_name,login_pass;
String url="http://192.168.1.100:10080/eventvoting/fargetpass.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt = findViewById(R.id.Reg);
        txt1 = findViewById(R.id.link_for_pass);
        btn = findViewById(R.id.btn_login);
        ET_NAME = findViewById(R.id.admin_name);
        ET_PASS = findViewById(R.id.pass_word);

        //String user=sharedprefrance.getString("username","");
     //   String pass=sharedprefrance.getString("password","");



        /*if (sharedprefrance.contains("username") && sharedprefrance.contains("password")){
            startActivity(new Intent(loginActivity.this,HomeActivity.class));
            finish();
        }*/



        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(loginActivity.this,RegisterActivity.class);
                startActivity(in);

            }
        });

    }
    public void userLogin(View view) {
        login_name = ET_NAME.getText().toString();
        login_pass = ET_PASS.getText().toString();

        String method = "login";
        RegLog regLog = new RegLog(this);
        regLog.execute(method, login_name, login_pass);
       loginCheck();
      // finish();


    }
    public void btn_forget(View view){
        String admin=ET_NAME.getText().toString();
        if (admin.equals("")){
            Toast.makeText(loginActivity.this,"You should enter admin name",Toast.LENGTH_SHORT).show();
        }
        else {
            String type="admin";
            final Forgetlist d1=new Forgetlist(this,url,type);
            d1.execute(admin);
        }
    }
  void loginCheck(){
        login_name = ET_NAME.getText().toString();
        login_pass = ET_PASS.getText().toString();
        //check username and password are correct and then add them to SharedPreferences
            SharedPreferences sp=getSharedPreferences("login",Context.MODE_PRIVATE);


                SharedPreferences.Editor e = sp.edit();
                e.clear();
                e.putString("username", login_name);
                e.putString("password", login_pass);

                //Set name and email in global/application context

                //  session_id=login_name;
                e.commit();


    }



}
