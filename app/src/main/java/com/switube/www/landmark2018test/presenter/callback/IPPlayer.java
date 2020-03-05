package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.database.entity.MusicEntity;
import com.switube.www.landmark2018test.gson.GPlayer;
import com.switube.www.landmark2018test.gson.GPushNewVideo;
import com.switube.www.landmark2018test.gson.GReport;
import com.switube.www.landmark2018test.gson.GSendLove;

import java.util.List;

public interface IPPlayer {
    void handleMusicData(GPlayer gPlayer);
    void handleFinishInsert(GPlayer gPlayer);
    void init(List<MusicEntity> musicEntities, boolean needRandom, int randomMode, boolean isAllShow);
    void handleSmallRange(List<MusicEntity> musicEntities);
    void handleMiddleRange(List<MusicEntity> musicEntities);
    void handleLargeRange(List<MusicEntity> musicEntities);
    void handleFinishSend();
    void handleLoveData(GSendLove gSendLove, boolean isPush, int pushIndex);
    void handleReportSuccess(GReport gReport);
    void handlePushNewVideoSuccess(GPushNewVideo gPushNewVideo);
}
