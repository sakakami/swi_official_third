package com.switube.www.landmark2018test.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.gson.GCommentsData;
import com.switube.www.landmark2018test.gson.GLikeUnlike;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.gson.MessageAndReplyGson;
import com.switube.www.landmark2018test.model.MReplies;
import com.switube.www.landmark2018test.presenter.callback.IPReplies;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVReplies;

import java.util.Map;

public class PReplies implements IPReplies {
    private IVReplies ivReplies;
    private MReplies mReplies;
    public PReplies(IVReplies ivReplies) {
        this.ivReplies = ivReplies;
        mReplies = new MReplies(this);
    }

    public void sendReplyData(String artid, String acid, String message) {
        MessageAndReplyGson messageAndReplyGson = new MessageAndReplyGson("reply", artid, acid, message);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String value = gson.toJson(messageAndReplyGson);
        map.put("value", value);
        mReplies.sendReplyData(map);
    }

    public void sendLikeData(String arid, String like, int index, boolean isReply) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("xaid", arid);
        if (isReply) {
            map.put("norm", "reply");
        } else {
            map.put("norm", "comment");
        }
        map.put("dislike", like);
        mReplies.sendLikeData(map, like, index, isReply);
    }

    public void sendEditData(String id, String message, boolean isComment, int index, int mainIndex) {
        JsonObject jsonObject = new JsonObject();
        if (isComment) {
            jsonObject.addProperty("type", "msg");
        } else {
            jsonObject.addProperty("type", "reply");
        }
        jsonObject.addProperty("mode", "edit");
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("txt", message);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("value", jsonObject.toString());
        mReplies.sendEditData(map, index, message, isComment, mainIndex);
    }

    public void sendDelData(String id, int index, boolean isComment, int mainIndex) {
        JsonObject jsonObject = new JsonObject();
        if (isComment) {
            jsonObject.addProperty("type", "msg");
        } else {
            jsonObject.addProperty("type", "reply");
        }
        jsonObject.addProperty("mode", "del");
        jsonObject.addProperty("id", id);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("value", jsonObject.toString());
        mReplies.sendDelData(map, index, isComment, mainIndex);
    }

    @Override
    public void handleResult(GCommentsData gCommentsData) {
        ivReplies.handleRefresh(gCommentsData);
    }

    @Override
    public void handleLikeResult(GLikeUnlike gLikeUnlike, String like, int index, boolean isReply) {
        ivReplies.handleRefresh(gLikeUnlike.getCount(), like, index, isReply);
    }

    @Override
    public void handleFinishEdit(GSendLove gSendLove, int index, String message, boolean isComment, int mainIndex) {
        if (gSendLove.getSave().equals("2") || gSendLove.getSave().equals("1")) {
            if (isComment) {
                MyApplication.getAppData().getMessageList().get(mainIndex).setTxt(message);
            } else {
                MyApplication.getAppData().getMessageList().get(mainIndex).getReply().get(index).setTxt(message);
            }
            ivReplies.handleFinishEdit(isComment, message, mainIndex);
        }
    }

    @Override
    public void handleFinishDel(GSendLove gSendLove, int index, boolean isComment, int mainIndex) {
        if (gSendLove.getSave().equals("12") || gSendLove.getSave().equals("11")) {
            if (isComment) {
                MyApplication.getAppData().getMessageList().remove(mainIndex);
            } else {
                MyApplication.getAppData().getMessageList().get(mainIndex).getReply().remove(index);
            }
            ivReplies.handleFinishDel(isComment, mainIndex);
        }
    }
}
