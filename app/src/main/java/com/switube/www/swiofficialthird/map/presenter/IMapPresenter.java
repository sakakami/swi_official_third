package com.switube.www.swiofficialthird.map.presenter;

import android.content.Context;

import com.switube.www.swiofficialthird.map.gson.AttractionDataEntity;

public interface IMapPresenter {
    void handleParseAttractionGson(Context context, AttractionDataEntity attractionDataEntity);
}
