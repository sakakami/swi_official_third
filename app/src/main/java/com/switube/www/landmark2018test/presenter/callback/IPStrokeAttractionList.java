package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSendLove;

import java.util.List;

public interface IPStrokeAttractionList {
    void init(List<AttractionStyleEntity> attractionStyleEntities);
    void handleFinishAddToCollect(GSendLove gSendLove, int index);
    void handleFinishDelToCollect(GSendLove gSendLove, int index);
    void handleSaveList(GSaveList gSaveList);
    void handleFinishAddToStroke(GSaveList gSaveList, List<Boolean> isClickedList, String attractionId);
    void handleFinishToStroke(GSendLove gSendLove, boolean isMove, int index);
    void handleFinishCreate(GSendLove gSendLove, String title);
}
