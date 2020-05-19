package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.gson.GCashFlow;
import com.switube.www.landmark2018test.gson.GSendLove;

public interface IPMyCode {
    void finishSend(GSendLove gSendLove);
    void handleCashFlowData(GCashFlow gCashFlow);
}
