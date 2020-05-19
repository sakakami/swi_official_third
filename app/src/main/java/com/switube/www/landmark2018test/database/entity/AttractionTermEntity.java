package com.switube.www.landmark2018test.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "attractionTerm")
public class AttractionTermEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String msid;
    private String mtid;
    private String mtitle_en;
    private String mtitle_tw;
    private String mtitle_ch;
    private String mtitle_jp;
    public AttractionTermEntity(String msid, String mtid, String mtitle_en, String mtitle_tw,
                                String mtitle_ch, String mtitle_jp) {
        this.msid = msid;
        this.mtid = mtid;
        this.mtitle_en = mtitle_en;
        this.mtitle_tw = mtitle_tw;
        this.mtitle_ch = mtitle_ch;
        this.mtitle_jp = mtitle_jp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMsid(String msid) {
        this.msid = msid;
    }

    public void setMtid(String mtid) {
        this.mtid = mtid;
    }

    public void setMtitle_ch(String mtitle_ch) {
        this.mtitle_ch = mtitle_ch;
    }

    public void setMtitle_en(String mtitle_en) {
        this.mtitle_en = mtitle_en;
    }

    public void setMtitle_jp(String mtitle_jp) {
        this.mtitle_jp = mtitle_jp;
    }

    public void setMtitle_tw(String mtitle_tw) {
        this.mtitle_tw = mtitle_tw;
    }

    public int getId() {
        return id;
    }

    public String getMsid() {
        return msid;
    }

    public String getMtid() {
        return mtid;
    }

    public String getMtitle_ch() {
        return mtitle_ch;
    }

    public String getMtitle_en() {
        return mtitle_en;
    }

    public String getMtitle_jp() {
        return mtitle_jp;
    }

    public String getMtitle_tw() {
        return mtitle_tw;
    }
}
