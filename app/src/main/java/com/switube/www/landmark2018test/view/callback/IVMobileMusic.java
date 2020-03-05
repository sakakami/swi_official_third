package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.gson.GMusicRadio;
import com.switube.www.landmark2018test.gson.GPushMusic;

import java.util.List;

public interface IVMobileMusic {
    void init(GMusicRadio gMusicRadio, List<String> musicTitle, List<String> musicPhotoUrl);
    void init(GPushMusic gPushMusic, List<String> musicTitle, List<String> musicPhotoUrl, List<String> musicCount, List<String> musicLove);
    void showSearchError();
    void refreshLoveData(String save, int index);
}
