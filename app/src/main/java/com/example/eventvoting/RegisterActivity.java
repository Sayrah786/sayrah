package com.example.eventvoting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Spinner sp;
    String name,password,cpassword,sec_ans,email,org_nam,record;
    EditText usr,pas,cpas,sa,em,org;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sp=findViewById(R.id.sp);
        String [] sec_que={"favourite language","favourite food","nick name","favourite game","first school","first teacher"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,sec_que);
        sp.setAdapter(adapter);
        usr=findViewById(R.id.user_name);
        pas=findViewById(R.id.edt_pass);
        cpas=findViewById(R.id.edt_conf_pass);
        sa=findViewById(R.id.edt_sa);
        em=findViewById(R.id.edt_email);
        org=findViewById(R.id.edt_org);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

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
    public void userReg(View view)
    {

        name = usr.getText().toString();
        password = pas.getText().toString();
        cpassword =cpas.getText().toString();
        sec_ans =sa.getText().toString();
        email = em.getText().toString();
        org_nam =org.getText().toString();
        String method = "register";
        if(name.equals("")||password.equals("")||record.equals("")||sec_ans.equals("")||email.equals("")||org_nam.equals("")||(!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))) {
            //Toast.makeText(RegisterActivity.this, "Plz enter the fields", Toast.LENGTH_LONG).show();
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
            else if(org_nam.equals("")){
                org.setError("Enter the Security Answer");
                org.requestFocus();
                org.setText("");
            }
            else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                em.setError("Invalid Email Address");
                em.requestFocus();
                em.setText("");
            }


        }
        else
        {
            if(password.equals(cpassword)) {
                RegLog regLog = new RegLog(this);
                regLog.execute(method, name, password, record, sec_ans, email, org_nam);
            }
            else
            {
               // Toast.makeText(RegisterActivity.this, "Password must match", Toast.LENGTH_LONG).show();
                pas.setError("Password must match");
                pas.requestFocus();
                pas.setText("");
                cpas.setText("");
            }
        }
    }

    public void userbtn(View view) {
    }
}
