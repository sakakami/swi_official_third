package com.switube.www.landmark2018test.adapter.callback;

public interface IASearchAttraction {
    void SwitchPage(String spid);
    void handleSaveAttractions(String spid, boolean saved, int index);
    void handleCollectAttractions(String spid);
}
