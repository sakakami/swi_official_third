package com.switube.www.swiofficialthird.util;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class ClusterItemUtil implements ClusterItem {
    private LatLng latLng;
    private String trid;
    private String title;
    public ClusterItemUtil(LatLng latLng, String trid, String title) {
        this.latLng = latLng;
        this.trid = trid;
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

    public String getTrid() {
        return trid;
    }
}
