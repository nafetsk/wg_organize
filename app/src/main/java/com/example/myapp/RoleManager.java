package com.example.myapp;

import android.content.Context;
import android.content.SharedPreferences;

public class RoleManager {
    private static final String PREF_NAME = "RolePreference";
    private static final String KEY_WG_NAME = "wgName";
    private static final String KEY_MITBEWOHNER_NAME = "mitbewohnerName";

    public static void saveRole(Context context, String wgName, String mitbewohnerName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_WG_NAME, wgName);
        editor.putString(KEY_MITBEWOHNER_NAME, mitbewohnerName);
        editor.apply();
    }

    public static String getWGName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_WG_NAME, null);
    }

    public static String getMitbewohnerName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_MITBEWOHNER_NAME, null);
    }

    public static void clearRole(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_WG_NAME);
        editor.remove(KEY_MITBEWOHNER_NAME);
        editor.apply();
    }
}
