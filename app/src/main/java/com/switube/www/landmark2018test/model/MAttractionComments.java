package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GCommentsData;
import com.switube.www.landmark2018test.gson.GLikeUnlike;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.presenter.callback.IPAttractionComments;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MAttractionComments {
    private IPAttractionComments ipAttractionComments;
    public MAttractionComments(IPAttractionComments ipAttractionComments) {
        this.ipAttractionComments = ipAttractionComments;
    }

    public void sendMessageData(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendMessage("SendNewArticleComment_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GCommentsData>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GCommentsData gCommentsData) {
                        ipAttractionComments.handleResult(gCommentsData);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendLikeData(Map<String, String> map, final int index, final String like) {
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
                        ipAttractionComments.handleLikeResult(gLikeUnlike, index, like);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendEditData(Map<String, String> map, final int index, final String message) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendEditData("SendModifyArticleComment_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipAttractionComments.handleFinishEdit(gSendLove, index, message);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendDelComment(Map<String, String> map, final int index) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendEditData("SendModifyArticleComment_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipAttractionComments.handleFinishDel(gSendLove, index);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
