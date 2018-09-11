package com.switube.www.swiofficialthird.map.presenter;

import android.content.Context;

import com.switube.www.swiofficialthird.database.entity.AttractionClassEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionItemEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionTermEntity;

import java.util.List;

public interface IFindSettingPresenter {
    void handleDataOne(Context context, List<AttractionClassEntity> attractionClassEntities);
    void handleDataTwo(Context context, List<AttractionTermEntity> attractionTermEntities);
    void handleDataThree(List<AttractionItemEntity> attractionItemEntities);
}
