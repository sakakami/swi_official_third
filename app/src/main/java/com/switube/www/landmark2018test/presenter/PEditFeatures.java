package com.switube.www.landmark2018test.presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;
import com.switube.www.landmark2018test.entity.EEditFeatures;
import com.switube.www.landmark2018test.entity.EFeatures;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.model.MEditFeatures;
import com.switube.www.landmark2018test.presenter.callback.IPEditFeatures;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.callback.IVEditFeatures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PEditFeatures implements IPEditFeatures {
    private IVEditFeatures ivEditFeatures;
    private MEditFeatures mEditFeatures;
    public PEditFeatures(IVEditFeatures ivEditFeatures) {
        this.ivEditFeatures = ivEditFeatures;
        mEditFeatures = new MEditFeatures(this);
    }

    public void getStyleData(String msid) {
        mEditFeatures.getStyleData(msid);
    }

    public void sendEditData(List<EFeatures> eFeaturesList, String spid) {
        EEditFeatures eEditFeatures = new EEditFeatures();
        EEditFeatures.Class editClass = new EEditFeatures.Class();
        EEditFeatures.Term editTerm;
        List<EEditFeatures.Term> termList = new ArrayList<>();
        List<EEditFeatures.Item> itemListOne = new ArrayList<>();
        List<EEditFeatures.Item> itemListTwo = new ArrayList<>();
        List<EEditFeatures.Item> itemListThree = new ArrayList<>();
        List<String> mtid = new ArrayList<>();
        EEditFeatures.Item item;
        eEditFeatures.setSpid(spid);
        int size = eFeaturesList.size();
        for (int i = 0; i < size; i++) {
            switch (eFeaturesList.get(i).getFrom()) {
                case "class":
                    if (eFeaturesList.get(i).isSelect()) {
                        editClass.setMcid(eFeaturesList.get(i).getId2());
                        editClass.setMscid(eFeaturesList.get(i).getId());
                    }
                    break;
                case "term":
                    mtid.add(eFeaturesList.get(i).getId());
                    break;
                case "item0":
                    if (eFeaturesList.get(i).isSelect()) {
                        item = new EEditFeatures.Item();
                        item.setMstid(eFeaturesList.get(i).getId());
                        item.setMiid(eFeaturesList.get(i).getId2());
                        itemListOne.add(item);
                    }
                    break;
                case "item1":
                    if (eFeaturesList.get(i).isSelect()) {
                        item = new EEditFeatures.Item();
                        item.setMstid(eFeaturesList.get(i).getId());
                        item.setMiid(eFeaturesList.get(i).getId2());
                        itemListTwo.add(item);
                    }
                    break;
                case "item2":
                    if (eFeaturesList.get(i).isSelect()) {
                        item = new EEditFeatures.Item();
                        item.setMstid(eFeaturesList.get(i).getId());
                        item.setMiid(eFeaturesList.get(i).getId2());
                        itemListThree.add(item);
                    }
                    break;
                default:
                    break;
            }
        }
        size = mtid.size();
        for (int i = 0; i < size; i++) {
            switch (i) {
                case 0:
                    if (itemListOne.size() > 0) {
                        editTerm = new EEditFeatures.Term();
                        editTerm.setMtid(mtid.get(0));
                        editTerm.setItem(itemListOne);
                        termList.add(editTerm);
                    }
                    break;
                case 1:
                    if (itemListTwo.size() > 0) {
                        editTerm = new EEditFeatures.Term();
                        editTerm.setMtid(mtid.get(1));
                        editTerm.setItem(itemListTwo);
                        termList.add(editTerm);
                    }
                    break;
                case 2:
                    if (itemListThree.size() > 0) {
                        editTerm = new EEditFeatures.Term();
                        editTerm.setMtid(mtid.get(2));
                        editTerm.setItem(itemListThree);
                        termList.add(editTerm);
                    }
                    break;
                default:
                    break;
            }
        }
        eEditFeatures.setmClass(editClass);
        eEditFeatures.setmTerm(termList);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        String value = gson.toJson(eEditFeatures);
        map.put("value", value);
        mEditFeatures.sendEditData(map);
    }

    @Override
    public void handleStyleData(AttractionStyleEntity attractionStyleEntity, String msid) {
        List<EFeatures> eFeaturesList = new ArrayList<>();
        switch (MyApplication.getLanguageIndex()) {
            case 1:
                eFeaturesList.add(new EFeatures("", "", "", attractionStyleEntity.getMstitle_tw(), true, false, false, false, "t"));
                break;
            case 2:
                eFeaturesList.add(new EFeatures("", "", "", attractionStyleEntity.getMstitle_ch(), true, false, false, false, "t"));
                break;
            case 3:
                eFeaturesList.add(new EFeatures("", "", "", attractionStyleEntity.getMstitle_jp(), true, false, false, false, "t"));
                break;
            default:
                eFeaturesList.add(new EFeatures("", "", "", attractionStyleEntity.getMstitle_en(), true, false, false, false, "t"));
                break;
        }
        mEditFeatures.getClassData(msid, eFeaturesList);
    }

    @Override
    public void handleClassData(List<AttractionClassEntity> attractionClassEntities, String msid, List<EFeatures> eFeaturesList) {
        int size = attractionClassEntities.size();
        boolean isSelected;
        for (int i = 0; i < size; i++) {
            if (attractionClassEntities.get(i).getMsid().equals(msid)) {
                isSelected = attractionClassEntities.get(i).getMscid().equals(MyApplication.getAppData().getMscid());
                switch (MyApplication.getLanguageIndex()) {
                    case 1:
                        eFeaturesList.add(new EFeatures(attractionClassEntities.get(i).getMscid(), attractionClassEntities.get(i).getMcid(), "", attractionClassEntities.get(i).getMctitle_tw(), true, true, isSelected, true, "class"));
                        break;
                    case 2:
                        eFeaturesList.add(new EFeatures(attractionClassEntities.get(i).getMscid(), attractionClassEntities.get(i).getMcid(), "", attractionClassEntities.get(i).getMctitle_ch(), true, true, isSelected, true, "class"));
                        break;
                    case 3:
                        eFeaturesList.add(new EFeatures(attractionClassEntities.get(i).getMscid(), attractionClassEntities.get(i).getMcid(), "", attractionClassEntities.get(i).getMctitle_jp(), true, true, isSelected, true, "class"));
                        break;
                    default:
                        eFeaturesList.add(new EFeatures(attractionClassEntities.get(i).getMscid(), attractionClassEntities.get(i).getMcid(), "", attractionClassEntities.get(i).getMctitle_en(), true, true, isSelected, true, "class"));
                        break;
                }
            }
        }
        eFeaturesList.add(new EFeatures("", "", "", "", false, false, false, false, "n"));
        mEditFeatures.getTermData(msid, eFeaturesList);
    }

    @Override
    public void handleTermData(List<AttractionTermEntity> attractionTermEntities, List<EFeatures> eFeaturesList) {
        mEditFeatures.getItemData(attractionTermEntities, eFeaturesList);
    }

    @Override
    public void handleItemData(List<AttractionItemEntity> attractionItemEntities, List<AttractionTermEntity> attractionTermEntities, List<EFeatures> eFeaturesList) {
        int termSize = attractionTermEntities.size();
        int itemSize = attractionItemEntities.size();
        boolean isSelected;
        for (int i = 0; i < termSize; i++) {
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    eFeaturesList.add(new EFeatures(attractionTermEntities.get(i).getMtid(), "", "", attractionTermEntities.get(i).getMtitle_tw(), true, false, false, false, "term"));
                    break;
                case 2:
                    eFeaturesList.add(new EFeatures(attractionTermEntities.get(i).getMtid(), "", "", attractionTermEntities.get(i).getMtitle_ch(), true, false, false, false, "term"));
                    break;
                case 3:
                    eFeaturesList.add(new EFeatures(attractionTermEntities.get(i).getMtid(), "", "", attractionTermEntities.get(i).getMtitle_jp(), true, false, false, false, "term"));
                    break;
                default:
                    eFeaturesList.add(new EFeatures(attractionTermEntities.get(i).getMtid(), "", "", attractionTermEntities.get(i).getMtitle_en(), true, false, false, false, "term"));
                    break;
            }
            for (int j = 0; j < itemSize; j++) {
                if (attractionItemEntities.get(j).getMtid().equals(attractionTermEntities.get(i).getMtid())) {
                    isSelected = MyApplication.getAppData().getMstidList().contains(attractionItemEntities.get(j).getMstid());
                    switch (MyApplication.getLanguageIndex()) {
                        case 1:
                            eFeaturesList.add(new EFeatures(attractionItemEntities.get(j).getMstid(), attractionItemEntities.get(j).getMiid(), attractionItemEntities.get(j).getMtid(), attractionItemEntities.get(j).getMititle_tw(), true, true, isSelected, false, "item" + String.valueOf(i)));
                            break;
                        case 2:
                            eFeaturesList.add(new EFeatures(attractionItemEntities.get(j).getMstid(), attractionItemEntities.get(j).getMiid(), attractionItemEntities.get(j).getMtid(), attractionItemEntities.get(j).getMititle_ch(), true, true, isSelected, false, "item" + String.valueOf(i)));
                            break;
                        case 3:
                            eFeaturesList.add(new EFeatures(attractionItemEntities.get(j).getMstid(), attractionItemEntities.get(j).getMiid(), attractionItemEntities.get(j).getMtid(), attractionItemEntities.get(j).getMititle_jp(), true, true, isSelected, false, "item" + String.valueOf(i)));
                            break;
                        default:
                            eFeaturesList.add(new EFeatures(attractionItemEntities.get(j).getMstid(), attractionItemEntities.get(j).getMiid(), attractionItemEntities.get(j).getMtid(), attractionItemEntities.get(j).getMititle_en(), true, true, isSelected, false, "item" + String.valueOf(i)));
                            break;
                    }
                }
            }
            eFeaturesList.add(new EFeatures("", "", "", "", false, false, false, false, "n"));
        }
        ivEditFeatures.init(eFeaturesList);
    }

    @Override
    public void finishSend(GSendLove gSendLove) {
        if (gSendLove.getSave().equals("1")) {
            ivEditFeatures.finishSend();
        }
    }
}
