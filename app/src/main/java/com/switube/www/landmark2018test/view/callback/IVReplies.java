package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.gson.GCommentsData;

public interface IVReplies {
    void handleRefresh(GCommentsData gCommentsData);
    void handleRefresh(String count, String like, int index, boolean isReply);
    void handleFinishEdit(boolean isComment, String message, int index);
    void handleFinishDel(boolean isComment, int index);
}
