package com.switube.www.landmark2018test.presenter;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.adapter.ATagSelected;
import com.switube.www.landmark2018test.gson.CheckInDataGson;
import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.model.MLeaveComments;
import com.switube.www.landmark2018test.presenter.callback.IPLeaveComments;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.VTag;
import com.switube.www.landmark2018test.view.callback.IVLeaveComments;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class PLeaveComments implements IPLeaveComments {
    private IVLeaveComments ivLeaveComments;
    private MLeaveComments mLeaveComments;
    public PLeaveComments(IVLeaveComments ivLeaveComments) {
        this.ivLeaveComments = ivLeaveComments;
        mLeaveComments = new MLeaveComments(this);
    }

    public void handleDataToSend(Context context, String spid, List<String> photo, String privacy, String txt) {
        List<String> qbids = new ArrayList<>();
        List<CheckInDataGson.Tag> tags = new ArrayList<>();
        if (ATagSelected.tagQBNEntities.size() > 0) {
            int size = VTag.eTagQB.size();
            for (int i = 0; i < size; i++) {
                qbids.add(VTag.eTagQB.get(i).getQbid());
            }
            size = ATagSelected.tagQBAEntities.size();
            String tqbid;
            String qbid;
            String natxt;
            int index;
            for (int i = 0; i < size; i++) {
                qbid = ATagSelected.tagQBNEntities.get(i).getQbid();
                index = qbids.indexOf(qbid);
                tqbid = VTag.eTagQB.get(index).getTqbid();
                natxt = ATagSelected.tagQBNEntities.get(i).getNorm() + " : " + ATagSelected.tagQBAEntities.get(i).getAssess();
                tags.add(new CheckInDataGson.Tag(tqbid, ATagSelected.tagQBAEntities.get(i).getNaid(), natxt));
            }
        }
        CheckInDataGson checkInDataGson = new CheckInDataGson(spid, privacy, String.valueOf(photo.size()), txt, tags);
        if (photo.size() > 0) {
            handlePhotoDataToSend(context, checkInDataGson, photo);
        } else {
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            String value = gson.toJson(checkInDataGson);
            Map<String, String> map = NetworkUtil.getInstance().getMap();
            map.put("value", value);
            mLeaveComments.sendCheckInData(map);
        }
    }

    public void sendEditData() {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("artid", MyApplication.getAppData().geteEditComment().getArtid());
        jsonObject.addProperty("spid", MyApplication.getAppData().geteEditComment().getSpid());
        jsonObject.addProperty("type", "edit");
        if (MyApplication.getAppData().isEditCommentMode()) {
            jsonObject.addProperty("txt", MyApplication.getAppData().geteEditComment().getMessage());
            jsonObject.addProperty("photocut", String.valueOf(MyApplication.getAppData().getSelectedPhotos().size()));
            jsonObject.addProperty("privacy", MyApplication.getAppData().geteEditComment().getPrivacy());
            if (MyApplication.getAppData().geteEditComment().getImageDel().size() > 0) {
                JsonArray jsonArray = new JsonArray();
                for (int i = 0; i < MyApplication.getAppData().geteEditComment().getImageDel().size(); i++) {
                    jsonArray.add(MyApplication.getAppData().geteEditComment().getImageDel().get(i));
                }
                jsonObject.add("photodel", jsonArray);
            }
        }
        if (MyApplication.getAppData().isEditTagMode()) {
            JsonArray jsonArray = new JsonArray();
            int size = MyApplication.getAppData().geteEditComment().getNaid().size();
            for (int i = 0; i < size; i++) {
                JsonObject tagData = new JsonObject();
                tagData.addProperty("naid", MyApplication.getAppData().geteEditComment().getNaid().get(i));
                tagData.addProperty("tqbid", MyApplication.getAppData().geteEditComment().getTqbid().get(i));
                String message = MyApplication.getAppData().geteEditComment().getTagA().get(i) + " : " + MyApplication.getAppData().geteEditComment().getTagB().get(i);
                tagData.addProperty("natxt", message);
                jsonArray.add(tagData);
            }
            jsonObject.add("tags", jsonArray);
        }
        if (MyApplication.getAppData().getSelectedPhotos().size() > 0) {
            final int size = MyApplication.getAppData().getSelectedPhotos().size();
            Luban.with(MyApplication.getInstance())
                    .load(MyApplication.getAppData().getSelectedPhotos())
                    .ignoreBy(100)
                    .setCompressListener(new OnCompressListener() {
                        @Override
                        public void onStart() {}

                        @Override
                        public void onSuccess(File file) {
                            Log.e("luban", "absolutePath -> " + file.getAbsolutePath());
                            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                            switch (image.size()) {
                                case 0:
                                    image.put("img1" + "\"; filename=\"" + file.getName(), requestBody);
                                    if (image.size() == size) {
                                        Map<String, String> map = NetworkUtil.getInstance().getMap();
                                        map.put("value", jsonObject.toString());
                                        mLeaveComments.sendEditData(map, image);
                                    }
                                    break;
                                case 1:
                                    image.put("img2" + "\"; filename=\"" + file.getName(), requestBody);
                                    if (image.size() == size) {
                                        Map<String, String> map = NetworkUtil.getInstance().getMap();
                                        map.put("value", jsonObject.toString());
                                        mLeaveComments.sendEditData(map, image);
                                    }
                                    break;
                                case 2:
                                    image.put("img3" + "\"; filename=\"" + file.getName(), requestBody);
                                    if (image.size() == size) {
                                        Map<String, String> map = NetworkUtil.getInstance().getMap();
                                        map.put("value", jsonObject.toString());
                                        mLeaveComments.sendEditData(map, image);
                                    }
                                    break;
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}
                    })
                    .launch();
        } else {
            Map<String, String> map = NetworkUtil.getInstance().getMap();
            map.put("value", jsonObject.toString());
            mLeaveComments.sendEditData(map);
        }
    }

    private Map<String, RequestBody> image = new HashMap<>();
    private void handlePhotoDataToSend(final Context context, final CheckInDataGson checkInDataGson, final List<String> photo) {
        final int size = photo.size();
        Log.e("before", "0 address -> " + photo.get(0));
        Luban.with(context)
                .load(photo)
                .ignoreBy(100)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {}

                    @Override
                    public void onSuccess(File file) {
                        Log.e("after", "address -> " + file.getAbsolutePath());
                        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        switch (image.size()) {
                            case 0:
                                image.put("img1" + "\"; filename=\"" + file.getName(), requestBody);
                                if (image.size() == size) {
                                    sendCheckInData(checkInDataGson);
                                }
                                break;
                            case 1:
                                image.put("img2" + "\"; filename=\"" + file.getName(), requestBody);
                                if (image.size() == size) {
                                    sendCheckInData(checkInDataGson);
                                }
                                break;
                            case 2:
                                image.put("img3" + "\"; filename=\"" + file.getName(), requestBody);
                                if (image.size() == size) {
                                    sendCheckInData(checkInDataGson);
                                }
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}
                })
                .launch();
    }

    private void sendCheckInData(CheckInDataGson checkInDataGson) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String value = gson.toJson(checkInDataGson);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("value", value);
        mLeaveComments.sendCheckInData(map, image);
    }

    @Override
    public void handleFinishSend() {
        ATagSelected.tagQBAEntities.clear();
        ATagSelected.tagQBNEntities.clear();
        ivLeaveComments.handleFinishSend();
    }

    @Override
    public void handleFinishSend(GSendLove gSendLove) {
        if (gSendLove.getSave().equals("1")) {
            if (MyApplication.getAppData().isFromAttractionSteaming()) {
                Map<String, String> map = NetworkUtil.getInstance().getMap();
                map.put("spid", MyApplication.getAppData().getgInfoData().getData().get(0).getSpid());
                mLeaveComments.getInfoData(map);
            } else {
                ivLeaveComments.handleFinishSend();
            }
        }
    }

    @Override
    public void handleInfoData(GInfoData gInfoData) {
        MyApplication.getAppData().setArticleList(gInfoData.getData().get(0).getArticle());
        MyApplication.getAppData().setgInfoData(gInfoData);
        ivLeaveComments.handleFinishSend();
    }
}
