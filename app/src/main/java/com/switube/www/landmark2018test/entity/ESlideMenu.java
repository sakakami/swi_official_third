package com.switube.www.landmark2018test.entity;

public class ESlideMenu {
    private int icon;
    private String title;
    private boolean hasSub;
    private boolean isSub;
    private String openId;
    private boolean isOpened;
    private String msid;
    public ESlideMenu(int icon, String title, boolean hasSub, boolean isSub, String openId, boolean isOpened, String msid) {
        this.icon = icon;
        this.title = title;
        this.hasSub = hasSub;
        this.isSub = isSub;
        this.openId = openId;
        this.isOpened = isOpened;
        this.msid = msid;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public boolean isHasSub() {
        return hasSub;
    }

    public boolean isSub() {
        return isSub;
    }

    public String getOpenId() {
        return openId;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public String getMsid() {
        return msid;
    }
}
