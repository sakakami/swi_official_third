package com.switube.www.landmark2018test.gson;

public class MessageAndReplyGson {
    private String type = "";
    private String artid = "";
    private String acid = "";
    private String txt = "";

    public MessageAndReplyGson(String type, String artid, String acid, String txt) {
        this.type = type;
        this.artid = artid;
        this.acid = acid;
        this.txt = txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public void setAcid(String acid) {
        this.acid = acid;
    }

    public void setArtid(String artid) {
        this.artid = artid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTxt() {
        return txt;
    }
}
