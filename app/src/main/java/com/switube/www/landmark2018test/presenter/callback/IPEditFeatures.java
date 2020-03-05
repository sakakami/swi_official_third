package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;
import com.switube.www.landmark2018test.entity.EFeatures;
import com.switube.www.landmark2018test.gson.GSendLove;

import java.util.List;

public interface IPEditFeatures {
    void handleStyleData(AttractionStyleEntity attractionStyleEntity, String msid);
    void handleClassData(List<AttractionClassEntity> attractionClassEntities, String msid, List<EFeatures> eFeaturesList);
    void handleTermData(List<AttractionTermEntity> attractionTermEntities, List<EFeatures> eFeaturesList);
    void handleItemData(List<AttractionItemEntity> attractionItemEntities, List<AttractionTermEntity> attractionTermEntities, List<EFeatures> eFeaturesList);
    void finishSend(GSendLove gSendLove);
}
