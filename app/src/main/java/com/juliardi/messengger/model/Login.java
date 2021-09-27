package com.juliardi.messengger.model;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("pesan_error")
    private String pesanError;

    @SerializedName("error_pesan")
    private String errorPesan;

    @SerializedName("nik")
    private String nik;

    @SerializedName("nama")
    private String nama;

    @SerializedName("email")
    private String email;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public String getPesanError() {
        return pesanError;
    }

    public void setPesanError(String pesanError) {
        this.pesanError = pesanError;
    }

    public String getErrorPesan() {
        return errorPesan;
    }

    public void setErrorPesan(String errorPesan) {
        this.errorPesan = errorPesan;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}

