package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.entity.ECreateAttraction;
import com.switube.www.landmark2018test.gson.GAttractionDataGoogle;

import java.util.List;

public interface IVCreateAttraction {
    void init(List<GAttractionDataGoogle> detailEntities);
    void init(ECreateAttraction entity);
    void savePlaceId(String id);
}
