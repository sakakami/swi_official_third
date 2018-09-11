package com.switube.www.swiofficialthird.map.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceBaseDataEntity {
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
        private String countryid;
        private String cityid;
        private String place_en;
        private String place_tw;
        private String place_ch;
        private String palce_jp;
        private String addr_en;
        private String addr_tw;
        private String addr_ch;
        private String addr_jp;
        private String rating;
        private List<String> mon;
        private List<String> tue;
        private List<String> wed;
        private List<String> thu;
        private List<String> fri;
        private List<String> sat;
        private List<String> sun;

        public void setCountryid(String countryid) {
            this.countryid = countryid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public void setThu(List<String> thu) {
            this.thu = thu;
        }

        public void setPlace_tw(String place_tw) {
            this.place_tw = place_tw;
        }

        public void setPlace_en(String place_en) {
            this.place_en = place_en;
        }

        public void setWed(List<String> wed) {
            this.wed = wed;
        }

        public void setPlace_ch(String place_ch) {
            this.place_ch = place_ch;
        }

        public void setTue(List<String> tue) {
            this.tue = tue;
        }

        public void setMon(List<String> mon) {
            this.mon = mon;
        }

        public void setMsid(String msid) {
            this.msid = msid;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public void setPalce_jp(String palce_jp) {
            this.palce_jp = palce_jp;
        }

        public void setSpid(String spid) {
            this.spid = spid;
        }

        public void setSun(List<String> sun) {
            this.sun = sun;
        }

        public void setSat(List<String> sat) {
            this.sat = sat;
        }

        public void setFri(List<String> fir) {
            this.fri = fir;
        }

        public void setAddr_tw(String addr_tw) {
            this.addr_tw = addr_tw;
        }

        public void setAddr_jp(String addr_jp) {
            this.addr_jp = addr_jp;
        }

        public void setAddr_en(String addr_en) {
            this.addr_en = addr_en;
        }

        public void setAddr_ch(String addr_ch) {
            this.addr_ch = addr_ch;
        }

        public String getCountryid() {
            return countryid;
        }

        public String getCityid() {
            return cityid;
        }

        public String getLng() {
            return lng;
        }

        public String getLat() {
            return lat;
        }

        public List<String> getWed() {
            return wed;
        }

        public List<String> getThu() {
            return thu;
        }

        public List<String> getSat() {
            return sat;
        }

        public String getPlace_tw() {
            return place_tw;
        }

        public List<String> getSun() {
            return sun;
        }

        public List<String> getTue() {
            return tue;
        }

        public String getPlace_en() {
            return place_en;
        }

        public List<String> getMon() {
            return mon;
        }

        public String getPlace_ch() {
            return place_ch;
        }

        public String getMsid() {
            return msid;
        }

        public String getRating() {
            return rating;
        }

        public List<String> getFri() {
            return fri;
        }

        public String getPalce_jp() {
            return palce_jp;
        }

        public String getSpid() {
            return spid;
        }

        public String getAddr_jp() {
            return addr_jp;
        }

        public String getAddr_tw() {
            return addr_tw;
        }

        public String getAddr_en() {
            return addr_en;
        }

        public String getAddr_ch() {
            return addr_ch;
        }
    }
}
