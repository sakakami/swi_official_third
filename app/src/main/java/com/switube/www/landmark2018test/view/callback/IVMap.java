package com.switube.www.landmark2018test.view.callback;

import com.google.android.gms.maps.model.LatLng;
import com.switube.www.landmark2018test.database.entity.AttractionModeEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.entity.ECarBonList;
import com.switube.www.landmark2018test.entity.EClusterItem;
import com.switube.www.landmark2018test.entity.EEcoList;
import com.switube.www.landmark2018test.entity.EMobileMusic;
import com.switube.www.landmark2018test.gson.GAttractionListData;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSearchAttractionDetail;
import com.switube.www.landmark2018test.gson.GSendLove;

import java.util.ArrayList;
import java.util.List;

public interface IVMap {
    void handleUpdateAttractionData(List<EClusterItem> list);
    void initDrawer(List<AttractionModeEntity> modeEntities, List<AttractionStyleEntity> styleEntities, GAttractionListData gAttractionListData);
    void handleOpenBluetooth();
    void handleSelectBike();
    void handleReturnBike();
    void savePlaceId(String id);
    void showDetailForAttractionInfo(GSearchAttractionDetail gSearchAttractionDetail, String type, String isOpen, String time);
    void handSearchData(int index);
    void showFinishAddOrDel(String save);
    void showSaveList(GSaveList gSaveList);
    void finishAddToStroke();
    void showFinishCreateStroke();
    void finishAddToCollect(boolean isAdd, String sucid);
    void showFinishAddToMyStroke();
    void showPersonalMap(List<EClusterItem> list, int zoomIndex, LatLng latLng);
    void handleMobileMusic(List<EMobileMusic> mobileMusicList);
    void showFivePoint(List<EMobileMusic> mobileMusicList);
    void refreshLoveData(String save);
    void showSearchCaution(boolean noMatch);
    void showCaution();
    void finishCashFlowInsert();
    void finishInsertCarbon();
    void showToast(String msg);
    void showCarbonList(ArrayList<ECarBonList> list);
    void finishInsertEco();
    void showEEcoList(ArrayList<EEcoList> list);
}
