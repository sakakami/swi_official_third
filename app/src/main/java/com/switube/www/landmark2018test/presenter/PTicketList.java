package com.switube.www.landmark2018test.presenter;

import com.switube.www.landmark2018test.gson.GTicket;
import com.switube.www.landmark2018test.model.MTicketList;
import com.switube.www.landmark2018test.presenter.callback.IPTicketList;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVTicketList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PTicketList implements IPTicketList {
    private IVTicketList ivTicketList;
    private MTicketList mTicketList;
    public PTicketList(IVTicketList ivTicketList) {
        this.ivTicketList = ivTicketList;
        mTicketList = new MTicketList(this);
    }

    public void getTickets(boolean isPageOne) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        mTicketList.getTickets(map, isPageOne);
    }

    @Override
    public void handleTickets(GTicket gTicket, boolean isPageOne) {
        int size = gTicket.getData().size();
        String isUsed;
        if (isPageOne) {
            isUsed = "0";
        } else {
            isUsed = "1";
        }
        List<GTicket.Data> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (gTicket.getData().get(i).getGoodUse().equals(isUsed)) {
                list.add(gTicket.getData().get(i));
            }
        }
        ivTicketList.refreshAdapter(list);
    }
}
