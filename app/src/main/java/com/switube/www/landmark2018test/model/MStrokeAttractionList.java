package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.database.AppDatabase;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.presenter.callback.IPStrokeAttractionList;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MStrokeAttractionList {
    private IPStrokeAttractionList ipStrokeAttractionList;
    public MStrokeAttractionList(IPStrokeAttractionList ipStrokeAttractionList) {
        this.ipStrokeAttractionList = ipStrokeAttractionList;
    }

    public void getStyleData() {
        AppDatabase.getInstance()
                .attractionStyleDao()
                .getAttractionStyleDataWithRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AttractionStyleEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<AttractionStyleEntity> attractionStyleEntities) {
                        ipStrokeAttractionList.init(attractionStyleEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendAddToCollect(Map<String, String> map, final int index) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendLove("SendMapRouteCollect_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipStrokeAttractionList.handleFinishAddToCollect(gSendLove, index);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendDelToCollect(Map<String, String> map, final int index) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendLove("SendMapRouteCollect_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipStrokeAttractionList.handleFinishDelToCollect(gSendLove, index);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getSaveList(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getSaveList("GetLargeAppMapRouteList_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSaveList>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSaveList gSaveList) {
                        ipStrokeAttractionList.handleSaveList(gSaveList);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendAddToStroke(Map<String, String> map, final GSaveList gSaveList, final List<Boolean> isClickedList, final String attractionId) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendLove("SendMapRouteListPin_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipStrokeAttractionList.handleFinishAddToStroke(gSaveList, isClickedList, attractionId);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendAddToStroke(Map<String, String> map, final boolean isMove, final int index) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendLove("SendMapRouteListPin_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipStrokeAttractionList.handleFinishToStroke(gSendLove, isMove, index);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void createNewStroke(Map<String, String> map, final String title) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendLove("SendMapRouteTitle_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipStrokeAttractionList.handleFinishCreate(gSendLove, title);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
