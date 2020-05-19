package com.switube.www.landmark2018test.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "carbon_list")
public class CarbonEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String latitude = "";
    private String longitude = "";
    private String date_full = "";
    private String date_short = "";
    private String carbon_id = "";

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

    public void setCarbon_id(String carbon_id) { this.carbon_id = carbon_id; }
    public String getCarbon_id() { return carbon_id; }
}
