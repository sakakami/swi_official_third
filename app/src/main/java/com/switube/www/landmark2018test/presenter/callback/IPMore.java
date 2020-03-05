package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.gson.GMusicRadio;

public interface IPMore {
    void handleMoreData(GMusicRadio gMusicRadio);
    void handleStrokeData(GMusicRadio gMusicRadio, GMusicRadio more);
}
