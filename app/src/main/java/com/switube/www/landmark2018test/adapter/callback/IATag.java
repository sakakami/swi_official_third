package com.switube.www.landmark2018test.adapter.callback;

import com.switube.www.landmark2018test.database.entity.TagQBAEntity;
import com.switube.www.landmark2018test.database.entity.TagQBNEntity;

public interface IATag {
    void handleAddTagA(TagQBNEntity tagQBNEntity);
    void handleAddTagB(TagQBAEntity tagQBAEntity);
    void handleDefault();
    void handleTagSelected();
    void handleGetData(String qbid);
}
