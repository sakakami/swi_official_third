package com.switube.www.landmark2018test.presenter;

import android.content.Context;
import android.net.ConnectivityManager;

public class PLogo {
    public PLogo() {}

    public boolean checkNetworkState(Context context) {
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager != null && manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isAvailable();
    }
}
