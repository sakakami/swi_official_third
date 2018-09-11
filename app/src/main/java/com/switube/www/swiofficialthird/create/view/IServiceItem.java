package com.switube.www.swiofficialthird.create.view;

import com.switube.www.swiofficialthird.database.entity.AttractionClassEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionItemEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionTermEntity;

import java.util.List;

public interface IServiceItem {
    void init(List<AttractionClassEntity> classEntities, List<AttractionTermEntity> termEntities, List<AttractionItemEntity> itemEntities);
    void handleToFinish();
}
