package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.gson.GAttractionListData;

import java.util.List;

public interface IVAttractionList {
    void init(GAttractionListData gAttractionListData, List<String> distance);
    void savePlaceId(String id);
    void refreshAdapter(GAttractionListData gAttractionListData, List<String> distance);
}
