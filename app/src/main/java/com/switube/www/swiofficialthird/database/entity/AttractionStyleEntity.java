package com.switube.www.swiofficialthird.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "attractionStyle")
public class AttractionStyleEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String menuid;
    private String mmspid;
    private String mmid;
    private String msid;
    private String mstitle_en;
    private String mstitle_tw;
    private String mstitle_ch;
    private String mstitle_jp;

    public AttractionStyleEntity(String menuid, String mmspid, String mmid, String msid,
                                 String mstitle_en, String mstitle_tw, String mstitle_ch, String mstitle_jp) {
        this.menuid = menuid;
        this.mmspid = mmspid;
        this.mmid = mmid;
        this.msid = msid;
        this.mstitle_en = mstitle_en;
        this.mstitle_tw = mstitle_tw;
        this.mstitle_ch = mstitle_ch;
        this.mstitle_jp = mstitle_jp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public void setMmspid(String mmspid) {
        this.mmspid = mmspid;
    }

    public void setMmid(String mmid) {
        this.mmid = mmid;
    }

    public void setMsid(String msid) {
        this.msid = msid;
    }

    public void setMstitle_en(String mstitle_en) {
        this.mstitle_en = mstitle_en;
    }

    public void setMstitle_tw(String mstitle_tw) {
        this.mstitle_tw = mstitle_tw;
    }

    public void setMstitle_ch(String mstitle_ch) {
        this.mstitle_ch = mstitle_ch;
    }

    public void setMstitle_jp(String mstitle_jp) {
        this.mstitle_jp = mstitle_jp;
    }

    public int getId() {
        return id;
    }

    public String getMmid() {
        return mmid;
    }

    public String getMenuid() {
        return menuid;
    }

    public String getMmspid() {
        return mmspid;
    }

    public String getMsid() {
        return msid;
    }

    public String getMstitle_en() {
        return mstitle_en;
    }

    public String getMstitle_tw() {
        return mstitle_tw;
    }

    public String getMstitle_ch() {
        return mstitle_ch;
    }

    public String getMstitle_jp() {
        return mstitle_jp;
    }
}
