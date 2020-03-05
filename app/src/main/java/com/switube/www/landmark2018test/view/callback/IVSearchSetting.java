package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;

import java.util.List;

public interface IVSearchSetting {
    void init(List<AttractionClassEntity> classEntities, List<AttractionTermEntity> termEntities, List<AttractionItemEntity> itemEntities);
}
