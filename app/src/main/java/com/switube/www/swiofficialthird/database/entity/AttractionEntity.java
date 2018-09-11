package com.switube.www.swiofficialthird.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "attraction")
public class AttractionEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String trId;
    private String trName;
    private String trAddress;
    private String trGoId;
    private String tcId;
    private String latitude;
    private String longitude;
    private String trPhone;
    private String trUrl;
    private String score;
    public AttractionEntity(String trId, String trName, String trAddress, String trGoId,
                            String tcId, String latitude, String longitude, String trPhone,
                            String trUrl, String score) {
        this.trId = trId;
        this.trName = trName;
        this.trAddress = trAddress;
        this.trGoId = trGoId;
        this.tcId = tcId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.trPhone = trPhone;
        this.trUrl = trUrl;
        this.score = score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTrId() {
        return trId;
    }

    public String getTrName() {
        return trName;
    }

    public String getTrAddress() {
        return trAddress;
    }

    public String getTrGoId() {
        return trGoId;
    }

    public String getTcId() {
        return tcId;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getTrPhone() {
        return trPhone;
    }

    public String getTrUrl() {
        return trUrl;
    }

    public String getScore() {
        return score;
    }
}
