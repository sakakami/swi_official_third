package com.switube.www.swiofficialthird.map.model;

import android.content.Context;

import com.switube.www.swiofficialthird.database.AppDatabase;
import com.switube.www.swiofficialthird.database.entity.AttractionClassEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionItemEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionTermEntity;
import com.switube.www.swiofficialthird.map.presenter.IFindSettingPresenter;

import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FindSettingModel {
    private IFindSettingPresenter mIFindSettingPresenter;
    public FindSettingModel(IFindSettingPresenter iFindSettingPresenter) {
        mIFindSettingPresenter = iFindSettingPresenter;
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
                        mIFindSettingPresenter.handleDataOne(context, attractionClassEntities);
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
                        mIFindSettingPresenter.handleDataTwo(context, attractionTermEntities);
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
                        mIFindSettingPresenter.handleDataThree(attractionItemEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
