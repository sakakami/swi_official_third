package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.gson.GMyCollection;
import com.switube.www.landmark2018test.gson.GSaveList;

import java.util.List;

public interface IVMyCollection {
    void init(GMyCollection gMyCollection);
    void init(GMyCollection gMyCollection, List<String> distance);
    void handleFinishRemove(int index);
    void showSaveList(GSaveList gSaveList, String spid);
    void showFinishAdd();
    void showFinishCreate();
}
