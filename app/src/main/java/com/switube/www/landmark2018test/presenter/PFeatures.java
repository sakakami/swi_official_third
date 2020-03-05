package com.switube.www.landmark2018test.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.entity.ECreateAttraction;
import com.switube.www.landmark2018test.entity.EFeatures;
import com.switube.www.landmark2018test.gson.GCreateAttraction;
import com.switube.www.landmark2018test.model.MFeatures;
import com.switube.www.landmark2018test.presenter.callback.IPFeatures;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVFeatures;
import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class PFeatures implements IPFeatures {
    private IVFeatures ivFeatures;
    private MFeatures mFeatures;
    public PFeatures(IVFeatures IVFeatures) {
        ivFeatures = IVFeatures;
        mFeatures = new MFeatures(this);
    }

    private String mSid;
    public void getDataOne(String msid) {
        mSid = msid;
        mFeatures.getDataOne(msid);
    }

    public void sendEditData(List<Boolean> isEdit, String spid, List<EFeatures> eFeaturesList) {
        final Map<String, String> map = NetworkUtil.getInstance().getMap();
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("spid", spid);
        if (isEdit.get(0)) {
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
        if (isEdit.get(1)) {
            jsonObject.add("address", MyApplication.getAppData().getAddress());
            jsonObject.add("city", MyApplication.getAppData().getCity());
        }
        if (isEdit.get(2)) {
            JsonObject style = new JsonObject();
            style.addProperty("mmid", MyApplication.getAppData().getgInfoData().getData().get(0).getMmid());
            style.addProperty("mmspid", MyApplication.getAppData().getgInfoData().getData().get(0).getMmspid());
            style.addProperty("msid", MyApplication.getAppData().getgInfoData().getData().get(0).getMsid());
            jsonObject.add("mStyle", style);
            JsonObject mClass = new JsonObject();
            JsonArray term = new JsonArray();
            JsonObject itemOne = new JsonObject();
            JsonObject itemTwo = new JsonObject();
            JsonObject itemThree = new JsonObject();
            JsonArray itemOneA = new JsonArray();
            JsonArray itemTwoA = new JsonArray();
            JsonArray itemThreeA = new JsonArray();
            JsonObject item;
            int j = 0;
            int size = eFeaturesList.size();
            for (int i = 0; i < size; i++) {
                switch (eFeaturesList.get(i).getFrom()) {
                    case "class":
                        if (eFeaturesList.get(i).isSelect()) {
                            mClass.addProperty("mcid", eFeaturesList.get(i).getId2());
                            mClass.addProperty("mscid", eFeaturesList.get(i).getId());
                        }
                        break;
                    case "term":
                        if (j == 0) {
                            itemOne.addProperty("mtid", eFeaturesList.get(i).getId());
                            j++;
                        } else if (j == 1) {
                            itemTwo.addProperty("mtid", eFeaturesList.get(i).getId());
                            j++;
                        } else if (j == 2) {
                            itemThree.addProperty("mtid", eFeaturesList.get(i).getId());
                            j++;
                        }
                        break;
                    case "item0":
                        if (eFeaturesList.get(i).isSelect()) {
                            item = new JsonObject();
                            item.addProperty("miid", eFeaturesList.get(i).getId2());
                            item.addProperty("mstid", eFeaturesList.get(i).getId());
                            itemOneA.add(item);
                        }
                        break;
                    case "item1":
                        if (eFeaturesList.get(i).isSelect()) {
                            item = new JsonObject();
                            item.addProperty("miid", eFeaturesList.get(i).getId2());
                            item.addProperty("mstid", eFeaturesList.get(i).getId());
                            itemTwoA.add(item);
                        }
                        break;
                    case "item2":
                        if (eFeaturesList.get(i).isSelect()) {
                            item = new JsonObject();
                            item.addProperty("miid", eFeaturesList.get(i).getId2());
                            item.addProperty("mstid", eFeaturesList.get(i).getId());
                            itemThreeA.add(item);
                        }
                        break;
                    default:
                        break;
                }
            }
            jsonObject.add("mClass", mClass);
            itemOne.add("item", itemOneA);
            itemTwo.add("item", itemTwoA);
            itemThree.add("item", itemThreeA);
            term.add(itemOne);
            term.add(itemTwo);
            term.add(itemThree);
            jsonObject.add("mTerm", term);
        }
        if (isEdit.get(3)) {
            JsonObject editTime  = new JsonObject();
            editTime.addProperty("OepnBool", "0");
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
        if (isEdit.get(4)) {
            jsonObject.addProperty("phone", MyApplication.getAppData().getgInfoData().getData().get(0).getPhone());
        }
        if (isEdit.get(5)) {
            jsonObject.addProperty("website", MyApplication.getAppData().getgInfoData().getData().get(0).getWebsite());
        }
        if (isEdit.get(6)) {
            jsonObject.addProperty("SwiTubeURL", MyApplication.getAppData().getTubeUrl());
        }
        if (isEdit.get(7)) {
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
        if (isEdit.get(8)) {
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
                                            mFeatures.sendEditData(map, image);
                                        }
                                        break;
                                    case 1:
                                        image.put("img2" + "\"; filename=\"" + file.getName(), requestBody);
                                        if (image.size() == size) {
                                            map.put("value", jsonObject.toString());
                                            mFeatures.sendEditData(map, image);
                                        }
                                        break;
                                    case 2:
                                        image.put("img3" + "\"; filename=\"" + file.getName(), requestBody);
                                        if (image.size() == size) {
                                            map.put("value", jsonObject.toString());
                                            mFeatures.sendEditData(map, image);
                                        }
                                        break;
                                    case 3:
                                        image.put("img4" + "\"; filename=\"" + file.getName(), requestBody);
                                        if (image.size() == size) {
                                            map.put("value", jsonObject.toString());
                                            mFeatures.sendEditData(map, image);
                                        }
                                        break;
                                    case 4:
                                        image.put("img5" + "\"; filename=\"" + file.getName(), requestBody);
                                        if (image.size() == size) {
                                            map.put("value", jsonObject.toString());
                                            mFeatures.sendEditData(map, image);
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
            mFeatures.sendEditData(map);
        }
    }

    public void sendCreateData(ECreateAttraction attractionEntity,
                               List<EFeatures> itemEntities,
                               AttractionStyleEntity attractionStyleEntity, List<String> photos) {
        attractionEntity.setPhotoCount(String.valueOf(photos.size()));
        List<ECreateAttraction.Item> itemOne = new ArrayList<>();
        List<ECreateAttraction.Item> itemTwo = new ArrayList<>();
        List<ECreateAttraction.Item> itemThree = new ArrayList<>();
        List<String> term = new ArrayList<>();
        int size = itemEntities.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                if (itemEntities.get(i).isSelect()) {
                    switch (itemEntities.get(i).getFrom()) {
                        case "class":
                            attractionEntity.setmClass(new ECreateAttraction.Class(itemEntities.get(i).getId(), itemEntities.get(i).getId2()));
                            break;
                        case "item0":
                            itemOne.add(new ECreateAttraction.Item(itemEntities.get(i).getId(), itemEntities.get(i).getId2()));
                            break;
                        case "item1":
                            itemTwo.add(new ECreateAttraction.Item(itemEntities.get(i).getId(), itemEntities.get(i).getId2()));
                            break;
                        case "item2":
                            itemThree.add(new ECreateAttraction.Item(itemEntities.get(i).getId(), itemEntities.get(i).getId2()));
                            break;
                        default:
                            break;
                    }
                } else {
                    if (itemEntities.get(i).getFrom().equals("term")) {
                        term.add(itemEntities.get(i).getId());
                    }
                }
            }
        }
        List<ECreateAttraction.Term> terms = new ArrayList<>();
        if (term.size() != 0) {
            switch (term.size()) {
                case 1:
                    terms.add(new ECreateAttraction.Term(term.get(0), itemOne));
                    terms.add(new ECreateAttraction.Term("", itemTwo));
                    terms.add(new ECreateAttraction.Term("", itemThree));
                    break;
                case 2:
                    terms.add(new ECreateAttraction.Term(term.get(0), itemOne));
                    terms.add(new ECreateAttraction.Term(term.get(1), itemTwo));
                    terms.add(new ECreateAttraction.Term("", itemThree));
                    break;
                case 3:
                    terms.add(new ECreateAttraction.Term(term.get(0), itemOne));
                    terms.add(new ECreateAttraction.Term(term.get(1), itemTwo));
                    terms.add(new ECreateAttraction.Term(term.get(2), itemThree));
                    break;
                default:
                    break;
            }
        } else {
            terms.add(new ECreateAttraction.Term("", itemOne));
            terms.add(new ECreateAttraction.Term("", itemTwo));
            terms.add(new ECreateAttraction.Term("", itemThree));
        }
        attractionEntity.setmTerm(terms);
        attractionEntity.setmStyle(new ECreateAttraction.Style(attractionStyleEntity.getMmspid(), attractionStyleEntity.getMmid(), attractionStyleEntity.getMsid()));
        sendData(photos, attractionEntity);
    }

    private Map<String, RequestBody> image = new HashMap<>();
    private void sendData(List<String> photos, final ECreateAttraction attractionEntity) {
        final int size = photos.size();
        if (size > 0) {
            Luban.with(MyApplication.getInstance())
                    .load(photos)
                    .ignoreBy(100)
                    .setCompressListener(new OnCompressListener() {
                        @Override
                        public void onStart() {}

                        @Override
                        public void onSuccess(File file) {
                            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                            switch (image.size()) {
                                case 0:
                                    image.put("img1" + "\"; filename=\"" + file.getName(), requestBody);
                                    if (image.size() == size) {
                                        send(attractionEntity, image);
                                    }
                                    break;
                                case 1:
                                    image.put("img2" + "\"; filename=\"" + file.getName(), requestBody);
                                    if (image.size() == size) {
                                        send(attractionEntity, image);
                                    }
                                    break;
                                case 2:
                                    image.put("img3" + "\"; filename=\"" + file.getName(), requestBody);
                                    if (image.size() == size) {
                                        send(attractionEntity, image);
                                    }
                                    break;
                                case 3:
                                    image.put("img4" + "\"; filename=\"" + file.getName(), requestBody);
                                    if (image.size() == size) {
                                        send(attractionEntity, image);
                                    }
                                    break;
                                case 4:
                                    image.put("img5" + "\"; filename=\"" + file.getName(), requestBody);
                                    if (image.size() == size) {
                                        send(attractionEntity, image);
                                    }
                                    break;
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}
                    })
                    .launch();
        } else {
            send(attractionEntity, image);
        }
    }

    private void send(ECreateAttraction attractionEntity, Map<String, RequestBody> image) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        String value = gson.toJson(attractionEntity);
        map.put("value", value);
        if (image.size() > 0) {
            mFeatures.sendCreateData(map, image);
        } else {
            mFeatures.sendCreateData(map);
        }
    }

    private List<AttractionClassEntity> mClassList = new ArrayList<>();
    @Override
    public void handleDataOne(List<AttractionClassEntity> attractionClassEntities) {
        mClassList.clear();
        mClassList.addAll(attractionClassEntities);
        mFeatures.getDataTwo(mSid);
    }

    private List<AttractionTermEntity> mTermList = new ArrayList<>();
    @Override
    public void handleDataTwo(List<AttractionTermEntity> attractionTermEntities) {
        mTermList.clear();
        if (attractionTermEntities.size() != 0) {
            mTermList.addAll(attractionTermEntities);
            StringBuilder stringBuilder = new StringBuilder(attractionTermEntities.get(0).getMtid());
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            stringBuilder.append("%");
            mFeatures.getDataThree(stringBuilder.toString());
        } else {
            ivFeatures.init(mClassList, mTermList, new ArrayList<AttractionItemEntity>());
        }
    }

    @Override
    public void handleDataThree(List<AttractionItemEntity> attractionItemEntities) {
        ivFeatures.init(mClassList, mTermList, attractionItemEntities);
    }

    @Override
    public void handleToNextPage(GCreateAttraction gCreateAttraction) {
        if (gCreateAttraction.getSave().equals("Repeat")) {
            ivFeatures.handleToFinish(gCreateAttraction.getSpid(), false);
        } else {
            ivFeatures.handleToFinish(gCreateAttraction.getSpid(), true);
        }
    }

    @Override
    public void handleTimeOutError() {
        ivFeatures.handleTimeOutError();
    }

    @Override
    public void finishSend() {
        ivFeatures.finishSend();
    }
}
