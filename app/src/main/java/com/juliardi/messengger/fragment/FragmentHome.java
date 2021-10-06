package com.juliardi.messengger.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juliardi.messengger.R;
import com.juliardi.messengger.adapter.DataKerja;
import com.juliardi.messengger.adapter.SliderAdapter;
import com.juliardi.messengger.model.DataModel;
import com.juliardi.messengger.model.SliderItem;
import com.juliardi.messengger.res.ApiClient;
import com.juliardi.messengger.res.ApiService;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentHome extends Fragment{
    private RecyclerView recyclerView = null;
    ApiService apiService;
    private MutableLiveData<List<DataModel>> mutableLiveData;

    private List<SliderItem> sliderItemList;
    SliderView sliderView;




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

        sliderView = view.findViewById(R.id.imageSlider);
        loadImage();
        sliderView.setSliderAdapter(new SliderAdapter(getContext(),sliderItemList));

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });
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


    private void loadImage() {
        sliderItemList = new ArrayList<>();
        int[] gambar = {R.drawable.gambar_kapal_1,R.drawable.gambar_kapal_2,R.drawable.gambar_kapal_3};
        String[] descs = {"KMTC NAVA SEVA","CMA CGM GROUP","MAERSK LINE"};
        int count = 0;
        for (int image : gambar) {
            SliderItem sliderModel = new SliderItem(image,descs[count]);
            sliderItemList.add(sliderModel);
            count++;
        }

    }

}