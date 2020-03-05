package com.switube.www.landmark2018test.view.callback;

import com.switube.www.landmark2018test.database.entity.TagQBAEntity;
import com.switube.www.landmark2018test.database.entity.TagQBNEntity;
import com.switube.www.landmark2018test.entity.ETagQB;

import java.util.List;

public interface IVTag {
    void init(List<ETagQB> eTagQB);
    void initN(List<TagQBNEntity> tagQBNEntities);
    void initA(List<TagQBAEntity> tagQBAEntities);
    void refreshAdapter(List<TagQBNEntity> tagQBNEntities, List<TagQBAEntity> tagQBAEntities);
    void finishSave();
}
