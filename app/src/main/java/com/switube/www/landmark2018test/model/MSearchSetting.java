package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.database.AppDatabase;
import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;
import com.switube.www.landmark2018test.presenter.callback.IPSearchSetting;

import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MSearchSetting {
    private IPSearchSetting iPSearchSetting;
    public MSearchSetting(IPSearchSetting IPSearchSetting) {
        iPSearchSetting = IPSearchSetting;
    }

    public void getDataOne(String msid) {
        AppDatabase.getInstance()
                .attractionClassDao()
                .getAttractionClassDataWithRx(msid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AttractionClassEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<AttractionClassEntity> attractionClassEntities) {
                        iPSearchSetting.handleDataOne(attractionClassEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getDataTwo(String msid) {
        AppDatabase.getInstance()
                .attractionTermDao()
                .getAttractionTermDataWithRx(msid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AttractionTermEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<AttractionTermEntity> attractionTermEntities) {
                        iPSearchSetting.handleDataTwo(attractionTermEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getDataThree(String mtid) {
        AppDatabase.getInstance()
                .attractionItemDao()
                .getAttractionItemDataWithRx(mtid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AttractionItemEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<AttractionItemEntity> attractionItemEntities) {
                        iPSearchSetting.handleDataThree(attractionItemEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
