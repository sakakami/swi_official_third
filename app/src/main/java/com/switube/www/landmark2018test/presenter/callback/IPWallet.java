package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.database.entity.CashFlowEntity;
import com.switube.www.landmark2018test.gson.GCashFlow;
import com.switube.www.landmark2018test.gson.GSendLove;

import java.util.List;

public interface IPWallet {
    void finishInsert();
    void handleCashFlowData(List<CashFlowEntity> cashFlowEntities, boolean showDetail);
    void handleCashFlowData(GCashFlow gCashFlow, boolean showDetail);
    void handleCashFlowData(GCashFlow gCashFlow, String code);
    void finishSend(GSendLove gSendLove);
}
