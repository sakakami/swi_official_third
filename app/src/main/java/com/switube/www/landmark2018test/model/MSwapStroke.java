package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.presenter.callback.IPSwapStroke;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MSwapStroke {
    private IPSwapStroke ipSwapStroke;
    public MSwapStroke(IPSwapStroke ipSwapStroke) {
        this.ipSwapStroke = ipSwapStroke;
    }

    public void sendAddToStroke(Map<String, String> map, boolean isMyCollection, final List<Integer> fromPositionList, final List<Integer> toPositionList) {
        String url;
        if (isMyCollection) {
            url = "SendMapRouteCollect_v1.php";
        } else {
            url = "SendMapRouteListPin_v2.php";
        }
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendLove(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipSwapStroke.handleFinishToStroke(gSendLove, fromPositionList, toPositionList);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
