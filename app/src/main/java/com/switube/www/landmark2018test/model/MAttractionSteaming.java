package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GLikeUnlike;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.presenter.callback.IPAttractionSteaming;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MAttractionSteaming {
    private IPAttractionSteaming ipAttractionSteaming;
    public MAttractionSteaming(IPAttractionSteaming ipAttractionSteaming) {
        this.ipAttractionSteaming = ipAttractionSteaming;
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
                        ipAttractionSteaming.handleLikeOrUnlikeJson(gLikeUnlike, num);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void deleteComment(Map<String, String> map, final int index) {
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
                        ipAttractionSteaming.finishDeleteComment(gSendLove, index);
                    }

                    @Override
                    public void onError(Throwable e) { }

                    @Override
                    public void onComplete() {}
                });
    }
}
