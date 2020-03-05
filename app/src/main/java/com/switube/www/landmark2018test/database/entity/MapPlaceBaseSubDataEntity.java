package com.switube.www.landmark2018test.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "placeBaseSubData")
public class MapPlaceBaseSubDataEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String mstid;
    private String mtid;
    private String miid;
    private String spid;

    public void setMstid(String mstid) {
        this.mstid = mstid;
    }

    public void setMiid(String miid) {
        this.miid = miid;
    }

    public void setMtid(String mtid) {
        this.mtid = mtid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public String getMstid() {
        return mstid;
    }

    public String getMiid() {
        return miid;
    }

    public String getMtid() {
        return mtid;
    }

    public int getId() {
        return id;
    }

    public String getSpid() {
        return spid;
    }
}
