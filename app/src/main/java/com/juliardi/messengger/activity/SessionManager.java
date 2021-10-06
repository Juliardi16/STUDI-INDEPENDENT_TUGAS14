package com.juliardi.messengger.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.juliardi.messengger.model.Login;

import java.util.HashMap;

public class SessionManager {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor shpEditor;

    public static  final String IS_LOGGED_IN ="isloggedIn";
    public static  final String NIK ="nik";
    public static  final String USERNAME ="username";
    public static  final String PASSWORD ="password";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        shpEditor=sharedPreferences.edit();
    }

    public void createLoginSession(Login user){
        shpEditor.putBoolean(IS_LOGGED_IN, true);
        shpEditor.putString(NIK,user.getNik());
        shpEditor.putString(USERNAME,user.getUsername());
        shpEditor.putString(PASSWORD,user.getPassword());
        shpEditor.commit();

    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(NIK,sharedPreferences.getString(NIK,null));
        user.put(USERNAME,sharedPreferences.getString(USERNAME,null));
        user.put(PASSWORD,sharedPreferences.getString(PASSWORD,null));

        return user;
    }

    public void logoutSession(){
        shpEditor.clear();
        shpEditor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN,false);
    }
}
