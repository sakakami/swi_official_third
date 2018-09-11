package com.switube.www.swiofficialthird.home.view;

import android.widget.TextView;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.switube.www.swiofficialthird.create.CreateAttractionEntity;
import com.switube.www.swiofficialthird.create.TimeEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;
import com.switube.www.swiofficialthird.home.MainService;

import java.util.List;

public interface IMainActivity {
    //photoFragment 回傳已選圖片至 mainActivity
    void setSelectedPhoto(List<String> selectedPhoto);
    //從 mainActivity 取得所選圖片資料
    List<String> getSelectedPhoto();
    //取得 google map 實例
    GoogleMap getGoogleMap();
    //mapFragment 回傳 google map 實例至 mainActivity
    void setGoogleMap(GoogleMap googleMap);
    //離開地圖頁面時清空實例
    void handleCleanGoogleMap();
    //設定是否在進入replies頁面時需要立即進入留言狀態
    void setNeedFocusEditView(boolean b);
    boolean getNeedFocusEditView();
    //在地圖頁刷新自己位置
    void handleRefreshLocation();
    //開始計時
    void handleStartTimer(boolean isTimer);
    //暫停計時
    void handlePauseTimer();
    //結束計時
    void handleStopTimer();
    //設定TextView實例
    void handleSetTextView(TextView textCenter, TextView textRight, TextView textLeft);
    void handleChangeTextCenter(String text, int size);
    void handleChangeTextRight(String right);
    void handleChangeTextLeft(String left);
    //設定所選地標類型
    void setSelectedAttractionType(AttractionStyleEntity attractionType);
    //取得所選地標類型
    AttractionStyleEntity getSelectedAttractionType();
    //紀錄營業時間
    void setTimeEntity(List<TimeEntity> timeEntities);
    //取得營業時間
    List<TimeEntity> getTimeEntity();
    //紀錄創建內容
    void setCreateData(CreateAttractionEntity createAttractionEntity);
    //取得創建內容
    CreateAttractionEntity getCreateData();
    //紀錄API Key
    void setKeys(List<String> key);
    //取得API Key
    List<String> getKey();
    //設定place id
    void setPlaceId(String id);
    //取得place id
    String getPlaceId();
    //設定計費實例
    void handleBikeTextViews(TextView timer, TextView cost);
    void handleChangeTimeAndCost(String time, String cost);
    void setCost(double cost);
    double getCost();
    String getTotalTime();
    //畫線路圖
    void handleDrawLine();
    //清除座標紀錄
    void handleClearLatLngList();
    //紀錄所選車輛編號
    void saveSelectBikeNubmer(String number);
    //取得所選車輛編號
    String getSelectBikeNumber();
    //取得DetailPage所需資料
    String getBikeMoney();
    String getBikeDate();
    //紀錄租車地點
    void saveCentPlace(String place);
    //取得租車地點
    String getCentPlace();
    //紀錄還車地點
    void saveReturnPlace(String place);
    //取得還車地點
    String getReturnPlace();
    //取得移動軌跡
    List<LatLng> getLatLngList();
    //獲取現在座標
    LatLng getNowGps();
}
