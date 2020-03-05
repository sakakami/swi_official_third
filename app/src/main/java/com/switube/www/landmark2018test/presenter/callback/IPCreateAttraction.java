package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.gson.GAttractionDataGoogle;
import com.switube.www.landmark2018test.gson.GPlaceIdData;

public interface IPCreateAttraction {
    void handleAttractionDetail(GAttractionDataGoogle gAttractionDataGoogle);
    void handleAttractionDetailOne(String id, GAttractionDataGoogle gAttractionDataGoogle);
    void handleAttractionDetailTwo(String id, GAttractionDataGoogle gAttractionDataGoogle);
    void handleAttractionDetailThree(String id, GAttractionDataGoogle gAttractionDataGoogle);
    void handleSavePlaceId(GPlaceIdData gPlaceIdData);
}
