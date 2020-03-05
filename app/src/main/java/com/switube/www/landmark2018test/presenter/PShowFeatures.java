package com.switube.www.landmark2018test.presenter;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;
import com.switube.www.landmark2018test.entity.EFeatures;
import com.switube.www.landmark2018test.model.MShowFeatures;
import com.switube.www.landmark2018test.presenter.callback.IPShowFeatures;
import com.switube.www.landmark2018test.view.callback.IVShowFeatures;

import java.util.ArrayList;
import java.util.List;

public class PShowFeatures implements IPShowFeatures {
    private IVShowFeatures ivShowFeatures;
    private MShowFeatures mShowFeatures;
    public PShowFeatures(IVShowFeatures ivShowFeatures) {
        this.ivShowFeatures = ivShowFeatures;
        mShowFeatures = new MShowFeatures(this);
    }

    public void getStyleData(String msid, String mscid, List<String> mtidList, List<String> mstidList) {
        mShowFeatures.getStyleData(msid, mscid, mtidList, mstidList);
    }

    @Override
    public void handleStyleData(AttractionStyleEntity attractionStyleEntity, String msid, String mscid, List<String> mtidList, List<String> mstidList) {
        List<EFeatures> eFeaturesList = new ArrayList<>();
        switch (MyApplication.getLanguageIndex()) {
            case 1:
                eFeaturesList.add(new EFeatures("", "", "", attractionStyleEntity.getMstitle_tw(), false, false, false, false, "t"));
                break;
            case 2:
                eFeaturesList.add(new EFeatures("", "", "", attractionStyleEntity.getMstitle_ch(), false, false, false, false, "t"));
                break;
            case 3:
                eFeaturesList.add(new EFeatures("", "", "", attractionStyleEntity.getMstitle_jp(), false, false, false, false, "t"));
                break;
            default:
                eFeaturesList.add(new EFeatures("", "", "", attractionStyleEntity.getMstitle_en(), false, false, false, false, "t"));
                break;
        }
        mShowFeatures.getClassData(msid, eFeaturesList, mscid, mtidList, mstidList);
    }

    @Override
    public void handleClassData(List<AttractionClassEntity> attractionClassEntities, String msid, List<EFeatures> eFeaturesList, String mscid, List<String> mtidList, List<String> mstidList) {
        int size = attractionClassEntities.size();
        for (int i = 0; i < size; i++) {
            if (attractionClassEntities.get(i).getMscid().equals(mscid)) {
                switch (MyApplication.getLanguageIndex()) {
                    case 1:
                        eFeaturesList.add(new EFeatures("", "", "", attractionClassEntities.get(i).getMctitle_tw(), true, false, false, false, "class"));
                        break;
                    case 2:
                        eFeaturesList.add(new EFeatures("", "", "", attractionClassEntities.get(i).getMctitle_ch(), true, false, false, false, "class"));
                        break;
                    case 3:
                        eFeaturesList.add(new EFeatures("", "", "", attractionClassEntities.get(i).getMctitle_jp(), true, false, false, false, "class"));
                        break;
                    default:
                        eFeaturesList.add(new EFeatures("", "", "", attractionClassEntities.get(i).getMctitle_en(), true, false, false, false, "class"));
                        break;
                }
            }
        }
        eFeaturesList.add(new EFeatures("", "", "", "", false, false, false, false, "n"));
        mShowFeatures.getTermData(msid, eFeaturesList, mtidList, mstidList);
    }

    @Override
    public void handleTermData(List<AttractionTermEntity> attractionTermEntities, List<EFeatures> eFeaturesList, String msid, List<String> mtidList, List<String> mstidList) {
        mShowFeatures.getItemData(eFeaturesList, attractionTermEntities, mtidList, mstidList);
    }

    @Override
    public void handleItemData(List<AttractionItemEntity> attractionItemEntities, List<EFeatures> eFeaturesList, List<AttractionTermEntity> attractionTermEntities, List<String> mtidList, List<String> mstidList) {
        for (int i = 0; i < attractionTermEntities.size(); i++) {
            if (mtidList.contains(attractionTermEntities.get(i).getMtid())) {
                switch (MyApplication.getLanguageIndex()) {
                    case 1:
                        eFeaturesList.add(new EFeatures("", "", "", attractionTermEntities.get(i).getMtitle_tw(), false, false, false, false, "term"));
                        break;
                    case 2:
                        eFeaturesList.add(new EFeatures("", "", "", attractionTermEntities.get(i).getMtitle_ch(), false, false, false, false, "term"));
                        break;
                    case 3:
                        eFeaturesList.add(new EFeatures("", "", "", attractionTermEntities.get(i).getMtitle_jp(), false, false, false, false, "term"));
                        break;
                    default:
                        eFeaturesList.add(new EFeatures("", "", "", attractionTermEntities.get(i).getMtitle_en(), false, false, false, false, "term"));
                        break;
                }
                for (int j = 0; j < attractionItemEntities.size(); j++) {
                    if (mstidList.contains(attractionItemEntities.get(j).getMstid()) && mtidList.get(i).equals(attractionItemEntities.get(j).getMtid())) {
                        switch (MyApplication.getLanguageIndex()) {
                            case 1:
                                eFeaturesList.add(new EFeatures("", "", "", attractionItemEntities.get(j).getMititle_tw(), true, false, false, false, "item"));
                                break;
                            case 2:
                                eFeaturesList.add(new EFeatures("", "", "", attractionItemEntities.get(j).getMititle_ch(), true, false, false, false, "item"));
                                break;
                            case 3:
                                eFeaturesList.add(new EFeatures("", "", "", attractionItemEntities.get(j).getMititle_jp(), true, false, false, false, "item"));
                                break;
                            default:
                                eFeaturesList.add(new EFeatures("", "", "", attractionItemEntities.get(j).getMititle_en(), true, false, false, false, "item"));
                                break;
                        }
                    }
                }
                eFeaturesList.add(new EFeatures("", "", "", "", false, false, false, false, "n"));
            } else {
                attractionTermEntities.remove(i);
                i--;
            }
        }
        ivShowFeatures.init(eFeaturesList);
    }
}
