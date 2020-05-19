package com.switube.www.landmark2018test.presenter;

import com.google.gson.Gson;
import com.switube.www.landmark2018test.gson.GCashFlow;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.model.MCreditCard;
import com.switube.www.landmark2018test.presenter.callback.IPCreditCard;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IVCreditCard;

import java.util.ArrayList;
import java.util.Map;

public class PCreditCard implements IPCreditCard {
    private IVCreditCard ivCreditCard;
    private MCreditCard mCreditCard;
    public PCreditCard(IVCreditCard ivCreditCard) {
        this.ivCreditCard = ivCreditCard;
        mCreditCard = new MCreditCard(this);
    }

    public void sendCashFlow() {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("mode", "0");
        map.put("edit", "");
        ArrayList<GCashFlow.Data> cashFlows = new ArrayList<>();
        GCashFlow.Data data = new GCashFlow.Data();
        data.setTarget("E-bike 美和街站點");
        data.setType("租車");
        data.setCheck("0");
        data.setMaid(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"));
        data.setCash("700");
        cashFlows.add(data);
        Gson gson = new Gson();
        String stringData = gson.toJson(cashFlows);
        map.put("data", stringData);
        mCreditCard.sendCashFlow(map);
    }

    @Override
    public void finishSendCashFlow(GSendLove gSendLove) {
        if (gSendLove.getSave().equals("1")) {
            ivCreditCard.finishSend();
        }
    }
}
