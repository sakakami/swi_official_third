package com.switube.www.landmark2018test.entity;

public class ETagQB {
    private String tqbid;
    private String qbid;
    private String qb_name;

    public ETagQB(String tqbid, String qbid, String qb_name) {
        this.tqbid = tqbid;
        this.qbid = qbid;
        this.qb_name = qb_name;
    }

    public void setQbid(String qbid) {
        this.qbid = qbid;
    }

    public void setTqbid(String tqbid) {
        this.tqbid = tqbid;
    }

    public void setQb_name(String qb_name) {
        this.qb_name = qb_name;
    }

    public String getQbid() {
        return qbid;
    }

    public String getTqbid() {
        return tqbid;
    }

    public String getQb_name() {
        return qb_name;
    }
}
