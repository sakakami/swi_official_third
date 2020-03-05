package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.database.entity.TagQBAEntity;
import com.switube.www.landmark2018test.database.entity.TagQBNEntity;
import com.switube.www.landmark2018test.gson.GTagData;

import java.util.List;

public interface IPTag {
    void handleTagGsonData(GTagData gTagData);
    void handleQBNData(List<TagQBNEntity> tagQBNEntities);
    void handleQBAData(List<TagQBAEntity> tagQBAEntities);
}
