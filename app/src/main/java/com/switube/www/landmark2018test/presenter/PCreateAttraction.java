package com.switube.www.landmark2018test.presenter;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.switube.www.landmark2018test.entity.ECreateAttraction;
import com.switube.www.landmark2018test.gson.GAttractionDataGoogle;
import com.switube.www.landmark2018test.gson.GPlaceIdData;
import com.switube.www.landmark2018test.model.MCreateAttraction;
import com.switube.www.landmark2018test.presenter.callback.IPCreateAttraction;
import com.switube.www.landmark2018test.view.callback.IVCreateAttraction;
import com.switube.www.landmark2018test.view.callback.IMainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PCreateAttraction implements IPCreateAttraction {
    private IVCreateAttraction ivCreateAttraction;
    private MCreateAttraction mCreateAttraction;
    private List<String> apiKeysForEn = new ArrayList<>();
    private List<String> apiKeysForTw = new ArrayList<>();
    private List<String> apiKeysForCn = new ArrayList<>();
    private List<String> apiKeysForJp = new ArrayList<>();
    private List<String> apiKeys = new ArrayList<>();
    private String[] baseCodeB = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private String[] baseCodeC = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public PCreateAttraction(IVCreateAttraction IVCreateAttraction) {
        ivCreateAttraction = IVCreateAttraction;
        mCreateAttraction = new MCreateAttraction(this);
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

    private static String mKeyA;
    private Random random = new Random();
    public void getAttractionDetail(String id) {
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
        mCreateAttraction.getAttractionDetail(id, "en", map);
    }

    private ECreateAttraction entity = new ECreateAttraction();
    public void init(List<GAttractionDataGoogle> detailEntities, IMainActivity iMainActivity) {
        boolean hasData = true;
        for (int i = 0; i < 4; i++) {
            if (detailEntities.get(i).getResult() == null) {
                hasData = false;
            }
        }
        if (hasData) {
            entity.setPlace_id(iMainActivity.getPlaceId());
            entity.setMapurl(detailEntities.get(0).getResult().getUrl());
            entity.setLat(detailEntities.get(0).getResult().getGeometry().getLocation().getLat());
            entity.setLng(detailEntities.get(0).getResult().getGeometry().getLocation().getLng());
            entity.setPlace(new ECreateAttraction.Name(
                    detailEntities.get(0).getResult().getName(), detailEntities.get(1).getResult().getName(),
                    detailEntities.get(2).getResult().getName(), detailEntities.get(3).getResult().getName()));
            entity.setAddress(new ECreateAttraction.Address(
                    detailEntities.get(0).getResult().getFormatted_address(), detailEntities.get(1).getResult().getFormatted_address(),
                    detailEntities.get(2).getResult().getFormatted_address(), detailEntities.get(3).getResult().getFormatted_address()));
            if (detailEntities.get(0).getResult().getFormatted_phone_number() != null) {
                entity.setPhone(detailEntities.get(0).getResult().getFormatted_phone_number());
            }
            if (detailEntities.get(0).getResult().getWebsite() != null) {
                entity.setWebsite(detailEntities.get(0).getResult().getWebsite());
            }
            if (detailEntities.get(0).getResult().getRating() != null) {
                entity.setRating(detailEntities.get(0).getResult().getRating());
            }
            if (detailEntities.get(0).getResult().getPhotos() != null) {
                int size = detailEntities.get(0).getResult().getPhotos().size();
                String base = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1800&photoreference=";
                if (size >= 5) {
                    size = 5;
                }
                List<String> photos = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    String result = base + detailEntities.get(0).getResult().getPhotos().get(i).getPhoto_reference();
                    photos.add(result);
                }
                entity.setDefaultPhoto(photos);
            } else {
                entity.setDefaultPhoto(new ArrayList<String>());
            }
            if (detailEntities.get(0).getResult().getAddress_components().size() > 0) {
                int size = detailEntities.get(0).getResult().getAddress_components().size();
                String postal = "";
                String country = "";
                for (int i = 0; i < size; i++) {
                    switch (detailEntities.get(0).getResult().getAddress_components().get(i).getTypes()[0]) {
                        case "administrative_area_level_1":
                            entity.setCity(new ECreateAttraction.LocationCity(
                                    detailEntities.get(0).getResult().getAddress_components().get(i).getLong_name(),
                                    detailEntities.get(0).getResult().getAddress_components().get(i).getShort_name(),
                                    detailEntities.get(1).getResult().getAddress_components().get(i).getLong_name(),
                                    detailEntities.get(1).getResult().getAddress_components().get(i).getShort_name(),
                                    detailEntities.get(2).getResult().getAddress_components().get(i).getLong_name(),
                                    detailEntities.get(2).getResult().getAddress_components().get(i).getShort_name(),
                                    detailEntities.get(3).getResult().getAddress_components().get(i).getLong_name(),
                                    detailEntities.get(3).getResult().getAddress_components().get(i).getShort_name()));
                            break;
                        case "administrative_area_level_2":
                            entity.setCity(new ECreateAttraction.LocationCity(
                                    detailEntities.get(0).getResult().getAddress_components().get(i).getLong_name(),
                                    detailEntities.get(0).getResult().getAddress_components().get(i).getShort_name(),
                                    detailEntities.get(1).getResult().getAddress_components().get(i).getLong_name(),
                                    detailEntities.get(1).getResult().getAddress_components().get(i).getShort_name(),
                                    detailEntities.get(2).getResult().getAddress_components().get(i).getLong_name(),
                                    detailEntities.get(2).getResult().getAddress_components().get(i).getShort_name(),
                                    detailEntities.get(3).getResult().getAddress_components().get(i).getLong_name(),
                                    detailEntities.get(3).getResult().getAddress_components().get(i).getShort_name()));
                            break;
                        case "administrative_area_level_3":
                            entity.setCity(new ECreateAttraction.LocationCity(
                                    detailEntities.get(0).getResult().getAddress_components().get(i).getLong_name(),
                                    detailEntities.get(0).getResult().getAddress_components().get(i).getShort_name(),
                                    detailEntities.get(1).getResult().getAddress_components().get(i).getLong_name(),
                                    detailEntities.get(1).getResult().getAddress_components().get(i).getShort_name(),
                                    detailEntities.get(2).getResult().getAddress_components().get(i).getLong_name(),
                                    detailEntities.get(2).getResult().getAddress_components().get(i).getShort_name(),
                                    detailEntities.get(3).getResult().getAddress_components().get(i).getLong_name(),
                                    detailEntities.get(3).getResult().getAddress_components().get(i).getShort_name()));
                            break;
                        case "locality":
                            entity.setCity(new ECreateAttraction.LocationCity(
                                    detailEntities.get(0).getResult().getAddress_components().get(i).getLong_name(),
                                    detailEntities.get(0).getResult().getAddress_components().get(i).getShort_name(),
                                    detailEntities.get(1).getResult().getAddress_components().get(i).getLong_name(),
                                    detailEntities.get(1).getResult().getAddress_components().get(i).getShort_name(),
                                    detailEntities.get(2).getResult().getAddress_components().get(i).getLong_name(),
                                    detailEntities.get(2).getResult().getAddress_components().get(i).getShort_name(),
                                    detailEntities.get(3).getResult().getAddress_components().get(i).getLong_name(),
                                    detailEntities.get(3).getResult().getAddress_components().get(i).getShort_name()));
                            break;
                        case "country":
                            country = detailEntities.get(0).getResult().getAddress_components().get(i).getShort_name();
                            break;
                        case "postal_code":
                            postal = detailEntities.get(0).getResult().getAddress_components().get(i).getLong_name();
                            break;
                    }
                }
                StringBuilder stringBuilder = new StringBuilder(postal);
                if (stringBuilder.length() > 3) {
                    stringBuilder.delete(3, stringBuilder.length());
                }
                entity.getCity().setCity_id(stringBuilder.toString());
                entity.getCity().setCountry(country);
            }
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
                    int size = detailEntities.get(0).getResult().getOpening_hours().getPeriods().size();
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
                entity.setTime(new ECreateAttraction.Time("0", time1, time2, time3, time4, time5, time6, time7));
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
                entity.setTime(new ECreateAttraction.Time("1", time1, time2, time3, time4, time5, time6, time7));
            }
            ivCreateAttraction.init(entity);
        }
    }

    public void getPlaceData(LatLng latLng) {
        int index = new Random().nextInt(apiKeys.size());
        String builder = String.valueOf(latLng.latitude) + "," + String.valueOf(latLng.longitude);
        Map<String, String> map = new HashMap<>();
        map.put("latlng", builder);
        map.put("language", "zh-TW");
        map.put("key", apiKeys.get(index));
        mCreateAttraction.handleGetPlaceId(map);
    }

    private List<GAttractionDataGoogle> detailEntities = new ArrayList<>();
    private static String mKeyB;
    @Override
    public void handleAttractionDetailOne(String id, GAttractionDataGoogle gAttractionDataGoogle) {
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
        mCreateAttraction.getAttractionDetail(id, "zh-TW", map);
    }

    private static String mKeyC;
    @Override
    public void handleAttractionDetailTwo(final String id, GAttractionDataGoogle gAttractionDataGoogle) {
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
        mCreateAttraction.getAttractionDetail(id, "zh-CN", map);
    }

    private static String mKeyD;
    @Override
    public void handleAttractionDetailThree(String id, GAttractionDataGoogle gAttractionDataGoogle) {
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
        mCreateAttraction.getAttractionDetail(id, "jp", map);
    }

    @Override
    public void handleAttractionDetail(GAttractionDataGoogle gAttractionDataGoogle) {
        detailEntities.add(gAttractionDataGoogle);
        ivCreateAttraction.init(detailEntities);
    }

    @Override
    public void handleSavePlaceId(GPlaceIdData gPlaceIdData) {
        ivCreateAttraction.savePlaceId(gPlaceIdData.getResults().get(0).getPlace_id());
    }
}
