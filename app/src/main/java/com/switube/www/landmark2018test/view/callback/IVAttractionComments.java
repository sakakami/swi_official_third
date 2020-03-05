package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.gson.GCommentsData;

public interface IVAttractionComments {
    void handleRefreshData(GCommentsData gCommentsData);
    void handleRefreshCount(String count, int index, String like);
    void handleFinishEdit();
    void handleFinishDel();
}
