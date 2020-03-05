package com.switube.www.landmark2018test.adapter.callback;

import com.switube.www.landmark2018test.gson.GInfoData;

import java.util.List;

public interface IAInfo {
    void handleSwitchPage(int i);
    void handleLikeOrUnlike(String artid, String isLike, int num);
    void handleRating(String artid, String rate);
    void saveSwiTubeUrl(String url);
    void handleSelectArtid(int index);
    void handViewCustomerPhoto(List<String> photo);
    void handSignIn();
    void handleClickHeadPhoto(String maid, String wsid, String type, String privacy);
    void handleLinkClick(String attractionId);
    void handleClickSave();
    void handleClickCollect(boolean isAdd, String sucid);
    void handleEdit(int index, GInfoData gInfoData, boolean isComment);
    void handleDeleteComment(int index, GInfoData gInfoData);
}
