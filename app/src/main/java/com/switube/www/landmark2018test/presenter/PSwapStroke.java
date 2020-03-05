package com.switube.www.landmark2018test.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.entity.ESwapData;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.model.MSwapStroke;
import com.switube.www.landmark2018test.presenter.callback.IPSwapStroke;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVSwapStroke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PSwapStroke implements IPSwapStroke {
    private IVSwapStroke ivSwapStroke;
    private MSwapStroke mSwapStroke;
    public PSwapStroke(IVSwapStroke ivSwapStroke) {
        this.ivSwapStroke = ivSwapStroke;
        mSwapStroke = new MSwapStroke(this);
    }

    public void init() {
        if (MyApplication.getAppData().isCollectionPage()) {
            ivSwapStroke.init(MyApplication.getAppData().geteSwapDataListForCollect());
        } else {
            ivSwapStroke.init(MyApplication.getAppData().geteSwapDataList());
        }
    }

    public void sendMovedData(List<ESwapData> eSwapDataList, String urid, List<Integer> fromPositionList, List<Integer> toPositionList) {
        List<String> urspidList = new ArrayList<>();
        int size = eSwapDataList.size();
        for (int i = 0; i < size; i++) {
            urspidList.add(eSwapDataList.get(i).getId());
        }
        Gson gson = new Gson();
        String urspids = gson.toJson(urspidList);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("type", "move");
        if (eSwapDataList.get(0).isMyCollection()) {
            map.put("raid", "WebSite");
            map.put("spid", urspids);
        } else {
            map.put("raid", MyApplication.getAppData().getRaid());
            map.put("urid", urid);
        }
        map.put("val", urspids);
        Log.e("swap", map.toString());
        mSwapStroke.sendAddToStroke(map, eSwapDataList.get(0).isMyCollection(), fromPositionList, toPositionList);
    }

    @Override
    public void handleFinishToStroke(GSendLove gSendLove, List<Integer> fromPositionList, List<Integer> toPositionList) {
        if (gSendLove.getSave().equals("3")) {
            int size = fromPositionList.size();
            for (int i = 0; i < size; i++) {
                if (MyApplication.getAppData().isCollectionPage()) {
                    Collections.swap(MyApplication.getAppData().geteSwapDataListForCollect(), fromPositionList.get(i), toPositionList.get(i));
                } else {
                    Collections.swap(MyApplication.getAppData().getgStrokeList().getData(), fromPositionList.get(i), toPositionList.get(i));
                }
            }
            ivSwapStroke.handleFinishSave();
        }
    }
}
