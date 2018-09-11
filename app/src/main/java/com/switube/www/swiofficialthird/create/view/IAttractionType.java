package com.switube.www.swiofficialthird.create.view;

import com.switube.www.swiofficialthird.database.entity.AttractionModeEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;

import java.util.List;

public interface IAttractionType {
    void init(List<AttractionModeEntity> attractionModeEntities, List<AttractionStyleEntity> attractionStyleEntities);
    void handleSelectType(AttractionStyleEntity attractionStyleEntity);
}
