package com.switube.www.swiofficialthird.create.presenter;

import com.switube.www.swiofficialthird.create.gson.AttractionDetailEntity;

public interface ICreateAttractionPresenter {
    void handleAttractionDetail(AttractionDetailEntity attractionDetailEntity);
    void handleAttractionDetailOne(String id, AttractionDetailEntity attractionDetailEntity);
    void handleAttractionDetailTwo(String id, AttractionDetailEntity attractionDetailEntity);
    void handleAttractionDetailThree(String id, AttractionDetailEntity attractionDetailEntity);
}
