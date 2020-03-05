package com.switube.www.landmark2018test.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.gson.GLikeUnlike;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.gson.MessageAndReplyGson;
import com.switube.www.landmark2018test.gson.GCommentsData;
import com.switube.www.landmark2018test.model.MAttractionComments;
import com.switube.www.landmark2018test.presenter.callback.IPAttractionComments;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVAttractionComments;

import java.util.Map;

public class PAttractionComments implements IPAttractionComments {
    private IVAttractionComments ivAttractionComments;
    private MAttractionComments mAttractionComments;
    public PAttractionComments(IVAttractionComments ivAttractionComments) {
        this.ivAttractionComments = ivAttractionComments;
        mAttractionComments = new MAttractionComments(this);
    }

    public void sendMessageData(String artid, String message) {
        MessageAndReplyGson messageAndReplyGson = new MessageAndReplyGson("msg", artid, "", message);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String value = gson.toJson(messageAndReplyGson);
        map.put("value", value);
        mAttractionComments.sendMessageData(map);
    }

    public void sendLikeData(String acid, String like, int index) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("xaid", acid);
        map.put("norm", "comment");
        map.put("dislike", like);
        mAttractionComments.sendLikeData(map, index, like);
    }

    public void sendEditData(String id, String message, int index) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "msg");
        jsonObject.addProperty("mode", "edit");
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("txt", message);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("value", jsonObject.toString());
        mAttractionComments.sendEditData(map, index, message);
    }

    public void sendDelData(String id, int index) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "msg");
        jsonObject.addProperty("mode", "del");
        jsonObject.addProperty("id", id);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("value", jsonObject.toString());
        mAttractionComments.sendDelComment(map, index);
    }

    @Override
    public void handleResult(GCommentsData gCommentsData) {
        ivAttractionComments.handleRefreshData(gCommentsData);
    }

    @Override
    public void handleLikeResult(GLikeUnlike gLikeUnlike, int index, String like) {
        ivAttractionComments.handleRefreshCount(gLikeUnlike.getCount(), index, like);
    }

    @Override
    public void handleFinishEdit(GSendLove gSendLove, int index, String message) {
        if (gSendLove.getSave().equals("1")) {
            MyApplication.getAppData().getMessageList().get(index).setTxt(message);
            ivAttractionComments.handleFinishEdit();
        }
    }

    @Override
    public void handleFinishDel(GSendLove gSendLove, int index) {
        if (gSendLove.getSave().equals("11")) {
            MyApplication.getAppData().getMessageList().remove(index);
            ivAttractionComments.handleFinishDel();
        }
    }
}
