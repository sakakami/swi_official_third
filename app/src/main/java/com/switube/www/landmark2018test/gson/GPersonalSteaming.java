package com.switube.www.landmark2018test.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GPersonalSteaming {
    @SerializedName("UName")
    private String name = "";
    @SerializedName("UImage")
    private String image = "";
    @SerializedName("Data")
    private List<GInfoData.Article> data = new ArrayList<>();
    @SerializedName("PicData")
    private List<String> picData = new ArrayList<>();

    public void setData(List<GInfoData.Article> data) {
        this.data = data;
    }
    public List<GInfoData.Article> getData() {
        return data;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPicData(List<String> picData) {
        this.picData = picData;
    }
    public List<String> getPicData() {
        return picData;
    }
}
