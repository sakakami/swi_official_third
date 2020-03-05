package com.switube.www.landmark2018test.presenter;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.gson.GAttractionDataGoogle;
import com.switube.www.landmark2018test.gson.GPlaceIdData;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.model.MEditAttraction;
import com.switube.www.landmark2018test.presenter.callback.IPEditAttraction;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVEditAttraction;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class PEditAttraction implements IPEditAttraction {
    private IVEditAttraction ivEditAttraction;
    private MEditAttraction mEditAttraction;
    private List<String> apiKeysForEn = new ArrayList<>();
    private List<String> apiKeysForTw = new ArrayList<>();
    private List<String> apiKeysForCn = new ArrayList<>();
    private List<String> apiKeysForJp = new ArrayList<>();
    private List<String> apiKeys = new ArrayList<>();
    private Random random;
    private String[] baseCodeB = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private String[] baseCodeC = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public PEditAttraction(IVEditAttraction ivEditAttraction) {
        this.ivEditAttraction = ivEditAttraction;
        mEditAttraction = new MEditAttraction(this);
        random = new Random();
        String key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[1] + baseCodeC[20] + baseCodeB[8] + "7" + baseCodeC[10] + baseCodeC[5]
                + "1" + baseCodeB[13] + baseCodeB[16] + baseCodeB[8] + baseCodeC[14] + baseCodeB[23]
                + baseCodeB[12] + baseCodeB[4] + baseCodeB[11] + baseCodeC[12] + baseCodeB[19] + baseCodeC[12]
                + baseCodeB[11] + baseCodeB[17] + baseCodeC[9] + baseCodeB[8] + baseCodeB[24] + baseCodeB[11]
                + baseCodeB[14] + "8" + baseCodeB[20] + baseCodeB[0] + baseCodeC[10] + baseCodeC[14]
                + "_4" + baseCodeB[8];
        apiKeysForEn.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[2] + baseCodeC[0] + baseCodeB[20] + baseCodeC[15] + baseCodeB[10] + baseCodeB[12]
                + baseCodeB[23] + baseCodeC[7] + baseCodeB[7] + baseCodeB[15] + baseCodeC[21] + baseCodeB[2]
                + baseCodeB[12] + baseCodeB[23] + "5" + baseCodeB[16] + baseCodeC[22] + "8"
                + baseCodeC[2] + baseCodeB[2] + baseCodeB[7] + baseCodeB[24] + baseCodeB[4] + baseCodeC[24]
                + baseCodeC[13] + "5" + baseCodeB[13] + baseCodeC[21] + baseCodeB[0] + baseCodeC[17]
                + baseCodeB[10] + baseCodeC[5] + baseCodeC[18];
        apiKeysForEn.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[2] + "2" + baseCodeB[15] + baseCodeB[14] + baseCodeB[15] + baseCodeC[2]
                + baseCodeC[6] + baseCodeB[4] + "5" + baseCodeC[21] + baseCodeC[10] + baseCodeC[10]
                + baseCodeC[25] + baseCodeC[23] + "_" + baseCodeB[24] + baseCodeC[2] + baseCodeB[6]
                + baseCodeB[16] + baseCodeC[12] + baseCodeB[3] + baseCodeC[11] + baseCodeC[18] + baseCodeC[23]
                + "6" + baseCodeB[16] + baseCodeC[19] + baseCodeC[18] + baseCodeC[22] + baseCodeC[7]
                + baseCodeB[8] + baseCodeC[20] + baseCodeC[22];
        apiKeysForEn.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[2] + baseCodeC[3] + baseCodeC[1] + baseCodeC[17] + "63"
                + baseCodeB[3] + baseCodeC[24] + "0" + baseCodeC[21] + baseCodeB[18] + baseCodeB[4]
                + baseCodeC[23] + baseCodeB[4] + "9" + baseCodeC[15] + baseCodeC[25] + baseCodeB[25]
                + baseCodeC[8] + baseCodeB[22] + "2" + baseCodeC[17] + "9" + baseCodeB[16]
                + baseCodeC[1] + "_" + baseCodeB[15] + "5" + baseCodeB[11] + baseCodeC[11]
                + baseCodeB[11] + baseCodeB[22] + baseCodeB[16];
        apiKeysForEn.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[3] + baseCodeB[25] + baseCodeB[0] + baseCodeC[3] + baseCodeB[11] + baseCodeB[23]
                + baseCodeB[9] + baseCodeC[25] + baseCodeC[13] + baseCodeB[0] + baseCodeC[0] + baseCodeB[1]
                + baseCodeB[25] + baseCodeC[24] + baseCodeC[3] + baseCodeB[21] + "8" + baseCodeB[11]
                + "1" + baseCodeB[3] + baseCodeB[11] + baseCodeB[12] + "9" + baseCodeB[11]
                + baseCodeC[5] + baseCodeC[10] + baseCodeC[8] + baseCodeB[19] + baseCodeC[14] + baseCodeB[7]
                + baseCodeB[17] + baseCodeC[22] + baseCodeC[2];
        apiKeysForTw.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[3] + baseCodeC[6] + baseCodeB[11] + baseCodeB[16] + baseCodeC[0] + baseCodeC[13]
                + baseCodeC[7] + baseCodeC[1] + baseCodeB[14] + baseCodeC[16] + baseCodeB[23] + "5"
                + "-" + baseCodeC[12] + baseCodeC[10] + baseCodeC[16] + baseCodeB[3] + baseCodeB[1]
                + baseCodeB[12] + "_" + baseCodeC[5] + "_" + baseCodeB[17] + baseCodeC[4]
                + baseCodeB[22] + baseCodeB[24] + "82" + baseCodeC[22] + baseCodeC[1]
                + baseCodeB[23] + "6" + baseCodeB[4];
        apiKeysForTw.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[1] + "3" + baseCodeC[5] + baseCodeB[24] + "-" + baseCodeB[11]
                + baseCodeC[22] + "47" + baseCodeB[16] + "3" + baseCodeC[13]
                + baseCodeC[11] + "5" + baseCodeC[12] + baseCodeC[2] + baseCodeB[17] + baseCodeB[16]
                + baseCodeB[4] + "6" + baseCodeB[18] + baseCodeB[0] + baseCodeC[7] + baseCodeC[5]
                + baseCodeB[8] + baseCodeC[2] + baseCodeB[23] + baseCodeB[11] + baseCodeB[3] + baseCodeB[23]
                + baseCodeC[14] + "1" + baseCodeC[10];
        apiKeysForTw.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[3] + baseCodeB[6] + baseCodeC[2] + baseCodeB[20] + baseCodeB[12] + baseCodeB[20]
                + baseCodeC[5] + "-" + baseCodeC[3] + baseCodeC[18] + baseCodeC[23] + baseCodeB[17]
                + baseCodeC[18] + baseCodeC[2] + baseCodeC[0] + baseCodeB[9] + baseCodeB[2] + baseCodeB[24]
                + baseCodeC[22] + "6" + baseCodeB[12] + baseCodeC[21] + "9" + baseCodeC[21]
                + baseCodeC[17] + "11" + baseCodeB[13] + baseCodeB[2] + baseCodeB[2]
                + baseCodeC[23] + baseCodeB[16] + baseCodeB[12];
        apiKeysForTw.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[0] + "_" + baseCodeC[6] + baseCodeC[20] + baseCodeC[1] + baseCodeB[10]
                + "_8" + baseCodeB[15] + baseCodeB[7] + baseCodeB[15] + baseCodeB[11]
                + "6" + baseCodeB[23] + baseCodeC[18] + baseCodeC[25] + baseCodeB[20] + baseCodeC[23]
                + baseCodeC[19] + "1" + baseCodeC[3] + baseCodeC[19] + baseCodeB[3] + baseCodeC[9]
                + baseCodeB[17] + baseCodeC[12] + baseCodeC[6] + baseCodeC[19] + baseCodeB[0] + baseCodeB[10]
                + baseCodeB[2] + baseCodeC[16] + "8";
        apiKeysForCn.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[1] + "6" + baseCodeC[25] + baseCodeB[3] + baseCodeB[20] + baseCodeC[21]
                + baseCodeC[23] + baseCodeC[10] + baseCodeB[10] + baseCodeB[9] + "3" + baseCodeB[17]
                + baseCodeC[21] + "0" + baseCodeC[15] + baseCodeC[11] + baseCodeB[10] + baseCodeB[11]
                + baseCodeC[16] + baseCodeC[22] + baseCodeB[16] + "9" + baseCodeB[3] + baseCodeB[5]
                + "9" + baseCodeB[18] + baseCodeC[20] + baseCodeC[22] + baseCodeB[0] + "8"
                + baseCodeC[15] + "-" + baseCodeB[20];
        apiKeysForCn.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[3] + baseCodeB[8] + baseCodeC[21] + baseCodeC[24] + "7" + baseCodeB[19]
                + baseCodeB[21] + "2" + baseCodeC[24] + "5" + baseCodeC[11] + baseCodeB[10]
                + "3" + baseCodeC[3] + baseCodeB[23] + baseCodeB[18] + "1" + baseCodeC[24]
                + baseCodeC[23] + baseCodeC[20] + "0" + baseCodeC[13] + baseCodeB[13] + "9"
                + baseCodeB[3] + "3" + baseCodeB[0] + baseCodeB[23] + baseCodeC[1] + baseCodeB[17]
                + baseCodeC[11] + "4" + baseCodeC[18];
        apiKeysForCn.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[1] + "64" + baseCodeB[24] + baseCodeC[23] + baseCodeB[12]
                + "7-" + baseCodeB[18] + baseCodeB[21] + "8" + baseCodeC[8]
                + baseCodeB[14] + "4" + baseCodeC[19] + baseCodeB[12] + baseCodeC[9] + baseCodeB[16]
                + baseCodeB[10] + baseCodeC[11] + baseCodeC[21] + baseCodeB[5] + baseCodeC[4] + baseCodeB[12]
                + baseCodeC[0] + baseCodeC[19] + baseCodeC[23] + "-" + baseCodeB[2] + baseCodeC[17]
                + baseCodeC[0] + "_0";
        apiKeysForCn.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[2] + baseCodeC[13] + "5" + baseCodeB[7] + "-" + baseCodeC[17]
                + baseCodeC[6] + baseCodeB[5] + baseCodeC[25] + baseCodeC[22] + baseCodeB[8] + baseCodeB[10]
                + baseCodeC[4] + baseCodeC[9] + "1" + baseCodeB[16] + "-" + baseCodeC[9]
                + baseCodeC[9] + "9" + baseCodeC[24] + baseCodeC[25] + baseCodeC[23] + baseCodeC[6]
                + "74" + baseCodeC[14] + "3" + baseCodeB[12] + baseCodeB[10]
                + baseCodeC[5] + baseCodeC[14] + baseCodeC[22];
        apiKeysForJp.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[1] + baseCodeB[15] + baseCodeB[3] + baseCodeC[3] + baseCodeC[12] + "1"
                + baseCodeB[18] + baseCodeC[25] + baseCodeC[1] + baseCodeC[5] + "46"
                + "5" + baseCodeB[1] + baseCodeC[5] + baseCodeC[3] + baseCodeC[0] + baseCodeC[0]
                + "0" + baseCodeC[16] + baseCodeB[18] + "8" + baseCodeB[5] + baseCodeC[2]
                + baseCodeB[12] + "5" + baseCodeB[15] + "6" + baseCodeC[13] + baseCodeB[8]
                + baseCodeC[1] + baseCodeC[17] + baseCodeB[0];
        apiKeysForJp.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[3] + "5" + baseCodeC[15] + baseCodeB[19] + "80"
                + baseCodeB[3] + baseCodeB[12] + baseCodeC[15] + "-" + baseCodeC[22] + baseCodeC[17]
                + baseCodeC[13] + baseCodeB[16] + baseCodeB[16] + baseCodeB[5] + baseCodeB[13] + baseCodeC[3]
                + "8" + baseCodeC[3] + baseCodeB[14] + baseCodeB[17] + baseCodeC[24] + baseCodeB[1]
                + "9" + baseCodeB[4] + "1" + baseCodeB[15] + baseCodeB[9] + "1"
                + "4" + baseCodeB[18] + "4";
        apiKeysForJp.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[2] + baseCodeC[20] + "0" + baseCodeB[15] + baseCodeC[16] + baseCodeB[10]
                + baseCodeC[1] + "9" + baseCodeC[4] + baseCodeB[15] + baseCodeB[4] + "5"
                + baseCodeC[0] + baseCodeB[1] + baseCodeC[20] + baseCodeB[22] + baseCodeC[11] + baseCodeC[23]
                + baseCodeC[25] + "9" + baseCodeB[23] + baseCodeB[14] + baseCodeC[13] + baseCodeB[24]
                + baseCodeC[6] + baseCodeB[6] + baseCodeC[18] + baseCodeB[22] + baseCodeB[16] + baseCodeB[4]
                + baseCodeB[15] + baseCodeB[23] + "4";
        apiKeysForJp.add(key);
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

    public void sendEditData(String spid) {
        final Map<String, String> map = NetworkUtil.getInstance().getMap();
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("spid", spid);
        if (MyApplication.getAppData().getIsEdit().get(0)) {
            JsonObject place = new JsonObject();
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    place.addProperty("place_tw", MyApplication.getAppData().getgInfoData().getData().get(0).getPlace());
                    if (MyApplication.getAppData().getIsEdit().get(1)) {
                        place.addProperty("place_ch", MyApplication.getAppData().getName().get("place_cn"));
                        place.addProperty("place_jp", MyApplication.getAppData().getName().get("place_jp"));
                        place.addProperty("place_en", MyApplication.getAppData().getName().get("place_en"));
                    }
                    break;
                case 2:
                    place.addProperty("place_ch", MyApplication.getAppData().getgInfoData().getData().get(0).getPlace());
                    if (MyApplication.getAppData().getIsEdit().get(1)) {
                        place.addProperty("place_tw", MyApplication.getAppData().getName().get("place_tw"));
                        place.addProperty("place_jp", MyApplication.getAppData().getName().get("place_jp"));
                        place.addProperty("place_en", MyApplication.getAppData().getName().get("place_en"));
                    }
                    break;
                case 3:
                    place.addProperty("place_jp", MyApplication.getAppData().getgInfoData().getData().get(0).getPlace());
                    if (MyApplication.getAppData().getIsEdit().get(1)) {
                        place.addProperty("place_ch", MyApplication.getAppData().getName().get("place_cn"));
                        place.addProperty("place_tw", MyApplication.getAppData().getName().get("place_tw"));
                        place.addProperty("place_en", MyApplication.getAppData().getName().get("place_en"));
                    }
                    break;
                default:
                    place.addProperty("place_en", MyApplication.getAppData().getgInfoData().getData().get(0).getPlace());
                    if (MyApplication.getAppData().getIsEdit().get(1)) {
                        place.addProperty("place_ch", MyApplication.getAppData().getName().get("place_cn"));
                        place.addProperty("place_jp", MyApplication.getAppData().getName().get("place_jp"));
                        place.addProperty("place_tw", MyApplication.getAppData().getName().get("place_tw"));
                    }
                    break;
            }
            jsonObject.add("place", place);
        }
        if (MyApplication.getAppData().getIsEdit().get(1)) {
            jsonObject.add("address", MyApplication.getAppData().getAddress());
            jsonObject.add("city", MyApplication.getAppData().getCity());
        }
        if (MyApplication.getAppData().getIsEdit().get(3)) {
            JsonObject editTime  = new JsonObject();
            editTime.addProperty("OpenBool", "0");
            JsonArray mon = new JsonArray();
            JsonArray tue = new JsonArray();
            JsonArray wed = new JsonArray();
            JsonArray thu = new JsonArray();
            JsonArray fri = new JsonArray();
            JsonArray sat = new JsonArray();
            JsonArray sun = new JsonArray();
            for (int i = 0; i < 4; i++) {
                mon.add(MyApplication.getAppData().getgInfoData().getData().get(0).getMon().get(i));
                tue.add(MyApplication.getAppData().getgInfoData().getData().get(0).getTue().get(i));
                wed.add(MyApplication.getAppData().getgInfoData().getData().get(0).getWed().get(i));
                thu.add(MyApplication.getAppData().getgInfoData().getData().get(0).getThu().get(i));
                fri.add(MyApplication.getAppData().getgInfoData().getData().get(0).getFri().get(i));
                sat.add(MyApplication.getAppData().getgInfoData().getData().get(0).getSat().get(i));
                sun.add(MyApplication.getAppData().getgInfoData().getData().get(0).getSun().get(i));
            }
            editTime.add("mon", mon);
            editTime.add("tue", tue);
            editTime.add("wed", wed);
            editTime.add("thu", thu);
            editTime.add("fri", fri);
            editTime.add("sat", sat);
            editTime.add("sun", sun);
            jsonObject.add("time", editTime);
        }
        if (MyApplication.getAppData().getIsEdit().get(4)) {
            jsonObject.addProperty("phone", MyApplication.getAppData().getgInfoData().getData().get(0).getPhone());
        }
        if (MyApplication.getAppData().getIsEdit().get(5)) {
            jsonObject.addProperty("website", MyApplication.getAppData().getgInfoData().getData().get(0).getWebsite());
        }
        if (MyApplication.getAppData().getIsEdit().get(6)) {
            jsonObject.addProperty("SwiTubeURL", MyApplication.getAppData().getTubeUrl());
        }
        if (MyApplication.getAppData().getIsEdit().get(7)) {
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    jsonObject.addProperty("info_tw", MyApplication.getAppData().getgInfoData().getData().get(0).getInfo());
                    break;
                case 2:
                    jsonObject.addProperty("info_ch", MyApplication.getAppData().getgInfoData().getData().get(0).getInfo());
                    break;
                case 3:
                    jsonObject.addProperty("info_jp", MyApplication.getAppData().getgInfoData().getData().get(0).getInfo());
                    break;
                default:
                    jsonObject.addProperty("info", MyApplication.getAppData().getgInfoData().getData().get(0).getInfo());
                    break;
            }
        }
        if (MyApplication.getAppData().getIsEdit().get(8)) {
            jsonObject.addProperty("photoCount", String.valueOf(MyApplication.getAppData().getgInfoData().getData().get(0).getPhoto().size()));
            final int size = MyApplication.getAppData().getSelectedPhotos().size();
            if (size > 0) {
                final Map<String, RequestBody> image = new HashMap<>();
                Luban.with(MyApplication.getInstance())
                        .load(MyApplication.getAppData().getSelectedPhotos())
                        .ignoreBy(100)
                        .setCompressListener(new OnCompressListener() {
                            @Override
                            public void onStart() { }

                            @Override
                            public void onSuccess(File file) {
                                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                                switch (image.size()) {
                                    case 0:
                                        image.put("img1" + "\"; filename=\"" + file.getName(), requestBody);
                                        if (image.size() == size) {
                                            map.put("value", jsonObject.toString());
                                            mEditAttraction.sendEditData(map, image);
                                        }
                                        break;
                                    case 1:
                                        image.put("img2" + "\"; filename=\"" + file.getName(), requestBody);
                                        if (image.size() == size) {
                                            map.put("value", jsonObject.toString());
                                            mEditAttraction.sendEditData(map, image);
                                        }
                                        break;
                                    case 2:
                                        image.put("img3" + "\"; filename=\"" + file.getName(), requestBody);
                                        if (image.size() == size) {
                                            map.put("value", jsonObject.toString());
                                            mEditAttraction.sendEditData(map, image);
                                        }
                                        break;
                                    case 3:
                                        image.put("img4" + "\"; filename=\"" + file.getName(), requestBody);
                                        if (image.size() == size) {
                                            map.put("value", jsonObject.toString());
                                            mEditAttraction.sendEditData(map, image);
                                        }
                                        break;
                                    case 4:
                                        image.put("img5" + "\"; filename=\"" + file.getName(), requestBody);
                                        if (image.size() == size) {
                                            map.put("value", jsonObject.toString());
                                            mEditAttraction.sendEditData(map, image);
                                        }
                                        break;
                                }
                            }

                            @Override
                            public void onError(Throwable e) {}
                        })
                        .launch();
            }
        } else {
            map.put("value", jsonObject.toString());
            mEditAttraction.sendEditData(map);
        }
    }

    private String mKeyA;
    public void getPlaceDetail(String id) {
        int index = random.nextInt(4);
        if (mKeyA == null) {
            mKeyA = apiKeysForEn.get(index);
        } else {
            if (apiKeysForEn.get(index).equals(mKeyA)) {
                mKeyA = apiKeysForEn.get(random.nextInt(4));
            } else {
                mKeyA = apiKeysForEn.get(index);
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("placeid", id);
        map.put("key", mKeyA);
        map.put("language", "en");
        mEditAttraction.getPlaceDetail(id, "en", map);
    }

    public void getPlaceId(LatLng latLng) {
        int index = new Random().nextInt(apiKeys.size());
        String builder = String.valueOf(latLng.latitude) + "," + String.valueOf(latLng.longitude);
        Map<String, String> map = new HashMap<>();
        map.put("latlng", builder);
        map.put("language", "zh-TW");
        map.put("key", apiKeys.get(index));
        mEditAttraction.getPlaceId(map);
    }

    public void sendDelPlaceData(String spid) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("spid", spid);
        Log.e("del place", spid);
        mEditAttraction.sendDelPlace(map);
    }

    private void handlePlaceData() {
        JsonObject address = new JsonObject();
        address.addProperty("address_en", detailEntities.get(0).getResult().getFormatted_address());
        address.addProperty("address_tw", detailEntities.get(1).getResult().getFormatted_address());
        address.addProperty("address_ch", detailEntities.get(2).getResult().getFormatted_address());
        address.addProperty("address_jp", detailEntities.get(3).getResult().getFormatted_address());
        address.addProperty("lat", detailEntities.get(0).getResult().getGeometry().getLocation().getLat());
        address.addProperty("lng", detailEntities.get(0).getResult().getGeometry().getLocation().getLng());
        address.addProperty("place_id", detailEntities.get(0).getResult().getPlace_id());
        address.addProperty("mapurl", detailEntities.get(0).getResult().getUrl());
        MyApplication.getAppData().setAddress(address);

        JsonObject city = new JsonObject();
        int size = detailEntities.get(0).getResult().getAddress_components().size();
        boolean hadOne = false;
        boolean hadTwo = false;
        boolean hadThree = false;
        for (int i = 0; i < size; i++) {
            switch (detailEntities.get(0).getResult().getAddress_components().get(i).getTypes()[0]) {
                case "administrative_area_level_1":
                    hadOne = true;
                    break;
                case "administrative_area_level_2":
                    hadTwo = true;
                    break;
                case "administrative_area_level_3":
                    hadThree = true;
                    break;
                default:
                    break;
            }
        }
        for (int i = 0; i < size; i++) {
            switch (detailEntities.get(0).getResult().getAddress_components().get(i).getTypes()[0]) {
                case "postal_code":
                    city.addProperty("city_id", detailEntities.get(0).getResult().getAddress_components().get(i).getShort_name());
                    break;
                case "country":
                    city.addProperty("country", detailEntities.get(0).getResult().getAddress_components().get(i).getShort_name());
                    break;
                case "administrative_area_level_1":
                    if (hadOne) {
                        city.addProperty("city_s_en", detailEntities.get(0).getResult().getAddress_components().get(i).getShort_name());
                        city.addProperty("city_l_en", detailEntities.get(0).getResult().getAddress_components().get(i).getLong_name());
                        city.addProperty("city_s_tw", detailEntities.get(1).getResult().getAddress_components().get(i).getShort_name());
                        city.addProperty("city_l_tw", detailEntities.get(1).getResult().getAddress_components().get(i).getLong_name());
                        city.addProperty("city_s_ch", detailEntities.get(2).getResult().getAddress_components().get(i).getShort_name());
                        city.addProperty("city_l_ch", detailEntities.get(2).getResult().getAddress_components().get(i).getLong_name());
                        city.addProperty("city_s_jp", detailEntities.get(3).getResult().getAddress_components().get(i).getShort_name());
                        city.addProperty("city_l_jp", detailEntities.get(3).getResult().getAddress_components().get(i).getLong_name());
                    }
                    break;
                case "administrative_area_level_2":
                    if (!hadOne && hadTwo) {
                        city.addProperty("city_s_en", detailEntities.get(0).getResult().getAddress_components().get(i).getShort_name());
                        city.addProperty("city_l_en", detailEntities.get(0).getResult().getAddress_components().get(i).getLong_name());
                        city.addProperty("city_s_tw", detailEntities.get(1).getResult().getAddress_components().get(i).getShort_name());
                        city.addProperty("city_l_tw", detailEntities.get(1).getResult().getAddress_components().get(i).getLong_name());
                        city.addProperty("city_s_ch", detailEntities.get(2).getResult().getAddress_components().get(i).getShort_name());
                        city.addProperty("city_l_ch", detailEntities.get(2).getResult().getAddress_components().get(i).getLong_name());
                        city.addProperty("city_s_jp", detailEntities.get(3).getResult().getAddress_components().get(i).getShort_name());
                        city.addProperty("city_l_jp", detailEntities.get(3).getResult().getAddress_components().get(i).getLong_name());
                    }
                    break;
                case "administrative_area_level_3":
                    if (!hadOne && !hadTwo && hadThree) {
                        city.addProperty("city_s_en", detailEntities.get(0).getResult().getAddress_components().get(i).getShort_name());
                        city.addProperty("city_l_en", detailEntities.get(0).getResult().getAddress_components().get(i).getLong_name());
                        city.addProperty("city_s_tw", detailEntities.get(1).getResult().getAddress_components().get(i).getShort_name());
                        city.addProperty("city_l_tw", detailEntities.get(1).getResult().getAddress_components().get(i).getLong_name());
                        city.addProperty("city_s_ch", detailEntities.get(2).getResult().getAddress_components().get(i).getShort_name());
                        city.addProperty("city_l_ch", detailEntities.get(2).getResult().getAddress_components().get(i).getLong_name());
                        city.addProperty("city_s_jp", detailEntities.get(3).getResult().getAddress_components().get(i).getShort_name());
                        city.addProperty("city_l_jp", detailEntities.get(3).getResult().getAddress_components().get(i).getLong_name());
                    }
                    break;
                case "locality":
                    if (!hadOne && !hadTwo && !hadThree) {
                        city.addProperty("city_s_en", detailEntities.get(0).getResult().getAddress_components().get(i).getShort_name());
                        city.addProperty("city_l_en", detailEntities.get(0).getResult().getAddress_components().get(i).getLong_name());
                        city.addProperty("city_s_tw", detailEntities.get(1).getResult().getAddress_components().get(i).getShort_name());
                        city.addProperty("city_l_tw", detailEntities.get(1).getResult().getAddress_components().get(i).getLong_name());
                        city.addProperty("city_s_ch", detailEntities.get(2).getResult().getAddress_components().get(i).getShort_name());
                        city.addProperty("city_l_ch", detailEntities.get(2).getResult().getAddress_components().get(i).getLong_name());
                        city.addProperty("city_s_jp", detailEntities.get(3).getResult().getAddress_components().get(i).getShort_name());
                        city.addProperty("city_l_jp", detailEntities.get(3).getResult().getAddress_components().get(i).getLong_name());
                    }
                    break;
                default:
                    break;
            }
        }
        MyApplication.getAppData().setCity(city);

        Map<String, String> name = new HashMap<>();
        name.put("place_en", detailEntities.get(0).getResult().getName());
        name.put("place_tw", detailEntities.get(1).getResult().getName());
        name.put("place_cn", detailEntities.get(2).getResult().getName());
        name.put("place_jp", detailEntities.get(3).getResult().getName());
        MyApplication.getAppData().setName(name);

        MyApplication.getAppData().getgInfoData().getData().get(0).setPlaceid(detailEntities.get(0).getResult().getPlace_id());
        if (detailEntities.get(0).getResult().getWebsite() != null) {
            MyApplication.getAppData().getgInfoData().getData().get(0).setWebsite(detailEntities.get(0).getResult().getWebsite());
        } else {
            MyApplication.getAppData().getgInfoData().getData().get(0).setWebsite("");
        }
        if (detailEntities.get(0).getResult().getFormatted_phone_number() != null) {
            MyApplication.getAppData().getgInfoData().getData().get(0).setPhone(detailEntities.get(0).getResult().getFormatted_phone_number());
        } else {
            MyApplication.getAppData().getgInfoData().getData().get(0).setPhone("");
        }
        switch (MyApplication.getLanguageIndex()) {
            case 1:
                MyApplication.getAppData().getgInfoData().getData().get(0).setAddr(detailEntities.get(1).getResult().getFormatted_address());
                MyApplication.getAppData().getgInfoData().getData().get(0).setPlace(detailEntities.get(1).getResult().getName());
                break;
            case 2:
                MyApplication.getAppData().getgInfoData().getData().get(0).setAddr(detailEntities.get(2).getResult().getFormatted_address());
                MyApplication.getAppData().getgInfoData().getData().get(0).setPlace(detailEntities.get(2).getResult().getName());
                break;
            case 3:
                MyApplication.getAppData().getgInfoData().getData().get(0).setAddr(detailEntities.get(3).getResult().getFormatted_address());
                MyApplication.getAppData().getgInfoData().getData().get(0).setPlace(detailEntities.get(3).getResult().getName());
                break;
            default:
                MyApplication.getAppData().getgInfoData().getData().get(0).setAddr(detailEntities.get(0).getResult().getFormatted_address());
                MyApplication.getAppData().getgInfoData().getData().get(0).setPlace(detailEntities.get(0).getResult().getName());
                break;
        }
        MyApplication.getAppData().getgInfoData().getData().get(0).setInfo("");
        if (MyApplication.getAppData().getgInfoData().getData().get(0).getTubeWeb().size() > 0) {
            MyApplication.getAppData().getgInfoData().getData().get(0).getTubeWeb().get(0).setUrl("");
        }
        MyApplication.getAppData().setTubeUrl("");
        List<String> time1 = new ArrayList<>();
        List<String> time2 = new ArrayList<>();
        List<String> time3 = new ArrayList<>();
        List<String> time4 = new ArrayList<>();
        List<String> time5 = new ArrayList<>();
        List<String> time6 = new ArrayList<>();
        List<String> time7 = new ArrayList<>();
        if (detailEntities.get(0).getResult().getOpening_hours() != null) {
            if (detailEntities.get(0).getResult().getOpening_hours().getPeriods().get(0).getOpen().getTime().equals("0000")) {
                time1.add("24");
                time1.add("");
                time1.add("");
                time1.add("");
                time2.add("24");
                time2.add("");
                time2.add("");
                time2.add("");
                time3.add("24");
                time3.add("");
                time3.add("");
                time3.add("");
                time4.add("24");
                time4.add("");
                time4.add("");
                time4.add("");
                time5.add("24");
                time5.add("");
                time5.add("");
                time5.add("");
                time6.add("24");
                time6.add("");
                time6.add("");
                time6.add("");
                time7.add("24");
                time7.add("");
                time7.add("");
                time7.add("");
            } else {
                size = detailEntities.get(0).getResult().getOpening_hours().getPeriods().size();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < size; i++) {
                    stringBuilder.delete(0, stringBuilder.length());
                    stringBuilder.append(detailEntities.get(0).getResult().getOpening_hours().getPeriods().get(i).getOpen().getTime());
                    stringBuilder.insert(2, ":");
                    stringBuilder.append("-");
                    stringBuilder.append(detailEntities.get(0).getResult().getOpening_hours().getPeriods().get(i).getClose().getTime());
                    stringBuilder.insert(8, ":");
                    switch (detailEntities.get(0).getResult().getOpening_hours().getPeriods().get(i).getOpen().getDay()) {
                        case 0:
                            time1.add(stringBuilder.toString());
                            break;
                        case 1:
                            time2.add(stringBuilder.toString());
                            break;
                        case 2:
                            time3.add(stringBuilder.toString());
                            break;
                        case 3:
                            time4.add(stringBuilder.toString());
                            break;
                        case 4:
                            time5.add(stringBuilder.toString());
                            break;
                        case 5:
                            time6.add(stringBuilder.toString());
                            break;
                        case 6:
                            time7.add(stringBuilder.toString());
                            break;
                        default:
                            break;
                    }
                }
                size = time1.size();
                if (size < 4) {
                    int count = 4 - size;
                    for (int j = 0; j < count; j++) {
                        time1.add("");

                    }
                }
                size = time2.size();
                if (size < 4) {
                    int count = 4 - size;
                    for (int j = 0; j < count; j++) {
                        time2.add("");

                    }
                }
                size = time3.size();
                if (size < 4) {
                    int count = 4 - size;
                    for (int j = 0; j < count; j++) {
                        time3.add("");

                    }
                }
                size = time4.size();
                if (size < 4) {
                    int count = 4 - size;
                    for (int j = 0; j < count; j++) {
                        time4.add("");

                    }
                }
                size = time5.size();
                if (size < 4) {
                    int count = 4 - size;
                    for (int j = 0; j < count; j++) {
                        time5.add("");

                    }
                }
                size = time6.size();
                if (size < 4) {
                    int count = 4 - size;
                    for (int j = 0; j < count; j++) {
                        time6.add("");

                    }
                }
                size = time7.size();
                if (size < 4) {
                    int count = 4 - size;
                    for (int j = 0; j < count; j++) {
                        time7.add("");

                    }
                }
            }
            MyApplication.getAppData().getgInfoData().getData().get(0).setSun(time1);
            MyApplication.getAppData().getgInfoData().getData().get(0).setMon(time2);
            MyApplication.getAppData().getgInfoData().getData().get(0).setTue(time3);
            MyApplication.getAppData().getgInfoData().getData().get(0).setWed(time4);
            MyApplication.getAppData().getgInfoData().getData().get(0).setThu(time5);
            MyApplication.getAppData().getgInfoData().getData().get(0).setFri(time6);
            MyApplication.getAppData().getgInfoData().getData().get(0).setSat(time7);
        } else {
            time1.add("");
            time1.add("");
            time1.add("");
            time1.add("");
            time2.add("");
            time2.add("");
            time2.add("");
            time2.add("");
            time3.add("");
            time3.add("");
            time3.add("");
            time3.add("");
            time4.add("");
            time4.add("");
            time4.add("");
            time4.add("");
            time5.add("");
            time5.add("");
            time5.add("");
            time5.add("");
            time6.add("");
            time6.add("");
            time6.add("");
            time6.add("");
            time7.add("");
            time7.add("");
            time7.add("");
            time7.add("");
            MyApplication.getAppData().getgInfoData().getData().get(0).setSun(time1);
            MyApplication.getAppData().getgInfoData().getData().get(0).setMon(time2);
            MyApplication.getAppData().getgInfoData().getData().get(0).setTue(time3);
            MyApplication.getAppData().getgInfoData().getData().get(0).setWed(time4);
            MyApplication.getAppData().getgInfoData().getData().get(0).setThu(time5);
            MyApplication.getAppData().getgInfoData().getData().get(0).setFri(time6);
            MyApplication.getAppData().getgInfoData().getData().get(0).setSat(time7);
        }
        ivEditAttraction.handleNewPlace();
    }

    @Override
    public void handleResult() {
        ivEditAttraction.finishSend();
    }

    private List<GAttractionDataGoogle> detailEntities = new ArrayList<>();
    private String mKeyB;
    @Override
    public void handlePlaceDetailEN(GAttractionDataGoogle gAttractionDataGoogle, String id) {
        detailEntities.clear();
        detailEntities.add(gAttractionDataGoogle);
        int index = random.nextInt(4);
        if (mKeyB == null) {
            mKeyB = apiKeysForTw.get(index);
        } else {
            if (apiKeysForTw.get(index).equals(mKeyB)) {
                mKeyB = apiKeysForTw.get(random.nextInt(4));
            } else {
                mKeyB = apiKeysForTw.get(index);
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("placeid", id);
        map.put("key", mKeyB);
        map.put("language", "zh-TW");
        mEditAttraction.getPlaceDetail(id, "zh-TW", map);
    }

    private String mKeyC;
    @Override
    public void handlePlaceDetailTW(GAttractionDataGoogle gAttractionDataGoogle, String id) {
        detailEntities.add(gAttractionDataGoogle);
        int index = random.nextInt(4);
        if (mKeyC == null) {
            mKeyC = apiKeysForCn.get(index);
        } else {
            if (apiKeysForCn.get(index).equals(mKeyC)) {
                mKeyC = apiKeysForCn.get(random.nextInt(4));
            } else {
                mKeyC = apiKeysForCn.get(index);
            }
        }
        final Map<String, String> map = new HashMap<>();
        map.put("placeid", id);
        map.put("key", mKeyC);
        map.put("language", "zh-CN");
        mEditAttraction.getPlaceDetail(id, "zh-CN", map);
    }

    private String mKeyD;
    @Override
    public void handlePlaceDetailCN(GAttractionDataGoogle gAttractionDataGoogle, String id) {
        detailEntities.add(gAttractionDataGoogle);
        int index = random.nextInt(4);
        if (mKeyD == null) {
            mKeyD = apiKeysForJp.get(index);
        } else {
            if (apiKeysForJp.get(index).equals(mKeyD)) {
                mKeyD = apiKeysForJp.get(random.nextInt(4));
            } else {
                mKeyD = apiKeysForJp.get(index);
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("placeid", id);
        map.put("key", mKeyD);
        map.put("language", "jp");
        mEditAttraction.getPlaceDetail(id, "jp", map);
    }

    @Override
    public void handlePlaceDetailJP(GAttractionDataGoogle gAttractionDataGoogle) {
        detailEntities.add(gAttractionDataGoogle);
        handlePlaceData();
    }

    @Override
    public void handlePlaceId(GPlaceIdData gPlaceIdData) {
        ivEditAttraction.handleNewPlaceId(gPlaceIdData.getResults().get(0).getPlace_id());
        getPlaceDetail(gPlaceIdData.getResults().get(0).getPlace_id());
    }

    @Override
    public void finishDelPlace(GSendLove gSendLove) {
        if (gSendLove.getSave().equals("1")) {
            ivEditAttraction.finishDelPlace();
        }
    }
}
