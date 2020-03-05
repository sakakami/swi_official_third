package com.switube.www.landmark2018test;

import android.app.Application;

import com.switube.www.landmark2018test.util.AppData;

import java.util.Locale;

/**
 * language index
 * 0 ==> en
 * 1 ==> tw
 * 2 ==> cn
 * 3 ==> jp
 */
public class MyApplication extends Application {
    private static MyApplication application;
    private static AppData appData;
    private static int languageIndex = 0;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        appData = new AppData();
        switch (Locale.getDefault().getLanguage()) {
            case "zh":
                switch (Locale.getDefault().getCountry()) {
                    case "TW":
                        languageIndex = 1;
                        break;
                    case "CN":
                        languageIndex = 2;
                        break;
                    default:
                        languageIndex = 0;
                        break;
                }
                break;
            case "jp":
                languageIndex = 3;
                break;
            default:
                languageIndex = 0;
                break;
        }
    }

    public static MyApplication getInstance() {
        return application;
    }

    public static AppData getAppData() {
        return appData;
    }

    public static int getLanguageIndex() {
        return languageIndex;
    }
}
