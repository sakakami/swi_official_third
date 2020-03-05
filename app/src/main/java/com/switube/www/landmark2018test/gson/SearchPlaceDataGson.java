package com.switube.www.landmark2018test.gson;

import java.util.List;

public class SearchPlaceDataGson {
    private String msid;
    private List<String> mscid;
    private List<String> mstid;
    public SearchPlaceDataGson(String msid, List<String> mscid, List<String> mstid) {
        this.mscid = mscid;
        this.msid = msid;
        this.mstid = mstid;
    }

    public String getMsid() {
        return msid;
    }

    public List<String> getMscid() {
        return mscid;
    }

    public List<String> getMstid() {
        return mstid;
    }

    public void setMstid(List<String> mstid) {
        this.mstid = mstid;
    }

    public void setMsid(String msid) {
        this.msid = msid;
    }

    public void setMscid(List<String> mscid) {
        this.mscid = mscid;
    }
}
