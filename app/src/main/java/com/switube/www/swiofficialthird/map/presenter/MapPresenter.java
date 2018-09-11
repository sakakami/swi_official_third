package com.switube.www.swiofficialthird.map.presenter;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.switube.www.swiofficialthird.database.entity.AttractionClassEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionItemEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionModeEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionTermEntity;
import com.switube.www.swiofficialthird.database.entity.MapPlaceBaseDataEntity;
import com.switube.www.swiofficialthird.map.gson.AttractionDataEntity;
import com.switube.www.swiofficialthird.map.gson.MenuDataEntity;
import com.switube.www.swiofficialthird.map.gson.PlaceBaseDataEntity;
import com.switube.www.swiofficialthird.map.gson.PlaceDataEntity;
import com.switube.www.swiofficialthird.map.model.MapModel;
import com.switube.www.swiofficialthird.map.view.IMapFragment;
import com.switube.www.swiofficialthird.map.view.MapFragment;
import com.switube.www.swiofficialthird.util.ClusterItemUtil;
import com.switube.www.swiofficialthird.util.NetworkUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MapPresenter implements IMapPresenter {
    private MapModel mMapModel;
    private IMapFragment mIMapFragment;
    public MapPresenter(IMapFragment iMapFragment) {
        mIMapFragment = iMapFragment;
        mMapModel = new MapModel(this);
    }

    /*public void getAttractionData(Context context) {
        mMapModel.getAttractionData(context, new HashMap<>(NetworkUtil.getInstance().getMap(context)));
    }*/

    public void getMenuData(Context context) {
        mMapModel.getMenuData(context, NetworkUtil.getInstance().getMap(context));
    }

    public List<ClusterItemUtil> getmAttraction() {
        return mAttraction;
    }

    public void getPlaceData(LatLng latLng) {
        String builder = String.valueOf(latLng.latitude) + "," + String.valueOf(latLng.longitude);
        mMapModel.handleGetPlaceId(builder, "zh-TW");
    }

    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    public void checkBluetoothState(Activity activity) {
        new RxPermissions(activity)
                .requestEach(Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN)
                .subscribe(new Observer<Permission>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Permission permission) {
                        if (permission.granted) {
                            if (mBluetoothAdapter.isEnabled()) {
                                if (MapFragment.isCent) {
                                    mIMapFragment.handleReturnBike();
                                } else {
                                    mIMapFragment.handleSelectBike();
                                }
                            } else {
                                mIMapFragment.handleOpenBluetooth();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    //private List<ClusterItemUtil> caClusterItems = new ArrayList<>();
    //private List<ClusterItemUtil> cbClusterItems = new ArrayList<>();
    public void handleMapPoint(List<AttractionStyleEntity> styleEntities, PlaceBaseDataEntity placeBaseDataEntity) {
        List<ClusterItemUtil> clusterItemUtils = new ArrayList<>();
        List<String> msidList = new ArrayList<>();
        int size = styleEntities.size();
        for (int i = 0; i < size; i++) {
            msidList.add(styleEntities.get(i).getMsid());
        }
        size = placeBaseDataEntity.getData().size();
        for (int i = 0; i < size; i++) {
            //int index;
            if (!placeBaseDataEntity.getData().get(i).getMsid().equals("cb")) {
                //index = msidList.indexOf("ca");
                int index = msidList.indexOf(placeBaseDataEntity.getData().get(i).getMsid());
                String name;
                String title;
                String address;
                //Log.e("en", placeBaseDataEntity.getData().get(i).getPlace_en());
                //Log.e("tw", placeBaseDataEntity.getData().get(i).getPlace_tw());
                //Log.e("ch", placeBaseDataEntity.getData().get(i).getPlace_ch());
                //Log.e("jp", placeBaseDataEntity.getData().get(i).getPalce_jp());
                switch (Locale.getDefault().getLanguage()) {
                    case "zh":
                        switch (Locale.getDefault().getCountry()) {
                            case "TW":
                                name = styleEntities.get(index).getMstitle_tw();
                                title = placeBaseDataEntity.getData().get(i).getPlace_tw();
                                address = placeBaseDataEntity.getData().get(i).getAddr_tw();
                                break;
                            case "CN":
                                name = styleEntities.get(index).getMstitle_ch();
                                title = placeBaseDataEntity.getData().get(i).getPlace_ch();
                                address = placeBaseDataEntity.getData().get(i).getAddr_ch();
                                break;
                            default:
                                name = styleEntities.get(index).getMstitle_en();
                                title = placeBaseDataEntity.getData().get(i).getPlace_en();
                                address = placeBaseDataEntity.getData().get(i).getAddr_en();
                                break;
                        }
                        break;
                    case "jp":
                        name = styleEntities.get(index).getMstitle_jp();
                        title = placeBaseDataEntity.getData().get(i).getPalce_jp();
                        address = placeBaseDataEntity.getData().get(i).getAddr_jp();
                        break;
                    default:
                        name = styleEntities.get(index).getMstitle_en();
                        title = placeBaseDataEntity.getData().get(i).getPlace_en();
                        address = placeBaseDataEntity.getData().get(i).getAddr_en();
                        break;
                }
                LatLng latLng = new LatLng(Double.parseDouble(placeBaseDataEntity.getData().get(i).getLat()),
                        Double.parseDouble(placeBaseDataEntity.getData().get(i).getLng()));
                int days = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                String time1;
                String time2;
                String time3;
                String time4;
                switch (days) {
                    case 1:
                        if (placeBaseDataEntity.getData().get(i).getSun() != null) {
                            time1 = placeBaseDataEntity.getData().get(i).getSun().get(0);
                            time2 = placeBaseDataEntity.getData().get(i).getSun().get(1);
                            time3 = placeBaseDataEntity.getData().get(i).getSun().get(2);
                            time4 = placeBaseDataEntity.getData().get(i).getSun().get(3);
                        } else {
                            time1 = "";
                            time2 = "";
                            time3 = "";
                            time4 = "";
                        }
                        break;
                    case 2:
                        if (placeBaseDataEntity.getData().get(i).getMon() != null) {
                            time1 = placeBaseDataEntity.getData().get(i).getMon().get(0);
                            time2 = placeBaseDataEntity.getData().get(i).getMon().get(1);
                            time3 = placeBaseDataEntity.getData().get(i).getMon().get(2);
                            time4 = placeBaseDataEntity.getData().get(i).getMon().get(3);
                        } else {
                            time1 = "";
                            time2 = "";
                            time3 = "";
                            time4 = "";
                        }
                        break;
                    case 3:
                        if (placeBaseDataEntity.getData().get(i).getTue() != null) {
                            time1 = placeBaseDataEntity.getData().get(i).getTue().get(0);
                            time2 = placeBaseDataEntity.getData().get(i).getTue().get(1);
                            time3 = placeBaseDataEntity.getData().get(i).getTue().get(2);
                            time4 = placeBaseDataEntity.getData().get(i).getSun().get(3);
                        } else {
                            time1 = "";
                            time2 = "";
                            time3 = "";
                            time4 = "";
                        }
                        break;
                    case 4:
                        if (placeBaseDataEntity.getData().get(i).getWed() != null) {
                            time1 = placeBaseDataEntity.getData().get(i).getWed().get(0);
                            time2 = placeBaseDataEntity.getData().get(i).getWed().get(1);
                            time3 = placeBaseDataEntity.getData().get(i).getWed().get(2);
                            time4 = placeBaseDataEntity.getData().get(i).getWed().get(3);
                        } else {
                            time1 = "";
                            time2 = "";
                            time3 = "";
                            time4 = "";
                        }
                        break;
                    case 5:
                        if (placeBaseDataEntity.getData().get(i).getThu() != null) {
                            time1 = placeBaseDataEntity.getData().get(i).getThu().get(0);
                            time2 = placeBaseDataEntity.getData().get(i).getThu().get(1);
                            time3 = placeBaseDataEntity.getData().get(i).getThu().get(2);
                            time4 = placeBaseDataEntity.getData().get(i).getThu().get(3);
                        } else {
                            time1 = "";
                            time2 = "";
                            time3 = "";
                            time4 = "";
                        }
                        break;
                    case 6:
                        if (placeBaseDataEntity.getData().get(i).getFri() != null) {
                            time1 = placeBaseDataEntity.getData().get(i).getFri().get(0);
                            time2 = placeBaseDataEntity.getData().get(i).getFri().get(1);
                            time3 = placeBaseDataEntity.getData().get(i).getFri().get(2);
                            time4 = placeBaseDataEntity.getData().get(i).getFri().get(3);
                        } else {
                            time1 = "";
                            time2 = "";
                            time3 = "";
                            time4 = "";
                        }
                        break;
                    case 7:
                        if (placeBaseDataEntity.getData().get(i).getSat() != null) {
                            time1 = placeBaseDataEntity.getData().get(i).getSat().get(0);
                            time2 = placeBaseDataEntity.getData().get(i).getSat().get(1);
                            time3 = placeBaseDataEntity.getData().get(i).getSat().get(2);
                            time4 = placeBaseDataEntity.getData().get(i).getSat().get(3);
                        } else {
                            time1 = "";
                            time2 = "";
                            time3 = "";
                            time4 = "";
                        }
                        break;
                    default:
                        time1 = "";
                        time2 = "";
                        time3 = "";
                        time4 = "";
                        break;
                }
                clusterItemUtils.add(new ClusterItemUtil(latLng, title, name, time1, time2, time3, time4,
                        placeBaseDataEntity.getData().get(i).getRating(), placeBaseDataEntity.getData().get(i).getMsid(), address));
            }
        }
        mIMapFragment.handleUpdateAttractionData(clusterItemUtils);
    }

    /*public void handleSwitchMapPoint(boolean isCent, List<ClusterItemUtil> clusterItemUtils) {
        if (isCent) {
            for (int i = 0; i < clusterItemUtils.size(); i++) {
                if (clusterItemUtils.get(i).getMsid().equals("ca")) {
                    clusterItemUtils.remove(i);
                    i--;
                }
            }
            int size = cbClusterItems.size();
            for (int i = 0; i < size; i++) {
                clusterItemUtils.add(cbClusterItems.get(i));
            }
        } else {
            for (int i = 0; i < clusterItemUtils.size(); i++) {
                if (clusterItemUtils.get(i).getMsid().equals("cb")) {
                    clusterItemUtils.remove(i);
                    i--;
                }
            }
            int size = caClusterItems.size();
            for (int i = 0; i < size; i++) {
                clusterItemUtils.add(caClusterItems.get(i));
            }
        }
        mIMapFragment.handleUpdateAttractionData(clusterItemUtils);
    }*/

    private List<ClusterItemUtil> mAttraction = new ArrayList<>();
    @Override
    public void handleParseAttractionGson(Context context, AttractionDataEntity attractionDataEntity) {
        /*int size = attractionDataEntity.getTData().size();
        List<AttractionEntity> attractionEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionEntityList.add(new AttractionEntity(
                    attractionDataEntity.getTData().get(i).getTrid(),
                    attractionDataEntity.getTData().get(i).getTrname(),
                    attractionDataEntity.getTData().get(i).getTraddr(),
                    attractionDataEntity.getTData().get(i).getTrgoid(),
                    attractionDataEntity.getTData().get(i).getTcid(),
                    attractionDataEntity.getTData().get(i).getLat(),
                    attractionDataEntity.getTData().get(i).getLongs(),
                    attractionDataEntity.getTData().get(i).getTrphone(),
                    attractionDataEntity.getTData().get(i).getTrurl(),
                    attractionDataEntity.getTData().get(i).getScore()
            ));
            LatLng latLng = new LatLng(Double.parseDouble(attractionDataEntity.getTData().get(i).getLat()), Double.parseDouble(attractionDataEntity.getTData().get(i).getLongs()));
            mAttraction.add(new ClusterItemUtil(latLng, attractionDataEntity.getTData().get(i).getTrid(), attractionDataEntity.getTData().get(i).getTrname()));
        }
        mIMapFragment.handleUpdateAttractionData(mAttraction);
        mMapModel.handleInsertAttractionData(context, attractionEntityList);*/
    }

    @Override
    public void handleSavePlaceId(PlaceDataEntity placeDataEntity) {
        mIMapFragment.savePlaceId(placeDataEntity.getResults().get(0).getPlace_id());
    }

    @Override
    public void handleParseMenuDataGson(Context context, MenuDataEntity menuDataEntity) {
       /*int size = menuDataEntity.getModes().size();
        List<AttractionModeEntity> attractionModeEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionModeEntityList.add(new AttractionModeEntity(
                    menuDataEntity.getModes().get(i).getMmid(),
                    menuDataEntity.getModes().get(i).getMmtitle_en(),
                    menuDataEntity.getModes().get(i).getMmtitle_tw(),
                    menuDataEntity.getModes().get(i).getMmtitle_ch(),
                    menuDataEntity.getModes().get(i).getMmtitle_jp()));
        }
        size = menuDataEntity.getStyles().size();
        List<AttractionStyleEntity> attractionStyleEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionStyleEntityList.add(new AttractionStyleEntity(
                    menuDataEntity.getStyles().get(i).getMenuid(),
                    menuDataEntity.getStyles().get(i).getMmspid(),
                    menuDataEntity.getStyles().get(i).getMmid(),
                    menuDataEntity.getStyles().get(i).getMsid(),
                    menuDataEntity.getStyles().get(i).getMstitle_en(),
                    menuDataEntity.getStyles().get(i).getMstitle_tw(),
                    menuDataEntity.getStyles().get(i).getMstitle_ch(),
                    menuDataEntity.getStyles().get(i).getMstitle_jp()));
        }
        size = menuDataEntity.getClasses().size();
        List<AttractionClassEntity> attractionClassEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionClassEntityList.add(new AttractionClassEntity(
                    menuDataEntity.getClasses().get(i).getMscid(),
                    menuDataEntity.getClasses().get(i).getMsid(),
                    menuDataEntity.getClasses().get(i).getMcid(),
                    menuDataEntity.getClasses().get(i).getMctitle_en(),
                    menuDataEntity.getClasses().get(i).getMctitle_tw(),
                    menuDataEntity.getClasses().get(i).getMctitle_ch(),
                    menuDataEntity.getClasses().get(i).getMctitle_jp()));
        }
        size = menuDataEntity.getTerms().size();
        List<AttractionTermEntity> attractionTermEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionTermEntityList.add(new AttractionTermEntity(
                    menuDataEntity.getTerms().get(i).getMsid(),
                    menuDataEntity.getTerms().get(i).getMtid(),
                    menuDataEntity.getTerms().get(i).getMttile_en(),
                    menuDataEntity.getTerms().get(i).getMttile_tw(),
                    menuDataEntity.getTerms().get(i).getMttile_ch(),
                    menuDataEntity.getTerms().get(i).getMttile_jp()));
        }
        size = menuDataEntity.getItems().size();
        List<AttractionItemEntity> attractionItemEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionItemEntityList.add(new AttractionItemEntity(
                    menuDataEntity.getItems().get(i).getMstid(),
                    menuDataEntity.getItems().get(i).getMtid(),
                    menuDataEntity.getItems().get(i).getMiid(),
                    menuDataEntity.getItems().get(i).getMititle_en(),
                    menuDataEntity.getItems().get(i).getMititle_tw(),
                    menuDataEntity.getItems().get(i).getMititle_ch(),
                    menuDataEntity.getItems().get(i).getMititle_jp()));
        }
        List<String> keys = new ArrayList<>(menuDataEntity.getKey());
        mIMapFragment.saveAPIKeys(keys);
        mIMapFragment.initDrawer(attractionModeEntityList, attractionStyleEntityList);
        mMapModel.handleInsertMenuData(context, attractionModeEntityList, attractionStyleEntityList,
                attractionClassEntityList, attractionTermEntityList, attractionItemEntityList);*/
    }

    @Override
    public void handleParseGsonData(Context context, MenuDataEntity menuDataEntity, PlaceBaseDataEntity placeBaseDataEntity) {
        int size = menuDataEntity.getModes().size();
        List<AttractionModeEntity> attractionModeEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionModeEntityList.add(new AttractionModeEntity(
                    menuDataEntity.getModes().get(i).getMmid(),
                    menuDataEntity.getModes().get(i).getMmtitle_en(),
                    menuDataEntity.getModes().get(i).getMmtitle_tw(),
                    menuDataEntity.getModes().get(i).getMmtitle_ch(),
                    menuDataEntity.getModes().get(i).getMmtitle_jp()));
        }
        size = menuDataEntity.getStyles().size();
        List<AttractionStyleEntity> attractionStyleEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionStyleEntityList.add(new AttractionStyleEntity(
                    menuDataEntity.getStyles().get(i).getMenuid(),
                    menuDataEntity.getStyles().get(i).getMmspid(),
                    menuDataEntity.getStyles().get(i).getMmid(),
                    menuDataEntity.getStyles().get(i).getMsid(),
                    menuDataEntity.getStyles().get(i).getMstitle_en(),
                    menuDataEntity.getStyles().get(i).getMstitle_tw(),
                    menuDataEntity.getStyles().get(i).getMstitle_ch(),
                    menuDataEntity.getStyles().get(i).getMstitle_jp()));
        }
        size = menuDataEntity.getClasses().size();
        List<AttractionClassEntity> attractionClassEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionClassEntityList.add(new AttractionClassEntity(
                    menuDataEntity.getClasses().get(i).getMscid(),
                    menuDataEntity.getClasses().get(i).getMsid(),
                    menuDataEntity.getClasses().get(i).getMcid(),
                    menuDataEntity.getClasses().get(i).getMctitle_en(),
                    menuDataEntity.getClasses().get(i).getMctitle_tw(),
                    menuDataEntity.getClasses().get(i).getMctitle_ch(),
                    menuDataEntity.getClasses().get(i).getMctitle_jp()));
        }
        size = menuDataEntity.getTerms().size();
        List<AttractionTermEntity> attractionTermEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionTermEntityList.add(new AttractionTermEntity(
                    menuDataEntity.getTerms().get(i).getMsid(),
                    menuDataEntity.getTerms().get(i).getMtid(),
                    menuDataEntity.getTerms().get(i).getMttitle_en(),
                    menuDataEntity.getTerms().get(i).getMttitle_tw(),
                    menuDataEntity.getTerms().get(i).getMttitle_ch(),
                    menuDataEntity.getTerms().get(i).getMttitle_jp()));
        }
        size = menuDataEntity.getItems().size();
        List<AttractionItemEntity> attractionItemEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionItemEntityList.add(new AttractionItemEntity(
                    menuDataEntity.getItems().get(i).getMstid(),
                    menuDataEntity.getItems().get(i).getMtid(),
                    menuDataEntity.getItems().get(i).getMiid(),
                    menuDataEntity.getItems().get(i).getMititle_en(),
                    menuDataEntity.getItems().get(i).getMititle_tw(),
                    menuDataEntity.getItems().get(i).getMititle_ch(),
                    menuDataEntity.getItems().get(i).getMititle_jp()));
        }
        List<String> keys = new ArrayList<>(menuDataEntity.getKey());
        size = placeBaseDataEntity.getData().size();
        List<MapPlaceBaseDataEntity> mapPlaceBaseDataEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            MapPlaceBaseDataEntity mapPlaceBaseDataEntity = new MapPlaceBaseDataEntity();
            mapPlaceBaseDataEntity.setSpid(placeBaseDataEntity.getData().get(i).getSpid());
            mapPlaceBaseDataEntity.setMsid(placeBaseDataEntity.getData().get(i).getMsid());
            mapPlaceBaseDataEntity.setLat(placeBaseDataEntity.getData().get(i).getLat());
            mapPlaceBaseDataEntity.setLng(placeBaseDataEntity.getData().get(i).getLng());
            mapPlaceBaseDataEntity.setCountryid(placeBaseDataEntity.getData().get(i).getCountryid());
            mapPlaceBaseDataEntity.setCityid(placeBaseDataEntity.getData().get(i).getCityid());
            mapPlaceBaseDataEntity.setPlace_en(placeBaseDataEntity.getData().get(i).getPlace_en());
            mapPlaceBaseDataEntity.setPlace_tw(placeBaseDataEntity.getData().get(i).getPlace_tw());
            mapPlaceBaseDataEntity.setPlace_ch(placeBaseDataEntity.getData().get(i).getPlace_ch());
            mapPlaceBaseDataEntity.setPlace_jp(placeBaseDataEntity.getData().get(i).getPalce_jp());
            mapPlaceBaseDataEntity.setRating(placeBaseDataEntity.getData().get(i).getRating());
            if (placeBaseDataEntity.getData().get(i).getMon() != null) {
                Log.e("notnull", String.valueOf(i));
                mapPlaceBaseDataEntity.setMon_1(placeBaseDataEntity.getData().get(i).getMon().get(0));
                mapPlaceBaseDataEntity.setMon_2(placeBaseDataEntity.getData().get(i).getMon().get(1));
                mapPlaceBaseDataEntity.setMon_3(placeBaseDataEntity.getData().get(i).getMon().get(2));
                mapPlaceBaseDataEntity.setMon_4(placeBaseDataEntity.getData().get(i).getMon().get(3));
                mapPlaceBaseDataEntity.setTue_1(placeBaseDataEntity.getData().get(i).getTue().get(0));
                mapPlaceBaseDataEntity.setTue_2(placeBaseDataEntity.getData().get(i).getTue().get(1));
                mapPlaceBaseDataEntity.setTue_3(placeBaseDataEntity.getData().get(i).getTue().get(2));
                mapPlaceBaseDataEntity.setTue_4(placeBaseDataEntity.getData().get(i).getTue().get(3));
                mapPlaceBaseDataEntity.setWed_1(placeBaseDataEntity.getData().get(i).getWed().get(0));
                mapPlaceBaseDataEntity.setWed_2(placeBaseDataEntity.getData().get(i).getWed().get(1));
                mapPlaceBaseDataEntity.setWed_3(placeBaseDataEntity.getData().get(i).getWed().get(2));
                mapPlaceBaseDataEntity.setWed_4(placeBaseDataEntity.getData().get(i).getWed().get(3));
                mapPlaceBaseDataEntity.setThu_1(placeBaseDataEntity.getData().get(i).getThu().get(0));
                mapPlaceBaseDataEntity.setThu_2(placeBaseDataEntity.getData().get(i).getThu().get(1));
                mapPlaceBaseDataEntity.setThu_3(placeBaseDataEntity.getData().get(i).getThu().get(2));
                mapPlaceBaseDataEntity.setThu_4(placeBaseDataEntity.getData().get(i).getThu().get(3));
                mapPlaceBaseDataEntity.setFri_1(placeBaseDataEntity.getData().get(i).getFri().get(0));
                mapPlaceBaseDataEntity.setFri_2(placeBaseDataEntity.getData().get(i).getFri().get(1));
                mapPlaceBaseDataEntity.setFri_3(placeBaseDataEntity.getData().get(i).getFri().get(2));
                mapPlaceBaseDataEntity.setFri_4(placeBaseDataEntity.getData().get(i).getFri().get(3));
                mapPlaceBaseDataEntity.setSat_1(placeBaseDataEntity.getData().get(i).getSat().get(0));
                mapPlaceBaseDataEntity.setSat_2(placeBaseDataEntity.getData().get(i).getSat().get(1));
                mapPlaceBaseDataEntity.setSat_3(placeBaseDataEntity.getData().get(i).getSat().get(2));
                mapPlaceBaseDataEntity.setSat_4(placeBaseDataEntity.getData().get(i).getSat().get(3));
                mapPlaceBaseDataEntity.setSun_1(placeBaseDataEntity.getData().get(i).getSun().get(0));
                mapPlaceBaseDataEntity.setSun_2(placeBaseDataEntity.getData().get(i).getSun().get(1));
                mapPlaceBaseDataEntity.setSun_3(placeBaseDataEntity.getData().get(i).getSun().get(2));
                mapPlaceBaseDataEntity.setSun_4(placeBaseDataEntity.getData().get(i).getSun().get(3));
                mapPlaceBaseDataEntityList.add(mapPlaceBaseDataEntity);
            } else {
                Log.e("null", String.valueOf(i));
                mapPlaceBaseDataEntity.setMon_1("");
                mapPlaceBaseDataEntity.setMon_2("");
                mapPlaceBaseDataEntity.setMon_3("");
                mapPlaceBaseDataEntity.setMon_4("");
                mapPlaceBaseDataEntity.setTue_1("");
                mapPlaceBaseDataEntity.setTue_2("");
                mapPlaceBaseDataEntity.setTue_3("");
                mapPlaceBaseDataEntity.setTue_4("");
                mapPlaceBaseDataEntity.setWed_1("");
                mapPlaceBaseDataEntity.setWed_2("");
                mapPlaceBaseDataEntity.setWed_3("");
                mapPlaceBaseDataEntity.setWed_4("");
                mapPlaceBaseDataEntity.setThu_1("");
                mapPlaceBaseDataEntity.setThu_2("");
                mapPlaceBaseDataEntity.setThu_3("");
                mapPlaceBaseDataEntity.setThu_4("");
                mapPlaceBaseDataEntity.setFri_1("");
                mapPlaceBaseDataEntity.setFri_2("");
                mapPlaceBaseDataEntity.setFri_3("");
                mapPlaceBaseDataEntity.setFri_4("");
                mapPlaceBaseDataEntity.setSat_1("");
                mapPlaceBaseDataEntity.setSat_2("");
                mapPlaceBaseDataEntity.setSat_3("");
                mapPlaceBaseDataEntity.setSat_4("");
                mapPlaceBaseDataEntity.setSun_1("");
                mapPlaceBaseDataEntity.setSun_2("");
                mapPlaceBaseDataEntity.setSun_3("");
                mapPlaceBaseDataEntity.setSun_4("");
                mapPlaceBaseDataEntityList.add(mapPlaceBaseDataEntity);
            }
        }
        mIMapFragment.saveAPIKeys(keys);
        mMapModel.handleInsertMenuData(context, attractionModeEntityList, attractionStyleEntityList,
                attractionClassEntityList, attractionTermEntityList, attractionItemEntityList,
                mapPlaceBaseDataEntityList);
        mIMapFragment.initDrawer(attractionModeEntityList, attractionStyleEntityList, placeBaseDataEntity);
    }
}
