package com.example.kafshapp.model;

import android.content.Context;
import android.content.SharedPreferences;

public class ShearedPerfernce {
    private SharedPreferences sharedPerferns;

    public ShearedPerfernce(Context context) {
        this.sharedPerferns = context.getSharedPreferences("LogIn",Context.MODE_PRIVATE);
    }

    public void SaveUser(String firstName,String password,String phone){
        SharedPreferences.Editor editor = sharedPerferns.edit();
        editor.putString("firstName",firstName);
        editor.putString("password",password);
        editor.putString("phone",phone);
        editor.apply();
    }

    public void SaveUser2(String firstName,String password){
        SharedPreferences.Editor editor = sharedPerferns.edit();
        editor.putString("firstName",firstName);
        editor.putString("password",password);
        editor.apply();
    }
    public void DeleteUser(){
        SharedPreferences.Editor editor = sharedPerferns.edit();
        editor.clear();
        editor.apply();
    }

    public void SaveName(String name){
        SharedPreferences.Editor editor = sharedPerferns.edit();
        editor.putString("firstName",name);
        editor.apply();
    }

    public void SavePhone(String phone){
        SharedPreferences.Editor editor = sharedPerferns.edit();
        editor.putString("phone",phone);
        editor.apply();
    }

    public void SaveAddress(String address){
        SharedPreferences.Editor editor = sharedPerferns.edit();
        editor.putString("address",address);
        editor.apply();
    }

    public String getFirstName(){
        return sharedPerferns.getString("firstName","");
    }

    public String getLastName(){
        return sharedPerferns.getString("password","");
    }

    public String getPhone(){
        return sharedPerferns.getString("phone","");
    }

    public String getAddress(){
        return sharedPerferns.getString("address","");
    }
}


