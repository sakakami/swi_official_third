package com.switube.www.landmark2018test.gson;

import java.util.List;

public class CheckInDataGson {
    private String spid;
    private String privacy;
    private String photocut;
    private String txt;
    private List<Tag> tags;

    public CheckInDataGson(String spid, String privacy, String photocut, String txt, List<Tag> tags) {
        this.spid = spid;
        this.privacy = privacy;
        this.photocut = photocut;
        this.txt = txt;
        this.tags = tags;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public void setPhotocut(String photocut) {
        this.photocut = photocut;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getSpid() {
        return spid;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getPhotocut() {
        return photocut;
    }

    public String getPrivacy() {
        return privacy;
    }

    public String getTxt() {
        return txt;
    }

    public static class Tag {
        private String tqbid;
        private String naid;
        private String natxt;

        public Tag(String tqbid, String naid, String natxt) {
            this.tqbid = tqbid;
            this.naid = naid;
            this.natxt = natxt;
        }

        public void setTqbid(String tqbid) {
            this.tqbid = tqbid;
        }

        public void setNaid(String naid) {
            this.naid = naid;
        }

        public void setNatxt(String natxt) {
            this.natxt = natxt;
        }

        public String getTqbid() {
            return tqbid;
        }

        public String getNaid() {
            return naid;
        }

        public String getNatxt() {
            return natxt;
        }
    }
}
