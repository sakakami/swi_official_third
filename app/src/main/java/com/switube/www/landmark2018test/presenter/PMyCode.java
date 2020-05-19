package com.switube.www.landmark2018test.presenter;

import com.google.gson.Gson;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.gson.CodeContent;
import com.switube.www.landmark2018test.gson.GCashFlow;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.model.MMyCode;
import com.switube.www.landmark2018test.presenter.callback.IPMyCode;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVMyCode;

import java.util.Map;

public class PMyCode implements IPMyCode {
    private IVMyCode ivMyCode;
    private MMyCode mMyCode;
    public PMyCode(IVMyCode ivMyCode) {
        this.ivMyCode = ivMyCode;
        mMyCode = new MMyCode(this);
    }

    private String codeId = "";
    public void getCashFlowData(String codeId) {
        this.codeId = codeId;
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        mMyCode.getCashData(map);
    }

    @Override
    public void finishSend(GSendLove gSendLove) {
        if (gSendLove.getSave().equals("2")) {
            ivMyCode.finishSend();
        }
    }

    @Override
    public void handleCashFlowData(GCashFlow gCashFlow) {
        for (int i = 0; i < gCashFlow.getData().size(); i++) {
            if (gCashFlow.getData().get(i).getCheckout().equals(codeId)) {
                CodeContent codeContent = new CodeContent();
                codeContent.setMaid(gCashFlow.getData().get(i).getTargetMaid());
                codeContent.setName(gCashFlow.getData().get(i).getTarget());
                codeContent.setId(gCashFlow.getData().get(i).getCheckout());
                Gson gson = new Gson();
                String code = gson.toJson(codeContent);
                MyApplication.getAppData().setScannerResult(code);
                MyApplication.getAppData().setPayCash(gCashFlow.getData().get(i).getCash());
                ivMyCode.finishFlash();
            }
        }
    }
}
