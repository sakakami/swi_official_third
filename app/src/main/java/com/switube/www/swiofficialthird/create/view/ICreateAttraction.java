package com.switube.www.swiofficialthird.create.view;

import com.switube.www.swiofficialthird.create.CreateAttractionEntity;
import com.switube.www.swiofficialthird.create.gson.AttractionDetailEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;

import java.util.List;

public interface ICreateAttraction {
    void init(List<AttractionDetailEntity> detailEntities);
    void switchPage(int type);
    void init(CreateAttractionEntity entity);
    AttractionStyleEntity getStyleEntity();
}
