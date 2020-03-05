package com.switube.www.landmark2018test.model;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.switube.www.landmark2018test.database.AppDatabase;
import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionModeEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;
import com.switube.www.landmark2018test.database.entity.CarbonEntity;
import com.switube.www.landmark2018test.database.entity.CashFlowEntity;
import com.switube.www.landmark2018test.database.entity.EcoEntity;
import com.switube.www.landmark2018test.database.entity.MapPlaceBaseDataEntity;
import com.switube.www.landmark2018test.database.entity.MapPlaceBaseSubDataEntity;
import com.switube.www.landmark2018test.gson.GMusicRadio;
import com.switube.www.landmark2018test.gson.GPushMusic;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.gson.GSlideMenuData;
import com.switube.www.landmark2018test.gson.GAttractionListData;
import com.switube.www.landmark2018test.gson.GPlaceIdData;
import com.switube.www.landmark2018test.gson.GSearchAttractionDetail;
import com.switube.www.landmark2018test.gson.GStrokeList;
import com.switube.www.landmark2018test.presenter.callback.IPMap;
import com.switube.www.landmark2018test.util.NetworkUtil;

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

public class MMap {
    private IPMap ipMap;
    public MMap(IPMap ipMap) {
        this.ipMap = ipMap;
    }

    private void getPlaceData(@NotNull Map<String, String> map, final GSlideMenuData gSlideMenuData) {
        NetworkUtil
                .getInstance()
                .getNetworkService()
                .getPlaceBaseDataWithRx("GetLargeAppMapPinList_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GAttractionListData>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GAttractionListData gAttractionListData) {
                        ipMap.handleMusicMenu(gSlideMenuData, gAttractionListData);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getMenuData(final Map<String, String> map) {
        NetworkUtil
                .getInstance()
                .getNetworkService()
                .getMenuDataWithRx("GetMapLargeAppMenu_v2.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSlideMenuData>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSlideMenuData gSlideMenuData) {
                        getPlaceData(map, gSlideMenuData);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getMusicRadioData(Map<String, String> map, final GSlideMenuData gSlideMenuData, final GAttractionListData gAttractionListData) {
        NetworkUtil.getInstance().getNetworkService()
                .getMusicRadioData("GetLargeAppMapTubeApp_v2.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GMusicRadio>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GMusicRadio gMusicRadio) {
                        ipMap.handleParseGsonData(gSlideMenuData, gAttractionListData, gMusicRadio);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void handleInsertMenuData(final List<AttractionModeEntity> attractionModeEntities,
                                     final List<AttractionStyleEntity> attractionStyleEntities,
                                     final List<AttractionClassEntity> attractionClassEntities,
                                     final List<AttractionTermEntity> attractionTermEntities,
                                     final List<AttractionItemEntity> attractionItemEntities,
                                     final List<MapPlaceBaseDataEntity> mapPlaceBaseDataEntities,
                                     final List<MapPlaceBaseSubDataEntity> subDataEntities,
                                     final GMusicRadio gMusicRadio,
                                     final GAttractionListData gAttractionListData) {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        AppDatabase.getInstance().attractionModeDao().cleanTable();
                        AppDatabase.getInstance().attractionModeDao().insertAllData(attractionModeEntities);
                        AppDatabase.getInstance().attractionStyleDao().cleanTable();
                        AppDatabase.getInstance().attractionStyleDao().insertAllData(attractionStyleEntities);
                        AppDatabase.getInstance().attractionClassDao().cleanTable();
                        AppDatabase.getInstance().attractionClassDao().insertAllData(attractionClassEntities);
                        AppDatabase.getInstance().attractionTermDao().cleanTable();
                        AppDatabase.getInstance().attractionTermDao().insertAllData(attractionTermEntities);
                        AppDatabase.getInstance().attractionItemDao().cleanTable();
                        AppDatabase.getInstance().attractionItemDao().insertAllData(attractionItemEntities);
                        AppDatabase.getInstance().mapPlaceBaseDataDao().cleanTable();
                        AppDatabase.getInstance().mapPlaceBaseDataDao().insertAllData(mapPlaceBaseDataEntities);
                        AppDatabase.getInstance().mapPlaceBaseSubDataDao().cleanTable();
                        AppDatabase.getInstance().mapPlaceBaseSubDataDao().insertAllData(subDataEntities);
                        emitter.onComplete();
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
                    public void onComplete() {
                        ipMap.handleFinishInsert(gMusicRadio, attractionModeEntities, attractionStyleEntities, gAttractionListData);
                    }
                });
    }

    public void saveCarbon(final List<CarbonEntity> list) {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        AppDatabase.getInstance().carbonDao().insert(list);
                        emitter.onComplete();
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
                    public void onComplete() { ipMap.finishSaveCarbon(); }
                });
    }

    public void saveEco(final List<EcoEntity> list) {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        AppDatabase.getInstance().ecoDao().insert(list);
                        emitter.onComplete();
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
                    public void onComplete() { ipMap.finishSaveEco(); }
                });
    }

