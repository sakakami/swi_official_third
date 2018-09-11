package com.switube.www.swiofficialthird.create.presenter;

import android.content.Context;

import com.switube.www.swiofficialthird.database.entity.AttractionModeEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;

import java.util.List;

public interface IAttractionTypePresenter {
    void handleListDataOne(Context context, List<AttractionModeEntity> attractionModeEntities);
    void handleListDataTwo(List<AttractionStyleEntity> attractionStyleEntities);
}
