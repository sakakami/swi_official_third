package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.gson.GMusicRadio;
import com.switube.www.landmark2018test.gson.GPushMusic;
import com.switube.www.landmark2018test.gson.GSendLove;

public interface IPMobileMusic {
    void handleMusicRadioData(GMusicRadio gMusicRadio, boolean isCollect);
    void handlePushMusicData(GPushMusic gPushMusic);
    void handleLoveData(GSendLove gSendLove, int index);

}
