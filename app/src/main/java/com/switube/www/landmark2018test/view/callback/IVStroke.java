package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.gson.GPushStroke;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GStrokeList;

import java.util.List;

public interface IVStroke {
    void init(GSaveList gSaveList);
    void showFinishCreate();
    void showFinishEdit();
    void showFinishDel();
    void init(GPushStroke gPushStroke);
    void init(GStrokeList gStrokeList, List<String> style, List<String> time, List<String> isOpen);
    void showFinishAddToCollect(int index, boolean isAdd, String sucid);
    void finishDelFormStroke(int index);
    void showSaveList(final GSaveList gSaveList);
    void finishAddToStroke();
    void showFinishAddToMyStroke();
    void showCaution();
}
