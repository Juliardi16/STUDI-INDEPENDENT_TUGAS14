package com.juliardi.messengger.res;



import com.juliardi.messengger.model.DataModel;
import com.juliardi.messengger.model.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("login/login_user.php")
    Call<Login>loginUser
            (@Query("username") String username,
             @Query("password") String password);

    @FormUrlEncoded
    @POST("login/register_user.php")
    Call<Login>registerUser
            (@Field("nik") String nik,
             @Field("nama")String nama,
             @Field("email") String email,
             @Field("username") String username,
             @Field("password")String password);

    @GET("utils/show_data.php?no_job")
    Call<List<DataModel>> showData();

    @FormUrlEncoded
    @POST("utils/tambah_data.php")
    Call<DataModel> tambahData(
            @Field("no_job") String no_job,
            @Field("pic") String nama_pic,
             @Field("departement") String departement,
             @Field("customer") String customer,
             @Field("keperluan") String keperluan,
             @Field("nama_dokumen") String nama_dokumen,
             @Field("tujuan") String tujuan);


    @FormUrlEncoded
    @POST("utils/update_data.php")
    Call<DataModel> updateData(@Field("no_job") String no_job);

    @FormUrlEncoded
    @POST("utils/hapus_data.php")
    Call<DataModel> hapusData(@Field("no_job") String no_job);


}
