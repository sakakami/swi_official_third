package com.switube.www.swiofficialthird.home;

import com.switube.www.swiofficialthird.home.view.MainActivity;

import dagger.Component;

@MainActivityScope
@Component(modules = {MainActivityModule.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
