package com.switube.www.landmark2018test.presenter;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionModeEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;
import com.switube.www.landmark2018test.database.entity.CarbonEntity;
import com.switube.www.landmark2018test.database.entity.EcoEntity;
import com.switube.www.landmark2018test.database.entity.MapPlaceBaseDataEntity;
import com.switube.www.landmark2018test.database.entity.MapPlaceBaseSubDataEntity;
import com.switube.www.landmark2018test.entity.ECarBonList;
import com.switube.www.landmark2018test.entity.ECarbon;
import com.switube.www.landmark2018test.entity.EClusterItem;
import com.switube.www.landmark2018test.entity.EEco;
import com.switube.www.landmark2018test.entity.EEcoList;
import com.switube.www.landmark2018test.entity.EMobileMusic;
import com.switube.www.landmark2018test.gson.GAttractionListData;
import com.switube.www.landmark2018test.gson.GCashFlow;
import com.switube.www.landmark2018test.gson.GMusicRadio;
import com.switube.www.landmark2018test.gson.GPushMusic;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.gson.GSlideMenuData;
import com.switube.www.landmark2018test.gson.GPlaceIdData;
import com.switube.www.landmark2018test.gson.GSearchAttractionDetail;
import com.switube.www.landmark2018test.gson.GStrokeList;
import com.switube.www.landmark2018test.model.MMap;
import com.switube.www.landmark2018test.presenter.callback.IPMap;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.VMap;
import com.switube.www.landmark2018test.view.callback.IVMap;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import au.com.bytecode.opencsv.CSVWriter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PMap implements IPMap {
    private MMap mMap;
    private IVMap ivMap;
    private List<String> apiKeys = new ArrayList<>();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd", Locale.TAIWAN);
    private String[] baseCodeB = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private String[] baseCodeC = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public PMap(IVMap ivMap) {
        this.ivMap = ivMap;
        mMap = new MMap(this);
        String key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[2] + baseCodeB[5] + "6" + baseCodeB[6] + baseCodeC[15] + baseCodeC[15]
                + baseCodeC[9] + baseCodeC[5] + baseCodeC[25] + baseCodeB[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[17] + baseCodeB[17] + baseCodeC[15] + baseCodeB[14] + "5" + baseCodeB[9]
                + baseCodeC[20] + baseCodeC[1] + baseCodeB[15] + baseCodeC[18] + baseCodeC[23] + baseCodeB[14]
                + baseCodeB[10] + baseCodeC[19] + baseCodeC[1] + baseCodeB[20] + "2" + baseCodeC[16]
                + baseCodeB[10] + baseCodeB[7] + baseCodeB[4];
        apiKeys.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[2] + "-7" + baseCodeB[8] + baseCodeC[25] + baseCodeC[19]
                + "0" + baseCodeC[20] + baseCodeC[16] + baseCodeB[24] + baseCodeC[24] + baseCodeB[19]
                + baseCodeB[21] + baseCodeC[25] + baseCodeB[14] + baseCodeB[8] + baseCodeC[18] + baseCodeB[19]
                + baseCodeB[3] + baseCodeB[21] + baseCodeB[5] + baseCodeC[20] + "3" + baseCodeC[1]
                + baseCodeB[18] + baseCodeB[9] + "87" + baseCodeC[18] + baseCodeB[20]
                + baseCodeC[11] + baseCodeB[7] + baseCodeB[12];
        apiKeys.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[3] + baseCodeC[19] + baseCodeB[4] + baseCodeB[19] + baseCodeC[24] + "9"
                + baseCodeC[7] + baseCodeC[11] + baseCodeC[0] + baseCodeB[14] + baseCodeB[5] + baseCodeB[5]
                + baseCodeC[11] + "0" + baseCodeC[23] + baseCodeB[23] + "_" + baseCodeB[5]
                + "_" + baseCodeC[12] + baseCodeC[10] + baseCodeB[21] + baseCodeB[20] + baseCodeB[17]
                + baseCodeB[3] + baseCodeC[6] + "05" + baseCodeC[10] + baseCodeC[22]
                + baseCodeC[2] + baseCodeB[8] + baseCodeB[0];
        apiKeys.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[1] + baseCodeC[20] + baseCodeC[4] + "9" + baseCodeB[22] + baseCodeC[21]
                + baseCodeC[6] + baseCodeC[18] + baseCodeC[7] + baseCodeB[25] + baseCodeB[14] + baseCodeB[24]
                + baseCodeB[7] + baseCodeC[11] + baseCodeB[22] + "5-5"
                + baseCodeB[21] + baseCodeC[13] + baseCodeC[3] + baseCodeC[5] + "8" + baseCodeB[12]
                + "27" + baseCodeB[13] + baseCodeC[9] + baseCodeB[3] + baseCodeB[15]
                + baseCodeB[7] + "3" + baseCodeC[10];
        apiKeys.add(key);
    }

    public void getMenuData() {
        mMap.getMenuData(NetworkUtil.getInstance().getMap());
    }

    public List<EClusterItem> getmAttraction() {
        return mAttraction;
    }

    public void getPlaceData(LatLng latLng) {
        int index = new Random().nextInt(apiKeys.size());
        String builder = "";
        if (latLng != null) {
            builder = latLng.latitude + "," + latLng.longitude;
        }
        Map<String, String> map = new HashMap<>();
        map.put("latlng", builder);
        map.put("language", "en");
        map.put("key", apiKeys.get(index));
        mMap.handleGetPlaceId(map);
    }

    public void getPlaceData(List<String> spid, List<AttractionStyleEntity> styleEntities) {
        mMap.getAttractionInfo(spid, styleEntities);
    }

    public void getPlaceDetail(String spid, String type, LatLng latLng) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("spid", spid);
        mMap.getAttractionDetail(map, type, latLng);
    }

    public void getPlaceData(String keyword, List<EClusterItem> list) {
        int size = list.size();
        List<String> name = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            name.add(list.get(i).getTitle());
        }
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        StringBuilder stringBuilder = new StringBuilder(keyword);
        stringBuilder.delete(1, stringBuilder.length());
        Matcher matcher = pattern.matcher(stringBuilder.toString());
        if (matcher.matches()) {
            if (keyword.length() >= 2) {
                size = name.size();
                for (int i = 0; i < size; i++) {
                    if (name.get(i).contains(keyword)) {
                        ivMap.handSearchData(i);
                        return;
                    }
                    if (i == size - 1) {
                        ivMap.showSearchCaution(true);
                    }
                }
            } else {
                ivMap.showSearchCaution(false);
            }
        } else {
            if (keyword.length() >= 4) {
                size = name.size();
                for (int i = 0; i < size; i++) {
                    if (name.get(i).toLowerCase().contains(keyword.toLowerCase())) {
                        ivMap.handSearchData(i);
                        return;
                    }
                    if (i == size - 1) {
                        ivMap.showSearchCaution(true);
                    }
                }
            } else {
                ivMap.showSearchCaution(false);
            }
        }
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
                                if (VMap.isCent) {
                                    ivMap.handleReturnBike();
                                } else {
                                    ivMap.handleSelectBike();
                                }
                            } else {
                                ivMap.handleOpenBluetooth();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    private void handleMapPoint(List<AttractionStyleEntity> styleEntities, GAttractionListData gAttractionListData) {
        List<EClusterItem> eClusterItems = new ArrayList<>();
        List<String> msidList = new ArrayList<>();
        int size = styleEntities.size();
        for (int i = 0; i < size; i++) {
            msidList.add(styleEntities.get(i).getMsid());
        }
        size = gAttractionListData.getData().size();
        for (int i = 0; i < size; i++) {
            int index = msidList.indexOf(gAttractionListData.getData().get(i).getMsid());
            if (index >= 0) {
                String name;
                switch (MyApplication.getLanguageIndex()) {
                    case 1:
                        name = styleEntities.get(index).getMstitle_tw();
                        break;
                    case 2:
                        name = styleEntities.get(index).getMstitle_ch();
                        break;
                    case 3:
                        name = styleEntities.get(index).getMstitle_jp();
                        break;
                    default:
                        name = styleEntities.get(index).getMstitle_en();
                        break;
                }
                LatLng latLng;
                if (gAttractionListData.getData().get(i).getLat() != null) {
                    latLng = new LatLng(Double.parseDouble(gAttractionListData.getData().get(i).getLat()),
                            Double.parseDouble(gAttractionListData.getData().get(i).getLng()));
                } else {
                    latLng = new LatLng(0,0);
                }
                eClusterItems.add(new EClusterItem(latLng, name,
                        gAttractionListData.getData().get(i).getMsid(), gAttractionListData.getData().get(i).getSpid(),
                        gAttractionListData.getData().get(i).getPlace()));
            }
        }
        MyApplication.getAppData().setGlobalClusterItemList(eClusterItems);
        ivMap.handleUpdateAttractionData(eClusterItems);
    }

    public void getSelectedAttractionDataByStyle(List<String> msidList, GAttractionListData gAttractionListData) {
        mMap.getSelectedStyleData(msidList, gAttractionListData);
    }

    public void sendAddOrDel(String type) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("type", type);
        map.put("raid", MyApplication.getAppData().getRaid());
        map.put("urid", MyApplication.getAppData().getUrid());
        map.put("title", "");
        mMap.sendAddOrDelStroke(map);
    }

    public void getSaveList() {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("raid", MyApplication.getAppData().getRaid());
        mMap.getSaveList(map);
    }

    public void sendSaveData(GSaveList gSaveList, List<Boolean> isClickedList, String attractionId) {
        int index = isClickedList.indexOf(true);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("type", "add");
        map.put("raid", MyApplication.getAppData().getRaid());
        map.put("urid", gSaveList.getData().get(index).getUrid());
        map.put("urspid", "");
        map.put("spid", attractionId);
        map.put("sort", "");
        isClickedList.set(index, false);
        mMap.sendAddToStroke(map, gSaveList, isClickedList, attractionId);
    }

    public void handleNewStroke(String title) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("title", title);
        map.put("raid", MyApplication.getAppData().getRaid());
        mMap.createNewStroke(map);
    }

    public void sendAddToCollect(String spid, boolean isAdd) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        if (isAdd) {
            map.put("type", "add");
        } else {
            map.put("type", "del");
        }
        map.put("spid", spid);
        if (MyApplication.getAppData().getRaid().length() > 0) {
            map.put("raid", MyApplication.getAppData().getRaid());
        } else {
            map.put("raid", "WebSite");
        }
        mMap.sendAddToCollect(map);
    }

    public void sendAddToMyStroke() {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("urid", MyApplication.getAppData().getUrid());
        mMap.sendAddToMyStroke(map);
    }

    public void getStrokeList() {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("urid", MyApplication.getAppData().getUrid());
        mMap.getStrokeList(map);
    }

    public void handleFivePoints(List<EMobileMusic> mobileMusicList, LatLng nowLatLng, float zoomLevel) {
        int size = mobileMusicList.size();
        for (int i = 0; i < size; i++) {
            mobileMusicList.get(i).setSelected(false);
        }
        Random random = new Random();
        int history = -1;
        int times = 0;
        do {
            int index = random.nextInt(size - 1);
            if (history != index) {
                history = index;
                times++;
                mobileMusicList.get(index).setSelected(true);
                int angle = (int)(Math.random() * 360 + 1);
                int distance = 0;
                if (zoomLevel < 13) {
                    distance = (int)(Math.random() * 10000 + 5000);
                } else {
                    distance = (int)(Math.random() * 700 + 300);
                }
                mobileMusicList.get(index).setLatLng(calculateLatLng(nowLatLng, angle, distance));
            }
        } while (times < 5);
        ivMap.showFivePoint(mobileMusicList);
    }

    public void getPushMusicData() {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("type", "channel");
        map.put("value", MyApplication.getAppData().getMenuId());
        map.put("taid", MyApplication.getAppData().getTaid());
        switch (MyApplication.getLanguageIndex()) {
            case 1:
                map.put("lang", "TW");
                break;
            case 2:
                map.put("lang", "CH");
                break;
            case 3:
                map.put("Lang", "JP");
                break;
            default:
                map.put("lang", "EN");
                break;
        }
        mMap.getPushMusicData(map);
    }

    public void sendLove(String type, String taid, String webid, String lat, String lng) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("type", type);
        map.put("taid", taid);
        map.put("webid", webid);
        map.put("lat", lat);
        map.put("lng", lng);
        mMap.sendLove(map);
    }

    public void handleCentBike(int carCash) {
        //String date = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault()).format(new Date());
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("mode", "0");
        map.put("edit", "");
        ArrayList<GCashFlow.Data> cashFlows = new ArrayList<>();
        GCashFlow.Data data = new GCashFlow.Data();
        data.setTarget("E-bike 美和街站點");
        data.setType("租車");
        data.setCheck("0");
        data.setMaid(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"));
        data.setCash(String.valueOf((int)(carCash * 0.8)));
        cashFlows.add(data);
        Gson gson = new Gson();
        String stringData = gson.toJson(cashFlows);
        map.put("data", stringData);
        mMap.sendCashFlow(map);
        //String name = "E-bike 美和街站點";
        //String type = "租車";
        //String maid = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null");
        //String cash = String.valueOf((int)(carCash * 0.8));
        //CashFlowEntity cashFlowEntity = new CashFlowEntity(maid, name, type, cash, date);
        //mMap.insertData(cashFlowEntity);
    }

    public void handleSaveCarbon() {
        if (MyApplication.getAppData().getCarbons().isEmpty()) {
            ivMap.showToast("尚無任何旅程資訊");
        } else {
            mMap.getCarbon(false);
        }
    }

    public void handleSaveEco() {
        if (MyApplication.getAppData().geteEcos().isEmpty()) {
            ivMap.showToast("尚無任何節能資料");
        } else {
            mMap.getEco(false);
        }
    }

    public void handleGetCarbon(boolean isShow) { mMap.getCarbon(isShow); }

    public void handleGetEco(boolean isShow) { mMap.getEco(isShow); }

    public void handleToFile() {
        ExportDatabaseCSVTask task = new ExportDatabaseCSVTask();
        task.execute();
    }

    private List<EClusterItem> mAttraction = new ArrayList<>();
    @Override
    public void handleSavePlaceId(GPlaceIdData gPlaceIdData) {
        MyApplication.getAppData().setgPlaceIdData(gPlaceIdData);
        ivMap.savePlaceId(gPlaceIdData.getResults().get(0).getPlace_id());
    }

    @Override
    public void handleMusicMenu(GSlideMenuData gSlideMenuData, GAttractionListData gAttractionListData) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("taid", "");
        mMap.getMusicRadioData(map, gSlideMenuData, gAttractionListData);
    }

    @Override
    public void handleParseGsonData(GSlideMenuData gSlideMenuData, GAttractionListData gAttractionListData, GMusicRadio gMusicRadio) {
        int size = gSlideMenuData.getModes().size();
        List<AttractionModeEntity> attractionModeEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionModeEntityList.add(new AttractionModeEntity(
                    gSlideMenuData.getModes().get(i).getMmid(),
                    gSlideMenuData.getModes().get(i).getMmtitle_en(),
                    gSlideMenuData.getModes().get(i).getMmtitle_tw(),
                    gSlideMenuData.getModes().get(i).getMmtitle_ch(),
                    gSlideMenuData.getModes().get(i).getMmtitle_jp()));
        }
        size = gSlideMenuData.getStyles().size();
        List<AttractionStyleEntity> attractionStyleEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionStyleEntityList.add(new AttractionStyleEntity(
                    gSlideMenuData.getStyles().get(i).getMenuid(),
                    gSlideMenuData.getStyles().get(i).getMmspid(),
                    gSlideMenuData.getStyles().get(i).getMmid(),
                    gSlideMenuData.getStyles().get(i).getMsid(),
                    gSlideMenuData.getStyles().get(i).getMstitle_en(),
                    gSlideMenuData.getStyles().get(i).getMstitle_tw(),
                    gSlideMenuData.getStyles().get(i).getMstitle_ch(),
                    gSlideMenuData.getStyles().get(i).getMstitle_jp()));
        }
        size = gSlideMenuData.getClasses().size();
        List<AttractionClassEntity> attractionClassEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionClassEntityList.add(new AttractionClassEntity(
                    gSlideMenuData.getClasses().get(i).getMscid(),
                    gSlideMenuData.getClasses().get(i).getMsid(),
                    gSlideMenuData.getClasses().get(i).getMcid(),
                    gSlideMenuData.getClasses().get(i).getMctitle_en(),
                    gSlideMenuData.getClasses().get(i).getMctitle_tw(),
                    gSlideMenuData.getClasses().get(i).getMctitle_ch(),
                    gSlideMenuData.getClasses().get(i).getMctitle_jp()));
        }
        size = gSlideMenuData.getTerms().size();
        List<AttractionTermEntity> attractionTermEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionTermEntityList.add(new AttractionTermEntity(
                    gSlideMenuData.getTerms().get(i).getMsid(),
                    gSlideMenuData.getTerms().get(i).getMtid(),
                    gSlideMenuData.getTerms().get(i).getMttitle_en(),
                    gSlideMenuData.getTerms().get(i).getMttitle_tw(),
                    gSlideMenuData.getTerms().get(i).getMttitle_ch(),
                    gSlideMenuData.getTerms().get(i).getMttitle_jp()));
        }
        size = gSlideMenuData.getItems().size();
        List<AttractionItemEntity> attractionItemEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionItemEntityList.add(new AttractionItemEntity(
                    gSlideMenuData.getItems().get(i).getMstid(),
                    gSlideMenuData.getItems().get(i).getMtid(),
                    gSlideMenuData.getItems().get(i).getMiid(),
                    gSlideMenuData.getItems().get(i).getMititle_en(),
                    gSlideMenuData.getItems().get(i).getMititle_tw(),
                    gSlideMenuData.getItems().get(i).getMititle_ch(),
                    gSlideMenuData.getItems().get(i).getMititle_jp()));
        }
        size = gAttractionListData.getData().size();
        List<MapPlaceBaseDataEntity> mapPlaceBaseDataEntityList = new ArrayList<>();
        List<MapPlaceBaseSubDataEntity> subDataEntities = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            MapPlaceBaseDataEntity mapPlaceBaseDataEntity = new MapPlaceBaseDataEntity();
            mapPlaceBaseDataEntity.setSpid(gAttractionListData.getData().get(i).getSpid());
            mapPlaceBaseDataEntity.setMsid(gAttractionListData.getData().get(i).getMsid());
            mapPlaceBaseDataEntity.setLat(gAttractionListData.getData().get(i).getLat());
            mapPlaceBaseDataEntity.setLng(gAttractionListData.getData().get(i).getLng());
            mapPlaceBaseDataEntityList.add(mapPlaceBaseDataEntity);

        }
        mMap.handleInsertMenuData(attractionModeEntityList, attractionStyleEntityList,
                attractionClassEntityList, attractionTermEntityList, attractionItemEntityList,
                mapPlaceBaseDataEntityList, subDataEntities, gMusicRadio, gAttractionListData);

    }

    @Override
    public void handleFinishInsert(GMusicRadio gMusicRadio, List<AttractionModeEntity> attractionModeEntityList, List<AttractionStyleEntity> attractionStyleEntityList, GAttractionListData gAttractionListData) {
        ivMap.initDrawer(attractionModeEntityList, attractionStyleEntityList, gAttractionListData);
        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("musicTaid", "null").equals("null")) {
            int size = gMusicRadio.getTdData().size();
            List<String> musicTaid = new ArrayList<>();
            List<String> menuid = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (gMusicRadio.getTdData().get(i).getIs_push().equals("1")) {
                    musicTaid.add(gMusicRadio.getTdData().get(i).getTaid());
                    menuid.add(gMusicRadio.getTdData().get(i).getMenuid());
                }
            }
            size = musicTaid.size();
            Random random = new Random();
            int index = random.nextInt(size - 1);
            MyApplication.getAppData().setTaid(musicTaid.get(index));
            MyApplication.getAppData().setMenuId(menuid.get(index));
            SharePreferencesUtil.getInstance().getEditor().putString("musicTaid", musicTaid.get(index)).apply();
            SharePreferencesUtil.getInstance().getEditor().putString("musicValue", menuid.get(index)).apply();
            SharePreferencesUtil.getInstance().getEditor().putString("musicDate", simpleDateFormat.format(new Date())).apply();
        } else {
            MyApplication.getAppData().setTaid(SharePreferencesUtil.getInstance().getSharedPreferences().getString("musicTaid", "null"));
            MyApplication.getAppData().setMenuId(SharePreferencesUtil.getInstance().getSharedPreferences().getString("musicValue", "null"));
        }

        /*String taid;
        int size;
        if (MyApplication.getAppData().getHotKeyMode() == 2) {
            taid = MyApplication.getAppData().getRaid();
            size = gMusicRadio.getRaData().size();
        } else {
            if (MyApplication.getAppData().getTaid().equals("")) {
                taid = gMusicRadio.getTdData().get(0).getTaid();
            } else {
                taid = MyApplication.getAppData().getTaid();
            }
            size = gMusicRadio.getTaData().size();
        }
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (MyApplication.getAppData().getHotKeyMode() == 2) {
                if (gMusicRadio.getRaData().get(i).getRaid().equals(taid)) {
                    index = i;
                }
            } else {
                if (gMusicRadio.getTaData().get(i).getTaid().equals(taid)) {
                    index = i;
                }
            }
        }
        if (MyApplication.getAppData().getHotKeyMode() == 2) {
            MyApplication.getAppData().setMoreTitle(MyApplication.getAppData().getStrokeTitle());
        } else {
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    MyApplication.getAppData().setMoreTitle(gMusicRadio.getTaData().get(index).getTitle_tw());
                    break;
                case 2:
                    MyApplication.getAppData().setMoreTitle(gMusicRadio.getTaData().get(index).getTitle_ch());
                    break;
                case 3:
                    MyApplication.getAppData().setMoreTitle(gMusicRadio.getTaData().get(index).getTitle_jp());
                    break;
                default:
                    MyApplication.getAppData().setMoreTitle(gMusicRadio.getTaData().get(index).getTitle_en());
                    break;
            }
        }*/
    }

    @Override
    public void handleAttractionData(List<MapPlaceBaseDataEntity> mapPlaceBaseDataEntities, List<AttractionStyleEntity> styleEntities) {
        List<EClusterItem> eClusterItems = new ArrayList<>();
        List<String> msidList = new ArrayList<>();
        int size = styleEntities.size();
        for (int i = 0; i < size; i++) {
            msidList.add(styleEntities.get(i).getMsid());
        }
        size = mapPlaceBaseDataEntities.size();
        String name;
        String title = "";
        for (int i = 0; i < size; i++) {
            int index = msidList.indexOf(mapPlaceBaseDataEntities.get(i).getMsid());
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    name = styleEntities.get(index).getMstitle_tw();
                    break;
                case 2:
                    name = styleEntities.get(index).getMstitle_ch();
                    break;
                case 3:
                    name = styleEntities.get(index).getMstitle_jp();
                    break;
                default:
                    name = styleEntities.get(index).getMstitle_en();
                    break;
            }
            LatLng latLng = new LatLng(Double.parseDouble(mapPlaceBaseDataEntities.get(i).getLat()),
                    Double.parseDouble(mapPlaceBaseDataEntities.get(i).getLng()));

            eClusterItems.add(new EClusterItem(latLng, name,
                    mapPlaceBaseDataEntities.get(i).getMsid(), mapPlaceBaseDataEntities.get(i).getSpid(),
                    title));
        }
        ivMap.handleUpdateAttractionData(eClusterItems);
    }

    @Override
    public void handleAttractionDetailData(GSearchAttractionDetail gSearchAttractionDetail, String type, LatLng latLng) {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String week;
        String isOpen;
        List<String> open;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm", Locale.getDefault());
        int index = 0;
        switch (day) {
            case 1:
                week = MyApplication.getInstance().getString(R.string.weekly_sun);
                open = gSearchAttractionDetail.getData().get(0).getSun();
                break;
            case 2:
                week = MyApplication.getInstance().getString(R.string.weekly_mon);
                open = gSearchAttractionDetail.getData().get(0).getMon();
                break;
            case 3:
                week = MyApplication.getInstance().getString(R.string.weekly_tue);
                open = gSearchAttractionDetail.getData().get(0).getTue();
                break;
            case 4:
                week = MyApplication.getInstance().getString(R.string.weekly_wed);
                open = gSearchAttractionDetail.getData().get(0).getWed();
                break;
            case 5:
                week = MyApplication.getInstance().getString(R.string.weekly_thu);
                open = gSearchAttractionDetail.getData().get(0).getThu();
                break;
            case 6:
                week = MyApplication.getInstance().getString(R.string.weekly_fri);
                open = gSearchAttractionDetail.getData().get(0).getFri();
                break;
            case 7:
                week = MyApplication.getInstance().getString(R.string.weekly_sat);
                open = gSearchAttractionDetail.getData().get(0).getSat();
                break;
            default:
                open = new ArrayList<>();
                week = "";
                break;
        }
        int j = 0;
        boolean isNoData = true;
        boolean canLoop = true;
        int timeIndex = -1;
        List<Boolean> list = new ArrayList<>();
        list.add(gSearchAttractionDetail.getData().get(0).getSun().get(0).equals(""));
        list.add(gSearchAttractionDetail.getData().get(0).getMon().get(0).equals(""));
        list.add(gSearchAttractionDetail.getData().get(0).getTue().get(0).equals(""));
        list.add(gSearchAttractionDetail.getData().get(0).getWed().get(0).equals(""));
        list.add(gSearchAttractionDetail.getData().get(0).getThu().get(0).equals(""));
        list.add(gSearchAttractionDetail.getData().get(0).getFri().get(0).equals(""));
        list.add(gSearchAttractionDetail.getData().get(0).getSat().get(0).equals(""));
        StringBuilder stringBuilder = new StringBuilder();
        do {
            if (!list.get(j)) {
                canLoop = false;
                isNoData = false;
            }
            j++;
        } while (canLoop && j < 7);
        if (isNoData) {
            isOpen = "2";
        } else {
            canLoop = true;
            j = 0;
            boolean nowOpen;
            do {
                timeIndex = -1;
                if (!open.get(j).isEmpty()) {
                    if (open.get(j).contains("24")) {
                        timeIndex = j;
                        nowOpen = true;
                        canLoop = false;
                    } else {
                        if (stringBuilder.length() > 0) {
                            stringBuilder.delete(0, stringBuilder.length());
                        }
                        stringBuilder.append(open.get(j).replace(" ", ""));
                        if (stringBuilder.toString().contains("A")) {
                            int number = stringBuilder.indexOf("A");
                            stringBuilder.delete(number, number + 2);
                        }
                        if (stringBuilder.toString().contains("P")) {
                            int number = stringBuilder.indexOf("P");
                            stringBuilder.delete(number, number + 2);
                        }
                        String temp = stringBuilder.toString();
                        if (stringBuilder.toString().contains("–")) {
                            index = stringBuilder.indexOf("–");
                        }
                        if (stringBuilder.toString().contains("-")) {
                            index = stringBuilder.indexOf("-");
                        }
                        stringBuilder.delete(index, stringBuilder.length());
                        index = stringBuilder.indexOf(":");
                        int start = Integer.parseInt(stringBuilder.delete(index, index + 1).toString());
                        stringBuilder.delete(0, stringBuilder.length());
                        stringBuilder.append(temp);
                        if (stringBuilder.toString().contains("–")) {
                            index = stringBuilder.indexOf("–");
                        }
                        if (stringBuilder.toString().contains("-")) {
                            index = stringBuilder.indexOf("-");
                        }
                        stringBuilder.delete(0, index + 1);
                        index = stringBuilder.indexOf(":");
                        int end = Integer.parseInt(stringBuilder.delete(index, index + 1).toString());
                        int now = Integer.parseInt(simpleDateFormat.format(Calendar.getInstance().getTime()));
                        if (now > start) {
                            if (end > now) {
                                canLoop = false;
                                nowOpen = true;
                                timeIndex = j;
                            } else if (start > end) {
                                canLoop = false;
                                nowOpen = true;
                                timeIndex = j;
                            } else {
                                nowOpen = false;
                            }
                        } else {
                            timeIndex = j;
                            canLoop = false;
                            nowOpen = false;
                        }
                    }
                } else {
                    timeIndex = 0;
                    canLoop = false;
                    nowOpen = false;
                }
                j++;
            } while (j < 4 && canLoop);
            if (!nowOpen) {
                isOpen = "1";
            } else {
                isOpen = "0";
            }
        }
        float[] dis = new float[1];
        String d = "";
        if (latLng != null) {
            Location.distanceBetween(latLng.latitude, latLng.longitude,
                    Double.parseDouble(gSearchAttractionDetail.getData().get(0).getLat()),
                    Double.parseDouble(gSearchAttractionDetail.getData().get(0).getLng()), dis);
            d = String.format(Locale.TAIWAN, "%.1f", dis[0] / 1000);
        }
        String style = type + " · " + d + " " + MyApplication.getInstance().getString(R.string.global_km);
        String time;
        switch (isOpen) {
            case "0":
                if (open.get(timeIndex).equals("24")) {
                    time = MyApplication.getInstance().getString(R.string.global_center) + " " + open.get(timeIndex) + " " + MyApplication.getInstance().getString(R.string.open_24) + " " + week;
                } else {
                    time = MyApplication.getInstance().getString(R.string.global_center) +  " " + open.get(timeIndex) + " " + week;
                }
                break;
            case "1":
                time = "";
                break;
            case "2":
                time = "";
                break;
            default:
                time = "";
                break;
        }
        ivMap.showDetailForAttractionInfo(gSearchAttractionDetail, style, isOpen, time);
    }

    @Override
    public void handleSelectedStyleData(List<AttractionStyleEntity> attractionStyleEntities, GAttractionListData gAttractionListData) {
        handleMapPoint(attractionStyleEntities, gAttractionListData);
    }

    @Override
    public void handleStrokeList(GStrokeList gStrokeList, List<AttractionStyleEntity> attractionStyleEntities) {
        MyApplication.getAppData().setgStrokeList(gStrokeList);
        int size = gStrokeList.getData().size();
        List<EClusterItem> eClusterItemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            double lat = Double.parseDouble(gStrokeList.getData().get(i).getLat());
            double lng = Double.parseDouble(gStrokeList.getData().get(i).getLng());
            LatLng latLng = new LatLng(lat, lng);
            String place = gStrokeList.getData().get(i).getPlace();
            String msid = gStrokeList.getData().get(i).getMsid();
            String spid = gStrokeList.getData().get(i).getSpid();
            String style = "";
            for (int j = 0; j < attractionStyleEntities.size(); j++) {
                if (attractionStyleEntities.get(j).getMsid().equals(msid)) {
                    switch (MyApplication.getLanguageIndex()) {
                        case 1:
                            style = attractionStyleEntities.get(j).getMstitle_tw();
                            break;
                        case 2:
                            style = attractionStyleEntities.get(j).getMstitle_ch();
                            break;
                        case 3:
                            style = attractionStyleEntities.get(j).getMstitle_jp();
                            break;
                        default:
                            style = attractionStyleEntities.get(j).getMstitle_en();
                            break;
                    }
                }
            }
            eClusterItemList.add(new EClusterItem(latLng, style, msid, spid, place));
        }
        //MyApplication.getAppData().setPersonalClusterItemList(eClusterItemList);
        if (MyApplication.getAppData().isNormalMap()) {
            ivMap.handleUpdateAttractionData(eClusterItemList);
        } else {
            handleCenterAttraction(eClusterItemList, gStrokeList);
        }
    }

    @Override
    public void handleFinishAddOrDel(GSendLove gSendLove) {
        if (gSendLove.getSave().equals("1") || gSendLove.getSave().equals("3")) {
            ivMap.showFinishAddOrDel(gSendLove.getSave());
        }
    }

    @Override
    public void handleSaveList(GSaveList gSaveList) {
        ivMap.showSaveList(gSaveList);
    }

    @Override
    public void handleFinishAddToStroke(GSaveList gSaveList, List<Boolean> isClickedList, String attractionId) {
        int index = isClickedList.indexOf(true);
        if (index >= 0) {
            sendSaveData(gSaveList, isClickedList, attractionId);
        } else {
            ivMap.finishAddToStroke();
        }
    }

    @Override
    public void handleFinishCreateNewStroke(GSendLove gSendLove) {
        if (gSendLove.getSave().equals("1")) {
            ivMap.showFinishCreateStroke();
        }
    }

    @Override
    public void handleFinishAddToCollect(GSendLove gSendLove) {
        if (gSendLove.getSave().equals("1")) {
            ivMap.finishAddToCollect(true, gSendLove.getSucid());
        } else if (gSendLove.getSave().equals("10")) {
            ivMap.finishAddToCollect(false, gSendLove.getSucid());
        }
    }

    @Override
    public void handleFinishAddToMyStroke(GSendLove gSendLove) {
        if (gSendLove.getSave().equals("1")) {
            ivMap.showFinishAddToMyStroke();
        } else {
            ivMap.showCaution();
        }
    }

    @Override
    public void handlePushMusicData(GPushMusic gPushMusic) {
        List<EMobileMusic> mobileMusicList = new ArrayList<>();
        int size = gPushMusic.getPushMusicData().size();
        for (int i = 0; i < size; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    stringBuilder.append(gPushMusic.getPushMusicData().get(i).getWtitle_tw());
                    break;
                case 2:
                    stringBuilder.append(gPushMusic.getPushMusicData().get(i).getWtitle_ch());
                    break;
                case 3:
                    stringBuilder.append(gPushMusic.getPushMusicData().get(i).getWtitle_jp());
                    break;
                default:
                    stringBuilder.append(gPushMusic.getPushMusicData().get(i).getWtitle());
                    break;
            }
            if (stringBuilder.toString().contains("<>")) {
                int index = stringBuilder.indexOf("<");
                stringBuilder.replace(index, index + 2, " ");
            } else if (stringBuilder.toString().contains("&amp;#039;")) {
                int index = stringBuilder.indexOf("&");
                stringBuilder.replace(index, index + 10, "'");
            }
            EMobileMusic eMobileMusic = new EMobileMusic();
            eMobileMusic.setCount(gPushMusic.getPushMusicData().get(i).getCount());
            eMobileMusic.setLike(gPushMusic.getPushMusicData().get(i).getLove());
            eMobileMusic.setPhoto(gPushMusic.getPushMusicData().get(i).getPath_img());
            eMobileMusic.setWebid(gPushMusic.getPushMusicData().get(i).getWebid());
            eMobileMusic.setTitle(stringBuilder.toString());
            eMobileMusic.setPhpname(gPushMusic.getPushMusicData().get(i).getPhpname());
            mobileMusicList.add(eMobileMusic);
        }
        ivMap.handleMobileMusic(mobileMusicList);
    }

    @Override
    public void handleLoveData(GSendLove gSendLove) {
        ivMap.refreshLoveData(gSendLove.getSave());
    }

    @Override
    public void finishCashFlowInsert() { ivMap.finishCashFlowInsert(); }

    @Override
    public void finishSendCashFlow(GSendLove gSendLove) {
        if (gSendLove.getSave().equals("1")) {
            ivMap.finishCashFlowInsert();
        } else {
            Log.e("Save", gSendLove.getSave());
        }
    }

    @Override
    public void finishSaveCarbon() {
        MyApplication.getAppData().getCarbons().clear();
        ivMap.finishInsertCarbon();
    }

    @Override
    public void handleCarbon(List<CarbonEntity> list, boolean isShow) {
        if (isShow) {
            if (list.isEmpty()) {
                ivMap.showToast("尚未儲存任何資料");
            } else  {
                ArrayList<ECarBonList> eCarBonLists = new ArrayList<>();
                String id = "";
                for (CarbonEntity entity : list) {
                    if (!entity.getCarbon_id().equals(id)) {
                        ECarBonList eCarBonList = new ECarBonList();
                        id = entity.getCarbon_id();
                        eCarBonList.setDate(entity.getDate_short());
                        eCarBonList.setName(entity.getLatitude() + "," + entity.getLongitude());
                        ECarbon eCarbon = new ECarbon();
                        eCarbon.setCarbonId(entity.getCarbon_id());
                        eCarbon.setLatitude(Double.parseDouble(entity.getLatitude()));
                        eCarbon.setLongitude(Double.parseDouble(entity.getLongitude()));
                        eCarbon.setDateFull(entity.getDate_full());
                        eCarbon.setDateShort(entity.getDate_short());
                        eCarBonList.getList().add(eCarbon);
                        eCarBonLists.add(eCarBonList);
                    } else {
                        ECarbon eCarbon = new ECarbon();
                        eCarbon.setCarbonId(entity.getCarbon_id());
                        eCarbon.setLatitude(Double.parseDouble(entity.getLatitude()));
                        eCarbon.setLongitude(Double.parseDouble(entity.getLongitude()));
                        eCarbon.setDateFull(entity.getDate_full());
                        eCarbon.setDateShort(entity.getDate_short());
                        eCarBonLists.get(eCarBonLists.size() - 1).getList().add(eCarbon);
                    }
                }
                for (ECarBonList eCarBonList : eCarBonLists) {
                    double distance = 0;
                    for (int i = 0; i < eCarBonList.getList().size(); i++) {
                        if (i > 0) {
                            float[] result = new float[1];
                            Location.distanceBetween(
                                    eCarBonList.getList().get(i - 1).getLatitude(),
                                    eCarBonList.getList().get(i - 1).getLongitude(),
                                    eCarBonList.getList().get(i).getLatitude(),
                                    eCarBonList.getList().get(i).getLongitude(),
                                    result
                            );
                            distance += result[0];
                        }
                    }
                    eCarBonList.setDistance(String.valueOf(distance));
                    double km = distance / 1000;
                    eCarBonList.setCarbon(String.valueOf(km * 7));
                }
                ivMap.showCarbonList(eCarBonLists);
            }
        } else {
            boolean canLoop = true;
            String id = "Carbon";
            while (canLoop) {
                canLoop = false;
                id += String.valueOf(Math.random() * 999999999 + 1000000001);
                for (CarbonEntity entity : list) { if (entity.getCarbon_id().equals(id)) { canLoop = true; } }
            }
            ArrayList<CarbonEntity> carbonEntities = new ArrayList<>();
            for (ECarbon eCarbon : MyApplication.getAppData().getCarbons()) {
                CarbonEntity entity = new CarbonEntity();
                entity.setLatitude(String.valueOf(eCarbon.getLatitude()));
                entity.setLongitude(String.valueOf(eCarbon.getLongitude()));
                entity.setDate_full(eCarbon.getDateFull());
                entity.setDate_short(eCarbon.getDateShort());
                entity.setCarbon_id(id);
                carbonEntities.add(entity);
            }
            mMap.saveCarbon(carbonEntities);
        }
    }

    private ArrayList<EcoEntity> ecoEntities = new ArrayList<>();
    @Override
    public void handleEco(List<EcoEntity> list, boolean isShow) {
        if (isShow) {
            if (list.isEmpty()) {
                ivMap.showToast("尚未儲存任何資料");
            } else  {
                ecoEntities.clear();
                ecoEntities.addAll(list);
                ArrayList<EEcoList> eEcoLists = new ArrayList<>();
                String id = "";
                for (EcoEntity entity : list) {
                    if (!entity.getEco_id().equals(id)) {
                        EEcoList eEcoList = new EEcoList();
                        id = entity.getEco_id();
                        eEcoList.setDate(entity.getDate_short());
                        eEcoList.setName(entity.getLatitude() + "," + entity.getLongitude());
                        EEco eEco = new EEco();
                        eEco.setCarbonId(entity.getEco_id());
                        eEco.setLatitude(Double.parseDouble(entity.getLatitude()));
                        eEco.setLongitude(Double.parseDouble(entity.getLongitude()));
                        eEco.setDateFull(entity.getDate_full());
                        eEco.setDateShort(entity.getDate_short());
                        eEco.setDistance(Double.parseDouble(entity.getDistance()));
                        eEco.setSpeed(Double.parseDouble(entity.getSpeed()));
                        eEcoList.getList().add(eEco);
                        eEcoLists.add(eEcoList);
                    } else {
                        EEco eEco = new EEco();
                        eEco.setCarbonId(entity.getEco_id());
                        eEco.setLatitude(Double.parseDouble(entity.getLatitude()));
                        eEco.setLongitude(Double.parseDouble(entity.getLongitude()));
                        eEco.setDateFull(entity.getDate_full());
                        eEco.setDateShort(entity.getDate_short());
                        eEco.setDistance(Double.parseDouble(entity.getDistance()));
                        eEco.setSpeed(Double.parseDouble(entity.getSpeed()));
                        eEcoLists.get(eEcoLists.size() - 1).getList().add(eEco);
                    }
                }
                for (EEcoList eEcoList : eEcoLists) {
                    double distance = 0;
                    double speed = 0;
                    double size = eEcoList.getList().size();
                    for (EEco eEco : eEcoList.getList()) {
                        distance += eEco.getDistance();
                        speed += eEco.getSpeed();
                    }
                    double disAVG = Math.round(distance / size * 100) / 100;
                    double speedAVG = Math.round(speed / size * 100) / 100;
                    eEcoList.setDistance(String.valueOf(disAVG));
                    //double km = distance / 1000;
                    eEcoList.setSpeed(String.valueOf(speedAVG));
                }
                ivMap.showEEcoList(eEcoLists);
            }
        } else {
            boolean canLoop = true;
            String id = "Eco";
            while (canLoop) {
                canLoop = false;
                id += String.valueOf(Math.random() * 999999999 + 1000000001);
                for (EcoEntity entity : list) { if (entity.getEco_id().equals(id)) { canLoop = true; } }
            }
            ArrayList<EcoEntity> ecoEntities = new ArrayList<>();
            ArrayList<EEco> eEcos = MyApplication.getAppData().geteEcos();
            for (int i = 0; i < eEcos.size(); i++) {
                EcoEntity entity = new EcoEntity();
                entity.setLatitude(String.valueOf(eEcos.get(i).getLatitude()));
                entity.setLongitude(String.valueOf(eEcos.get(i).getLongitude()));
                entity.setDate_full(eEcos.get(i).getDateFull());
                entity.setDate_short(eEcos.get(i).getDateShort());
                entity.setEco_id(id);
                if (i == 0) {
                    entity.setSpeed("0");
                    entity.setDistance("0");
                } else {
                    float[] result = new float[1];
                    Location.distanceBetween(
                            eEcos.get(i - 1).getLatitude(), eEcos.get(i - 1).getLongitude(),
                            eEcos.get(i).getLatitude(), eEcos.get(i).getLongitude(),
                            result
                    );
                    entity.setDistance(String.valueOf(result[0]));
                    try {
                        Date date1 = new SimpleDateFormat("yyyy/M/d HH:mm:ss:SSS", Locale.getDefault()).parse(eEcos.get(i - 1).getDateFull());
                        Date date2 = new SimpleDateFormat("yyyy/M/d HH:mm:ss:SSS", Locale.getDefault()).parse(eEcos.get(i).getDateFull());
                        long time = date2.getTime() - date1.getTime();
                        double dis = result[0] / 1000;
                        double hour = time / 3600;
                        double speed = Math.round((dis / hour) * 100) / 100;
                        entity.setSpeed(String.valueOf(speed));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                ecoEntities.add(entity);
            }
            mMap.saveEco(ecoEntities);
        }
    }

    @Override
    public void finishSaveEco() {
        MyApplication.getAppData().geteEcos().clear();
        ivMap.finishInsertEco();
    }

    private void handleCenterAttraction(List<EClusterItem> eClusterItemList, GStrokeList gStrokeList) {
        int size = eClusterItemList.size();
        if (size > 2) {
            int center = size / 2;
            int front = 1;
            int rear = size - 2;
            LatLng latLng = new LatLng(Double.parseDouble(gStrokeList.getData().get(center).getLat()),
                    Double.parseDouble(gStrokeList.getData().get(center).getLng()));
            float[] disFront = new float[1];
            Location.distanceBetween(Double.parseDouble(gStrokeList.getData().get(center).getLat()),
                    Double.parseDouble(gStrokeList.getData().get(center).getLng()),
                    Double.parseDouble(gStrokeList.getData().get(front).getLat()),
                    Double.parseDouble(gStrokeList.getData().get(front).getLng()), disFront);
            float[] disRear = new float[1];
            Location.distanceBetween(Double.parseDouble(gStrokeList.getData().get(center).getLat()),
                    Double.parseDouble(gStrokeList.getData().get(center).getLng()),
                    Double.parseDouble(gStrokeList.getData().get(rear).getLat()),
                    Double.parseDouble(gStrokeList.getData().get(rear).getLng()), disRear);
            int avg = (int)((disFront[0] + disRear[0]) / 2);
            int zoomIndex;
            if (avg <= 200) {
                zoomIndex = 19;
            } else if (avg <= 400) {
                zoomIndex = 18;
            } else if (avg <= 600) {
                zoomIndex = 17;
            } else if (avg <= 1600) {
                zoomIndex = 16;
            } else if (avg <= 3200) {
                zoomIndex = 15;
            } else if (avg <= 6400) {
                zoomIndex = 14;
            } else if (avg <= 12000) {
                zoomIndex = 13;
            } else if (avg <= 24000) {
                zoomIndex = 12;
            } else {
                zoomIndex = 11;
            }
            ivMap.showPersonalMap(eClusterItemList, zoomIndex, latLng);
        } else {
            ivMap.showPersonalMap(eClusterItemList, 15, eClusterItemList.get(0).getPosition());
        }
    }

    private LatLng calculateLatLng(LatLng latLng, int angle, int distance) {
        double a = 6378137;
        double b = 6356752.314245;
        double f = 1/298.257223563;
        double latR = Math.toRadians(latLng.latitude);
        double longR = Math.toRadians(latLng.longitude);
        double angleR = Math.toRadians(angle);
        double sinAngleR = Math.sin(angleR);
        double cosAngleR = Math.cos(angleR);
        double tanLatR = (1 - f) * Math.tan(latR);
        double cosLatR = 1 / Math.sqrt((1 + Math.pow(tanLatR, 2)));
        double sinLatR = tanLatR * cosLatR;
        double sigma1 = Math.atan2(tanLatR, cosAngleR);
        double sinA = cosLatR * sinAngleR;
        double cosSqA = 1 - Math.pow(sinA, 2);
        double uSq = cosSqA * (Math.pow(a, 2) - Math.pow(b, 2)) / Math.pow(b, 2);
        double A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
        double B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));
        double cos2SigmaM = 0;
        double sinSigma = 0;
        double cosSigma = 0;
        double deltaSigma = 0;
        double sigma = distance / (b * A);
        double sigmaP = 0;
        int iterations = 0;
        do {
            cos2SigmaM = Math.cos(2 * sigma1 + sigma);
            sinSigma = Math.sin(sigma);
            cosSigma = Math.cos(sigma);
            deltaSigma = B * sinSigma * (cos2SigmaM + B / 4 * (cosSigma * (-1 + 2 * Math.pow(cos2SigmaM, 2)) - B / 6 * cos2SigmaM * (-3 + 4 * Math.pow(sinSigma, 2)) * (-3 + 4 * Math.pow(cos2SigmaM, 2))));
            sigmaP = sigma;
            sigma = distance / (b * A) + deltaSigma;
        } while (Math.abs(sigma - sigmaP) > 1e-12 && ++iterations < 100);
        double x = sinLatR * sinSigma - cosLatR * cosSigma * cosAngleR;
        double latR2 = Math.atan2(sinLatR * cosSigma + cosLatR * sinSigma * cosAngleR, (1 - f) * Math.sqrt(Math.pow(sinA, 2) + Math.pow(x, 2)));
        double lng = Math.atan2(sinSigma * sinAngleR, cosLatR * cosSigma - sinLatR * sinSigma * cosAngleR);
        double C = f / 16 * cosSqA * (4 + f * (4 - 3 * cosSqA));
        double L = lng - (1 - C) * f * sinA * (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1 + 2 * Math.pow(cos2SigmaM, 2))));
        double longR2 = longR + L;
        double angleR2 = Math.atan2(sinA, -x);
        return new LatLng(Math.toDegrees(latR2), Math.toDegrees(longR2));
    }

    private class ExportDatabaseCSVTask extends AsyncTask<String, String, Boolean> {
        //boolean memoryErr = false;
        @Override
        protected Boolean doInBackground(String... strings) {
            //boolean success = false;
            //String currentDateString = new SimpleDateFormat("yyyy/M/d", Locale.getDefault()).format(new Date());
            String currentDate = new SimpleDateFormat("yyyyMdHHmmss", Locale.getDefault()).format(new Date());
            //File dbDile = MyApplication.getInstance().getDatabasePath("swi_official_database");
            File exportDir = new File(Environment.getExternalStorageDirectory(), "");
            if (!exportDir.exists()) exportDir.mkdirs();
            File export = new File(exportDir, currentDate + ".csv");
            try {
                export.createNewFile();
                CSVWriter csvWriter = new CSVWriter(new FileWriter(export));
                String[] arrayString = {"eco_id", "latitude", "longitude", "distance", "speed", "date_short", "date_full"};
                csvWriter.writeNext(arrayString);
                for (EcoEntity entity: ecoEntities) {
                    String[] array = {entity.getEco_id(),
                            entity.getLatitude(),
                            entity.getLongitude(),
                            entity.getDistance(),
                            entity.getSpeed(),
                            entity.getDate_short(),
                            entity.getDate_full()
                    };
                    csvWriter.writeNext(array);
                }
                csvWriter.close();
            } catch (Exception exception) {
                Log.e("PMap", exception.getMessage());
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                ivMap.showToast("已成功匯出");
            } else {
                super.onPostExecute(aBoolean);
            }
        }
    }
}
