package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.database.AppDatabase;
import com.switube.www.landmark2018test.database.entity.TagQBAEntity;
import com.switube.www.landmark2018test.database.entity.TagQBNEntity;
import com.switube.www.landmark2018test.gson.GTagData;
import com.switube.www.landmark2018test.presenter.callback.IPTag;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MTag {
    private IPTag iPTag;
    public MTag(IPTag iPTag) {
        this.iPTag = iPTag;
    }

    public void getAttractionData(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getTagData("GetMapLargeAppTouristTag_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GTagData>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GTagData gTagData) {
                        iPTag.handleTagGsonData(gTagData);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void handleInsertData(final List<TagQBNEntity> tagQBNEntities, final List<TagQBAEntity> tagQBAEntities, final String qbid) {
        Observable
                .create((ObservableOnSubscribe<String>) emitter -> {
                    AppDatabase.getInstance().tagQBNDao().handleClearTable();
                    AppDatabase.getInstance().tagQBNDao().handleInsertAllData(tagQBNEntities);
                    AppDatabase.getInstance().tagQBADao().handleClearTable();
                    AppDatabase.getInstance().tagQBADao().handleInsertAllData(tagQBAEntities);
                    emitter.onNext("finish");
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(String s) {
                        getSearchQBNData(qbid);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getSearchQBNData(String qbid) {
        AppDatabase.getInstance()
                .tagQBNDao()
                .getSelectTag(qbid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<TagQBNEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<TagQBNEntity> tagQBNEntities) {
                        iPTag.handleQBNData(tagQBNEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getSearchQBAData(String qbnid) {
        AppDatabase.getInstance()
                .tagQBADao()
                .getSelectTag(qbnid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<TagQBAEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<TagQBAEntity> tagQBAEntities) {
                        iPTag.handleQBAData(tagQBAEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
