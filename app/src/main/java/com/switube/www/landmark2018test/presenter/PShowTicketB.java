package com.switube.www.landmark2018test.presenter;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.view.callback.IVShowTicketB;

public class PShowTicketB {
    private IVShowTicketB ivShowTicketB;
    public PShowTicketB(IVShowTicketB ivShowTicketB) {
        this.ivShowTicketB = ivShowTicketB;
    }

    public void init() {
        String msgA = "";
        String msgB = "";
        String msgC = "";
        String msgD = "";
        String msgE = "";
        if (MyApplication.getAppData().getTicketData() != null) {
            msgA = "已使用";
            msgB = MyApplication.getAppData().getTicketData().getStoreName();
            msgC = MyApplication.getAppData().getTicketData().getuDate();
            msgD = MyApplication.getAppData().getTicketData().getGoodId();
            msgE = MyApplication.getAppData().getTicketData().getGoodName();
        }
        ivShowTicketB.showDetail(msgA, msgB, msgC, msgD, msgE);
    }
}
