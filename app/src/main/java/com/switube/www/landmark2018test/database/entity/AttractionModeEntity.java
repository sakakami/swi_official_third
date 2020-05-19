package com.switube.www.landmark2018test.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "attractionMode")
public class AttractionModeEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String mmid;
    private String mmtitle_en;
    private String mmtitle_tw;
    private String mmtitle_ch;
    private String mmtitle_jp;

    public AttractionModeEntity(String mmid, String mmtitle_en, String mmtitle_tw,
                                String mmtitle_ch, String mmtitle_jp) {
        this.mmid = mmid;
        this.mmtitle_en = mmtitle_en;
        this.mmtitle_tw = mmtitle_tw;
        this.mmtitle_ch = mmtitle_ch;
        this.mmtitle_jp = mmtitle_jp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMmid(String mmid) {
        this.mmid = mmid;
    }

    public void setMmtitle_en(String mmtitle_en) {
        this.mmtitle_en = mmtitle_en;
    }

    public void setMmtitle_tw(String mmtitle_tw) {
        this.mmtitle_tw = mmtitle_tw;
    }

    public void setMmtitle_ch(String mmtitle_ch) {
        this.mmtitle_ch = mmtitle_ch;
    }

    public void setMmtitle_jp(String mmtitle_jp) {
        this.mmtitle_jp = mmtitle_jp;
    }

    public int getId() {
        return id;
    }

    public String getMmid() {
        return mmid;
    }

    public String getMmtitle_en() {
        return mmtitle_en;
    }

    public String getMmtitle_tw() {
        return mmtitle_tw;
    }

    public String getMmtitle_ch() {
        return mmtitle_ch;
    }

    public String getMmtitle_jp() {
        return mmtitle_jp;
    }
}
