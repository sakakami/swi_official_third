package com.switube.www.landmark2018test.presenter.callback;

import com.google.android.gms.maps.model.LatLng;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSearchAttractionDetail;
import com.switube.www.landmark2018test.gson.GSendLove;

import java.util.List;

public interface IPSearchAttraction {
    void handleAttractionDataWithSelected(GSearchAttractionDetail gSearchAttractionDetail, LatLng latLng, int distance);
    void handleAttractionDataWithSelected(List<AttractionStyleEntity> attractionStyleEntities, GSearchAttractionDetail gSearchAttractionDetail, LatLng latLng, int distance);
    void handleSaveList(GSaveList gSaveList, String spid);
    void handleFinishAddToStroke(GSaveList gSaveList, List<Boolean> isClickedList, String attractionId);
    void handleFinishCreateNewStroke(GSendLove gSendLove);
    void handleFinishAddToCollect(GSendLove gSendLove, int index);
}
