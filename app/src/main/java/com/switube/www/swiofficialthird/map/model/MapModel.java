package com.switube.www.swiofficialthird.map.model;

import android.content.Context;
import android.util.Log;

import com.switube.www.swiofficialthird.database.AppDatabase;
import com.switube.www.swiofficialthird.database.entity.AttractionClassEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionItemEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionModeEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionTermEntity;
import com.switube.www.swiofficialthird.database.entity.MapPlaceBaseDataEntity;
import com.switube.www.swiofficialthird.map.gson.AttractionDataEntity;
import com.switube.www.swiofficialthird.map.gson.MenuDataEntity;
import com.switube.www.swiofficialthird.map.gson.PlaceBaseDataEntity;
import com.switube.www.swiofficialthird.map.gson.PlaceDataEntity;
import com.switube.www.swiofficialthird.map.presenter.IMapPresenter;
import com.switube.www.swiofficialthird.util.AppConstant;
import com.switube.www.swiofficialthird.util.NetworkBUtil;
import com.switube.www.swiofficialthird.util.NetworkUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import io.reactivex.MaybeObserver;
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

    private void getPlaceData(final Context context, @NotNull Map<String, String> map, final MenuDataEntity menuDataEntity) {
        Log.e("map", map.toString());
        NetworkUtil
                .getInstance()
                .getNetworkService()
                .getPlaceBaseDataWithRx("GetLargeAppMapPinList_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlaceBaseDataEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(PlaceBaseDataEntity placeBaseDataEntity) {
                        mIMapPresenter.handleParseGsonData(context, menuDataEntity, placeBaseDataEntity);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getMenuData(final Context context, final Map<String, String> map) {
        NetworkUtil
                .getInstance()
                .getNetworkService()
                .getMenuDataWithRx("GetMapLargeAppMenu_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MenuDataEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(MenuDataEntity menuDataEntity) {
                        //mIMapPresenter.handleParseMenuDataGson(context, menuDataEntity);
                        getPlaceData(context, map, menuDataEntity);
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

    public void handleInsertMenuData(final Context context, final List<AttractionModeEntity> attractionModeEntities,
                                     final List<AttractionStyleEntity> attractionStyleEntities,
                                     final List<AttractionClassEntity> attractionClassEntities,
                                     final List<AttractionTermEntity> attractionTermEntities,
                                     final List<AttractionItemEntity> attractionItemEntities,
                                     final List<MapPlaceBaseDataEntity> mapPlaceBaseDataEntities) {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        AppDatabase.getInstance(context).attractionModeDao().cleanTable();
                        AppDatabase.getInstance(context).attractionModeDao().insertAllData(attractionModeEntities);
                        AppDatabase.getInstance(context).attractionStyleDao().cleanTable();
                        AppDatabase.getInstance(context).attractionStyleDao().insertAllData(attractionStyleEntities);
                        AppDatabase.getInstance(context).attractionClassDao().cleanTable();
                        AppDatabase.getInstance(context).attractionClassDao().insertAllData(attractionClassEntities);
                        AppDatabase.getInstance(context).attractionTermDao().cleanTable();
                        AppDatabase.getInstance(context).attractionTermDao().insertAllData(attractionTermEntities);
                        AppDatabase.getInstance(context).attractionItemDao().cleanTable();
                        AppDatabase.getInstance(context).attractionItemDao().insertAllData(attractionItemEntities);
                        AppDatabase.getInstance(context).mapPlaceBaseDataDao().cleanTable();
                        AppDatabase.getInstance(context).mapPlaceBaseDataDao().insertAllData(mapPlaceBaseDataEntities);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(String s) {
                        Log.e("insert", "data");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", e.toString());
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    public void handleGetPlaceId(String latlng, String language) {
        Log.e("map", latlng + "," + language);
        NetworkBUtil.getInstance()
                .getNetworkService()
                .getPlaceDataWithRx("json", NetworkBUtil.getInstance().getMap(latlng, language))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlaceDataEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(PlaceDataEntity placeDataEntity) {
                        mIMapPresenter.handleSavePlaceId(placeDataEntity);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
