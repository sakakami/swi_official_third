package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.gson.GSaveList;

public interface IVInfo {
    void init(String style, String item, GInfoData gInfoData);
    void refreshAdapter(String count, int num);
    void refreshAdapter(String count);
    void showSaveList(GSaveList gSaveList);
    void finishAddToStroke();
    void finishAddToCollect(boolean isAdd, String sucid);
    void showFinishCreateStroke();
    void showEditComment();
    void showEditTag();
    void finishDelete();
}
