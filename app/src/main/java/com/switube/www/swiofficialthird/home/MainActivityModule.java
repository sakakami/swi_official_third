package com.switube.www.swiofficialthird.home;

import com.switube.www.swiofficialthird.home.model.MainActivityModel;
import com.switube.www.swiofficialthird.home.presenter.IMainActivityPresenter;
import com.switube.www.swiofficialthird.home.presenter.MainActivityPresenter;
import com.switube.www.swiofficialthird.home.view.IMainActivity;
import com.switube.www.swiofficialthird.home.view.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    private MainActivity mMainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    /*@Provides
    public IMainActivity ProvidesMainActivityPresenter() {
        return mIMainActivity;
    }*/

    @Provides
    @MainActivityScope
    public IMainActivity providesIMainActivity() {
        return mMainActivity;
    }

    /*@Provides
    @MainActivityScope
    public IMainActivityPresenter providesIMainActivityPresenter() {
        return mMainActivityPresenter;
    }*/

    //private MainActivityPresenter mMainActivityPresenter;
    @Provides
    @MainActivityScope
    public MainActivityPresenter providesMainActivityPresenter(IMainActivity iMainActivity) {
        return new MainActivityPresenter(iMainActivity);
    }

    /*@Provides
    @MainActivityScope
    public MainActivityModel providesMainActivityModel(IMainActivityPresenter iMainActivityPresenter) {
        return new MainActivityModel(iMainActivityPresenter);
    }*/
}
