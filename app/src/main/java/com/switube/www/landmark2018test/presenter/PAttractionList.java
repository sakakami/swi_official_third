package com.switube.www.landmark2018test.presenter;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.switube.www.landmark2018test.gson.GAttractionListData;
import com.switube.www.landmark2018test.gson.GPlaceIdData;
import com.switube.www.landmark2018test.gson.SearchPlaceDataGson;
import com.switube.www.landmark2018test.model.MAttractionList;
import com.switube.www.landmark2018test.presenter.callback.IPAttractionList;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVAttractionList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PAttractionList implements IPAttractionList {
    private IVAttractionList ivAttractionList;
    private MAttractionList mAttractionList;
    private List<String> apiKeys = new ArrayList<>();
    public PAttractionList(IVAttractionList IVAttractionList) {
        ivAttractionList = IVAttractionList;
        mAttractionList = new MAttractionList(this);
        String[] baseCodeB = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] baseCodeC = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
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
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[2] + baseCodeB[5] + "6" + baseCodeB[6] + baseCodeC[15] + baseCodeC[15]
                + baseCodeB[9] + baseCodeC[5] + baseCodeC[25] + baseCodeB[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[17] + baseCodeB[17] + baseCodeC[15] + baseCodeB[14] + "5" + baseCodeB[9]
                + baseCodeC[20] + baseCodeC[1] + baseCodeB[15] + baseCodeC[18] + baseCodeC[23] + baseCodeB[14]
                + baseCodeB[10] + baseCodeC[19] + baseCodeC[1] + baseCodeB[20] + "2" + baseCodeC[16]
                + baseCodeB[10] + baseCodeB[7] + baseCodeB[4];
        apiKeys.add(key);
        key = baseCodeB[0] + baseCodeB[8] + baseCodeC[25] + baseCodeC[0] + baseCodeB[18] + baseCodeC[24]
                + baseCodeB[2] + "-7" + baseCodeB[8] + baseCodeC[25] + baseCodeC[19]
                + "0" + baseCodeC[20] + baseCodeC[16] + baseCodeB[24] + baseCodeC[24] + baseCodeB[19]
                + baseCodeB[21] + baseCodeC[25] + baseCodeB[14] + baseCodeB[8] + baseCodeC[18] + baseCodeB[19]
                + baseCodeB[3] + baseCodeB[20] + baseCodeB[5] + baseCodeC[20] + "3" + baseCodeC[1]
                + baseCodeB[18] + baseCodeB[9] + "87" + baseCodeC[18] + baseCodeB[20]
                + baseCodeC[11] + baseCodeB[7] + baseCodeB[12];
        apiKeys.add(key);
    }

    public void getAllAttractionData(LatLng latLng) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        SearchPlaceDataGson searchPlaceDataGson = new SearchPlaceDataGson("xxx", new ArrayList<>(), new ArrayList<>());
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String value = gson.toJson(searchPlaceDataGson);
        map.put("value", value);
        mAttractionList.getAllAttractionData(map, latLng);
    }

    public void getPlaceData(LatLng latLng) {
        int index = new Random().nextInt(apiKeys.size());
        String builder = latLng.latitude + "," + latLng.longitude;
        Map<String, String> map = new HashMap<>();
        map.put("latlng", builder);
        map.put("language", "zh-TW");
        map.put("key", apiKeys.get(index));
        mAttractionList.handleGetPlaceId(map);
    }

    public void handleSearch(String keyword, GAttractionListData gAttractionListData, List<String> distance) {
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        StringBuilder stringBuilder = new StringBuilder(keyword);
        stringBuilder.delete(1, stringBuilder.length());
        Matcher matcher = pattern.matcher(stringBuilder.toString());
        if (matcher.matches()) {
            if (keyword.length() >= 2) {
                for (int i = 0; i < gAttractionListData.getData().size(); i++) {
                    if (!gAttractionListData.getData().get(i).getPlace().contains(keyword)) {
                        gAttractionListData.getData().remove(i);
                        distance.remove(i);
                        i--;
                    }
                }
            }
        } else {
            if (keyword.length() >= 4) {
                for (int i = 0; i < gAttractionListData.getData().size(); i++) {
                    if (!gAttractionListData.getData().get(i).getPlace().toLowerCase().contains(keyword.toLowerCase())) {
                        gAttractionListData.getData().remove(i);
                        distance.remove(i);
                        i--;
                    }
                }
            }
        }
        ivAttractionList.refreshAdapter(gAttractionListData, distance);
    }

    @Override
    public void handleParseGsonData(GAttractionListData gAttractionListData, LatLng latLng) {
        List<String> distance = new ArrayList<>();
        for (int i = 0; i < gAttractionListData.getData().size(); i++) {
            float[] dis = new float[1];
            if (gAttractionListData.getData().get(i).getLat() == null) {
                gAttractionListData.getData().remove(i);
                i--;
            } else {
                Location.distanceBetween(latLng.latitude, latLng.longitude,
                        Double.parseDouble(gAttractionListData.getData().get(i).getLat()),
                        Double.parseDouble(gAttractionListData.getData().get(i).getLng()), dis);
                if (dis[0] > 5000) {
                    gAttractionListData.getData().remove(i);
                    i--;
                } else {
                    distance.add(String.format(Locale.TAIWAN, "%.2f", dis[0]) + " m");
                }
            }
        }
        ivAttractionList.init(gAttractionListData, distance);
    }

    @Override
    public void handleSavePlaceId(GPlaceIdData gPlaceIdData) {
        ivAttractionList.savePlaceId(gPlaceIdData.getResults().get(0).getPlace_id());
    }
}
