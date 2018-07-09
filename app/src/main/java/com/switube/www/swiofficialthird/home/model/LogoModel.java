package com.switube.www.swiofficialthird.home.model;

import android.content.Context;

import com.switube.www.swiofficialthird.database.AppDatabase;
import com.switube.www.swiofficialthird.database.entity.LanguageEntity;
import com.switube.www.swiofficialthird.home.gson.MediumMenuEntity;
import com.switube.www.swiofficialthird.home.presenter.ILogoPresenter;
import com.switube.www.swiofficialthird.util.AppConstant;
import com.switube.www.swiofficialthird.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LogoModel {
    private ILogoPresenter mILogoPresenter;
    public LogoModel(ILogoPresenter iLogoPresenter) {
        mILogoPresenter = iLogoPresenter;
    }

    public void handleGetData(final Context context, Map<String, String> map) {
        NetworkUtil
                .getInstance()
                .getNetworkService()
                .getDataWithRx(AppConstant.NET_GET_APP_INFO, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MediumMenuEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(MediumMenuEntity mediumMenuEntity) {
                        mILogoPresenter.handleParseLanguage(context, mediumMenuEntity);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void handleInsertData(final Context context, final List<LanguageEntity> languageEntities,
                                 final boolean needShow) {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        AppDatabase.getInstance(context).languageDao().cleanTable();
                        AppDatabase.getInstance(context).languageDao().insertAllData(languageEntities);
                        emitter.onNext("ok");
                        emitter.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(String s) {
                        if (s.equals("ok")) {
                            if (needShow) {
                                getUpgradeMessage(context);
                            } else {
                                mILogoPresenter.handleFinishUpdate();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    private void getUpgradeMessage(final Context context) {
        Observable
                .create(new ObservableOnSubscribe<List<LanguageEntity>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<LanguageEntity>> emitter) throws Exception {
                        List<LanguageEntity> words = new ArrayList<>();
                        words.add(AppDatabase.getInstance(context).languageDao().getDataFromWordId("X2_a"));
                        words.add(AppDatabase.getInstance(context).languageDao().getDataFromWordId("X2_b1"));
                        words.add(AppDatabase.getInstance(context).languageDao().getDataFromWordId("X2_b2"));
                        emitter.onNext(words);
                        emitter.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LanguageEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(List<LanguageEntity> s) {
                        mILogoPresenter.handleUpgradeMessage(s);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
