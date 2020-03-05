package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;
import com.switube.www.landmark2018test.entity.EFeatures;

import java.util.List;

public interface IPShowFeatures {
    void handleStyleData(AttractionStyleEntity attractionStyleEntity, String msid, String mscid, List<String> mtidList, List<String> mstidList);
    void handleClassData(List<AttractionClassEntity> attractionClassEntities, String msid, List<EFeatures> eFeaturesList, String mscid, List<String> mtidList, List<String> mstidList);
    void handleTermData(List<AttractionTermEntity> attractionTermEntities, List<EFeatures> eFeaturesList, String msid, List<String> mtidList, List<String> mstidList);
    void handleItemData(List<AttractionItemEntity> attractionItemEntities, List<EFeatures> eFeaturesList, List<AttractionTermEntity> attractionTermEntities, List<String> mtidList, List<String> mstidList);
}
