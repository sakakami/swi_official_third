package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSearchAttractionDetail;

import java.util.List;

public interface IVSearchAttraction {
    void init(GSearchAttractionDetail gSearchAttractionDetail, List<String> style, List<String> time, List<String> open);
    void refreshAdapter(GSearchAttractionDetail gSearchAttractionDetail, List<String> style, List<String> time, List<String> open);
    void showSaveList(GSaveList gSaveList, String spid);
    void finishAddToStroke();
    void showFinishCreateStroke();
    void finishAddToCollect(String sucid, int index, boolean isAdd);
}
