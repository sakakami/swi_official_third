package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.gson.GAttractionDataGoogle;
import com.switube.www.landmark2018test.gson.GPlaceIdData;
import com.switube.www.landmark2018test.gson.GSendLove;

public interface IPEditAttraction {
    void handleResult();
    void handlePlaceDetailEN(GAttractionDataGoogle gAttractionDataGoogle, String id);
    void handlePlaceDetailTW(GAttractionDataGoogle gAttractionDataGoogle, String id);
    void handlePlaceDetailCN(GAttractionDataGoogle gAttractionDataGoogle, String id);
    void handlePlaceDetailJP(GAttractionDataGoogle gAttractionDataGoogle);
    void handlePlaceId(GPlaceIdData gPlaceIdData);
    void finishDelPlace(GSendLove gSendLove);
}
