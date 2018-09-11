package com.switube.www.swiofficialthird.app;

import android.app.Activity;
import android.app.Application;

import org.jetbrains.annotations.NotNull;

public class MyApplication extends Application {
    private MyApplicationComponent myApplicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplicationComponent = DaggerMyApplicationComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .build();
    }

    public static MyApplication getInstance(@NotNull Activity activity) {
        return (MyApplication)activity.getApplication();
    }
}
