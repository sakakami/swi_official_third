package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.gson.GSendLove;

public interface IPLeaveComments {
    void handleFinishSend();
    void handleFinishSend(GSendLove gSendLove);
    void handleInfoData(GInfoData gInfoData);
}
