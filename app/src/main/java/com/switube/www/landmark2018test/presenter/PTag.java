package com.switube.www.landmark2018test.presenter;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.database.entity.TagQBAEntity;
import com.switube.www.landmark2018test.database.entity.TagQBNEntity;
import com.switube.www.landmark2018test.entity.ETagQB;
import com.switube.www.landmark2018test.gson.GTagData;
import com.switube.www.landmark2018test.model.MTag;
import com.switube.www.landmark2018test.presenter.callback.IPTag;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVTag;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PTag implements IPTag {
    private IVTag iVTag;
    private MTag mTag;
    public PTag(IVTag iVTag) {
        this.iVTag = iVTag;
        mTag = new MTag(this);
    }

    public boolean handleTag(List<TagQBNEntity> listA, List<TagQBAEntity> listB) {
        if (listA.size() == listB.size()) {
            int size = listA.size();
            StringBuilder stringBuilderA = new StringBuilder();
            StringBuilder stringBuilderB = new StringBuilder();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    stringBuilderA.append("#");
                    stringBuilderA.append(listA.get(i).getNorm());
                    if (listA.size() - 1 > i) {
                        stringBuilderA.append("\n");
                    }
                    stringBuilderB.append(listB.get(i).getAssess());
                    if (listB.size() - 1 > i) {
                        stringBuilderB.append("\n");
                    }
                }
                SharePreferencesUtil.getInstance().getEditor().putString("selectedTagA", stringBuilderA.toString()).apply();
                SharePreferencesUtil.getInstance().getEditor().putString("selectedTagB", stringBuilderB.toString()).apply();
                return true;
            } else {
                SharePreferencesUtil.getInstance().getEditor().putString("selectedTagA", "finish").apply();
                SharePreferencesUtil.getInstance().getEditor().putString("selectedTagB", "finish").apply();
                return true;
            }
        } else {
            return false;
        }
    }

    public void getTagData(String msid) {
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("msid", msid);
        mTag.getAttractionData(map);
    }

    public void getQBNData(String qbid) {
        mTag.getSearchQBNData(qbid);
    }

    public void getQBAData(String qbnid) {
        mTag.getSearchQBAData(qbnid);
    }

    public void searchQBN(List<TagQBNEntity> tagQBNEntities, String keyWord) {
        List<TagQBNEntity> list = new ArrayList<>();
        for (int i = 0; i < tagQBNEntities.size(); i++) {
            if (tagQBNEntities.get(i).getNorm().contains(keyWord)) {
                list.add(tagQBNEntities.get(i));
            }
        }
        iVTag.initN(list);
    }

    public void searchQBA(List<TagQBAEntity> tagQBAEntities, String keyWord) {
        List<TagQBAEntity> list = new ArrayList<>();
        for (int i = 0; i < tagQBAEntities.size(); i++) {
            if (tagQBAEntities.get(i).getAssess().contains(keyWord)) {
                list.add(tagQBAEntities.get(i));
            }
        }
        iVTag.initA(list);
    }

    public void handleEditData(List<TagQBNEntity> listA, List<TagQBAEntity> listB, List<ETagQB> eTagQB) {
        int size = listB.size();
        List<String> tagA = new ArrayList<>();
        List<String> tagB = new ArrayList<>();
        List<String> tqbid = new ArrayList<>();
        List<String> naid = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            tagA.add(listA.get(i).getNorm());
            tagB.add(listB.get(i).getAssess());
            naid.add(listB.get(i).getNaid());
            for (int j = 0; j < eTagQB.size(); j++) {
                if (eTagQB.get(j).getQbid().equals(listA.get(i).getQbid())) {
                    tqbid.add(eTagQB.get(j).getTqbid());
                }
            }
        }
        MyApplication.getAppData().geteEditComment().setTagA(tagA);
        MyApplication.getAppData().geteEditComment().setTagB(tagB);
        MyApplication.getAppData().geteEditComment().setTqbid(tqbid);
        MyApplication.getAppData().geteEditComment().setNaid(naid);
        iVTag.finishSave();
    }

    @Override
    public void handleTagGsonData(GTagData gTagData) {
        if (gTagData.getQBData().size() > 0 && gTagData.getQBNData().size() > 0 && gTagData.getQBAData().size() > 0) {
            List<TagQBNEntity> tagQBNEntities = new ArrayList<>();
            List<TagQBAEntity> tagQBAEntities = new ArrayList<>();
            List<ETagQB> eTagQB = new ArrayList<>();
            int size = gTagData.getQBNData().size();
            for (int i = 0; i < size; i++) {
                tagQBNEntities.add(new TagQBNEntity(gTagData.getQBNData().get(i).getQbid(),
                        gTagData.getQBNData().get(i).getQbnid(), gTagData.getQBNData().get(i).getNorm()));
            }
            size = gTagData.getQBAData().size();
            for (int i = 0; i < size; i++) {
                tagQBAEntities.add(new TagQBAEntity(gTagData.getQBAData().get(i).getNaid(),
                        gTagData.getQBAData().get(i).getQbnid(), gTagData.getQBAData().get(i).getQbaid(),
                        gTagData.getQBAData().get(i).getAssess()));
            }
            size = gTagData.getQBData().size();
            for (int i = 0; i < size; i++) {
                eTagQB.add(new ETagQB(gTagData.getQBData().get(i).getTqbid(),
                        gTagData.getQBData().get(i).getQbid(), gTagData.getQBData().get(i).getQb_name()));
            }
            mTag.handleInsertData(tagQBNEntities, tagQBAEntities, eTagQB.get(0).getQbid());
            iVTag.init(eTagQB);
            if (MyApplication.getAppData().isEditMode()) {
                List<TagQBNEntity> tagQBNEntitiesSelected = new ArrayList<>();
                List<TagQBAEntity> tagQBAEntitiesSelected = new ArrayList<>();
                size = tagQBAEntities.size();
                for (int i = 0; i < size; i++) {
                    if (MyApplication.getAppData().geteEditComment().getNaid().contains(tagQBAEntities.get(i).getNaid())) {
                        tagQBAEntitiesSelected.add(tagQBAEntities.get(i));
                        for (int j = 0; j < tagQBNEntities.size(); j++) {
                            if (tagQBAEntities.get(i).getQbnid().equals(tagQBNEntities.get(j).getQbnid())) {
                                tagQBNEntitiesSelected.add(tagQBNEntities.get(j));
                            }
                        }
                    }
                }
                iVTag.refreshAdapter(tagQBNEntitiesSelected, tagQBAEntitiesSelected);
            }
        }
    }

    @Override
    public void handleQBNData(List<TagQBNEntity> tagQBNEntities) {
        iVTag.initN(tagQBNEntities);
    }

    @Override
    public void handleQBAData(List<TagQBAEntity> tagQBAEntities) {
        iVTag.initA(tagQBAEntities);
    }
}
