package com.switube.www.landmark2018test.entity;

public class ESwapData {
    private String id = "";
    private String title = "";
    private boolean isMyCollection = false;

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setMyCollection(boolean myCollection) {
        isMyCollection = myCollection;
    }
    public boolean isMyCollection() {
        return isMyCollection;
    }
}
