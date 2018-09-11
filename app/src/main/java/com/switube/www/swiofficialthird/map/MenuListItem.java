package com.switube.www.swiofficialthird.map;

public class MenuListItem {
    private int icon;
    private String title;
    private boolean hasSub;
    private boolean isSub;
    private String openId;
    private boolean isOpened;
    public MenuListItem(int icon, String title, boolean hasSub, boolean isSub, String openId, boolean isOpened) {
        this.icon = icon;
        this.title = title;
        this.hasSub = hasSub;
        this.isSub = isSub;
        this.openId = openId;
        this.isOpened = isOpened;
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
}
