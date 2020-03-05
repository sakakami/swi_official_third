package com.switube.www.landmark2018test.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.switube.www.landmark2018test.database.entity.CashFlowEntity;
import com.switube.www.landmark2018test.entity.ECashFlow;
import com.switube.www.landmark2018test.gson.GCashFlow;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.model.MWallet;
import com.switube.www.landmark2018test.presenter.callback.IPWallet;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IVWallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PWallet implements IPWallet {
    private IVWallet ivWallet;
    private MWallet mWallet;
    private String maid;
    public PWallet(IVWallet ivWallet) {
        this.ivWallet = ivWallet;
        mWallet = new MWallet(this);
        maid = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null");
        //mWallet.getData(maid, false);
        mWallet.getCashFlowData(NetworkUtil.getInstance().getMap(), false);
    }

    public void showDetailList() {
        //mWallet.getData(maid, true);
        mWallet.getCashFlowData(NetworkUtil.getInstance().getMap(), true);
    }

    public void handlePay(int cash, String pay) {
        StringBuilder builder = new StringBuilder(pay);
        int index = builder.indexOf("/");
        builder.delete(0, index + 1);
        index = builder.indexOf("/");
        builder.delete(index, builder.length());
        String money = builder.toString();
        builder.delete(0, builder.length());
        builder.append(pay);
        index = builder.indexOf("/");
        builder.delete(index, builder.length());
        String maid = builder.toString();
        builder.delete(0, builder.length());
        builder.append(pay);
        index = builder.lastIndexOf("/");
        builder.delete(0, index + 1);
        String code = builder.toString();
        Log.e("qrcode", "cash: " + money + ", maid: " + maid + ", code: " + code);
        if (cash >= Integer.parseInt(money)) {
            ivWallet.checkPay(money, maid, code);
        } else {
            ivWallet.payError();
        }
    }

    public void savePay(String pay, String targetMaid, String code) {
        String finalPay = "-" + pay;
        //CashFlowEntity entity = new CashFlowEntity(maid, "Dada Thai", "消費", finalPay, new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault()).format(new Date()));
        //mWallet.insertData(entity);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("mode", "0");
        map.put("edit", "");
        ArrayList<GCashFlow.Data> cashFlows = new ArrayList<>();
        GCashFlow.Data data = new GCashFlow.Data();
        data.setTarget("學生餐廳");
        data.setType("消費");
        data.setCheck("0");
        data.setCash(finalPay);
        data.setMaid(maid);
        cashFlows.add(data);
        GCashFlow.Data data2 = new GCashFlow.Data();
        data2.setTarget("宮本昭子");
        data2.setType("商品交易");
        data2.setMaid(targetMaid);
        data2.setCash(pay);
        data2.setCheck(code);
        cashFlows.add(data2);
        Gson gson = new Gson();
        String stringData = gson.toJson(cashFlows);
        map.put("data", stringData);
        mWallet.sendCashFlowData(map);
    }

    public void handleCheckPay(String code) { mWallet.getCashFlowData(NetworkUtil.getInstance().getMap(), code); }

    public void saveFromQR(String cash) {
        //final CashFlowEntity entity = new CashFlowEntity(maid, "蔡旻岳", "商品交易", cash, new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault()).format(new Date()));
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mWallet.insertData(entity);
            }
        }, 5000);*/
    }

    private boolean isClear = false;
    private int clearCash = 0;
    public void handleClear(int cash, ArrayList<ECashFlow> eCashFlows) {
        //isClear = true;
        //clearCash = cash;
        //mWallet.clearData(maid);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("mode", "1");
        ArrayList<GCashFlow.Data> cashFlows = new ArrayList<>();
        GCashFlow.Data data = new GCashFlow.Data();
        data.setTarget("中興大學");
        data.setCheck("0");
        data.setClear("1");
        data.setType("碳幣結算");
        data.setMaid(maid);
        ArrayList<String> walletIds = new ArrayList<>();
        int total = 0;
        for (int i = 0; i < eCashFlows.size(); i++) {
            if (eCashFlows.get(i).getClear().equals("0")) {
                total += Integer.parseInt(eCashFlows.get(i).getCash());
                walletIds.add(eCashFlows.get(i).getWalletId());
            }
        }
        data.setCash("-" + (total));
        cashFlows.add(data);
        Gson gson = new Gson();
        map.put("data", gson.toJson(cashFlows));
        map.put("edit", gson.toJson(walletIds));
        if (total > 0) {
            mWallet.sendCashFlowData(map);
        } else {
            ivWallet.clearError();
        }
    }

    @Override
    public void finishInsert() { mWallet.getData(maid, false); }

    @Override
    public void handleCashFlowData(List<CashFlowEntity> cashFlowEntities, boolean showDetail) {
        ArrayList<ECashFlow> cashFlow = new ArrayList<>();
        int cash = 0;
        if (cashFlowEntities.size() > 0) {
            int size = cashFlowEntities.size();
            for (int i = 0; i < size; i++) {
                ECashFlow eCashFlow = new ECashFlow();
                StringBuilder builder = new StringBuilder(cashFlowEntities.get(i).getDate());
                builder.delete(11, builder.length());
                eCashFlow.setName(cashFlowEntities.get(i).getName());
                eCashFlow.setType(cashFlowEntities.get(i).getType());
                eCashFlow.setCash(cashFlowEntities.get(i).getCash());
                eCashFlow.setDate(builder.toString());
                cashFlow.add(eCashFlow);
                cash += Integer.parseInt(cashFlowEntities.get(i).getCash());
            }
        }
        ivWallet.showDetail(cashFlow, showDetail, cash);
    }

    @Override
    public void handleCashFlowData(GCashFlow gCashFlow, boolean showDetail) {
        ArrayList<ECashFlow> eCashFlows = new ArrayList<>();
        int cash = 0;
        for (int i = 0; i < gCashFlow.getData().size(); i++) {
            ECashFlow eCashFlow = new ECashFlow();
            StringBuilder builder = new StringBuilder(gCashFlow.getData().get(i).getDate());
            builder.delete(11, builder.length());
            eCashFlow.setDate(builder.toString());
            eCashFlow.setName(gCashFlow.getData().get(i).getTarget());
            eCashFlow.setCash(gCashFlow.getData().get(i).getCash());
            eCashFlow.setType(gCashFlow.getData().get(i).getType());
            eCashFlow.setWalletId(gCashFlow.getData().get(i).getId());
            eCashFlow.setClear(gCashFlow.getData().get(i).getClear());
            eCashFlows.add(eCashFlow);
            if (gCashFlow.getData().get(i).getClear().equals("0")) {
                cash += Integer.parseInt(gCashFlow.getData().get(i).getCash());
            }
        }
        if (maid.equals("sbUhP7d4qS")) {
            cash = 0;
        }
        ivWallet.showDetail(eCashFlows, showDetail, cash);
    }

    @Override
    public void handleCashFlowData(GCashFlow gCashFlow, String code) {
        int cash = 0;
        String pay = "";
        for (int i = 0; i < gCashFlow.getData().size(); i++) {
            if (gCashFlow.getData().get(i).getCheck().equals(code)) {
                pay = gCashFlow.getData().get(i).getCash();
            }
            if (gCashFlow.getData().get(i).getClear().equals("0")) {
                cash += Integer.parseInt(gCashFlow.getData().get(i).getCash());
            }
        }
        ivWallet.showFinishCheck(pay, cash);
    }

    @Override
    public void finishSend(GSendLove gSendLove) { mWallet.getCashFlowData(NetworkUtil.getInstance().getMap(), false); }
}
