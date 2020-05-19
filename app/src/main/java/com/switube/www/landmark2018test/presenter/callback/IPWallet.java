package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.gson.GCashFlow;
import com.switube.www.landmark2018test.gson.GSendLove;

public interface IPWallet {
    void handleCashFlowData(GCashFlow gCashFlow);
    void handleTicketUsed(GSendLove gSendLove);
}
