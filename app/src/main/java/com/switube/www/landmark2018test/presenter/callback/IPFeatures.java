package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;
import com.switube.www.landmark2018test.gson.GCreateAttraction;

import java.util.List;

public interface IPFeatures {
    void handleDataOne(List<AttractionClassEntity> attractionClassEntities);
    void handleDataTwo(List<AttractionTermEntity> attractionTermEntities);
    void handleDataThree(List<AttractionItemEntity> attractionItemEntities);
    void handleToNextPage(GCreateAttraction gCreateAttraction);
    void handleTimeOutError();
    void finishSend();
}
