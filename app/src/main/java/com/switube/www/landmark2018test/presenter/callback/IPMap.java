package com.switube.www.landmark2018test.presenter.callback;

import com.google.android.gms.maps.model.LatLng;
import com.switube.www.landmark2018test.database.entity.AttractionModeEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.database.entity.CarbonEntity;
import com.switube.www.landmark2018test.database.entity.EcoEntity;
import com.switube.www.landmark2018test.database.entity.MapPlaceBaseDataEntity;
import com.switube.www.landmark2018test.gson.GAttractionListData;
import com.switube.www.landmark2018test.gson.GMusicRadio;
import com.switube.www.landmark2018test.gson.GPushMusic;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.gson.GSlideMenuData;
import com.switube.www.landmark2018test.gson.GPlaceIdData;
import com.switube.www.landmark2018test.gson.GSearchAttractionDetail;
import com.switube.www.landmark2018test.gson.GStrokeList;

import java.util.List;

public interface IPMap {
    void handleParseGsonData(GSlideMenuData gSlideMenuData, GAttractionListData gAttractionListData, GMusicRadio gMusicRadio);
    void handleMusicMenu(GSlideMenuData gSlideMenuData, GAttractionListData gAttractionListData);
    void handleSavePlaceId(GPlaceIdData gPlaceIdData);
    void handleAttractionData(List<MapPlaceBaseDataEntity> mapPlaceBaseDataEntities, List<AttractionStyleEntity> styleEntities);
    void handleAttractionDetailData(GSearchAttractionDetail gSearchAttractionDetail, String type, LatLng latLng);
    void handleSelectedStyleData(List<AttractionStyleEntity> attractionStyleEntities, GAttractionListData gAttractionListData);
    void handleStrokeList(GStrokeList gStrokeList, List<AttractionStyleEntity> attractionStyleEntities);
    void handleFinishAddOrDel(GSendLove gSendLove);
    void handleSaveList(GSaveList gSaveList);
    void handleFinishAddToStroke(GSaveList gSaveList, List<Boolean> isClickedList, String attractionId);
    void handleFinishCreateNewStroke(GSendLove gSendLove);
    void handleFinishAddToCollect(GSendLove gSendLove);
    void handleFinishAddToMyStroke(GSendLove gSendLove);
    void handleFinishInsert(GMusicRadio gMusicRadio, List<AttractionModeEntity> attractionModeEntityList, List<AttractionStyleEntity> attractionStyleEntityList, GAttractionListData gAttractionListData);
    void handlePushMusicData(GPushMusic gPushMusic);
    void handleLoveData(GSendLove gSendLove);
    void handleCarbon(List<CarbonEntity> list, boolean isShow);
    void finishCashFlowInsert();
    void finishSendCashFlow(GSendLove gSendLove);
    void finishSaveCarbon();
    void handleEco(List<EcoEntity> list, boolean isShow);
    void finishSaveEco();
}
