package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.presenter.callback.IPAttractionType;
import com.switube.www.landmark2018test.database.AppDatabase;
import com.switube.www.landmark2018test.database.entity.AttractionModeEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;

import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MAttractionType {
    private IPAttractionType ipAttractionType;
    public MAttractionType(IPAttractionType IPAttractionType) {
        ipAttractionType = IPAttractionType;
    }

    public void getModeListData() {
        AppDatabase.getInstance()
                .attractionModeDao()
                .getAttractionModeDataWithRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AttractionModeEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<AttractionModeEntity> attractionModeEntities) {
                        ipAttractionType.handleListDataOne(attractionModeEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getStyleListData() {
        AppDatabase.getInstance()
                .attractionStyleDao()
                .getAttractionStyleDataWithRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AttractionStyleEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<AttractionStyleEntity> attractionStyleEntities) {
                        ipAttractionType.handleListDataTwo(attractionStyleEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
