package com.switube.www.swiofficialthird.create.model;

import android.content.Context;
import android.util.Log;

import com.switube.www.swiofficialthird.create.presenter.IAttractionTypePresenter;
import com.switube.www.swiofficialthird.database.AppDatabase;
import com.switube.www.swiofficialthird.database.entity.AttractionModeEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;

import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AttractionTypeModel {
    private IAttractionTypePresenter mIAttractionTypePresenter;
    public AttractionTypeModel(IAttractionTypePresenter iAttractionTypePresenter) {
        mIAttractionTypePresenter = iAttractionTypePresenter;
    }

    public void getModeListData(final Context context) {
        AppDatabase.getInstance(context)
                .attractionModeDao()
                .getAttractionModeDataWithRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AttractionModeEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<AttractionModeEntity> attractionModeEntities) {
                        Log.e("modeSize", String.valueOf(attractionModeEntities.size()));
                        mIAttractionTypePresenter.handleListDataOne(context, attractionModeEntities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("modeError", e.toString());
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    public void getStyleListData(Context context) {
        AppDatabase.getInstance(context)
                .attractionStyleDao()
                .getAttractionStyleDataWithRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AttractionStyleEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<AttractionStyleEntity> attractionStyleEntities) {
                        Log.e("styleSize", String.valueOf(attractionStyleEntities.size()));
                        mIAttractionTypePresenter.handleListDataTwo(attractionStyleEntities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("styleSize", e.toString());
                    }

                    @Override
                    public void onComplete() {}
                });
    }
}
