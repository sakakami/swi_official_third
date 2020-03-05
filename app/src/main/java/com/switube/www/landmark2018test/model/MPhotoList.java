package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GLeaveComments;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.presenter.callback.IPPhotoList;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class MPhotoList {
    private IPPhotoList iPPhotoList;
    public MPhotoList(IPPhotoList IPPhotoList) {
        this.iPPhotoList = IPPhotoList;
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
                        iPPhotoList.handleFinishSend();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
