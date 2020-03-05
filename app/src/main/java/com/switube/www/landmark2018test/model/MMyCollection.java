package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GMyCollection;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.presenter.callback.IPMyCollection;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MMyCollection {
    private IPMyCollection ipMyCollection;
    public MMyCollection(IPMyCollection ipMyCollection) {
        this.ipMyCollection = ipMyCollection;
    }

    public void getCollectData(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getCollectData("GetLargeAppMapCollectList_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GMyCollection>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(GMyCollection gMyCollection) {
                        ipMyCollection.handleCollectData(gMyCollection);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendRemoveData(Map<String, String> map, final int index) {
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
                        ipMyCollection.handleRemove(gSendLove, index);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getSaveList(Map<String, String> map, final String spid) {
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
                        ipMyCollection.handleSaveList(gSaveList, spid);
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
                        ipMyCollection.handleFinishAdd(gSaveList, isClickedList, attractionId);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void createNewStroke(Map<String, String> map) {
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
                        ipMyCollection.handleFinishCreate(gSendLove);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
