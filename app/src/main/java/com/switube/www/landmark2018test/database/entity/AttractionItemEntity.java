package com.switube.www.landmark2018test.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "attractionItem")
public class AttractionItemEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String mstid;
    private String mtid;
    private String miid;
    private String mititle_en;
    private String mititle_tw;
    private String mititle_ch;
    private String mititle_jp;
    public AttractionItemEntity(String mstid, String mtid, String miid, String mititle_en,
                                String mititle_tw, String mititle_ch, String mititle_jp) {
        this.mstid = mstid;
        this.mtid = mtid;
        this.miid = miid;
        this.mititle_en = mititle_en;
        this.mititle_tw = mititle_tw;
        this.mititle_ch = mititle_ch;
        this.mititle_jp = mititle_jp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMtid(String mtid) {
        this.mtid = mtid;
    }

    public void setMititle_jp(String mititle_jp) {
        this.mititle_jp = mititle_jp;
    }

    public void setMititle_ch(String mititle_ch) {
        this.mititle_ch = mititle_ch;
    }

    public void setMititle_tw(String mititle_tw) {
        this.mititle_tw = mititle_tw;
    }

    public void setMititle_en(String mititle_en) {
        this.mititle_en = mititle_en;
    }

    public void setMiid(String miid) {
        this.miid = miid;
    }

    public void setMstid(String mstid) {
        this.mstid = mstid;
    }

    public int getId() {
        return id;
    }

    public String getMtid() {
        return mtid;
    }

    public String getMititle_jp() {
        return mititle_jp;
    }

    public String getMititle_ch() {
        return mititle_ch;
    }

    public String getMititle_tw() {
        return mititle_tw;
    }

    public String getMititle_en() {
        return mititle_en;
    }

    public String getMiid() {
        return miid;
    }

    public String getMstid() {
        return mstid;
    }
}
