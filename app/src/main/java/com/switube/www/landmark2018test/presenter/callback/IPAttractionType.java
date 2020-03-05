package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.database.entity.AttractionModeEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;

import java.util.List;

public interface IPAttractionType {
    void handleListDataOne(List<AttractionModeEntity> attractionModeEntities);
    void handleListDataTwo(List<AttractionStyleEntity> attractionStyleEntities);
}
