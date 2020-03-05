package com.switube.www.landmark2018test.entity;

import com.google.android.gms.maps.model.LatLng;

public class EMobileMusic {
    private String title = "";
    private String like = "";
    private String photo = "";
    private String count = "";
    private String webid = "";
    private String phpname = "";
    private boolean selected = false;
    private LatLng latLng = null;

    public void setCount(String count) {
        this.count = count;
    }
    public String getCount() {
        return count;
    }

    public void setLike(String like) {
        this.like = like;
    }
    public String getLike() {
        return like;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getPhoto() {
        return photo;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setWebid(String webid) {
        this.webid = webid;
    }
    public String getWebid() {
        return webid;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
    public LatLng getLatLng() {
        return latLng;
    }

    public void setPhpname(String phpname) {
        this.phpname = phpname;
    }
    public String getPhpname() {
        return phpname;
    }
}
