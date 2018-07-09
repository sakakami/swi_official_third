package com.switube.www.swiofficialthird.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.jetbrains.annotations.Contract;

public class SharePreferencesUtil {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private SharePreferencesUtil() {}

    @Contract(pure = true)
    public static SharePreferencesUtil getInstance() {
        return BaseSharePreferencesUtilHolder.INSTANCE;
    }

    public SharedPreferences getSharedPreferences(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return mSharedPreferences;
    }

    public SharedPreferences.Editor getEditor(Context context) {
        if (mEditor == null) {
            mEditor = getSharedPreferences(context).edit();
        }
        return mEditor;
    }

    private static class BaseSharePreferencesUtilHolder {
        private static final SharePreferencesUtil INSTANCE = new SharePreferencesUtil();
    }
}
