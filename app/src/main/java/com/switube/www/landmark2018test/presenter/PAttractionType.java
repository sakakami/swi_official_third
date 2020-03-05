package com.switube.www.landmark2018test.presenter;

import com.switube.www.landmark2018test.model.MAttractionType;
import com.switube.www.landmark2018test.presenter.callback.IPAttractionType;
import com.switube.www.landmark2018test.view.callback.IVAttractionType;
import com.switube.www.landmark2018test.database.entity.AttractionModeEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;

import java.util.ArrayList;
import java.util.List;

public class PAttractionType implements IPAttractionType {
    private IVAttractionType ivAttractionType;
    private MAttractionType mAttractionType;
    public PAttractionType(IVAttractionType IVAttractionType) {
        ivAttractionType = IVAttractionType;
        mAttractionType = new MAttractionType(this);
    }

    public void getListData() {
        mAttractionType.getModeListData();
    }

    private List<AttractionModeEntity> modeEntities = new ArrayList<>();
    @Override
    public void handleListDataOne(List<AttractionModeEntity> attractionModeEntities) {
        modeEntities.clear();
        modeEntities.addAll(attractionModeEntities);
        mAttractionType.getStyleListData();
    }

    @Override
    public void handleListDataTwo(List<AttractionStyleEntity> attractionStyleEntities) {
        ivAttractionType.init(modeEntities, attractionStyleEntities);
    }
}
