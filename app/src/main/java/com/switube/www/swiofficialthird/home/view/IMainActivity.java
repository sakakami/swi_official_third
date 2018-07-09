package com.switube.www.swiofficialthird.home.view;

import com.google.android.gms.maps.GoogleMap;

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
}
