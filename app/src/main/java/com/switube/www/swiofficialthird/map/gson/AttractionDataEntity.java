package com.switube.www.swiofficialthird.map.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AttractionDataEntity {
    private List<TData> TData;

    public void setTData(List<TData> TData) {
        this.TData = TData;
    }

    public List<TData> getTData() {
        return TData;
    }

    public static class TData {
        private String trid;
        private String tcid;
        private String trname;
        private String traddr;
        private String trphone;
        private String trurl;
        @SerializedName("long")
        private String longs;
        private String lat;
        private String score;
        private String trgoid;

        public void setTrid(String trid) {
            this.trid = trid;
        }

        public void setTcid(String tcid) {
            this.tcid = tcid;
        }

        public void setTrname(String trname) {
            this.trname = trname;
        }

        public void setTraddr(String traddr) {
            this.traddr = traddr;
        }

        public void setTrphone(String trphone) {
            this.trphone = trphone;
        }

        public void setTrurl(String trurl) {
            this.trurl = trurl;
        }

        public void setLongs(String longs) {
            this.longs = longs;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public void setTrgoid(String trgoid) {
            this.trgoid = trgoid;
        }

        public String getTrid() {
            return trid;
        }

        public String getTcid() {
            return tcid;
        }

        public String getTrname() {
            return trname;
        }

        public String getTraddr() {
            return traddr;
        }

        public String getTrphone() {
            return trphone;
        }

        public String getTrurl() {
            return trurl;
        }

        public String getLongs() {
            return longs;
        }

        public String getLat() {
            return lat;
        }

        public String getScore() {
            return score;
        }

        public String getTrgoid() {
            return trgoid;
        }
    }
}
