package com.switube.www.landmark2018test.presenter;

import com.google.gson.JsonObject;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.entity.EEditComment;
import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.gson.GLikeUnlike;
import com.switube.www.landmark2018test.model.MInfo;
import com.switube.www.landmark2018test.presenter.callback.IPInfo;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PInfo implements IPInfo {
    private IVInfo ivInfo;
    private MInfo mInfo;
    public PInfo(IVInfo ivInfo) {
        this.ivInfo = ivInfo;
        mInfo = new MInfo(this);
    }

    public void getDetailData(String spid) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("spid", spid);
        mInfo.getInfoData(map);
    }

    public void sendLikeOrUnlike(String artid, String isLike, int num) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("xaid", artid);
        map.put("norm", "article");
        map.put("dislike", isLike);
        mInfo.sendLikeOrUnlike(map, num);
    }

    public void sendRatingData(String artid, String rate) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("xaid", artid);
        map.put("norm", "rating");
        map.put("dislike", rate);
        mInfo.sendLikeOrUnlike(map, -1);
    }

    public void getSaveList() {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("raid", MyApplication.getAppData().getRaid());
        mInfo.getSaveList(map);
    }

    public void sendSaveData(GSaveList gSaveList, List<Boolean> isClickedList, String attractionId) {
        int index = isClickedList.indexOf(true);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("type", "add");
        map.put("raid", MyApplication.getAppData().getRaid());
        map.put("urid", gSaveList.getData().get(index).getUrid());
        map.put("urspid", "");
        map.put("spid", attractionId);
        map.put("sort", "");
        isClickedList.set(index, false);
        mInfo.sendAddToStroke(map, gSaveList, isClickedList, attractionId);
    }

    public void handleNewStroke(String title) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("title", title);
        map.put("raid", MyApplication.getAppData().getRaid());
        mInfo.createNewStroke(map);
    }

    public void sendAddToCollect(String spid, boolean isAdd) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        if (isAdd) {
            map.put("type", "add");
        } else {
            map.put("type", "del");
        }
        map.put("raid", MyApplication.getAppData().getRaid());
        map.put("spid", spid);
        mInfo.sendAddToCollect(map);
    }

    public void handleEditData(int index, GInfoData gInfoData, boolean isComment) {
        EEditComment eEditComment = new EEditComment();
        eEditComment.setName(gInfoData.getData().get(0).getPlace());
        eEditComment.setMessage(gInfoData.getData().get(0).getArticle().get(index).getContent());
        eEditComment.setPrivacy(gInfoData.getData().get(0).getArticle().get(index).getPrivacy());
        eEditComment.setImage(gInfoData.getData().get(0).getArticle().get(index).getImg());
        eEditComment.setMsid(gInfoData.getData().get(0).getMsid());
        eEditComment.setSpid(gInfoData.getData().get(0).getSpid());
        eEditComment.setArtid(gInfoData.getData().get(0).getArticle().get(index).getArtid());
        int size = gInfoData.getData().get(0).getArticle().get(index).getTagid().size();
        List<String> naid = new ArrayList<>();
        List<String> tqbid = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            naid.add(gInfoData.getData().get(0).getArticle().get(index).getTagid().get(i).getNaid());
            tqbid.add(gInfoData.getData().get(0).getArticle().get(index).getTagid().get(i).getTqbid());
        }
        eEditComment.setNaid(naid);
        eEditComment.setTqbid(tqbid);
        size = gInfoData.getData().get(0).getArticle().get(index).getTag().size();
        StringBuilder stringBuilder = new StringBuilder();
        List<String> tagA = new ArrayList<>();
        List<String> tagB = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (stringBuilder.length() > 0) {
                stringBuilder.delete(0, stringBuilder.length());
            }
            stringBuilder.append(gInfoData.getData().get(0).getArticle().get(index).getTag().get(i));
            int tagIndex = stringBuilder.indexOf(":");
            stringBuilder.delete(tagIndex - 1, stringBuilder.length());
            tagA.add(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder.append(gInfoData.getData().get(0).getArticle().get(index).getTag().get(i));
            stringBuilder.delete(0, tagIndex + 1);
            tagB.add(stringBuilder.toString());
        }
        eEditComment.setTagA(tagA);
        eEditComment.setTagB(tagB);
        MyApplication.getAppData().setEditMode(true);
        MyApplication.getAppData().seteEditComment(eEditComment);
        if (isComment) {
            ivInfo.showEditComment();
        } else {
            ivInfo.showEditTag();
        }
    }

    public void handleDeleteData(int index, GInfoData gInfoData) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("artid", gInfoData.getData().get(0).getArticle().get(index).getArtid());
        jsonObject.addProperty("type", "del");
        jsonObject.addProperty("spid", gInfoData.getData().get(0).getSpid());
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("value", jsonObject.toString());
        mInfo.sendDeleteComment(map, index);
    }

    @Override
    public void handleDetailGson(GInfoData gInfoData) {
        if (gInfoData.getData().size() > 0) {
            StringBuilder stringBuilder = new StringBuilder(gInfoData.getData().get(0).getPlace());
            if (stringBuilder.toString().contains("&#039")) {
                int index = stringBuilder.indexOf("&");
                stringBuilder.replace(index, index + 5, "'");
                gInfoData.getData().get(0).setPlace(stringBuilder.toString());
            }
            mInfo.getAttractionType(gInfoData.getData().get(0).getMsid(), gInfoData);
        }
    }

    @Override
    public void handleLikeOrUnlikeJson(GLikeUnlike gLikeUnlike, int num) {
        if (num == -1) {
            BigDecimal bigDecimal = new BigDecimal(gLikeUnlike.getCount());
            bigDecimal = bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP);
            ivInfo.refreshAdapter(bigDecimal.toString());
        } else {
            ivInfo.refreshAdapter(gLikeUnlike.getCount(), num);
        }
    }

    @Override
    public void handleAttractionStyle(AttractionStyleEntity attractionStyleEntity, GInfoData gInfoData) {
        String style;
        switch (Locale.getDefault().getLanguage()) {
            case "zh":
                switch (Locale.getDefault().getCountry()) {
                    case "TW":
                        style = attractionStyleEntity.getMstitle_tw();
                        break;
                    case "CN":
                        style = attractionStyleEntity.getMstitle_ch();
                        break;
                    default:
                        style = attractionStyleEntity.getMstitle_en();
                        break;
                }
                break;
            case "jp":
                style = attractionStyleEntity.getMstitle_jp();
                break;
            default:
                style = attractionStyleEntity.getMstitle_en();
                break;
        }
        MyApplication.getAppData().setStyle(style);
        mInfo.getAttractionClass(gInfoData.getData().get(0).getMscid(), style, gInfoData);
        //mInfo.getAttractionService(style, gInfoData);
    }

    @Override
    public void handleAttractionClass(String style, GInfoData gInfoData, AttractionClassEntity attractionClassEntity) {
        String attractionClass;
        switch (MyApplication.getLanguageIndex()) {
            case 1:
                attractionClass = attractionClassEntity.getMctitle_tw();
                break;
            case 2:
                attractionClass = attractionClassEntity.getMctitle_ch();
                break;
            case 3:
                attractionClass = attractionClassEntity.getMctitle_jp();
                break;
            default:
                attractionClass = attractionClassEntity.getMctitle_en();
                break;
        }
        String newStyle = style + "／" + attractionClass;
        mInfo.getAttractionService(newStyle, gInfoData);
    }

    @Override
    public void handleAttractionItem(String style, List<AttractionItemEntity> attractionItemEntities, GInfoData gInfoData) {
        StringBuilder item = new StringBuilder();
        List<String> mstid = new ArrayList<>();
        int size = attractionItemEntities.size();
        for (int i = 0; i < size; i++) {
            mstid.add(attractionItemEntities.get(i).getMstid());
        }
        size = gInfoData.getData().get(0).getTerm().size();
        if (size > 3) {
            size = 3;
        }
        int index;
        for (int i = 0; i < size; i++) {
            index = mstid.indexOf(gInfoData.getData().get(0).getTerm().get(i).getMstid());
            switch (Locale.getDefault().getLanguage()) {
                case "zh":
                    switch (Locale.getDefault().getCountry()) {
                        case "TW":
                            item.append(attractionItemEntities.get(index).getMititle_tw());
                            break;
                        case "CN":
                            item.append(attractionItemEntities.get(index).getMititle_ch());
                            break;
                        default:
                            item.append(attractionItemEntities.get(index).getMititle_en());
                            break;
                    }
                    break;
                case "jp":
                    item.append(attractionItemEntities.get(index).getMititle_jp());
                    break;
                default:
                    item.append(attractionItemEntities.get(index).getMititle_en());
                    break;
            }
            if (i != size - 1) {
                item.append(" · ");
            }
        }
        MyApplication.getAppData().setMsid(gInfoData.getData().get(0).getMsid());
        MyApplication.getAppData().setMscid(gInfoData.getData().get(0).getMscid());
        List<String> mtidList = new ArrayList<>();
        List<String> mstidList = new ArrayList<>();
        size = gInfoData.getData().get(0).getTerm().size();
        for (int i = 0; i < size; i++) {
            if (!mtidList.contains(gInfoData.getData().get(0).getTerm().get(i).getMtid())) {
                mtidList.add(gInfoData.getData().get(0).getTerm().get(i).getMtid());
            }
            if (!mstidList.contains(gInfoData.getData().get(0).getTerm().get(i).getMstid())) {
                mstidList.add(gInfoData.getData().get(0).getTerm().get(i).getMstid());
            }
        }
        MyApplication.getAppData().setMtidList(mtidList);
        MyApplication.getAppData().setMstidList(mstidList);
        MyApplication.getAppData().setgInfoData(gInfoData);
        ivInfo.init(style, item.toString(), gInfoData);
    }

    @Override
    public void handleSaveList(GSaveList gSaveList) {
        ivInfo.showSaveList(gSaveList);
    }

    @Override
    public void handleFinishAddToStroke(GSaveList gSaveList, List<Boolean> isClickedList, String attractionId) {
        int index = isClickedList.indexOf(true);
        if (index >= 0) {
            sendSaveData(gSaveList, isClickedList, attractionId);
        } else {
            ivInfo.finishAddToStroke();
        }
    }

    @Override
    public void handleFinishCreateNewStroke(GSendLove gSendLove) {
        if (gSendLove.getSave().equals("1")) {
            ivInfo.showFinishCreateStroke();
        }
    }

    @Override
    public void handleFinishAddToCollect(GSendLove gSendLove) {
        if (gSendLove.getSave().equals("1")) {
            ivInfo.finishAddToCollect(true, gSendLove.getSucid());
        } else if (gSendLove.getSave().equals("10")) {
            ivInfo.finishAddToCollect(false, gSendLove.getSucid());
        }
    }

    @Override
    public void handleFinishDelete(GSendLove gSendLove, int index) {
        if (gSendLove.getSave().equals("1")) {
            MyApplication.getAppData().getgInfoData().getData().get(0).getArticle().remove(index);
            ivInfo.finishDelete();
        }
    }
}
