package com.switube.www.landmark2018test.model;

import com.google.android.gms.maps.model.LatLng;
import com.switube.www.landmark2018test.gson.GAttractionListData;
import com.switube.www.landmark2018test.gson.GPlaceIdData;
import com.switube.www.landmark2018test.presenter.callback.IPAttractionList;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MAttractionList {
    private IPAttractionList ipAttractionList;
    public MAttractionList(IPAttractionList IPAttractionList) {
        ipAttractionList = IPAttractionList;
    }

    public void getAllAttractionData(Map<String, String> map, final LatLng latLng) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getPlaceBaseDataWithRx("GetLargeAppMapPinList_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GAttractionListData>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GAttractionListData gAttractionListData) {
                        ipAttractionList.handleParseGsonData(gAttractionListData, latLng);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void handleGetPlaceId(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getPlaceDataWithRx("https://maps.googleapis.com/maps/api/geocode/json", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GPlaceIdData>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GPlaceIdData gPlaceIdData) {
                        ipAttractionList.handleSavePlaceId(gPlaceIdData);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
