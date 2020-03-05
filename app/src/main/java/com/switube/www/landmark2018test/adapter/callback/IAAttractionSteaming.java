package com.switube.www.landmark2018test.adapter.callback;

import java.util.List;

public interface IAAttractionSteaming {
    void handleSwitchPage(int i);
    void handleLikeOrUnlike(String artid, String isLike, int num);
    void handleClickPhoto(List<String> photo);
    void handleClickPhoto(int index);
    void handleLinkClick(String attractionId);
    void handleDeleteComment(int index);
    void handleEdit(int index, boolean isComment);
}
