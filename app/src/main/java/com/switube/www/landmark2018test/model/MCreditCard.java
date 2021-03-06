package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.presenter.callback.IPCreditCard;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MCreditCard {
    private IPCreditCard ipCreditCard;
    public MCreditCard(IPCreditCard ipCreditCard) {
        this.ipCreditCard = ipCreditCard;
    }

    public void sendCashFlow(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendLove("SendCashFlow_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipCreditCard.finishSendCashFlow(gSendLove);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
