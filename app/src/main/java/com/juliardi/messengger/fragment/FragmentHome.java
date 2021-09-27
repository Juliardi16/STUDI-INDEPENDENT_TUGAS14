package com.juliardi.messengger.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juliardi.messengger.R;
import com.juliardi.messengger.adapter.DataKerja;
import com.juliardi.messengger.model.DataModel;
import com.juliardi.messengger.model.Login;
import com.juliardi.messengger.res.ApiClient;
import com.juliardi.messengger.res.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentHome extends Fragment {
    private RecyclerView recyclerView = null;
    ApiService apiService;
    private MutableLiveData<List<DataModel>> mutableLiveData;




    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView = view.findViewById(R.id.rc_view_fragment_home);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getData();

        return view;
    }
    public  void getConnect(){
        apiService = ApiClient.getApiService();
        apiService.showData().enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                    List<DataModel> dataModels = response.body();
                    recyclerView.setAdapter(new DataKerja(getContext(),dataModels));
                  //  mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                t.getLocalizedMessage();

            }
        });


    }
    public LiveData<List<DataModel>> getData(){
        if (mutableLiveData== null){
            mutableLiveData= new MutableLiveData<List<DataModel>>();
            getConnect();
        }

        return mutableLiveData;
    }
}