package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GStrokeList;

import java.util.List;

public interface IVStrokeAttractionList {
    void init(GStrokeList gStrokeList, List<String> style, List<String> time, List<String> isOpen);
    void showFinishAddToCollect(int index, String sucid);
    void showFinishDelToCollect(int index);
    void showSaveList(GSaveList gSaveList);
    void finishAddToStroke();
    void finishDelFormStroke(int index);
    void showFinishCreate();
    void showFinishEdit();
}
