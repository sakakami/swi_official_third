package com.switube.www.landmark2018test.presenter;

import com.google.gson.Gson;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.gson.GCashFlow;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.gson.GTicket;
import com.switube.www.landmark2018test.model.MExchangeCheck;
import com.switube.www.landmark2018test.presenter.callback.IPExchangeCheck;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IVExchangeCheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PExchangeCheck implements IPExchangeCheck {
    private IVExchangeCheck ivExchangeCheck;
    private MExchangeCheck mExchangeCheck;
    public PExchangeCheck(IVExchangeCheck ivExchangeCheck) {
        this.ivExchangeCheck = ivExchangeCheck;
        mExchangeCheck = new MExchangeCheck(this);
    }

    public void init() {
        mExchangeCheck.getCashData(NetworkUtil.getInstance().getMap());
    }

    public void saveTicket(int count) {
        int cost = Integer.parseInt(MyApplication.getAppData().geteStoreList().getMessage());
        int totalCost = cost * count;
        int cash = Integer.parseInt(MyApplication.getAppData().getCarbonCash());
        if (cash >= totalCost) {
            String maid = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null");
            String name = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userName", "null");
            List<GTicket.Data> ticketList = new ArrayList<>();
            List<GCashFlow.Data> dataList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                GTicket.Data ticket = new GTicket.Data();
                ticket.setMaid(maid);
                ticket.setGoodId(MyApplication.getAppData().geteStoreList().getId());
                ticket.setGoodName(MyApplication.getAppData().geteStoreList().getName());
                ticket.setGoodPhoto(MyApplication.getAppData().geteStoreList().getPhoto());
                ticket.setGoodPrice(MyApplication.getAppData().geteStoreList().getMessage());
                ticket.setGoodUse("0");
                ticket.setStoreName(MyApplication.getAppData().geteStoreList().getStore());
                ticket.setStoreId(MyApplication.getAppData().getSelectedStoreId());
                ticketList.add(ticket);
                GCashFlow.Data data = new GCashFlow.Data();
                data.setCash("-" + cost);
                data.setMaid(maid);
                data.setTicketId(MyApplication.getAppData().geteStoreList().getId());
                data.setTicketName(MyApplication.getAppData().geteStoreList().getName());
                data.setTicketStore(MyApplication.getAppData().geteStoreList().getStore());
                data.setTarget(MyApplication.getAppData().geteStoreList().getStore());
                data.setTargetMaid(MyApplication.getAppData().getStoreMaid());
                data.setType("兌換");
                data.setUsed("0");
                dataList.add(data);
                GCashFlow.Data data2 = new GCashFlow.Data();
                data2.setCash(String.valueOf(cost));
                data2.setMaid(MyApplication.getAppData().getStoreMaid());
                data2.setTarget(name);
                data2.setTargetMaid(maid);
                dataList.add(data2);
            }
            Gson gson = new Gson();
            Map<String, String> map = NetworkUtil.getInstance().getMap();
            map.put("data", gson.toJson(dataList));
            map.put("mode", "0");
            map.put("edit", "");
            String json = gson.toJson(ticketList);
            mExchangeCheck.sendCashFlow(map, json);
        } else {
            ivExchangeCheck.showHint("碳幣餘額不足，請重新選擇數量。");
        }
    }

    @Override
    public void handleCashFlowData(GCashFlow gCashFlow) { }

    @Override
    public void finishSend(GSendLove gSendLove, String json) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("data", json);
        mExchangeCheck.sendTickets(map);
    }

    @Override
    public void finishSend(GSendLove gSendLove) {
        ivExchangeCheck.finishInsert();
    }
}
