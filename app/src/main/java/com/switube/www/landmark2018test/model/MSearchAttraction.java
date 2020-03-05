package com.switube.www.landmark2018test.model;

import com.google.android.gms.maps.model.LatLng;
import com.switube.www.landmark2018test.database.AppDatabase;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSearchAttractionDetail;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.presenter.callback.IPSearchAttraction;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MSearchAttraction {
    private IPSearchAttraction ipSearchAttraction;
    public MSearchAttraction(IPSearchAttraction ipSearchAttraction) {
        this.ipSearchAttraction = ipSearchAttraction;
    }

    public void getSearchData(Map<String, String> map, final LatLng latLng,
                              final int distance) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getSearchPlaceDetailDataWithRx("GetLargeAppSearchPinDetail_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSearchAttractionDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSearchAttractionDetail gSearchAttractionDetail) {
                        ipSearchAttraction.handleAttractionDataWithSelected(gSearchAttractionDetail,
                                latLng, distance);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getAllStyleData(final GSearchAttractionDetail gSearchAttractionDetail,
                                final LatLng latLng, final int distance) {
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
                        ipSearchAttraction.handleAttractionDataWithSelected(attractionStyleEntities,
                                gSearchAttractionDetail, latLng, distance);
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
                        ipSearchAttraction.handleSaveList(gSaveList, spid);
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
                        ipSearchAttraction.handleFinishAddToStroke(gSaveList, isClickedList, attractionId);
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
                        ipSearchAttraction.handleFinishCreateNewStroke(gSendLove);
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
                        ipSearchAttraction.handleFinishAddToCollect(gSendLove, index);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