    public void getCarbon(final boolean isShow) {
        AppDatabase
                .getInstance()
                .carbonDao()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<CarbonEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<CarbonEntity> carbonEntities) {
                        ipMap.handleCarbon(carbonEntities, isShow);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getEco(final boolean isShow) {
        AppDatabase
                .getInstance()
                .ecoDao()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<EcoEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<EcoEntity> ecoEntities) {
                        ipMap.handleEco(ecoEntities, isShow);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void handleGetPlaceId(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getPlaceDataWithRx("https://maps.googleapis.com/maps/api/geocode/json", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GPlaceIdData>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GPlaceIdData gPlaceIdData) {
                        ipMap.handleSavePlaceId(gPlaceIdData);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getAttractionInfo(List<String> spid, final List<AttractionStyleEntity> styleEntities) {
        AppDatabase.getInstance()
                .mapPlaceBaseDataDao()
                .getDataFromSpid(spid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<MapPlaceBaseDataEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<MapPlaceBaseDataEntity> placeBaseDataEntities) {
                        ipMap.handleAttractionData(placeBaseDataEntities, styleEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getAttractionDetail(Map<String, String> map, final String type, final LatLng latLng) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getSearchPlaceDetailDataWithRx("GetLargeAppPinDetail_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSearchAttractionDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSearchAttractionDetail gSearchAttractionDetail) {
                        ipMap.handleAttractionDetailData(gSearchAttractionDetail, type, latLng);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getSelectedStyleData(List<String> msidList, final GAttractionListData gAttractionListData) {
        AppDatabase.getInstance()
                .attractionStyleDao()
                .getSelectedStyle(msidList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<AttractionStyleEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<AttractionStyleEntity> attractionStyleEntities) {
                        ipMap.handleSelectedStyleData(attractionStyleEntities, gAttractionListData);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getStrokeList(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getStrokeList("GetLargeAppMapRoutePinList_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GStrokeList>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GStrokeList gStrokeList) {
                        getStyleData(gStrokeList);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendAddOrDelStroke(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendLove("SendMapRouteTitle_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipMap.handleFinishAddOrDel(gSendLove);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getSaveList(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .getSaveList("GetLargeAppMapRouteList_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSaveList>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSaveList gSaveList) {
                        ipMap.handleSaveList(gSaveList);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendAddToStroke(Map<String, String> map, final GSaveList gSaveList, final List<Boolean> isClickedList, final String attractionId) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendLove("SendMapRouteListPin_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipMap.handleFinishAddToStroke(gSaveList, isClickedList, attractionId);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void createNewStroke(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendLove("SendMapRouteTitle_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipMap.handleFinishCreateNewStroke(gSendLove);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendAddToCollect(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendLove("SendMapRouteCollect_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipMap.handleFinishAddToCollect(gSendLove);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendAddToMyStroke(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendLove("SendMapRouteToMe_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipMap.handleFinishAddToMyStroke(gSendLove);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getPushMusicData(Map<String, String> map) {
        NetworkUtil.getInstance().getNetworkService()
                .getPushMusicData("GetLargeAppMapTubeChannel_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GPushMusic>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GPushMusic gPushMusic) {
                        ipMap.handlePushMusicData(gPushMusic);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendLove(Map<String, String> map) {
        NetworkUtil.getInstance().getNetworkService()
                .sendLove("SendMapTubeCollect_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipMap.handleLoveData(gSendLove);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void insertData(final CashFlowEntity cashFlowEntity) {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        AppDatabase.getInstance().cashFlowDao().insertData(cashFlowEntity);
                        emitter.onComplete();
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
                    public void onComplete() { ipMap.finishCashFlowInsert(); }
                });
    }

    public void sendCashFlow(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendLove("SendCashFlow_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipMap.finishSendCashFlow(gSendLove);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    private void getStyleData(final GStrokeList gStrokeList) {
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
                        ipMap.handleStrokeList(gStrokeList, attractionStyleEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
