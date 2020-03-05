package com.switube.www.landmark2018test.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "attractionClass")
public class AttractionClassEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String mscid;
    private String msid;
    private String mcid;
    private String mctitle_en;
    private String mctitle_tw;
    private String mctitle_ch;
    private String mctitle_jp;
    public AttractionClassEntity(String mscid, String msid, String mcid, String mctitle_en,
                                 String mctitle_tw, String mctitle_ch, String mctitle_jp) {
        this.mscid = mscid;
        this.msid = msid;
        this.mcid = mcid;
        this.mctitle_en = mctitle_en;
        this.mctitle_tw = mctitle_tw;
        this.mctitle_ch = mctitle_ch;
        this.mctitle_jp = mctitle_jp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMscid(String mscid) {
        this.mscid = mscid;
    }

    public void setMsid(String msid) {
        this.msid = msid;
    }

    public void setMcid(String mcid) {
        this.mcid = mcid;
    }

    public void setMctitle_en(String mctitle_en) {
        this.mctitle_en = mctitle_en;
    }

    public void setMctitle_tw(String mctitle_tw) {
        this.mctitle_tw = mctitle_tw;
    }

    public void setMctitle_ch(String mctitle_ch) {
        this.mctitle_ch = mctitle_ch;
    }

    public void setMctitle_jp(String mctitle_jp) {
        this.mctitle_jp = mctitle_jp;
    }

    public int getId() {
        return id;
    }

    public String getMsid() {
        return msid;
    }

    public String getMscid() {
        return mscid;
    }

    public String getMcid() {
        return mcid;
    }

    public String getMctitle_jp() {
        return mctitle_jp;
    }

    public String getMctitle_ch() {
        return mctitle_ch;
    }

    public String getMctitle_tw() {
        return mctitle_tw;
    }

    public String getMctitle_en() {
        return mctitle_en;
    }
}
