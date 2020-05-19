package com.switube.www.landmark2018test.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tagQBN")
public class TagQBNEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String qbid;
    private String qbnid;
    private String norm;

    public TagQBNEntity(String qbid, String qbnid, String norm) {
        this.qbid = qbid;
        this.qbnid = qbnid;
        this.norm = norm;
    }

    public int getId() {
        return id;
    }

    public String getNorm() {
        return norm;
    }

    public String getQbid() {
        return qbid;
    }

    public String getQbnid() {
        return qbnid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNorm(String norm) {
        this.norm = norm;
    }

    public void setQbid(String qbid) {
        this.qbid = qbid;
    }

    public void setQbnid(String qbnid) {
        this.qbnid = qbnid;
    }
}
