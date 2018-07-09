package com.switube.www.swiofficialthird.home.model;

import android.content.Context;

import com.switube.www.swiofficialthird.database.AppDatabase;
import com.switube.www.swiofficialthird.database.entity.LanguageEntity;
import com.switube.www.swiofficialthird.home.presenter.IMainActivityPresenter;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityModel {
    private IMainActivityPresenter mIMainActivityPresenter;
    public MainActivityModel(IMainActivityPresenter iMainActivityPresenter) {
        mIMainActivityPresenter = iMainActivityPresenter;
    }

    public void handleSearchWord (Context context, String keyWord) {
        AppDatabase.getInstance(context)
                .languageDao()
                .getDataFromWordIdWithRx(keyWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<LanguageEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(LanguageEntity languageEntity) {

                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
