package com.switube.www.landmark2018test.presenter;

import com.google.gson.JsonObject;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.entity.EEditComment;
import com.switube.www.landmark2018test.gson.GLikeUnlike;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.model.MAttractionSteaming;
import com.switube.www.landmark2018test.presenter.callback.IPAttractionSteaming;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVAttractionSteaming;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PAttractionSteaming implements IPAttractionSteaming {
    private IVAttractionSteaming ivAttractionSteaming;
    private MAttractionSteaming mAttractionSteaming;
    public PAttractionSteaming(IVAttractionSteaming ivAttractionSteaming) {
        this.ivAttractionSteaming = ivAttractionSteaming;
        mAttractionSteaming = new MAttractionSteaming(this);
    }

    public void sendLikeOrUnlike(String artid, String isLike, int num) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("xaid", artid);
        map.put("norm", "article");
        map.put("dislike", isLike);
        mAttractionSteaming.sendLikeOrUnlike(map, num);
    }

    public void handleDelete(int index) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("artid", MyApplication.getAppData().getArticleList().get(index).getArtid());
        jsonObject.addProperty("type", "del");
        jsonObject.addProperty("spid", MyApplication.getAppData().getgInfoData().getData().get(0).getSpid());
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("value", jsonObject.toString());
        mAttractionSteaming.deleteComment(map, index);
    }

    public void handleEdit(int index, boolean isComment) {
        EEditComment eEditComment = new EEditComment();
        eEditComment.setName(MyApplication.getAppData().getArticleList().get(index).getManame());
        eEditComment.setMessage(MyApplication.getAppData().getArticleList().get(index).getContent());
        eEditComment.setPrivacy(MyApplication.getAppData().getArticleList().get(index).getPrivacy());
        eEditComment.setImage(MyApplication.getAppData().getArticleList().get(index).getImg());
        eEditComment.setMsid(MyApplication.getAppData().getgInfoData().getData().get(0).getMsid());
        eEditComment.setSpid(MyApplication.getAppData().getgInfoData().getData().get(0).getSpid());
        eEditComment.setArtid(MyApplication.getAppData().getArticleList().get(index).getArtid());
        int size = MyApplication.getAppData().getArticleList().get(index).getTagid().size();
        List<String> naid = new ArrayList<>();
        List<String> tqbid = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            naid.add(MyApplication.getAppData().getArticleList().get(index).getTagid().get(i).getNaid());
            tqbid.add(MyApplication.getAppData().getArticleList().get(index).getTagid().get(i).getTqbid());
        }
        eEditComment.setNaid(naid);
        eEditComment.setTqbid(tqbid);
        size = MyApplication.getAppData().getArticleList().get(index).getTag().size();
        StringBuilder stringBuilder = new StringBuilder();
        List<String> tagA = new ArrayList<>();
        List<String> tagB = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (stringBuilder.length() > 0) {
                stringBuilder.delete(0, stringBuilder.length());
            }
            stringBuilder.append(MyApplication.getAppData().getArticleList().get(index).getTag().get(i));
            int tagIndex = stringBuilder.indexOf(":");
            stringBuilder.delete(tagIndex - 1, stringBuilder.length());
            tagA.add(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder.append(MyApplication.getAppData().getArticleList().get(index).getTag().get(i));
            stringBuilder.delete(0, tagIndex + 1);
            tagB.add(stringBuilder.toString());
        }
        eEditComment.setTagA(tagA);
        eEditComment.setTagB(tagB);
        MyApplication.getAppData().setEditMode(true);
        MyApplication.getAppData().seteEditComment(eEditComment);
        if (isComment) {
            ivAttractionSteaming.toEditCommentPage();
        } else {
            ivAttractionSteaming.toEditTagPage();
        }
    }

    @Override
    public void handleLikeOrUnlikeJson(GLikeUnlike gLikeUnlike, int num) {
        if (num != -1) {
            ivAttractionSteaming.refreshAdapter(gLikeUnlike.getCount(), num);
        }
    }

    @Override
    public void finishDeleteComment(GSendLove gSendLove, int index) {
        if (gSendLove.getSave().equals("1")) {
            MyApplication.getAppData().getArticleList().remove(index);
            ivAttractionSteaming.finishDeleteComment();
        }
    }
}
