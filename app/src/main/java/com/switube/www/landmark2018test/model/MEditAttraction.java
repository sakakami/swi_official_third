package com.switube.www.landmark2018test.model;

import android.util.Log;

import com.switube.www.landmark2018test.gson.GAttractionDataGoogle;
import com.switube.www.landmark2018test.gson.GPlaceIdData;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.presenter.callback.IPEditAttraction;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class MEditAttraction {
    private IPEditAttraction ipEditAttraction;
    public MEditAttraction(IPEditAttraction ipEditAttraction) {
        this.ipEditAttraction = ipEditAttraction;
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
                        ipEditAttraction.handleResult();
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
                        ipEditAttraction.handleResult();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });

    }

    public void getPlaceDetail(final String id, final String language, Map<String, String> map) {
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
                                ipEditAttraction.handlePlaceDetailJP(gAttractionDataGoogle);
                                break;
                            case "zh-TW":
                                ipEditAttraction.handlePlaceDetailTW(gAttractionDataGoogle, id);
                                break;
                            case "zh-CN":
                                ipEditAttraction.handlePlaceDetailCN(gAttractionDataGoogle, id);
                                break;
                            default:
                                ipEditAttraction.handlePlaceDetailEN(gAttractionDataGoogle, id);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getPlaceId(Map<String, String> map) {
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
                        ipEditAttraction.handlePlaceId(gPlaceIdData);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendDelPlace(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendDelPlace("SendDelPlaceData_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        Log.e("save", gSendLove.getSave());
                        ipEditAttraction.finishDelPlace(gSendLove);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
