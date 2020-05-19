package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.gson.GCashFlow;
import com.switube.www.landmark2018test.gson.GSendLove;

public interface IPExchangeCheck {
    void handleCashFlowData(GCashFlow gCashFlow);
    void finishSend(GSendLove gSendLove, String json);
    void finishSend(GSendLove gSendLove);
}
