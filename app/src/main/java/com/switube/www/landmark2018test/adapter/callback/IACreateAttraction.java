package com.switube.www.landmark2018test.adapter.callback;

import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;

public interface IACreateAttraction {
    void switchPage(int type);
    boolean checkPhotoSize();
    int getPhotoSize();
    AttractionStyleEntity getStyleEntity();
    String getPhoto();
}
