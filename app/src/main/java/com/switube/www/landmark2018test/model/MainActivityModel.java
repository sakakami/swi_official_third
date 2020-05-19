package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.gson.GSignInData;
import com.switube.www.landmark2018test.presenter.callback.IMainActivityPresenter;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityModel {
    private IMainActivityPresenter mIMainActivityPresenter;
    public MainActivityModel(IMainActivityPresenter iMainActivityPresenter) {
        mIMainActivityPresenter = iMainActivityPresenter;
    }

    public void sendSignInData(Map<String, String> map, final String signType, final String photoUrl) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendSignInData("https://www.switube.com/mobile_swimap/SendMemberInfo_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSignInData>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSignInData gSignInData) {
                        mIMainActivityPresenter.handleAccountGson(gSignInData, signType, photoUrl);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendHeadPhoto(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendHeadPhoto("SaveMemberSticker_v3.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {}

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
