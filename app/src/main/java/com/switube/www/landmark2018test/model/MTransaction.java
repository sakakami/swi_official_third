package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GCashFlow;
import com.switube.www.landmark2018test.presenter.callback.IPTransaction;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MTransaction {
    private IPTransaction ipTransaction;
    public MTransaction(IPTransaction ipTransaction) {
        this.ipTransaction = ipTransaction;
    }

    public void getData(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getCashFlowData("GetCashFlow_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GCashFlow>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GCashFlow gCashFlow) {
                        ipTransaction.handleCashData(gCashFlow);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
