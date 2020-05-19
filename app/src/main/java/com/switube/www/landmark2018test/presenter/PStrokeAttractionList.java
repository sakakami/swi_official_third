package com.switube.www.landmark2018test.presenter;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.entity.ESwapData;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.gson.GStrokeList;
import com.switube.www.landmark2018test.model.MStrokeAttractionList;
import com.switube.www.landmark2018test.presenter.callback.IPStrokeAttractionList;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVStrokeAttractionList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PStrokeAttractionList implements IPStrokeAttractionList {
    private IVStrokeAttractionList ivStrokeAttractionList;
    private MStrokeAttractionList mStrokeAttractionList;
    public PStrokeAttractionList(IVStrokeAttractionList ivStrokeAttractionList) {
        this.ivStrokeAttractionList = ivStrokeAttractionList;
        mStrokeAttractionList = new MStrokeAttractionList(this);
    }

    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HHmm", Locale.getDefault());
    public void init() {
        mStrokeAttractionList.getStyleData();
    }

    public void sendAddToCollect(String spid, int index) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("type", "add");
        map.put("raid", MyApplication.getAppData().getRaid());
        map.put("spid", spid);
        mStrokeAttractionList.sendAddToCollect(map, index);
    }

    public void sendDelToCollect(String spid, int index) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("type", "del");
        map.put("raid", MyApplication.getAppData().getRaid());
        map.put("spid", spid);
        mStrokeAttractionList.sendDelToCollect(map, index);
    }

    public void getSaveList() {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("raid", MyApplication.getAppData().getRaid());
        mStrokeAttractionList.getSaveList(map);
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
        mStrokeAttractionList.sendAddToStroke(map, gSaveList, isClickedList, attractionId);
    }

    public void sendSaveData(String urspid, String urid, int index) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("type", "del");
        map.put("raid", MyApplication.getAppData().getRaid());
        map.put("urid", urid);
        map.put("urspid", urspid);
        map.put("spid", "");
        map.put("sort", "");
        mStrokeAttractionList.sendAddToStroke(map, false, index);
    }

    public void handleNewStroke(String title, boolean isCreate) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("title", title);
        if (isCreate) {
            map.put("type", "add");
        } else {
            map.put("type", "edit");
        }
        map.put("urid", MyApplication.getAppData().getUrid());
        map.put("raid", MyApplication.getAppData().getRaid());
        mStrokeAttractionList.createNewStroke(map, title);
    }

    @Override
    public void init(List<AttractionStyleEntity> attractionStyleEntities) {
        GStrokeList gStrokeList = MyApplication.getAppData().getgStrokeList();
        List<String> style = new ArrayList<>();
        List<String> msid = new ArrayList<>();
        List<String> time = new ArrayList<>();
        List<String> isOpen = new ArrayList<>();
        int size = attractionStyleEntities.size();
        for (int i = 0; i < size; i++) {
            msid.add(attractionStyleEntities.get(i).getMsid());
        }
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        List<ESwapData> eSwapDataList = new ArrayList<>();
        size = gStrokeList.getData().size();
        for (int i = 0; i < size; i++) {
            ESwapData eSwapData = new ESwapData();
            eSwapData.setId(gStrokeList.getData().get(i).getUrspid());
            eSwapData.setTitle(gStrokeList.getData().get(i).getPlace());
            eSwapData.setMyCollection(false);
            eSwapDataList.add(eSwapData);
            int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            float[] dis = new float[1];
            String week;
            List<String> open;
            switch (day) {
                case 1:
                    week = MyApplication.getInstance().getString(R.string.weekly_sun);
                    open = gStrokeList.getData().get(i).getSun();
                    break;
                case 2:
                    week = MyApplication.getInstance().getString(R.string.weekly_mon);
                    open = gStrokeList.getData().get(i).getMon();
                    break;
                case 3:
                    week = MyApplication.getInstance().getString(R.string.weekly_tue);
                    open = gStrokeList.getData().get(i).getTue();
                    break;
                case 4:
                    week = MyApplication.getInstance().getString(R.string.weekly_wed);
                    open = gStrokeList.getData().get(i).getWed();
                    break;
                case 5:
                    week = MyApplication.getInstance().getString(R.string.weekly_thu);
                    open = gStrokeList.getData().get(i).getThu();
                    break;
                case 6:
                    week = MyApplication.getInstance().getString(R.string.weekly_fri);
                    open = gStrokeList.getData().get(i).getFri();
                    break;
                case 7:
                    week = MyApplication.getInstance().getString(R.string.weekly_sat);
                    open = gStrokeList.getData().get(i).getSat();
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
            list.add(gStrokeList.getData().get(i).getSun().get(0).isEmpty());
            list.add(gStrokeList.getData().get(i).getMon().get(0).isEmpty());
            list.add(gStrokeList.getData().get(i).getTue().get(0).isEmpty());
            list.add(gStrokeList.getData().get(i).getWed().get(0).isEmpty());
            list.add(gStrokeList.getData().get(i).getThu().get(0).isEmpty());
            list.add(gStrokeList.getData().get(i).getFri().get(0).isEmpty());
            list.add(gStrokeList.getData().get(i).getSat().get(0).isEmpty());
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
            index = msid.indexOf(gStrokeList.getData().get(i).getMsid());
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
        MyApplication.getAppData().seteSwapDataList(eSwapDataList);
        ivStrokeAttractionList.init(gStrokeList, style, time, isOpen);
    }

    @Override
    public void handleFinishAddToCollect(GSendLove gSendLove, int index) {
        if (gSendLove.getSave().equals("1") || gSendLove.getSave().equals("10")) {
            MyApplication.getAppData().getgStrokeList().getData().get(index).setColl(gSendLove.getSucid());
            ivStrokeAttractionList.showFinishAddToCollect(index, gSendLove.getSucid());
        }
    }

    @Override
    public void handleFinishDelToCollect(GSendLove gSendLove, int index) {
        if (gSendLove.getSave().equals("1") || gSendLove.getSave().equals("10")) {
            MyApplication.getAppData().getgStrokeList().getData().get(index).setColl("");
            ivStrokeAttractionList.showFinishDelToCollect(index);
        }
    }

    @Override
    public void handleSaveList(GSaveList gSaveList) {
        ivStrokeAttractionList.showSaveList(gSaveList);
    }

    @Override
    public void handleFinishAddToStroke(GSaveList gSaveList, List<Boolean> isClickedList, String attractionId) {
        int index = isClickedList.indexOf(true);
        if (index >= 0) {
            sendSaveData(gSaveList, isClickedList, attractionId);
        } else {
            ivStrokeAttractionList.finishAddToStroke();
        }
    }

    @Override
    public void handleFinishToStroke(GSendLove gSendLove, boolean isMove, int index) {
        if (!isMove) {
            if (gSendLove.getSave().equals("2")) {
                ivStrokeAttractionList.finishDelFormStroke(index);
                MyApplication.getAppData().getgStrokeList().getData().remove(index);
                init();
            }
        }
    }

    @Override
    public void handleFinishCreate(GSendLove gSendLove, String title) {
        if (gSendLove.getSave().equals("1")) {
            ivStrokeAttractionList.showFinishCreate();
        } else if (gSendLove.getSave().equals("2")) {
            MyApplication.getAppData().setTitleForUrid(title);
            ivStrokeAttractionList.showFinishEdit();
        }
    }
}
