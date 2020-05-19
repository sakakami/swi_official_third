package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.gson.GLikeUnlike;
import com.switube.www.landmark2018test.gson.GSendLove;

import java.util.List;

public interface IPPersonalSteaming {
    void handleLikeOrUnlikeJson(GLikeUnlike gLikeUnlike, int num);
    void finishDeleteComment(GSendLove gSendLove, int index);
    void handleMsidData(GInfoData gInfoData, List<GInfoData.Article> articles, int index, boolean isComment);
}
