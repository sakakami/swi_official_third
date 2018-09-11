package com.switube.www.swiofficialthird.map.presenter;

import android.content.Context;

import com.switube.www.swiofficialthird.database.entity.AttractionClassEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionItemEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionTermEntity;
import com.switube.www.swiofficialthird.map.model.FindSettingModel;
import com.switube.www.swiofficialthird.map.view.IFindSettingFragment;

import java.util.ArrayList;
import java.util.List;

public class FindSettingPresenter implements IFindSettingPresenter {
    private IFindSettingFragment mIFindSettingFragment;
    private FindSettingModel mFindSettingModel;
    public FindSettingPresenter(IFindSettingFragment iFindSettingFragment) {
        mIFindSettingFragment = iFindSettingFragment;
        mFindSettingModel = new FindSettingModel(this);
    }

    private String mSid;
    public void getDataOne(Context context, String msid) {
        mSid = msid;
        mFindSettingModel.getDataOne(context, msid);
    }

    private List<AttractionClassEntity> mClassList = new ArrayList<>();
    @Override
    public void handleDataOne(Context context, List<AttractionClassEntity> attractionClassEntities) {
        mClassList.clear();
        mClassList.addAll(attractionClassEntities);
        mFindSettingModel.getDataTwo(context, mSid);
    }

    private List<AttractionTermEntity> mTermList = new ArrayList<>();
    @Override
    public void handleDataTwo(Context context, List<AttractionTermEntity> attractionTermEntities) {
        mTermList.clear();
        if (attractionTermEntities.size() != 0) {
            mTermList.addAll(attractionTermEntities);
            StringBuilder stringBuilder = new StringBuilder(attractionTermEntities.get(0).getMtid());
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            stringBuilder.append("%");
            mFindSettingModel.getDataThree(context, stringBuilder.toString());
        } else {
            mIFindSettingFragment.init(mClassList, mTermList, new ArrayList<AttractionItemEntity>());
        }
    }

    @Override
    public void handleDataThree(List<AttractionItemEntity> attractionItemEntities) {
        mIFindSettingFragment.init(mClassList, mTermList, attractionItemEntities);
    }
}
