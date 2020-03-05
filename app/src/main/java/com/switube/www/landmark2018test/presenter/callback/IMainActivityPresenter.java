package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.gson.GSignInData;

public interface IMainActivityPresenter {
    void handleAccountGson(GSignInData gSignInData, String signType, String phptoUrl);
}
