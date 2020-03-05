package com.switube.www.landmark2018test.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.switube.www.landmark2018test.MyApplication;

import org.jetbrains.annotations.Contract;

public class SharePreferencesUtil {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private SharePreferencesUtil() {}

    @Contract(pure = true)
    public static SharePreferencesUtil getInstance() {
        return BaseSharePreferencesUtilHolder.INSTANCE;
    }

    public SharedPreferences getSharedPreferences() {
        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
        }
        return mSharedPreferences;
    }

    public SharedPreferences.Editor getEditor() {
        if (mEditor == null) {
            mEditor = getSharedPreferences().edit();
        }
        return mEditor;
    }

    private static class BaseSharePreferencesUtilHolder {
        private static final SharePreferencesUtil INSTANCE = new SharePreferencesUtil();
    }
}
