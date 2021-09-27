package com.juliardi.messengger.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.juliardi.messengger.MainActivity;
import com.juliardi.messengger.R;
import com.juliardi.messengger.model.DataModel;
import com.juliardi.messengger.res.ApiClient;
import com.juliardi.messengger.res.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        TextView noJob_detail = findViewById(R.id.tv_no_job_detail);
        TextView pic_detail = findViewById(R.id.tv_pic_detail);
        TextView departement_detail = findViewById(R.id.tv_departement_detail);
        TextView customer_detail = findViewById(R.id.tv_customer_detail);
        TextView keperluan_detail = findViewById(R.id.tv_Keperluan_detail);
        TextView nama_dokumen_detail = findViewById(R.id.tv_nm_dokumen_detail);
        TextView tujuan_detail = findViewById(R.id.tv_tujuan_detail);
        Button btnUpdate = findViewById(R.id.btn_update);
        Button btndelete = findViewById(R.id.btn_delete);


        String no_job =getIntent().getStringExtra("no_job");
        String pic =getIntent().getStringExtra("pic");
        String departemen =getIntent().getStringExtra("departement");
        String customer =getIntent().getStringExtra("customer");
        String keperluan =getIntent().getStringExtra("keperluan");
        String nama_dokumen =getIntent().getStringExtra("nama_dokumen");
        String tujuan =getIntent().getStringExtra("tujuan");

        noJob_detail.setText(no_job);
        pic_detail.setText(pic);
        departement_detail.setText(departemen);
        customer_detail.setText(customer);
        keperluan_detail.setText(keperluan);
        nama_dokumen_detail.setText(nama_dokumen);
        tujuan_detail.setText(tujuan);

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService apiService = ApiClient.getApiService();
                apiService.hapusData(no_job).enqueue(new Callback<DataModel>() {
                    @Override
                    public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                        Toast.makeText(DetailActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }

                    @Override
                    public void onFailure(Call<DataModel> call, Throwable t) {
                        Toast.makeText(DetailActivity.this, "gagal menghapus data", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService apiService = ApiClient.getApiService();
                apiService.updateData(no_job).enqueue(new Callback<DataModel>() {
                    @Override
                    public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                        Toast.makeText(DetailActivity.this, "Data berhasil diUpdate", Toast.LENGTH_SHORT).show();
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }

                    @Override
                    public void onFailure(Call<DataModel> call, Throwable t) {
                        Toast.makeText(DetailActivity.this, "gagal mengupdate data", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });




    }
}
