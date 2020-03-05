package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.database.entity.AttractionModeEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;

import java.util.List;

public interface IVAttractionType {
    void init(List<AttractionModeEntity> attractionModeEntities, List<AttractionStyleEntity> attractionStyleEntities);
}
