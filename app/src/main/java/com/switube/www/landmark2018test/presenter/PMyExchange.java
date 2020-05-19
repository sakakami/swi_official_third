package com.switube.www.landmark2018test.presenter;

import com.switube.www.landmark2018test.gson.GTicket;
import com.switube.www.landmark2018test.model.MMyExchange;
import com.switube.www.landmark2018test.presenter.callback.IPMyExchange;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVMyExchange;

public class PMyExchange implements IPMyExchange {
    private IVMyExchange ivExchangeList;
    private MMyExchange mMyExchange;
    public PMyExchange(IVMyExchange ivExchangeList) {
        this.ivExchangeList = ivExchangeList;
        mMyExchange = new MMyExchange(this);
    }

    public void getTicketsList() {
        mMyExchange.getTickets(NetworkUtil.getInstance().getMap());
    }

    @Override
    public void handleTickets(GTicket gTicket) {
        int notUsed = 0;
        int isUsed = 0;
        int size = gTicket.getData().size();
        for (int i = 0; i < size; i++) {
            if (gTicket.getData().get(i).getGoodUse().equals("0")) {
                notUsed++;
            } else {
                isUsed++;
            }
        }
        ivExchangeList.showCount(notUsed, isUsed);
    }
}
