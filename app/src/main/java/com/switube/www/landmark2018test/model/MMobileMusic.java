package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GMusicRadio;
import com.switube.www.landmark2018test.gson.GPushMusic;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.presenter.callback.IPMobileMusic;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MMobileMusic {
    private IPMobileMusic ipMobileMusic;
    public MMobileMusic(IPMobileMusic ipMobileMusic) {
        this.ipMobileMusic = ipMobileMusic;
    }

    public void getMusicRadioData(Map<String, String> map, final boolean isCollect) {
        NetworkUtil.getInstance().getNetworkService()
                .getMusicRadioData("GetLargeAppMapTubeApp_v2.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GMusicRadio>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GMusicRadio gMusicRadio) {
                        ipMobileMusic.handleMusicRadioData(gMusicRadio, isCollect);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getPushMusicData(Map<String, String> map) {
        NetworkUtil.getInstance().getNetworkService()
                .getPushMusicData("GetLargeAppMapTubeChannel_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GPushMusic>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GPushMusic gPushMusic) {
                        ipMobileMusic.handlePushMusicData(gPushMusic);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendLove(Map<String, String> map, final int index) {
        NetworkUtil.getInstance().getNetworkService()
                .sendLove("SendMapTubeCollect_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipMobileMusic.handleLoveData(gSendLove, index);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
