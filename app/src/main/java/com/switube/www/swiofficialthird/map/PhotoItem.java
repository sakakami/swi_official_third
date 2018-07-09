package com.switube.www.swiofficialthird.map;

public class PhotoItem {
    private String name;
    private String data;

    public PhotoItem(String name, String data) {
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
