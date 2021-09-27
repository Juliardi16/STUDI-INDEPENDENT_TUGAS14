package com.juliardi.messengger.model;


import com.google.gson.annotations.SerializedName;

public class DataModel {

    @SerializedName("no_job")
    private String noJob;

    @SerializedName("pic")
    private String pic;

    @SerializedName("departement")
    private String departement;

    @SerializedName("customer")
    private String customer;

    @SerializedName("keperluan")
    private String keperluan;

    @SerializedName("nama_dokumen")
    private String namaDokumen;

    @SerializedName("tujuan")
    private String tujuan;

    public String getNoJob() {
        return noJob;
    }

    public void setNoJob(String noJob) {
        this.noJob = noJob;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }

    public String getNamaDokumen() {
        return namaDokumen;
    }

    public void setNamaDokumen(String namaDokumen) {
        this.namaDokumen = namaDokumen;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }
}

