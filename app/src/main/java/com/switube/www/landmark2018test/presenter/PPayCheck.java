package com.switube.www.landmark2018test.presenter;

import com.google.gson.Gson;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.gson.CodeContent;
import com.switube.www.landmark2018test.gson.GCashFlow;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.model.MPayCheck;
import com.switube.www.landmark2018test.presenter.callback.IPPayCheck;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IVPayCheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PPayCheck implements IPPayCheck {
    private IVPayCheck ivPayCheck;
    private MPayCheck mPayCheck;
    public PPayCheck(IVPayCheck ivPayCheck) {
        this.ivPayCheck = ivPayCheck;
        mPayCheck = new MPayCheck(this);
    }

    public void init() {
        Gson gson = new Gson();
        CodeContent codeContent = gson.fromJson(MyApplication.getAppData().getScannerResult(), CodeContent.class);
        StringBuilder stringBuilder = new StringBuilder(MyApplication.getAppData().getPayCash());
        if (stringBuilder.indexOf("-") >= 0) {
            stringBuilder.delete(0, 1);
        }
        ivPayCheck.init(codeContent, stringBuilder.toString());
    }

    public void handlePay(CodeContent codeContent, String cash) {
        String maid = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null");
        String name = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userName", "null");
        GCashFlow.Data data = new GCashFlow.Data();
        if (codeContent.getId().equals(MyApplication.getAppData().getCheckId())) {
            data.setCash(cash);
            data.setTarget(name);
            data.setTargetMaid(maid);
            data.setMaid(codeContent.getMaid());
            data.setName(codeContent.getName());
        } else {
            data.setCash("-" + cash);
            data.setTarget(codeContent.getName());
            data.setTargetMaid(codeContent.getMaid());
            data.setMaid(maid);
            data.setName(name);
        }
        List<GCashFlow.Data> dataList = new ArrayList<>();
        dataList.add(data);
        Gson gson = new Gson();
        String json = gson.toJson(dataList);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        if (codeContent.getId().equals(MyApplication.getAppData().getCheckId())) {
            map.put("mode", "0");
        } else {
            map.put("mode", "1");
        }
        map.put("data", json);
        mPayCheck.sendCashFlow(map);
    }

    @Override
    public void finishSend(GSendLove gSendLove) {
        ivPayCheck.finishSend();
    }
}
