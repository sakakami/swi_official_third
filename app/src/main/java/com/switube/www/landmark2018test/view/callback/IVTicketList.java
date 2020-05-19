package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.gson.GTicket;

import java.util.List;

public interface IVTicketList {
    void refreshAdapter(List<GTicket.Data> list);
}
