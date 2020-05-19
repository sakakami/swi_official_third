package com.switube.www.landmark2018test.presenter;

import com.google.gson.Gson;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.gson.CodeContent;
import com.switube.www.landmark2018test.gson.GTicket;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IVShowTicket;

public class PShowTicket {
    private IVShowTicket ivShowTicket;
    public PShowTicket(IVShowTicket ivShowTicket) {
        this.ivShowTicket = ivShowTicket;
    }

    public void init() {
        GTicket.Data data = MyApplication.getAppData().getTicketData();
        CodeContent codeContent = new CodeContent();
        codeContent.setMode("1");
        codeContent.setMaid(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"));
        codeContent.setId(data.getTicketId());
        Gson gson = new Gson();
        String code = gson.toJson(codeContent);
        ivShowTicket.showCode(code, MyApplication.getAppData().getTicketData().getGoodId());
    }
}
