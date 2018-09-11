package com.switube.www.swiofficialthird.util;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class ClusterItemUtil implements ClusterItem {
    private LatLng latLng;
    private String title;
    private String name;
    private String time1;
    private String time2;
    private String time3;
    private String time4;
    private String rating;
    private String msid;
    private String address;
    public ClusterItemUtil(LatLng latLng, String title, String name, String time1, String time2,
                           String time3, String time4, String rating, String msid, String address) {
        this.latLng = latLng;
        this.title = title;
        this.name = name;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.time4 = time4;
        this.rating = rating;
        this.msid = msid;
        this.address = address;
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

    public String getTime1() {
        return time1;
    }

    public String getTime2() {
        return time2;
    }

    public String getTime3() {
        return time3;
    }

    public String getTime4() {
        return time4;
    }

    public String getRating() {
        return rating;
    }

    public String getMsid() {
        return msid;
    }

    public String getAddress() {
        return address;
    }
}
