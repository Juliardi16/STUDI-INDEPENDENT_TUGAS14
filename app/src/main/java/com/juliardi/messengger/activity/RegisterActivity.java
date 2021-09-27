package com.juliardi.messengger.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.juliardi.messengger.MainActivity;
import com.juliardi.messengger.R;
import com.juliardi.messengger.model.Login;
import com.juliardi.messengger.res.ApiClient;
import com.juliardi.messengger.res.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nik,nama,email,username,password;
     String nk,namaUser,userName,passWord,Email;
    Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nik = findViewById(R.id.ti_registerNik);
        nama = findViewById(R.id.ti_registerName);
        email = findViewById(R.id.ti_RegisterEmail);
        username = findViewById(R.id.ti_RegisterUsername);
        password = findViewById(R.id.ti_RegisterPassword);

        btnRegister =findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                nk = nik.getText().toString();
                namaUser = nama.getText().toString();
                userName = username.getText().toString();
                passWord = password.getText().toString();
                Email = email.getText().toString();
                register(nk,namaUser,Email,userName,passWord);
                finish();
                break;
        }

    }

    private void register(String nik,String nama,String email,String username,String password) {
        ApiService apiService = ApiClient.getApiService();
        Call<Login> login = apiService.registerUser(nik,nama, email, username, password);
        login.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
               if(response.body()!=null && response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Behasil Registrasi", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
               }else{
                    Toast.makeText(RegisterActivity.this,"gagal register",Toast.LENGTH_SHORT).show();
                }
           }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"gagal register",Toast.LENGTH_SHORT).show();


            }
        });

    }
}
