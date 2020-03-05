package com.switube.www.landmark2018test.gson;

import com.google.gson.annotations.SerializedName;

public class GSendLove {
    @SerializedName("Save")
    private String save = "";
    private String sucid = "";

    public void setSave(String save) {
        this.save = save;
    }
    public String getSave() {
        return save;
    }

    public void setSucid(String sucid) {
        this.sucid = sucid;
    }
    public String getSucid() {
        return sucid;
    }
}
