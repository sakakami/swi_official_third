package com.switube.www.landmark2018test.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "eco_list")
public class EcoEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String latitude = "";
    private String longitude = "";
    private String date_full = "";
    private String date_short = "";
    private String eco_id = "";
    private String distance = "";
    private String speed = "";

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public void setLatitude(String latitude) { this.latitude = latitude; }
    public String getLatitude() { return latitude; }

    public void setLongitude(String longitude) { this.longitude = longitude; }
    public String getLongitude() { return longitude; }

    public void setDate_full(String date_full) { this.date_full = date_full; }

    public String getDate_full() { return date_full; }

    public void setDate_short(String date_short) { this.date_short = date_short; }
    public String getDate_short() { return date_short; }

    public void setEco_id(String eco_id) { this.eco_id = eco_id; }
    public String getEco_id() { return eco_id; }

    public void setDistance(String distance) { this.distance = distance; }
    public String getDistance() { return distance; }

    public void setSpeed(String speed) { this.speed = speed; }
    public String getSpeed() { return speed; }
}
