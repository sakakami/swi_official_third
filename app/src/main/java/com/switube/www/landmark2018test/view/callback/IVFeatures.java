package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;

import java.util.List;

public interface IVFeatures {
    void init(List<AttractionClassEntity> classEntities, List<AttractionTermEntity> termEntities, List<AttractionItemEntity> itemEntities);
    void handleToFinish(String spid, boolean canToInfo);
    void finishSend();
    void handleTimeOutError();
}
