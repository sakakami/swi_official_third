package com.switube.www.landmark2018test.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tagQBA")
public class TagQBAEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String naid;
    private String qbnid;
    private String qbaid;
    private String assess;

    public TagQBAEntity(String naid, String qbnid, String qbaid, String assess) {
        this.naid = naid;
        this.qbnid = qbnid;
        this.qbaid = qbaid;
        this.assess = assess;
    }

    public String getQbnid() {
        return qbnid;
    }

    public String getAssess() {
        return assess;
    }

    public String getNaid() {
        return naid;
    }

    public String getQbaid() {
        return qbaid;
    }

    public int getId() {
        return id;
    }

    public void setQbnid(String qbnid) {
        this.qbnid = qbnid;
    }

    public void setAssess(String assess) {
        this.assess = assess;
    }

    public void setNaid(String naid) {
        this.naid = naid;
    }

    public void setQbaid(String qbaid) {
        this.qbaid = qbaid;
    }

    public void setId(int id) {
        this.id = id;
    }
}
