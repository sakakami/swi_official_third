package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GTicket;
import com.switube.www.landmark2018test.presenter.callback.IPMyExchange;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MMyExchange {
    private IPMyExchange ipMyExchange;
    public MMyExchange(IPMyExchange ipMyExchange) {
        this.ipMyExchange = ipMyExchange;
    }

    public void getTickets(Map<String, String> map) {
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
                        ipMyExchange.handleTickets(gTicket);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
