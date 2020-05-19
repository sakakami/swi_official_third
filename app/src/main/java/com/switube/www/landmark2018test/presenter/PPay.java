package com.switube.www.landmark2018test.presenter;

import com.google.gson.Gson;
import com.switube.www.landmark2018test.gson.CodeContent;
import com.switube.www.landmark2018test.gson.GCashFlow;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.model.MPay;
import com.switube.www.landmark2018test.presenter.callback.IPPay;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IVPay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PPay implements IPPay {
    private IVPay ivPay;
    private MPay mPay;
    public PPay(IVPay ivPay) {
        this.ivPay = ivPay;
        mPay = new MPay(this);
    }

    public void handlePay(CodeContent codeContent, String cash) {
        String maid = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null");
        String name = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userName", "null");
        Gson gson = new Gson();
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        GCashFlow.Data data = new GCashFlow.Data();
        data.setMaid(codeContent.getMaid());
        data.setTargetMaid(maid);
        data.setTarget(name);
        data.setName(codeContent.getName());
        data.setCash("-" + cash);
        data.setCheckout(codeContent.getId());
        List<GCashFlow.Data> dataList = new ArrayList<>();
        dataList.add(data);
        map.put("data", gson.toJson(dataList));
        map.put("mode", "0");
        mPay.sendCashFlow(map);
    }

    @Override
    public void finishSend(GSendLove gSendLove) {
        if (gSendLove.getSave().equals("1")) {
            ivPay.finishSend();
        }
    }
}
