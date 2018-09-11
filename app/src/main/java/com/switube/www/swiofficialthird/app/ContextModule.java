package com.switube.www.swiofficialthird.app;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private final Context mContext;
    public ContextModule(@NotNull Context context) {
        mContext = context;
    }

    @Provides
    @MyApplicationScope
    public Context providesContext() {
        return mContext;
    }
}
