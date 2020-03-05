package com.switube.www.landmark2018test.entity;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class EClusterItem implements ClusterItem {
    private LatLng latLng;
    private String name;
    private String msid;
    private String spid;
    private String title;
    private String like = "0";
    private String phpname = "";
    public EClusterItem(LatLng latLng, String name, String msid, String spid, String title) {
        this.latLng = latLng;
        this.name = name;
        this.msid = msid;
        this.spid = spid;
        this.title = title;
    }

    @Override
    public LatLng getPosition() {
        return latLng;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSnippet() {
        return null;
    }

    public String getName() {
        return name;
    }

    public String getMsid() {
        return msid;
    }

    public String getSpid() {
        return spid;
    }

    public void setLike(String like) {
        this.like = like;
    }
    public String getLike() {
        return like;
    }

    public void setPhpname(String phpname) {
        this.phpname = phpname;
    }
    public String getPhpname() {
        return phpname;
    }
}
