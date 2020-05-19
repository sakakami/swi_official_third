package com.switube.www.landmark2018test.service.callback;

import android.content.Context;

public interface IFloatPlayerService {
    void showFloatPlayer(boolean needShow);
    void initFloatPlayer(Context context);
    void initFloatPlayerKiller(Context context);
    void showSmallMode();
    void showBigMode();
    void switchPlayMode(boolean mode);
    void handleFloatPlayerMoving(int x, int y);
    void handleBackFromDeskTop(Context context, boolean isFirst);
    void handleKillFloatPlayer();
    String handleYouTubeTime(int time);
    void setIsFullScreenMode(boolean mode);
    void switchFullscreenMode(boolean mode);
    void handleSwitchFullscreen();
    void showFloatPlayerKiller(boolean b);
    void handleLoadVideo(String webid, float startSecond);
    void handlePlayingSwitch();
    void handlePlayerInitialized(String webid, float startSecond);
    boolean getCheckFloatPlayerCreate();
    void setCheckIsDeskTop(boolean b);
    boolean checkIsBigMode();
    boolean checkDeskTop();
    boolean getIsFullScreenMode();
    void handleRemoveFloatView();
    boolean getCheckFloatCanSee();
    void handleLocation();
}
