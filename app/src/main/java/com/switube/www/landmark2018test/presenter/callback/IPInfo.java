package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.gson.GLikeUnlike;

import java.util.List;

public interface IPInfo {
    void handleDetailGson(GInfoData gInfoData);
    void handleLikeOrUnlikeJson(GLikeUnlike gLikeUnlike, int num);
    void handleAttractionStyle(AttractionStyleEntity attractionStyleEntity, GInfoData gInfoData);
    void handleAttractionItem(String style, List<AttractionItemEntity> attractionItemEntities, GInfoData gInfoData);
    void handleSaveList(GSaveList gSaveList);
    void handleFinishAddToStroke(GSaveList gSaveList, List<Boolean> isClickedList, String attractionId);
    void handleFinishCreateNewStroke(GSendLove gSendLove);
    void handleFinishAddToCollect(GSendLove gSendLove);
    void handleFinishDelete(GSendLove gSendLove, int index);
    void handleAttractionClass(String style, GInfoData gInfoData, AttractionClassEntity attractionClassEntity);
}
