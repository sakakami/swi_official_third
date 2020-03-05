package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.gson.GPushStroke;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.gson.GStrokeList;

import java.util.List;

public interface IPStroke {
    void handleStrokeList(GSaveList gSaveList);
    void finishSend(int mode, GSendLove gSendLove);
    void handlePushStrokeData(GPushStroke gPushStroke);
    void handleStrokeList(GStrokeList gStrokeList);
    void init(List<AttractionStyleEntity> attractionStyleEntities);
    void handleFinishAddToCollect(GSendLove gSendLove, int index);
    void handleFinishToStroke(GSendLove gSendLove, int index);
    void handleSaveList(GSaveList gSaveList);
    void handleFinishAddToStroke(GSaveList gSaveList, List<Boolean> isClickedList, String attractionId);
    void handleFinishCreate(GSendLove gSendLove, String title);
    void handleFinishAddToMyStroke(GSendLove gSendLove);
}
