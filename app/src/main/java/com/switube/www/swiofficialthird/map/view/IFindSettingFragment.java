package com.switube.www.swiofficialthird.map.view;

import com.switube.www.swiofficialthird.database.entity.AttractionClassEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionItemEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionTermEntity;

import java.util.List;

public interface IFindSettingFragment {
    void init(List<AttractionClassEntity> classEntities, List<AttractionTermEntity> termEntities, List<AttractionItemEntity> itemEntities);
}
