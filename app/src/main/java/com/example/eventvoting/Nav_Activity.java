package com.example.eventvoting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class Nav_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView lv;
    String url="http://192.168.1.100:10080/eventvoting/vote.php";
    SharedPreferences sharedprefrance=null;
    SharedPreferences sp=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedprefrance =getApplicationContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        if(sharedprefrance.contains("username")&& sharedprefrance.contains("password"))
        {   String user=sharedprefrance.getString("username","");
            Intent intent=new Intent(Nav_Activity.this,HomeActivity.class);
            intent.putExtra("admin",user);
            startActivity(intent);
            finish();

        }
        sp =getApplicationContext().getSharedPreferences("user_login", Context.MODE_PRIVATE);
        if(sp.contains("username")&& sp.contains("password"))
        {   String user=sp.getString("username","");
            Intent intent=new Intent(Nav_Activity.this,AllEventActivity.class);
            intent.putExtra("user",user);
            startActivity(intent);
            finish();

        }




        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        lv=findViewById(R.id.lvs);
        final displaylist d=new displaylist(this,url,lv);
        d.execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.nav_login) {
           Intent i = new Intent(Nav_Activity.this, Ch_Op_Activity.class);
           startActivity(i);


        }
        else if (id == R.id.nav_share) {
                Intent si=new Intent(Intent.ACTION_SEND);
                si.setType("text/plain");
                String sb="Your body here";
                String ss="Your subject here";
                si.putExtra(Intent.EXTRA_TEXT,sb);
                si.putExtra(Intent.EXTRA_TEXT,ss);
                 startActivity(Intent.createChooser(si,"Share User"));
        }
        else {

       }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
