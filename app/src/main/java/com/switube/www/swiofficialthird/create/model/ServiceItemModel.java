package com.switube.www.swiofficialthird.create.model;

import android.content.Context;
import android.util.Log;

import com.switube.www.swiofficialthird.create.ResultEntity;
import com.switube.www.swiofficialthird.create.presenter.IServiceItemPresenter;
import com.switube.www.swiofficialthird.database.AppDatabase;
import com.switube.www.swiofficialthird.database.entity.AttractionClassEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionItemEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionTermEntity;
import com.switube.www.swiofficialthird.util.NetworkBUtil;
import com.switube.www.swiofficialthird.util.NetworkDUtil;
import com.switube.www.swiofficialthird.util.NetworkUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class ServiceItemModel {
    private IServiceItemPresenter mIServiceItemPresenter;
    public ServiceItemModel(IServiceItemPresenter iServiceItemPresenter) {
        mIServiceItemPresenter = iServiceItemPresenter;
    }

    public void getDataOne(final Context context, String msid) {
        AppDatabase.getInstance(context)
                .attractionClassDao()
                .getAttractionClassDataWithRx(msid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AttractionClassEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<AttractionClassEntity> attractionClassEntities) {
                        mIServiceItemPresenter.handleDataOne(context, attractionClassEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getDataTwo(final Context context, String msid) {
        AppDatabase.getInstance(context)
                .attractionTermDao()
                .getAttractionTermDataWithRx(msid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AttractionTermEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<AttractionTermEntity> attractionTermEntities) {
                        mIServiceItemPresenter.handleDataTwo(context, attractionTermEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getDataThree(Context context, String mtid) {
        AppDatabase.getInstance(context)
                .attractionItemDao()
                .getAttractionItemDataWithRx(mtid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AttractionItemEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<AttractionItemEntity> attractionItemEntities) {
                        mIServiceItemPresenter.handleDataThree(attractionItemEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendCreateData(Map<String, String> map, Map<String, RequestBody> image) {
        //mIServiceItemPresenter.handleToNextPage();
        NetworkDUtil.getInstance()
                .getNetworkService()
                .getValue("SendNewMapInfo_v1.php", map, image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(String s) {
                        Log.e("result", s);
                        mIServiceItemPresenter.handleToNextPage();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", e.toString());
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendCreateData(Map<String, String> map) {
        NetworkDUtil.getInstance()
                .getNetworkService()
                .getValue("SendNewMapInfo_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("result", s);
                        mIServiceItemPresenter.handleToNextPage();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", e.toString());
                    }

                    @Override
                    public void onComplete() {}
                });
    }
}
