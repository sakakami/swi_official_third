package com.switube.www.swiofficialthird.create.presenter;

import android.content.Context;

import com.switube.www.swiofficialthird.create.model.AttractionTypeModel;
import com.switube.www.swiofficialthird.create.view.IAttractionType;
import com.switube.www.swiofficialthird.database.entity.AttractionModeEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;

import java.util.ArrayList;
import java.util.List;

public class AttractionTypePresenter implements IAttractionTypePresenter {
    private IAttractionType mIAttractionType;
    private AttractionTypeModel mAttractionTypeModel;
    public AttractionTypePresenter(IAttractionType iAttractionType) {
        mIAttractionType = iAttractionType;
        mAttractionTypeModel = new AttractionTypeModel(this);
    }

    public void getListData(Context context) {
        mAttractionTypeModel.getModeListData(context);
    }

    private List<AttractionModeEntity> modeEntities = new ArrayList<>();
    @Override
    public void handleListDataOne(Context context, List<AttractionModeEntity> attractionModeEntities) {
        modeEntities.clear();
        modeEntities.addAll(attractionModeEntities);
        mAttractionTypeModel.getStyleListData(context);
    }

    @Override
    public void handleListDataTwo(List<AttractionStyleEntity> attractionStyleEntities) {
        mIAttractionType.init(modeEntities, attractionStyleEntities);
    }
}
