package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GAttractionDataGoogle;
import com.switube.www.landmark2018test.gson.GPlaceIdData;
import com.switube.www.landmark2018test.presenter.callback.IPCreateAttraction;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MCreateAttraction {
    private IPCreateAttraction ipCreateAttraction;
    public MCreateAttraction(IPCreateAttraction ipCreateAttraction) {
        this.ipCreateAttraction = ipCreateAttraction;
    }

    public void getAttractionDetail(final String id, final String language, Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getAttractionDetailWithRx("https://maps.googleapis.com/maps/api/place/details/json", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GAttractionDataGoogle>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GAttractionDataGoogle gAttractionDataGoogle) {
                        switch (language) {
                            case "jp":
                                ipCreateAttraction.handleAttractionDetail(gAttractionDataGoogle);
                                break;
                            case "zh-TW":
                                ipCreateAttraction.handleAttractionDetailTwo(id, gAttractionDataGoogle);
                                break;
                            case "zh-CN":
                                ipCreateAttraction.handleAttractionDetailThree(id, gAttractionDataGoogle);
                                break;
                            default:
                                ipCreateAttraction.handleAttractionDetailOne(id, gAttractionDataGoogle);
                                break;
                        }
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
                        ipCreateAttraction.handleSavePlaceId(gPlaceIdData);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
