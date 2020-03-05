package com.switube.www.landmark2018test.presenter.callback;

import com.google.android.gms.maps.model.LatLng;
import com.switube.www.landmark2018test.gson.GAttractionListData;
import com.switube.www.landmark2018test.gson.GPlaceIdData;

public interface IPAttractionList {
    void handleParseGsonData(GAttractionListData gAttractionListData, LatLng latLng);
    void handleSavePlaceId(GPlaceIdData gPlaceIdData);
}
