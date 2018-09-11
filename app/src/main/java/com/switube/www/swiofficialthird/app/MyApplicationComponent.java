package com.switube.www.swiofficialthird.app;

import android.content.SharedPreferences;

import com.switube.www.swiofficialthird.util.INetwork;

import dagger.Component;

@MyApplicationScope
@Component(modules = {NetworkModule.class, ContextModule.class, SharePreferencesModule.class})
public interface MyApplicationComponent {
    INetwork getINetwrok();
    SharedPreferences getSharePerences();
    SharedPreferences.Editor getEditor();
}
