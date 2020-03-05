package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.gson.GPersonalSteaming;

public interface IVPersonalSteaming {
    void init(GPersonalSteaming gPersonalSteaming);
    void refreshAdapter(String count, int num);
    void toEditCommentPage();
    void toEditTagPage();
    void finishDel(int index);
}
