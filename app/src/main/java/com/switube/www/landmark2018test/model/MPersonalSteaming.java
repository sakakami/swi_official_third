package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.gson.GLikeUnlike;
import com.switube.www.landmark2018test.gson.GPersonalSteaming;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.presenter.callback.IPPersonalSteaming;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MPersonalSteaming {
    private IPPersonalSteaming ipPersonalSteaming;
    public MPersonalSteaming(IPPersonalSteaming ipPersonalSteaming) {
        this.ipPersonalSteaming = ipPersonalSteaming;
    }

    public void getPersonalData(Map<String, String> map, DisposableObserver<GPersonalSteaming> disposableObserver) {
        NetworkUtil.getInstance().getNetworkService()
                .getPersonalData("GetLargeAppMapUserList_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
    }

    public void sendLikeOrUnlike(Map<String, String> map, final int num) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendLikeOrUnlike("SendNewArticleLike_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GLikeUnlike>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GLikeUnlike gLikeUnlike) {
                        ipPersonalSteaming.handleLikeOrUnlikeJson(gLikeUnlike, num);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendDeleteData(Map<String, String> map, final int index) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendEditData("SendModifyArticleInfo_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipPersonalSteaming.finishDeleteComment(gSendLove, index);
                    }

                    @Override
                    public void onError(Throwable e) { }

                    @Override
                    public void onComplete() {}
                });
    }

    public void getMsid(Map<String, String> map, final List<GInfoData.Article> articles, final int index, final boolean isComment) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getPlaceDetailDataWithRx("GetLargeAppPinDetail_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GInfoData>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GInfoData gInfoData) {
                        ipPersonalSteaming.handleMsidData(gInfoData, articles, index, isComment);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
