package com.switube.www.landmark2018test.entity;

public class EFeatures {
    private String id;
    private String id2;
    private String id3;
    private String title;
    private String from;
    private boolean canShow;
    private boolean canSelect;
    private boolean isSelect;
    private boolean isSingle;

    public EFeatures(String id, String id2, String id3, String title, boolean canShow, boolean canSelect, boolean isSelect, boolean isSingle, String from) {
        this.id = id;
        this.id2 = id2;
        this.id3 = id3;
        this.title = title;
        this.canShow = canShow;
        this.canSelect = canSelect;
        this.isSelect = isSelect;
        this.isSingle = isSingle;
        this.from = from;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCanSelect() {
        return canSelect;
    }

    public boolean isCanShow() {
        return canShow;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getFrom() {
        return from;
    }

    public String getId2() {
        return id2;
    }

    public String getId3() {
        return id3;
    }
}
