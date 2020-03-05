package com.switube.www.landmark2018test.entity;

import java.util.ArrayList;

public class ECarBonList {
    private String date = "";
    private String name = "";
    private String distance = "";
    private String carbon = "";
    private ArrayList<ECarbon> list = new ArrayList<>();

    public void setDate(String date) { this.date = date; }

    public String getDate() { return date; }

    public void setName(String name) { this.name = name; }

    public String getName() { return name; }

    public void setDistance(String distance) { this.distance = distance; }
    public String getDistance() { return distance; }

    public void setCarbon(String carbon) { this.carbon = carbon; }
    public String getCarbon() { return carbon; }

    public void setList(ArrayList<ECarbon> list) { this.list = list; }
    public ArrayList<ECarbon> getList() { return list; }
}
