package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.gson.GMyCollection;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSendLove;

import java.util.List;

public interface IPMyCollection {
    void handleCollectData(GMyCollection gMyCollection);
    void handleRemove(GSendLove gSendLove, int index);
    void handleSaveList(GSaveList gSaveList, String spid);
    void handleFinishAdd(GSaveList gSaveList, List<Boolean> isClickedList, String attractionId);
    void handleFinishCreate(GSendLove gSendLove);
}
