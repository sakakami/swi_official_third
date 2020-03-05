package com.switube.www.landmark2018test.presenter;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.entity.ESwapData;
import com.switube.www.landmark2018test.gson.GMyCollection;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.model.MMyCollection;
import com.switube.www.landmark2018test.presenter.callback.IPMyCollection;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVMyCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PMyCollection implements IPMyCollection {
    private IVMyCollection ivMyCollection;
    private MMyCollection mMyCollection;
    public PMyCollection(IVMyCollection ivMyCollection) {
        this.ivMyCollection = ivMyCollection;
        mMyCollection = new MMyCollection(this);
    }

    public void getCollectData() {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        mMyCollection.getCollectData(map);
    }

    public void handleInit(GMyCollection gMyCollection, LatLng latLng) {
        int size = gMyCollection.getData().size();
        List<ESwapData> eSwapDataList = new ArrayList<>();
        List<String> distance = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            float[] dis = new float[1];
            ESwapData eSwapData = new ESwapData();
            Location.distanceBetween(latLng.latitude, latLng.longitude,
                    Double.parseDouble(gMyCollection.getData().get(i).getLat()),
                    Double.parseDouble(gMyCollection.getData().get(i).getLng()), dis);
            String d = String.format(Locale.TAIWAN, "%.1f", dis[0] / 1000) + "Km";
            distance.add(d);
            eSwapData.setId(gMyCollection.getData().get(i).getSucid());
            eSwapData.setTitle(gMyCollection.getData().get(i).getPlace());
            eSwapData.setMyCollection(true);
            eSwapDataList.add(eSwapData);
        }
        MyApplication.getAppData().seteSwapDataListForCollect(eSwapDataList);
        ivMyCollection.init(gMyCollection, distance);
    }

    public void removeAttraction(int index, String spid) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("type", "del");
        map.put("raid", "WebSite");
        map.put("spid", spid);
        mMyCollection.sendRemoveData(map, index);
    }

    public void getSaveList(String spid) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("raid", "WebSite");
        mMyCollection.getSaveList(map, spid);
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
        mMyCollection.sendAddToStroke(map, gSaveList, isClickedList, attractionId);
    }

    public void handleNewStroke(String title) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("title", title);
        map.put("type", "add");
        map.put("urid", "");
        map.put("raid", "WebSite");
        mMyCollection.createNewStroke(map);
    }

    @Override
    public void handleCollectData(GMyCollection gMyCollection) {
        ivMyCollection.init(gMyCollection);
    }

    @Override
    public void handleRemove(GSendLove gSendLove, int index) {
        if (gSendLove.getSave().equals("10")) {
            ivMyCollection.handleFinishRemove(index);
            MyApplication.getAppData().geteSwapDataListForCollect().remove(index);
        }
    }

    @Override
    public void handleSaveList(GSaveList gSaveList, String spid) {
        ivMyCollection.showSaveList(gSaveList, spid);
    }

    @Override
    public void handleFinishAdd(GSaveList gSaveList, List<Boolean> isClickedList, String attractionId) {
        int indexInClicked = isClickedList.indexOf(true);
        if (indexInClicked >= 0) {
            sendSaveData(gSaveList, isClickedList, attractionId);
        } else {
            ivMyCollection.showFinishAdd();
        }
    }

    @Override
    public void handleFinishCreate(GSendLove gSendLove) {
        if (gSendLove.getSave().equals("1")) {
            ivMyCollection.showFinishCreate();
        }
    }
}
