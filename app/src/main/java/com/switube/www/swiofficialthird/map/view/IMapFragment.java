package com.switube.www.swiofficialthird.map.view;

import com.google.android.gms.location.places.Place;
import com.switube.www.swiofficialthird.database.entity.AttractionModeEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;
import com.switube.www.swiofficialthird.map.gson.PlaceBaseDataEntity;
import com.switube.www.swiofficialthird.util.ClusterItemUtil;

import java.util.List;

public interface IMapFragment {
    void handleUpdateAttractionData(List<ClusterItemUtil> list);
    void saveAPIKeys(List<String> keys);
    void initDrawer(List<AttractionModeEntity> modeEntities, List<AttractionStyleEntity> styleEntities, PlaceBaseDataEntity placeBaseDataEntity);
    void handleOpenBluetooth();
    void handleSelectBike();
    void handleReturnBike();
    void savePlaceId(String id);
}
