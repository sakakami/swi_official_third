package com.switube.www.landmark2018test.entity;

public class EPhotoList {
    private String name;
    private String data;

    public EPhotoList(String name, String data) {
        this.data = data;
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public String getName() {
        return name;
    }
}
