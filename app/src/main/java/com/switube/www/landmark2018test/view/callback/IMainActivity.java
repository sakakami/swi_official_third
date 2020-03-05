package com.switube.www.landmark2018test.view.callback;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.switube.www.landmark2018test.adapter.ASlideMenu;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.entity.ECreateAttraction;

import java.util.List;

public interface IMainActivity {
    //取得 google map 實例
    GoogleMap getGoogleMap();
    //mapFragment 回傳 google map 實例至 mainActivity
    void setGoogleMap(GoogleMap googleMap);
    //離開地圖頁面時清空實例
    void handleCleanGoogleMap();
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
    //取得創建內容
    ECreateAttraction getCreateData();
    //紀錄創建內容
    void setCreateData(ECreateAttraction eCreateAttraction);
    //設定place id
    void setPlaceId(String id);
    //取得place id
    String getPlaceId();
    //設定計費實例
    void handleBikeTextViews(TextView timer, TextView cost);
    void handleChangeTimeAndCost(String time, String cost);
    void setCost(double cost);
    double getCost();
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

    //紀錄篩選過的景點ID
    void saveSelectedAttractionId(List<String> spid);

    //取得篩選過的景點ID
    List<String> getSelectedAttractionId();

    //紀錄景點ID
    void saveAttractionId(String spid);

    //取得景點ID
    String getAttractionId();

    //登入
    void handleSignIn(String type);

    //google sign in
    void initForResult(Intent intent);

    //登出
    void handleSignOut();

    //slide menu units
    void saveSlideMenuUnits(TextView textView, ImageView imageView, ASlideMenu aSlideMenu, boolean isSlideMenu);

    //refreshSlideMenu
    void handleRefreshSlideMenu(String name, String image);

    Integer getMessageIndex();

    void setMessageIndex(int index);

    String getPlaceName();

    void setPlaceName(String name);

    void getPermission();

    void handleNeverCheckPermission();

    void switchScreenOrientation();

    void handleClearPolyline();

    void handleStartRecordLocation();
}
