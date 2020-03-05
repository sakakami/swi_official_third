package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.entity.ECashFlow;

import java.util.ArrayList;

public interface IVWallet {
    void showDetail(ArrayList<ECashFlow> eCashFlows, boolean showDetail, int cash);
    void checkPay(String cash, String maid, String code);
    void payError();
    void showFinishCheck(String pay, int cash);
    void clearError();
}
