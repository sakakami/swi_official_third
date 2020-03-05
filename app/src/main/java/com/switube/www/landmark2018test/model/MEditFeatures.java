package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.database.AppDatabase;
import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;
import com.switube.www.landmark2018test.entity.EFeatures;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.presenter.callback.IPEditFeatures;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MEditFeatures {
    private IPEditFeatures ipEditFeatures;
    public MEditFeatures(IPEditFeatures ipEditFeatures) {
        this.ipEditFeatures = ipEditFeatures;
    }

    public void getStyleData(final String msid) {
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
                        ipEditFeatures.handleStyleData(attractionStyleEntity, msid);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getClassData(final String msid, final List<EFeatures> eFeaturesList) {
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
                        ipEditFeatures.handleClassData(attractionClassEntities, msid, eFeaturesList);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getTermData(String msid, final List<EFeatures> eFeaturesList) {
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
                        ipEditFeatures.handleTermData(attractionTermEntities, eFeaturesList);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getItemData(final List<AttractionTermEntity> attractionTermEntities, final List<EFeatures> eFeaturesList) {
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
                        ipEditFeatures.handleItemData(attractionItemEntities, attractionTermEntities, eFeaturesList);
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
                .sendEditData("SendModifyMapInfo_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipEditFeatures.finishSend(gSendLove);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
