package com.switube.www.landmark2018test.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GAttractionListData {
    @SerializedName("Data")
    private List<Data> data;

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public static class Data {
        private String spid;
        private String msid;
        private String lat;
        private String lng;
        private String place;

        public void setLat(String lat) {
            this.lat = lat;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public void setMsid(String msid) {
            this.msid = msid;
        }

        public void setSpid(String spid) {
            this.spid = spid;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getLng() {
            return lng;
        }

        public String getLat() {
            return lat;
        }

        public String getMsid() {
            return msid;
        }

        public String getSpid() {
            return spid;
        }

        public String getPlace() {
            return place;
        }
    }
}
