package com.example.esportacademy.app;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSessionManager {
    private static UserSessionManager instance;
    private static Context context;
    private static final String TAG = "UserSession";
    private static final String KEY_USERNAME = "UserNameKey";
    private static final String KEY_USER_ID = "UserIDKEY";


    private UserSessionManager(Context context) {
        this.context = context;
    }

    public static synchronized UserSessionManager getInstance(Context context) {
        if (instance == null) {
            instance = new UserSessionManager(context);
        }
        return instance;
    }

    public boolean setLogin(int id,String username) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME,username);
        editor.putInt(KEY_USER_ID,id);
        editor.apply();
        return true;
    }

    public boolean isLoggedin() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG,context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USERNAME,null)!=null) {
            return true;
        }else {
            return false;
        }
    }

    public boolean setLogout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
    public String getUsername() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG,context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME,null);
    }

    public int getUserID() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG,context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_USER_ID,0);
    }


}

