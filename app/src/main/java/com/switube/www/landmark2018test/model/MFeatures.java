package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GCreateAttraction;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.presenter.callback.IPFeatures;
import com.switube.www.landmark2018test.database.AppDatabase;
import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class MFeatures {
    private IPFeatures ipFeatures;
    public MFeatures(IPFeatures ipFeatures) {
        this.ipFeatures = ipFeatures;
    }

    public void getDataOne(String msid) {
        AppDatabase.getInstance()
                .attractionClassDao()
                .getAttractionClassDataWithRx(msid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AttractionClassEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<AttractionClassEntity> attractionClassEntities) {
                        ipFeatures.handleDataOne(attractionClassEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getDataTwo(String msid) {
        AppDatabase.getInstance()
                .attractionTermDao()
                .getAttractionTermDataWithRx(msid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AttractionTermEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<AttractionTermEntity> attractionTermEntities) {
                        ipFeatures.handleDataTwo(attractionTermEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getDataThree(String mtid) {
        AppDatabase.getInstance()
                .attractionItemDao()
                .getAttractionItemDataWithRx(mtid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AttractionItemEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<AttractionItemEntity> attractionItemEntities) {
                        ipFeatures.handleDataThree(attractionItemEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendCreateData(Map<String, String> map, Map<String, RequestBody> image) {
        //mIPFeatures.handleToNextPage();
        NetworkUtil.getInstance()
                .getNetworkService()
                .getValue("SendNewMapInfo_v2.php", map, image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GCreateAttraction>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GCreateAttraction gCreateAttraction) {
                        ipFeatures.handleToNextPage(gCreateAttraction);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            ipFeatures.handleTimeOutError();
                        }
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendCreateData(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getValue("SendNewMapInfo_v2.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GCreateAttraction>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GCreateAttraction gCreateAttraction) {
                        ipFeatures.handleToNextPage(gCreateAttraction);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            ipFeatures.handleTimeOutError();
                        }
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendEditData(Map<String, String> map) {
        NetworkUtil.getInstance().getNetworkService()
                .sendEditData("SendModifyMapInfo_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipFeatures.finishSend();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendEditData(Map<String, String> map, Map<String, RequestBody> image) {
        NetworkUtil.getInstance().getNetworkService()
                .sendEditDataWithPhoto("SendModifyMapInfo_v1.php", map, image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipFeatures.finishSend();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });

    }
}
