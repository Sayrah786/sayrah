package com.example.eventvoting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewPassActivity extends AppCompatActivity {
    EditText edt1,edt2;
    String admin,pass,cpass;
    String url="http://192.168.1.100:10080/eventvoting/updateforpass.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pass);
        edt1=findViewById(R.id.pas_new);
        edt2=findViewById(R.id.cpass_new);
        admin=getIntent().getExtras().getString("admin");

    }
    public void btn_new1(View view){
        pass=edt1.getText().toString();
        cpass=edt2.getText().toString();
        if (pass.equals(cpass)){
            String method="updatepass";
            Updatepass updatepass=new Updatepass(this,url);
            updatepass.execute(method,admin,pass);
        }
        else {
            Toast.makeText(NewPassActivity.this,"Password dose't match",Toast.LENGTH_SHORT).show();
        }

    }
}
