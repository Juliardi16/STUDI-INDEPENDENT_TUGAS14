package com.juliardi.messengger.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juliardi.messengger.R;
import com.juliardi.messengger.model.DataModel;

import java.util.List;

public class DataKerja extends RecyclerView.Adapter<DataKerja.HolderData>{

    private Context ctx;
    private List<DataModel> lisData;

    public DataKerja(Context ctx, List<DataModel> lisData) {
        this.ctx = ctx;
        this.lisData = lisData;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);
        HolderData holderData = new HolderData(view);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
     DataModel dataModel = lisData.get(position);

        holder.noJob.setText(dataModel.getNoJob());
        holder.pic.setText(dataModel.getPic());
        holder.departement.setText(dataModel.getDepartement());
        holder.customer.setText(dataModel.getCustomer());
        holder.keperluan.setText(dataModel.getKeperluan());
        holder.namaDokumen.setText(dataModel.getNamaDokumen());
        holder.tujuan.setText(dataModel.getTujuan());


    }

    @Override
    public int getItemCount() {
        return lisData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{

        TextView noJob,pic,departement,customer,keperluan,namaDokumen,tujuan;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            noJob =itemView.findViewById(R.id.tv_no_job);
            pic =itemView.findViewById(R.id.tv_pic);
            departement =itemView.findViewById(R.id.tv_departement);
            customer =itemView.findViewById(R.id.tv_customer);
            keperluan =itemView.findViewById(R.id.tv_Keperluan);
            namaDokumen =itemView.findViewById(R.id.tv_nm_dokumen);
            tujuan =itemView.findViewById(R.id.tv_tujuan);

        }
    }
}