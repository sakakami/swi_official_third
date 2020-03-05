package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.database.AppDatabase;
import com.switube.www.landmark2018test.database.entity.CashFlowEntity;
import com.switube.www.landmark2018test.gson.GCashFlow;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.presenter.callback.IPWallet;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MWallet {
    private IPWallet ipWallet;
    public MWallet(IPWallet ipWallet) { this.ipWallet = ipWallet; }

    public void insertData(final CashFlowEntity cashFlowEntity) {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        AppDatabase.getInstance().cashFlowDao().insertData(cashFlowEntity);
                        emitter.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(String s) {}

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() { ipWallet.finishInsert(); }
                });
    }

    public void clearData(final String maid) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                AppDatabase.getInstance().cashFlowDao().deleteData(maid);
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(String s) {}

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() { ipWallet.finishInsert(); }
                });
    }

    public void getData(String maid, final boolean showDetail) {
        AppDatabase.getInstance()
                .cashFlowDao()
                .getCashFlowDataWithRx(maid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<CashFlowEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<CashFlowEntity> cashFlowEntities) { ipWallet.handleCashFlowData(cashFlowEntities, showDetail); }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendCashFlowData(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendLove("SendCashFlow_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) { ipWallet.finishSend(gSendLove); }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getCashFlowData(Map<String, String> map, final boolean showDetail) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getCashFlowData("GetCashFlow_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GCashFlow>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GCashFlow gCashFlow) { ipWallet.handleCashFlowData(gCashFlow, showDetail); }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getCashFlowData(Map<String, String> map, final String code) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getCashFlowData("GetCashFlow_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GCashFlow>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GCashFlow gCashFlow) { ipWallet.handleCashFlowData(gCashFlow, code); }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
