package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GMusicRadio;
import com.switube.www.landmark2018test.presenter.callback.IPMore;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MMore {
    private IPMore ipMore;
    public MMore(IPMore ipMore) {
        this.ipMore = ipMore;
    }

    public void getMoreData(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getMusicRadioData("GetLargeAppMapTubeApp_v2.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GMusicRadio>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GMusicRadio gMusicRadio) {
                        ipMore.handleMoreData(gMusicRadio);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getStrokeData(Map<String, String> map, final GMusicRadio more) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getMusicRadioData("GetLargeAppMapRouteApp_v2.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GMusicRadio>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GMusicRadio gMusicRadio) {
                        ipMore.handleStrokeData(gMusicRadio, more);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
