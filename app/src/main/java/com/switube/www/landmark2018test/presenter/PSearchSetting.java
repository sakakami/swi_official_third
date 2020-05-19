package com.switube.www.landmark2018test.presenter;

import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;
import com.switube.www.landmark2018test.model.MSearchSetting;
import com.switube.www.landmark2018test.presenter.callback.IPSearchSetting;
import com.switube.www.landmark2018test.view.callback.IVSearchSetting;

import java.util.ArrayList;
import java.util.List;

public class PSearchSetting implements IPSearchSetting {
    private IVSearchSetting iVSearchSetting;
    private MSearchSetting mSearchSetting;
    public PSearchSetting(IVSearchSetting IVSearchSetting) {
        iVSearchSetting = IVSearchSetting;
        mSearchSetting = new MSearchSetting(this);
    }

    private String mSid;
    public void getDataOne(String msid) {
        mSid = msid;
        mSearchSetting.getDataOne(msid);
    }

    private List<AttractionClassEntity> mClassList = new ArrayList<>();
    @Override
    public void handleDataOne(List<AttractionClassEntity> attractionClassEntities) {
        mClassList.clear();
        mClassList.addAll(attractionClassEntities);
        mSearchSetting.getDataTwo(mSid);
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
            mSearchSetting.getDataThree(stringBuilder.toString());
        } else {
            iVSearchSetting.init(mClassList, mTermList, new ArrayList<>());
        }
    }

    @Override
    public void handleDataThree(List<AttractionItemEntity> attractionItemEntities) {
        iVSearchSetting.init(mClassList, mTermList, attractionItemEntities);
    }
}
