package com.juliardi.messengger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.juliardi.messengger.activity.LoginActivity;
import com.juliardi.messengger.activity.SessionManager;
import com.juliardi.messengger.fragment.FragmentAdd;
import com.juliardi.messengger.fragment.FragmentHome;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

   SessionManager sessionManager;
   TextView tv;
   String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mengecek jika belum login maka akan darahkan ke login activity
        sessionManager = new SessionManager(MainActivity.this);
        if(!sessionManager.isLoggedIn()){
            moveToLogin();
        }


        BottomNavigationView bottomNavigationView =findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

       // tv = findViewById(R.id.tv_info);

        tampil(new FragmentHome());


    }

    private void moveToLogin() {
       Intent intent = new Intent(MainActivity.this, LoginActivity.class);
       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
       startActivity(intent);
       finish();
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.app_name)
                .setMessage("Keluar Aplikasi ?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int i = item.getItemId();
        Fragment fragment = null;
        switch(i){
            case R.id.menuHome:
                fragment = new FragmentHome();
                break;
            case R.id.menuAdd:
                fragment = new FragmentAdd();
                break;

        }
        return tampil(fragment);

    }

    private boolean tampil(Fragment fragment){
//        tv=findViewById(R.id.tv_info);
//        username = sessionManager.getUserDetail().get(SessionManager.USERNAME);
//        tv.setText(username);
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:
                sessionManager.logoutSession();
                moveToLogin();
        }

        return super.onOptionsItemSelected(item);

    }
}
