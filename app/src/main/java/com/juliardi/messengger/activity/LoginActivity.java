package com.juliardi.messengger.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.juliardi.messengger.MainActivity;
import com.juliardi.messengger.R;
import com.juliardi.messengger.model.Login;
import com.juliardi.messengger.res.ApiClient;
import com.juliardi.messengger.res.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText user, pass;
    Button btnlogin;
    TextView tvRegister;

    SessionManager sessionManager;
    String username, password;
    ApiService apiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        btnlogin = findViewById(R.id.btn_login);


        tvRegister = findViewById(R.id.tv_registrasi_account);
        tvRegister.setOnClickListener(this);


        btnlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                username = user.getText().toString();
                password = pass.getText().toString();

                login(username, password);
                break;
            case R.id.tv_registrasi_account:
                Intent intentT = new Intent(this, RegisterActivity.class);
                startActivity(intentT);
                break;

        }

    }

    private void login(String username, String password) {
        apiService = ApiClient.getApiService();
        Call<Login> login = apiService.loginUser(username, password);
        login.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()) {

                    sessionManager = new SessionManager(LoginActivity.this);
                    Login login = response.body();
                    sessionManager.createLoginSession(login);

                    Toast.makeText(LoginActivity.this, "Welcome " + response.body().getUsername(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,response.body().getPesanError(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginActivity.this,t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();


            }
        });

    }
}

