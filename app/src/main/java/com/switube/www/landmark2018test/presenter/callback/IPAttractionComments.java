package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.gson.GLikeUnlike;
import com.switube.www.landmark2018test.gson.GCommentsData;
import com.switube.www.landmark2018test.gson.GSendLove;

public interface IPAttractionComments {
    void handleResult(GCommentsData gCommentsData);
    void handleLikeResult(GLikeUnlike gLikeUnlike, int index, String like);
    void handleFinishEdit(GSendLove gSendLove, int index, String message);
    void handleFinishDel(GSendLove gSendLove, int index);
}
