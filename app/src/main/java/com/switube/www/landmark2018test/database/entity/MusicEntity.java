package com.switube.www.landmark2018test.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "musicDetail")
public class MusicEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String webid;
    private String stid;
    private String name;
    private String xx;
    private String yy;
    private String atdate;
    private String lengths;
    private String views;
    public MusicEntity(String webid, String stid, String name, String xx, String yy, String atdate, String lengths, String views) {
        this.webid = webid;
        this.stid = stid;
        this.name = name;
        this.xx = xx;
        this.yy = yy;
        this.atdate = atdate;
        this.lengths = lengths;
        this.views = views;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setWebid(String webid) {
        this.webid = webid;
    }
    public String getWebid() {
        return webid;
    }

    public void setStid(String stid) {
        this.stid = stid;
    }
    public String getStid() {
        return stid;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setXx(String xx) {
        this.xx = xx;
    }
    public String getXx() {
        return xx;
    }

    public void setYy(String yy) {
        this.yy = yy;
    }
    public String getYy() {
        return yy;
    }

    public void setAtdate(String atdate) {
        this.atdate = atdate;
    }
    public String getAtdate() {
        return atdate;
    }

    public void setLengths(String lengths) {
        this.lengths = lengths;
    }
    public String getLengths() {
        return lengths;
    }

    public void setViews(String views) {
        this.views = views;
    }
    public String getViews() {
        return views;
    }
}
