package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.gson.GLeaveComments;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.presenter.callback.IPLeaveComments;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class MLeaveComments {
    private IPLeaveComments ipLeaveComments;
    public MLeaveComments(IPLeaveComments ipLeaveComments) {
        this.ipLeaveComments = ipLeaveComments;
    }

    public void sendCheckInData(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendCheckInData("SendNewArticleInfo_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GLeaveComments>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GLeaveComments gLeaveComments) {
                        ipLeaveComments.handleFinishSend();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendCheckInData(Map<String, String> map, Map<String, RequestBody> image) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendCheckInData("SendNewArticleInfo_v1.php", map, image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GLeaveComments>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GLeaveComments gLeaveComments) {
                        ipLeaveComments.handleFinishSend();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendEditData(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendEditData("SendModifyArticleInfo_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipLeaveComments.handleFinishSend(gSendLove);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendEditData(Map<String, String> map, Map<String, RequestBody> image) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendEditDataWithPhoto("SendModifyArticleInfo_v1.php", map, image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipLeaveComments.handleFinishSend(gSendLove);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getInfoData(Map<String, String> map) {
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
                        ipLeaveComments.handleInfoData(gInfoData);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
