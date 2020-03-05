package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.gson.GLikeUnlike;
import com.switube.www.landmark2018test.gson.GSendLove;

public interface IPAttractionSteaming {
    void handleLikeOrUnlikeJson(GLikeUnlike gLikeUnlike, int num);
    void finishDeleteComment(GSendLove gSendLove, int index);
}
