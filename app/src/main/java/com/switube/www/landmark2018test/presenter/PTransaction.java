package com.switube.www.landmark2018test.presenter;

import com.switube.www.landmark2018test.entity.ETransaction;
import com.switube.www.landmark2018test.gson.GCashFlow;
import com.switube.www.landmark2018test.model.MTransaction;
import com.switube.www.landmark2018test.presenter.callback.IPTransaction;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVTransaction;

import java.util.ArrayList;
import java.util.List;

public class PTransaction implements IPTransaction {
    private IVTransaction ivTransaction;
    private MTransaction mTransaction;
    public PTransaction(IVTransaction ivTransaction) {
        this.ivTransaction = ivTransaction;
        mTransaction = new MTransaction(this);
    }

    public void init() {
        mTransaction.getData(NetworkUtil.getInstance().getMap());
    }

    @Override
    public void handleCashData(GCashFlow gCashFlow) {
        int size = gCashFlow.getData().size();
        List<ETransaction> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ETransaction eTransaction = new ETransaction();
            eTransaction.setId(gCashFlow.getData().get(i).getId());
            eTransaction.setDate(gCashFlow.getData().get(i).getCDate());
            StringBuilder builder = new StringBuilder(gCashFlow.getData().get(i).getCash());
            if (builder.indexOf("-") >= 0) {
                eTransaction.setType("付款");
                builder.delete(0, 1);
                eTransaction.setCash(builder.toString());
            } else {
                eTransaction.setType("收款");
                eTransaction.setCash(builder.toString());
            }
            if (gCashFlow.getData().get(i).getTarget().length() > 0) {
                eTransaction.setName(gCashFlow.getData().get(i).getTarget());
            } else if (gCashFlow.getData().get(i).getTicketName().length() > 0) {
                eTransaction.setName(gCashFlow.getData().get(i).getTicketName());
            } else {
                eTransaction.setName("暫無資料");
            }
            list.add(eTransaction);
        }
        ivTransaction.initAdapter(list);
    }
}
