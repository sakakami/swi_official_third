package com.switube.www.landmark2018test.entity;

import java.util.ArrayList;

public class EEcoList {
    private String date = "";
    private String name = "";
    private String distance = "";
    private String speed = "";
    private ArrayList<EEco> list = new ArrayList<>();

    public void setDate(String date) { this.date = date; }

    public String getDate() { return date; }

    public void setName(String name) { this.name = name; }

    public String getName() { return name; }

    public void setDistance(String distance) { this.distance = distance; }
    public String getDistance() { return distance; }

    public void setList(ArrayList<EEco> list) { this.list = list; }
    public ArrayList<EEco> getList() { return list; }

    public void setSpeed(String speed) { this.speed = speed; }
    public String getSpeed() { return speed; }
}
