package com.switube.www.swiofficialthird.map.model;

import android.content.Context;
import android.util.Log;

import com.switube.www.swiofficialthird.database.AppDatabase;
import com.switube.www.swiofficialthird.database.entity.AttractionEntity;
import com.switube.www.swiofficialthird.map.gson.AttractionDataEntity;
import com.switube.www.swiofficialthird.map.presenter.IMapPresenter;
import com.switube.www.swiofficialthird.util.AppConstant;
import com.switube.www.swiofficialthird.util.NetworkUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MapModel {
    private IMapPresenter mIMapPresenter;
    public MapModel(IMapPresenter iMapPresenter) {
        mIMapPresenter = iMapPresenter;
    }

    public void getAttractionData(final Context context, Map<String, String> map) {
        Log.e("get", "get");
        Log.e("map", map.toString());
        NetworkUtil
                .getInstance()
                .getNetworkService()
                .getAttractionDataWithRx(AppConstant.NET_GET_TRAVEL_CLASS, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AttractionDataEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(AttractionDataEntity attractionDataEntity) {
                        mIMapPresenter.handleParseAttractionGson(context, attractionDataEntity);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void handleInsertAttractionData(final Context context, final List<AttractionEntity> attractionEntities) {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        AppDatabase.getInstance(context).attractionDao().cleanTable();
                        AppDatabase.getInstance(context).attractionDao().insertAllData(attractionEntities);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(String s) {}

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
