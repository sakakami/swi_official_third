package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.gson.GTicket;

public interface IPTicketList {
    void handleTickets(GTicket gTicket, boolean isPageOne);
}
