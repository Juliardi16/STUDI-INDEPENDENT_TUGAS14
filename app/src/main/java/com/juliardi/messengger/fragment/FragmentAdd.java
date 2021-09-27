package com.juliardi.messengger.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.juliardi.messengger.R;
import com.juliardi.messengger.activity.LoginActivity;
import com.juliardi.messengger.activity.RegisterActivity;
import com.juliardi.messengger.model.DataModel;
import com.juliardi.messengger.model.Login;
import com.juliardi.messengger.res.ApiClient;
import com.juliardi.messengger.res.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentAdd extends Fragment {
   private EditText noJob,pic,departement,customer,keperluan,namaDokumen,tujuan;
   Button btnSave;
   String nJob ,nPic,nDepartement,nCustomer,nKeperluan,nNmaDokumen,nTujuan;


    public FragmentAdd() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add,container,false);
        noJob = view.findViewById(R.id.et_add_noJob);
        pic = view.findViewById(R.id.et_namaPic);
        departement = view.findViewById(R.id.et_departement);
        customer = view.findViewById(R.id.et_customer);
        keperluan = view.findViewById(R.id.et_keperluan);
        namaDokumen = view.findViewById(R.id.et_namaDokumen);
        tujuan = view.findViewById(R.id.et_tujuan);
        btnSave = view.findViewById(R.id.btn_register);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData(nJob ,nPic,nDepartement,nCustomer,nKeperluan,nNmaDokumen,nTujuan);
            }
        });

        return view;
    }
    private void addData(String noJob,String namaPic,String departement,String customer,
                         String keperluan,String namaDokumen, String tujuaan) {
        ApiService apiService = ApiClient.getApiService();
        Call<DataModel> addDataModel = apiService.tambahData(noJob, namaPic, departement, customer, keperluan, namaDokumen, tujuaan);
        addDataModel.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if(response.isSuccessful()){
                    Log.d(FragmentAdd.class.getSimpleName(),"");
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.e(FragmentAdd.class.getSimpleName(),t.toString());
            }
        });

    }
}