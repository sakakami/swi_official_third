package com.switube.www.landmark2018test.gson;

import com.google.gson.annotations.SerializedName;

public class GCreateAttraction {
    private String spid = "";
    @SerializedName("Save")
    private String save = "";

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public String getSpid() {
        return spid;
    }

    public String getSave() {
        return save;
    }
}
