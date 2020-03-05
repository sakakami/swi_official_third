package com.switube.www.landmark2018test.adapter.callback;

public interface IAAttractionComments {
    void handleSignIn();
    void handleClickLike(int index, String like);
    void handleClickMessage(int index);
    void handleEditComments(int index);
    void handleDelComments(int index);
}
