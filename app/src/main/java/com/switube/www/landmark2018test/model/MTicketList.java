package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GTicket;
import com.switube.www.landmark2018test.presenter.callback.IPTicketList;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MTicketList {
    private IPTicketList ipTicketList;
    public MTicketList(IPTicketList ipTicketList) {
        this.ipTicketList = ipTicketList;
    }

    public void getTickets(Map<String, String> map, boolean isPageOne) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getTicketData("GetTicket_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GTicket>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GTicket gTicket) {
                        ipTicketList.handleTickets(gTicket, isPageOne);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
