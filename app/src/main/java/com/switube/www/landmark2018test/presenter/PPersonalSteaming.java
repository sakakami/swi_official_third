package com.switube.www.landmark2018test.presenter;

import android.util.Log;

import com.google.gson.JsonObject;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.entity.EEditComment;
import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.gson.GLikeUnlike;
import com.switube.www.landmark2018test.gson.GPersonalSteaming;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.model.MPersonalSteaming;
import com.switube.www.landmark2018test.presenter.callback.IPPersonalSteaming;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVPersonalSteaming;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class PPersonalSteaming implements IPPersonalSteaming {
    private IVPersonalSteaming ivPersonalSteaming;
    private MPersonalSteaming mPersonalSteaming;
    public PPersonalSteaming(IVPersonalSteaming ivPersonalSteaming) {
        this.ivPersonalSteaming = ivPersonalSteaming;
        mPersonalSteaming = new MPersonalSteaming(this);
    }

    public void getPersonalData(String maid, String wsid, String type, String privacy, DisposableObserver<GPersonalSteaming> disposableObserver) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("maid", maid);
        map.put("wsid", wsid);
        map.put("type", type);
        map.put("privacy", privacy);
        mPersonalSteaming.getPersonalData(map, disposableObserver);
    }

    public void sendLikeOrUnlike(String artid, String isLike, int num) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("xaid", artid);
        map.put("norm", "article");
        map.put("dislike", isLike);
        mPersonalSteaming.sendLikeOrUnlike(map, num);
    }

    public void handleEditData(List<GInfoData.Article> articles, int index, boolean isComment) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("spid", articles.get(index).getSpid());
        mPersonalSteaming.getMsid(map, articles, index, isComment);
    }

    public void handleDelComment(List<GInfoData.Article> articles, int index) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("artid", articles.get(index).getArtid());
        jsonObject.addProperty("type", "del");
        jsonObject.addProperty("spid", articles.get(index).getSpid());
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("value", jsonObject.toString());
        mPersonalSteaming.sendDeleteData(map, index);
    }

    //@Override
    public void handPersonalData(GPersonalSteaming gPersonalSteaming) {
        ivPersonalSteaming.init(gPersonalSteaming);
    }

    @Override
    public void handleLikeOrUnlikeJson(GLikeUnlike gLikeUnlike, int num) {
        ivPersonalSteaming.refreshAdapter(gLikeUnlike.getCount(), num);
    }

    @Override
    public void finishDeleteComment(GSendLove gSendLove, int index) {
        if (gSendLove.getSave().equals("1")) {
            ivPersonalSteaming.finishDel(index);
        }
    }

    @Override
    public void handleMsidData(GInfoData gInfoData, List<GInfoData.Article> articles, int index, boolean isComment) {
        EEditComment eEditComment = new EEditComment();
        eEditComment.setName(articles.get(index).getManame());
        eEditComment.setMessage(articles.get(index).getContent());
        eEditComment.setPrivacy(articles.get(index).getPrivacy());
        eEditComment.setImage(articles.get(index).getImg());
        eEditComment.setMsid(gInfoData.getData().get(0).getMsid());
        eEditComment.setSpid(articles.get(index).getSpid());
        eEditComment.setArtid(articles.get(index).getArtid());
        int size = articles.get(index).getTagid().size();
        List<String> naid = new ArrayList<>();
        List<String> tqbid = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            naid.add(articles.get(index).getTagid().get(i).getNaid());
            tqbid.add(articles.get(index).getTagid().get(i).getTqbid());
        }
        eEditComment.setNaid(naid);
        eEditComment.setTqbid(tqbid);
        size = articles.get(index).getTag().size();
        StringBuilder stringBuilder = new StringBuilder();
        List<String> tagA = new ArrayList<>();
        List<String> tagB = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (stringBuilder.length() > 0) {
                stringBuilder.delete(0, stringBuilder.length());
            }
            stringBuilder.append(articles.get(index).getTag().get(i));
            int tagIndex = stringBuilder.indexOf(":");
            stringBuilder.delete(tagIndex - 1, stringBuilder.length());
            tagA.add(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder.append(articles.get(index).getTag().get(i));
            stringBuilder.delete(0, tagIndex + 1);
            tagB.add(stringBuilder.toString());
        }
        eEditComment.setTagA(tagA);
        eEditComment.setTagB(tagB);
        MyApplication.getAppData().setEditMode(true);
        MyApplication.getAppData().seteEditComment(eEditComment);
        if (isComment) {
            ivPersonalSteaming.toEditCommentPage();
        } else {
            ivPersonalSteaming.toEditTagPage();
        }
    }
}
