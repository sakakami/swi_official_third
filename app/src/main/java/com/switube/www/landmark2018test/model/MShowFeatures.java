package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.database.AppDatabase;
import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;
import com.switube.www.landmark2018test.entity.EFeatures;
import com.switube.www.landmark2018test.presenter.callback.IPShowFeatures;

import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MShowFeatures {
    private IPShowFeatures ipShowFeatures;
    public MShowFeatures(IPShowFeatures ipShowFeatures) {
        this.ipShowFeatures = ipShowFeatures;
    }

    public void getStyleData(final String msid, final String mscid, final List<String> mtidList, final List<String> mstidList) {
        AppDatabase.getInstance()
                .attractionStyleDao()
                .getAttractionStyleDataWitchRx(msid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<AttractionStyleEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(AttractionStyleEntity attractionStyleEntity) {
                        ipShowFeatures.handleStyleData(attractionStyleEntity, msid, mscid, mtidList, mstidList);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getClassData(final String msid, final List<EFeatures> eFeaturesList, final String mscid, final List<String> mtidList, final List<String> mstidList) {
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
                        ipShowFeatures.handleClassData(attractionClassEntities, msid, eFeaturesList, mscid, mtidList, mstidList);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getTermData(final String msid, final List<EFeatures> eFeaturesList, final List<String> mtidList, final List<String> mstidList) {
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
                        ipShowFeatures.handleTermData(attractionTermEntities, eFeaturesList, msid, mtidList, mstidList);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getItemData(final List<EFeatures> eFeaturesList, final List<AttractionTermEntity> attractionTermEntities, final List<String> mtidList, final List<String> mstidList) {
        AppDatabase.getInstance()
                .attractionItemDao()
                .getAttractionItemData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AttractionItemEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<AttractionItemEntity> attractionItemEntities) {
                        ipShowFeatures.handleItemData(attractionItemEntities, eFeaturesList, attractionTermEntities, mtidList, mstidList);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
