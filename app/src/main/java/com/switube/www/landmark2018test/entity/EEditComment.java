package com.switube.www.landmark2018test.entity;

import java.util.ArrayList;
import java.util.List;

public class EEditComment {
    private String name = "";
    private String privacy = "";
    private String message = "";
    private String msid = "";
    private String artid = "";
    private String spid = "";
    private List<String> image = new ArrayList<>();
    private List<String> tqbid = new ArrayList<>();
    private List<String> naid = new ArrayList<>();
    private List<String> tagA = new ArrayList<>();
    private List<String> tagB = new ArrayList<>();
    private List<String> imageDel = new ArrayList<>();
    private List<String> imageAdd = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }
    public String getPrivacy() {
        return privacy;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMsid(String msid) {
        this.msid = msid;
    }
    public String getMsid() {
        return msid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }
    public String getSpid() {
        return spid;
    }

    public void setArtid(String artid) {
        this.artid = artid;
    }
    public String getArtid() {
        return artid;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }
    public List<String> getImage() {
        return image;
    }

    public void setNaid(List<String> naid) {
        this.naid = naid;
    }
    public List<String> getNaid() {
        return naid;
    }

    public void setTqbid(List<String> tqbid) {
        this.tqbid = tqbid;
    }
    public List<String> getTqbid() {
        return tqbid;
    }

    public void setTagA(List<String> tagA) {
        this.tagA = tagA;
    }
    public List<String> getTagA() {
        return tagA;
    }

    public void setTagB(List<String> tagB) {
        this.tagB = tagB;
    }
    public List<String> getTagB() {
        return tagB;
    }

    public void setImageAdd(List<String> imageAdd) {
        this.imageAdd = imageAdd;
    }
    public List<String> getImageAdd() {
        return imageAdd;
    }

    public void setImageDel(List<String> imageDel) {
        this.imageDel = imageDel;
    }
    public List<String> getImageDel() {
        return imageDel;
    }
}
