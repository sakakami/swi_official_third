package com.switube.www.swiofficialthird.home;

import com.switube.www.swiofficialthird.home.view.IMainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    private IMainActivity mIMainActivity;

    public MainActivityModule(IMainActivity iMainActivity) {
        mIMainActivity = iMainActivity;
    }

    @Provides
    public IMainActivity ProvidesMainActivityPresenter() {
        return mIMainActivity;
    }
}
