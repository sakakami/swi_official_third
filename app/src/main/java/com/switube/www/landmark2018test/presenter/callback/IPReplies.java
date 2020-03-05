package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.gson.GLikeUnlike;
import com.switube.www.landmark2018test.gson.GCommentsData;
import com.switube.www.landmark2018test.gson.GSendLove;

public interface IPReplies {
    void handleResult(GCommentsData gCommentsData);
    void handleLikeResult(GLikeUnlike gLikeUnlike, String like, int index, boolean isReply);
    void handleFinishEdit(GSendLove gSendLove, int index, String message, boolean isComment, int mainIndex);
    void handleFinishDel(GSendLove gSendLove, int index, boolean isComment, int mainIndex);
}
