package com.example.login;

import android.content.Context;
import android.content.SharedPreferences;

public class Sharedpref {
    public static final String SHARED_PREF_NAME="my_shared_preff";
    private static  Sharedpref minstance;
    private Context mctx;

    int counter;



    public Sharedpref(Context mctx) {
        this.mctx = mctx;
    }
    public static synchronized Sharedpref getInstance(Context mctx){
        if (minstance==null){
            minstance=new Sharedpref(mctx);
        }
        return minstance;
    }
    public void saveitem(String key,String s){

        SharedPreferences sharedPreferences=mctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        // counter=sharedPreferences.getInt("count",0);

        editor.putString(key,s);
        //  editor.putInt("pos",position);
        editor.apply();
        editor.commit();


    }
    public String getUser(){

        SharedPreferences sharedPreferences=mctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        return sharedPreferences.getString(SHARED_PREF_NAME,null);
    }

    public static void deleteUser(Context mctx){
        SharedPreferences sharedPreferences=mctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        editor.commit();
    }
}
