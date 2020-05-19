package com.switube.www.landmark2018test.presenter;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.entity.EFeatures;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.gson.SearchPlaceDataGson;
import com.switube.www.landmark2018test.gson.GSearchAttractionDetail;
import com.switube.www.landmark2018test.model.MSearchAttraction;
import com.switube.www.landmark2018test.presenter.callback.IPSearchAttraction;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVSearchAttraction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PSearchAttraction implements IPSearchAttraction {
    private IVSearchAttraction ivSearchAttraction;
    private MSearchAttraction mSearchAttraction;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HHmm", Locale.getDefault());
    public PSearchAttraction(IVSearchAttraction ivSearchAttraction) {
        this.ivSearchAttraction = ivSearchAttraction;
        mSearchAttraction = new MSearchAttraction(this);
    }

    public void getAllAttractionData(String msid, LatLng latLng) {
        List<String> mscid = new ArrayList<>();
        SearchPlaceDataGson searchPlaceDataGson = new SearchPlaceDataGson(msid, mscid, new ArrayList<>());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String value = gson.toJson(searchPlaceDataGson);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("value", value);
        mSearchAttraction.getSearchData(map, latLng, 0);
    }

    public void getSearchData(String msid, List<EFeatures> itemEntities, LatLng latLng, int distance) {
        List<String> mscid = new ArrayList<>();
        List<String> miid = new ArrayList<>();
        int size = itemEntities.size();
        for (int i = 0; i < size; i++) {
            if (itemEntities.get(i).isSelect()) {
                if (itemEntities.get(i).getFrom().equals("class")) {
                    mscid.add(itemEntities.get(i).getId());
                } else {
                    miid.add(itemEntities.get(i).getId());
                }
            }
        }
        SearchPlaceDataGson searchPlaceDataGson = new SearchPlaceDataGson(msid, mscid, miid);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String value = gson.toJson(searchPlaceDataGson);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("value", value);
        switch (MyApplication.getLanguageIndex()) {
            case 1:
                map.put("lang", "tw");
                break;
            case 2:
                map.put("lang", "ch");
                break;
            case 3:
                map.put("lang", "jp");
                break;
            default:
                map.put("lang", "en");
                break;
        }
        mSearchAttraction.getSearchData(map, latLng, distance);
    }

    private int size = 0;
    public void switchByDistance(GSearchAttractionDetail gSearchAttractionDetail, List<String> style, List<String> time, List<String> open, LatLng nowGps) {
        size = gSearchAttractionDetail.getData().size();
        List<Float> distance = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            float[] dis = new float[1];
            Location.distanceBetween(nowGps.latitude, nowGps.longitude,
                    Double.parseDouble(gSearchAttractionDetail.getData().get(i).getLat()),
                    Double.parseDouble(gSearchAttractionDetail.getData().get(i).getLng()), dis);
            distance.add(dis[0]);
        }
        float tempDis;
        GSearchAttractionDetail.Data data;
        String temp;
        for (int i = 0; i < distance.size() - 1; i++) {
            for (int j = 0; j < distance.size() - 1 - i; j++) {
                if (distance.get(j) > distance.get(j + 1)) {
                    tempDis = distance.get(j);
                    distance.set(j, distance.get(j + 1));
                    distance.set(j + 1, tempDis);
                    temp = style.get(j);
                    style.set(j, style.get(j + 1));
                    style.set(j + 1, temp);
                    temp = time.get(j);
                    time.set(j, time.get(j + 1));
                    time.set(j + 1, temp);
                    temp = open.get(j);
                    open.set(j, open.get(j + 1));
                    open.set(j + 1, temp);
                    data = gSearchAttractionDetail.getData().get(j);
                    gSearchAttractionDetail.getData().set(j, gSearchAttractionDetail.getData().get(j + 1));
                    gSearchAttractionDetail.getData().set(j + 1, data);
                }
            }
        }
        ivSearchAttraction.refreshAdapter(gSearchAttractionDetail, style, time, open);
    }

    public void switchByTime(GSearchAttractionDetail gSearchAttractionDetail, List<String> style, List<String> time, List<String> open) {
        String temp;
        GSearchAttractionDetail.Data data;
        for (int i = 0; i < open.size() - 1; i++) {
            for (int j = 0; j < open.size() - 1 - i; j++) {
                if (Integer.parseInt(open.get(j)) > Integer.parseInt(open.get(j + 1))) {
                    temp = open.get(j);
                    open.set(j, open.get(j + 1));
                    open.set(j + 1, temp);
                    temp = style.get(j);
                    style.set(j, style.get(j + 1));
                    style.set(j + 1, temp);
                    temp = time.get(j);
                    time.set(j, time.get(j + 1));
                    time.set(j + 1, temp);
                    data = gSearchAttractionDetail.getData().get(j);
                    gSearchAttractionDetail.getData().set(j, gSearchAttractionDetail.getData().get(j + 1));
                    gSearchAttractionDetail.getData().set(j + 1, data);
                }
            }
        }
        ivSearchAttraction.refreshAdapter(gSearchAttractionDetail, style, time, open);
    }

    public void switchByRate(GSearchAttractionDetail gSearchAttractionDetail, List<String> style, List<String> time, List<String> open) {
        size = gSearchAttractionDetail.getData().size();
        List<Float> rate = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            rate.add(Float.parseFloat(gSearchAttractionDetail.getData().get(i).getRating()));
        }
        Float tempRate;
        String temp;
        GSearchAttractionDetail.Data data;
        for (int i = 0; i < rate.size() - 1; i++) {
            for (int j = 0; j < rate.size() - 1 - i; j++) {
                if (rate.get(j) < rate.get(j + 1)) {
                    tempRate = rate.get(j);
                    rate.set(j, rate.get(j + 1));
                    rate.set(j + 1, tempRate);
                    temp = open.get(j);
                    open.set(j, open.get(j + 1));
                    open.set(j + 1, temp);
                    temp = style.get(j);
                    style.set(j, style.get(j + 1));
                    style.set(j + 1, temp);
                    temp = time.get(j);
                    time.set(j, time.get(j + 1));
                    time.set(j + 1, temp);
                    data = gSearchAttractionDetail.getData().get(j);
                    gSearchAttractionDetail.getData().set(j, gSearchAttractionDetail.getData().get(j + 1));
                    gSearchAttractionDetail.getData().set(j + 1, data);
                }
            }
        }
        ivSearchAttraction.refreshAdapter(gSearchAttractionDetail, style, time, open);
    }

    public void getSaveList(String spid) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("raid", MyApplication.getAppData().getRaid());
        mSearchAttraction.getSaveList(map, spid);
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
        mSearchAttraction.sendAddToStroke(map, gSaveList, isClickedList, attractionId);
    }

    public void handleNewStroke(String title) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("title", title);
        map.put("raid", MyApplication.getAppData().getRaid());
        mSearchAttraction.createNewStroke(map);
    }

    public void sendAddToCollect(String spid, boolean isAdd, int index) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        if (isAdd) {
            map.put("type", "add");
        } else {
            map.put("type", "del");
        }
        map.put("raid", MyApplication.getAppData().getRaid());
        map.put("spid", spid);
        mSearchAttraction.sendAddToCollect(map, index);
    }

    @Override
    public void handleAttractionDataWithSelected(GSearchAttractionDetail gSearchAttractionDetail, LatLng latLng, int distance) {
        mSearchAttraction.getAllStyleData(gSearchAttractionDetail, latLng, distance);
    }

    @Override
    public void handleAttractionDataWithSelected(List<AttractionStyleEntity> attractionStyleEntities, GSearchAttractionDetail gSearchAttractionDetail, LatLng latLng, int distance) {
        List<String> style = new ArrayList<>();
        List<String> msid = new ArrayList<>();
        List<String> time = new ArrayList<>();
        List<String> isOpen = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int size = attractionStyleEntities.size();
        for (int i = 0; i < size; i++) {
            msid.add(attractionStyleEntities.get(i).getMsid());
        }
        int kmToM = distance * 1000;
        int index = 0;
        for (int i = 0; i < gSearchAttractionDetail.getData().size(); i++) {
            float[] dis = new float[1];
            Location.distanceBetween(latLng.latitude, latLng.longitude,
                    Double.parseDouble(gSearchAttractionDetail.getData().get(i).getLat()),
                    Double.parseDouble(gSearchAttractionDetail.getData().get(i).getLng()), dis);
            if (dis[0] > kmToM && distance != 0) {
                gSearchAttractionDetail.getData().remove(i);
                i--;
            } else {
                int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                String week;
                List<String> open;
                switch (day) {
                    case 1:
                        week = MyApplication.getInstance().getString(R.string.weekly_sun);
                        open = gSearchAttractionDetail.getData().get(i).getSun();
                        break;
                    case 2:
                        week = MyApplication.getInstance().getString(R.string.weekly_mon);
                        open = gSearchAttractionDetail.getData().get(i).getMon();
                        break;
                    case 3:
                        week = MyApplication.getInstance().getString(R.string.weekly_tue);
                        open = gSearchAttractionDetail.getData().get(i).getTue();
                        break;
                    case 4:
                        week = MyApplication.getInstance().getString(R.string.weekly_wed);
                        open = gSearchAttractionDetail.getData().get(i).getWed();
                        break;
                    case 5:
                        week = MyApplication.getInstance().getString(R.string.weekly_thu);
                        open = gSearchAttractionDetail.getData().get(i).getThu();
                        break;
                    case 6:
                        week = MyApplication.getInstance().getString(R.string.weekly_fri);
                        open = gSearchAttractionDetail.getData().get(i).getFri();
                        break;
                    case 7:
                        week = MyApplication.getInstance().getString(R.string.weekly_sat);
                        open = gSearchAttractionDetail.getData().get(i).getSat();
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
                list.add(gSearchAttractionDetail.getData().get(i).getSun().get(0).isEmpty());
                list.add(gSearchAttractionDetail.getData().get(i).getMon().get(0).isEmpty());
                list.add(gSearchAttractionDetail.getData().get(i).getTue().get(0).isEmpty());
                list.add(gSearchAttractionDetail.getData().get(i).getWed().get(0).isEmpty());
                list.add(gSearchAttractionDetail.getData().get(i).getThu().get(0).isEmpty());
                list.add(gSearchAttractionDetail.getData().get(i).getFri().get(0).isEmpty());
                list.add(gSearchAttractionDetail.getData().get(i).getSat().get(0).isEmpty());
                do {
                    if (!list.get(j)) {
                        canLoop = false;
                        isNoData = false;
                    }
                    j++;
                } while (canLoop && j < 7);
                if (isNoData) {
                    isOpen.add("2");
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
                                int start;
                                if (stringBuilder.toString().equals("999999")) {
                                    start = 2400;
                                } else {
                                    index = stringBuilder.indexOf(":");
                                    start = Integer.parseInt(stringBuilder.delete(index, index + 1).toString());
                                }
                                stringBuilder.delete(0, stringBuilder.length());
                                stringBuilder.append(temp);
                                if (stringBuilder.toString().contains("–")) {
                                    index = stringBuilder.indexOf("–");
                                }
                                if (stringBuilder.toString().contains("-")) {
                                    index = stringBuilder.indexOf("-");
                                }
                                stringBuilder.delete(0, index + 1);
                                int end;
                                if (stringBuilder.toString().equals("999999")) {
                                    end = 2400;
                                } else {
                                    index = stringBuilder.indexOf(":");
                                    end = Integer.parseInt(stringBuilder.delete(index, index + 1).toString());
                                }
                                int now = Integer.parseInt(mSimpleDateFormat.format(Calendar.getInstance().getTime()));
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
                        isOpen.add("1");
                    } else {
                        isOpen.add("0");
                    }
                }
                index = msid.indexOf(gSearchAttractionDetail.getData().get(i).getMsid());
                String d = String.format(Locale.TAIWAN, "%.1f", dis[0] / 1000);
                String selectedStyle;
                switch (MyApplication.getLanguageIndex()) {
                    case 1:
                        selectedStyle = attractionStyleEntities.get(index).getMstitle_tw();
                        break;
                    case 2:
                        selectedStyle = attractionStyleEntities.get(index).getMstitle_ch();
                        break;
                    case 3:
                        selectedStyle = attractionStyleEntities.get(index).getMstitle_jp();
                        break;
                    default:
                        selectedStyle = attractionStyleEntities.get(index).getMstitle_en();
                        break;
                }
                style.add(selectedStyle + " · " + d + " km");
                if ("0".equals(isOpen.get(i))) {
                    if (open.get(timeIndex).equals("24")) {
                        time.add(MyApplication.getInstance().getString(R.string.global_center) + " " + open.get(timeIndex) + MyApplication.getInstance().getString(R.string.open_24) + " " + week);
                    } else {
                        time.add(MyApplication.getInstance().getString(R.string.global_center) + " " + open.get(timeIndex) + " " + week);
                    }
                } else {
                    time.add("");
                }
            }
        }
        ivSearchAttraction.init(gSearchAttractionDetail, style, time, isOpen);
    }

    @Override
    public void handleSaveList(GSaveList gSaveList, String spid) {
        ivSearchAttraction.showSaveList(gSaveList, spid);
    }

    @Override
    public void handleFinishAddToStroke(GSaveList gSaveList, List<Boolean> isClickedList, String attractionId) {
        int index = isClickedList.indexOf(true);
        if (index >= 0) {
            sendSaveData(gSaveList, isClickedList, attractionId);
        } else {
            ivSearchAttraction.finishAddToStroke();
        }
    }

    @Override
    public void handleFinishCreateNewStroke(GSendLove gSendLove) {
        if (gSendLove.getSave().equals("1")) {
            ivSearchAttraction.showFinishCreateStroke();
        }
    }

    @Override
    public void handleFinishAddToCollect(GSendLove gSendLove, int index) {
        if (gSendLove.getSave().equals("1")) {
            ivSearchAttraction.finishAddToCollect(gSendLove.getSucid(), index, true);
        } else if (gSendLove.getSave().equals("10")) {
            ivSearchAttraction.finishAddToCollect(gSendLove.getSucid(), index, false);
        }
    }
}
