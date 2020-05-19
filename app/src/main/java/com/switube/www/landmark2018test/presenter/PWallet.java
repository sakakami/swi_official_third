package com.switube.www.landmark2018test.presenter;

import com.google.gson.Gson;
import com.switube.www.landmark2018test.gson.CodeContent;
import com.switube.www.landmark2018test.gson.GCashFlow;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.gson.GTicket;
import com.switube.www.landmark2018test.model.MWallet;
import com.switube.www.landmark2018test.presenter.callback.IPWallet;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVWallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PWallet implements IPWallet {
    private IVWallet ivWallet;
    private MWallet mWallet;
    public PWallet(IVWallet ivWallet) {
        this.ivWallet = ivWallet;
        mWallet = new MWallet(this);
    }

    public void init() {
        List<String> list = new ArrayList<>();
        list.add("附近\n租車點");
        list.add("我的\n條碼");
        list.add("掃碼");
        list.add("兌換");
        list.add("交易\n紀錄");
        list.add("我的\n兌換卷");
        ivWallet.initAdapter(list);
        mWallet.getCashFlowData(NetworkUtil.getInstance().getMap());
    }

    public void handleScanTicket(CodeContent codeContent) {
        GTicket.Data data = new GTicket.Data();
        data.setMaid(codeContent.getMaid());
        data.setGoodUse("1");
        data.setGoodId(codeContent.getId());
        List<GTicket.Data> dataList = new ArrayList<>();
        dataList.add(data);
        Gson gson = new Gson();
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("mode", "1");
        map.put("data", gson.toJson(dataList));
        mWallet.sendTicketData(map);
    }

    @Override
    public void handleCashFlowData(GCashFlow gCashFlow) {
        int cash = 0;
        for (int i = 0; i < gCashFlow.getData().size(); i++) {
            cash += Integer.parseInt(gCashFlow.getData().get(i).getCash());
        }
        ivWallet.showDetail(cash);
    }

    @Override
    public void handleTicketUsed(GSendLove gSendLove) {
    }
}
