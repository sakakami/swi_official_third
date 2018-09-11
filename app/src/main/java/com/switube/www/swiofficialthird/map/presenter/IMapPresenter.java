package com.switube.www.swiofficialthird.map.presenter;

import android.content.Context;

import com.switube.www.swiofficialthird.map.gson.AttractionDataEntity;
import com.switube.www.swiofficialthird.map.gson.MenuDataEntity;
import com.switube.www.swiofficialthird.map.gson.PlaceBaseDataEntity;
import com.switube.www.swiofficialthird.map.gson.PlaceDataEntity;

public interface IMapPresenter {
    void handleParseAttractionGson(Context context, AttractionDataEntity attractionDataEntity);
    void handleParseMenuDataGson(Context context, MenuDataEntity menuDataEntity);
    void handleParseGsonData(Context context, MenuDataEntity menuDataEntity, PlaceBaseDataEntity placeBaseDataEntity);
    void handleSavePlaceId(PlaceDataEntity placeDataEntity);
}
