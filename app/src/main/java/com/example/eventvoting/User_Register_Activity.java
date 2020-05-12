package com.example.eventvoting;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class User_Register_Activity extends AppCompatActivity {
Spinner spinner;

    String name,password,cpassword,sec_ans,record;
    EditText usr,pas,cpas,sa;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__register);
        spinner=findViewById(R.id.sp1);
        String [] sec_que={"favourite language","favourite food","nick name","favourite game","first school","first teacher"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,sec_que);
        spinner.setAdapter(adapter);
        usr=findViewById(R.id.user_name_user);
        pas=findViewById(R.id.user_edt_pass);
        cpas=findViewById(R.id.user_conf_pass);
        sa=findViewById(R.id.user_edt_sa);
        btn=findViewById(R.id.btn_login_u);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



                switch (position)

                {

                    case 0:
                        record = "favourite language";

                        break;

                    case 1:

                        record = "favourite food";

                        break;

                    case 2:

                        record = "nick name";

                        break;
                    case 3:

                        record = "favourite game";

                        break;
                    case 4:

                        record = "first school";

                        break;
                    case 5:

                        record = "first teacher";

                        break;

                }

            }

            @Override

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



    }

    public void userbtn(View view){
        name = usr.getText().toString();
        password = pas.getText().toString();
        cpassword =cpas.getText().toString();
        sec_ans =sa.getText().toString();

        String method = "register";
        if(name.equals("")||password.equals("")||record.equals("")||sec_ans.equals("")) {
            //Toast.makeText(User_Register_Activity.this, "Plz enter the fields", Toast.LENGTH_LONG).show();
            if (name.equals("")) {
                usr.setError("Enter the Name");
                usr.requestFocus();
                usr.setText("");
            }
            else if(password.equals("")){
                pas.setError("Enter the password");
                pas.requestFocus();
                pas.setText("");
            }
            else if(record.equals("")){
                sa.setError("Select the Security Question");

            }
            else if(sec_ans.equals("")){
                sa.setError("Enter the Security Answer");
                sa.requestFocus();
                sa.setText("");
            }
        }
        else
        {
            if(password.equals(cpassword)) {
                Userreg regLog = new Userreg(this);
                regLog.execute(method, name, password, record, sec_ans);
            }
            else
            {
               // Toast.makeText(User_Register_Activity.this, "Password must match", Toast.LENGTH_LONG).show();
                pas.setError("Password must match");
                pas.requestFocus();
                pas.setText("");
                cpas.setText("");
            }
        }
    }
}
