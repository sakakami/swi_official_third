package com.switube.www.swiofficialthird.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

@Module
public class SharePreferencesModule {
    @Provides
    @MyApplicationScope
    public SharedPreferences providesSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @MyApplicationScope
    public SharedPreferences.Editor providesEditor(SharedPreferences sharedPreferences) {
        return sharedPreferences.edit();
    }
}
