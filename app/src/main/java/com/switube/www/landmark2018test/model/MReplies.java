package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GCommentsData;
import com.switube.www.landmark2018test.gson.GLikeUnlike;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.presenter.callback.IPReplies;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MReplies {
    private IPReplies ipReplies;
    public MReplies(IPReplies ipReplies) {
        this.ipReplies = ipReplies;
    }

    public void sendReplyData(Map<String, String> map) {
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
                        ipReplies.handleResult(gCommentsData);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendLikeData(Map<String, String> map, final String like, final int index, final boolean isReply) {
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
                        ipReplies.handleLikeResult(gLikeUnlike, like, index, isReply);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendEditData(Map<String, String> map, final int index, final String message, final boolean isComment, final int mainIndex) {
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
                        ipReplies.handleFinishEdit(gSendLove, index, message, isComment, mainIndex);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendDelData(Map<String, String> map, final int index, final boolean isComment, final int mainIndex) {
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
                        ipReplies.handleFinishDel(gSendLove, index, isComment, mainIndex);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
