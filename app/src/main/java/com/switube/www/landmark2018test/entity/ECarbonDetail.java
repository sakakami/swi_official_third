package com.switube.www.landmark2018test.entity;

import com.github.mikephil.charting.data.Entry;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class ECarbonDetail {
    private ArrayList<LatLng> latLngs = new ArrayList<>();
    private ArrayList<Double> distances = new ArrayList<>();
    private ArrayList<Double> speeds = new ArrayList<>();
    private ArrayList<Double> carbons = new ArrayList<>();
    private ArrayList<Double> distanceAdd = new ArrayList<>();
    private ArrayList<Entry> spdEnList = new ArrayList<>();
    private ArrayList<Entry> cbEnList = new ArrayList<>();
    private ArrayList<String> disForString = new ArrayList<>();
    private String right = "";
    private String center = "";
    private String lift = "";
    private String name = "";
    private String dis = "";
    private String tool = "";
    private String carbon = "";

    public void setLatLngs(ArrayList<LatLng> latLngs) { this.latLngs = latLngs; }
    public ArrayList<LatLng> getLatLngs() { return latLngs; }

    public void setDistances(ArrayList<Double> distances) { this.distances = distances; }
    public ArrayList<Double> getDistances() { return distances; }

    public void setSpeeds(ArrayList<Double> speeds) { this.speeds = speeds; }
    public ArrayList<Double> getSpeeds() { return speeds; }

    public void setCarbons(ArrayList<Double> carbons) { this.carbons = carbons; }
    public ArrayList<Double> getCarbons() { return carbons; }

    public void setRight(String right) { this.right = right; }
    public String getRight() { return right; }

    public void setCenter(String center) { this.center = center; }
    public String getCenter() { return center; }

    public void setLift(String lift) { this.lift = lift; }
    public String getLift() { return lift; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setDis(String dis) { this.dis = dis; }
    public String getDis() { return dis; }

    public void setCarbon(String carbon) { this.carbon = carbon; }
    public String getCarbon() { return carbon; }

    public void setTool(String tool) { this.tool = tool; }
    public String getTool() { return tool; }

    public ArrayList<Double> getDistanceAdd() { return distanceAdd; }
    public void setDistanceAdd(ArrayList<Double> distanceAdd) { this.distanceAdd = distanceAdd; }

    public void setSpdEnList(ArrayList<Entry> spdEnList) { this.spdEnList = spdEnList; }
    public ArrayList<Entry> getSpdEnList() { return spdEnList; }

    public void setCbEnList(ArrayList<Entry> cbEnList) { this.cbEnList = cbEnList; }
    public ArrayList<Entry> getCbEnList() { return cbEnList; }

    public void setDisForString(ArrayList<String> disForString) { this.disForString = disForString; }
    public ArrayList<String> getDisForString() { return disForString; }
}
