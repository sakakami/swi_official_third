package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.database.AppDatabase;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.gson.GPushStroke;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.gson.GStrokeList;
import com.switube.www.landmark2018test.presenter.callback.IPStroke;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MStroke {
    private IPStroke ipStroke;
    public MStroke(IPStroke ipStroke) {
        this.ipStroke = ipStroke;
    }

    public void getMyStrokeData(Map<String, String> map) {
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
                        ipStroke.handleStrokeList(gSaveList);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void createNewStroke(Map<String, String> map, final int mode) {
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
                        ipStroke.finishSend(mode, gSendLove);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getPushStrokeData(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getPushStrokeData("GetLargeAppMapPushRouteList_v2.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GPushStroke>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GPushStroke gPushStroke) {
                        ipStroke.handlePushStrokeData(gPushStroke);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getStrokeList(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getStrokeList("GetLargeAppMapRoutePinList_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GStrokeList>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GStrokeList gStrokeList) {
                        ipStroke.handleStrokeList(gStrokeList);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
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
                        ipStroke.init(attractionStyleEntities);
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
                        ipStroke.handleFinishAddToCollect(gSendLove, index);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendAddToStroke(Map<String, String> map, final int index) {
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
                        ipStroke.handleFinishToStroke(gSendLove, index);
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
                        ipStroke.handleSaveList(gSaveList);
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
                        ipStroke.handleFinishAddToStroke(gSaveList, isClickedList, attractionId);
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
                        ipStroke.handleFinishCreate(gSendLove, title);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendAddToMyStroke(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendLove("SendMapRouteToMe_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipStroke.handleFinishAddToMyStroke(gSendLove);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
