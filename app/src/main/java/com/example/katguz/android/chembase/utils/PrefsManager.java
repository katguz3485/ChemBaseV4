package com.example.katguz.android.chembase.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Oleg on 2017-07-05.
 */

public class PrefsManager {


    private static final String PREFS_KEY = "com.example.sda.retrofitapp.sharedprefs";
    //private static final String TOKEN_KEY = "token_key";


    private SharedPreferences preferences;

    public PrefsManager(Context context) {
        preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
    }

/*  public void saveToken(String token) {
        preferences.edit()
                .putString(TOKEN_KEY, token)
                .apply();
    }

    public String getToken() {
        return preferences.getString(TOKEN_KEY, null);
    }*/

}

