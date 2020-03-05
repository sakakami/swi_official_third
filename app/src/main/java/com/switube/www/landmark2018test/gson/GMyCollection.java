package com.switube.www.landmark2018test.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GMyCollection {
    @SerializedName("Data")
    private List<Data> data = new ArrayList<>();

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }

    public class Data {
        private String sucid = "";
        private String sort = "";
        private String spid = "";
        private String placeid = "";
        private String mmid = "";
        private String msid = "";
        private String mscid = "";
        private String countryid = "";
        private String cityid = "";
        private String lat = "";
        private String lng = "";
        private String place = "";
        private String addr = "";
        private String rating = "";
        private List<String> mon = new ArrayList<>();
        private List<String> tue = new ArrayList<>();
        private List<String> wed = new ArrayList<>();
        private List<String> thu = new ArrayList<>();
        private List<String> fri = new ArrayList<>();
        private List<String> sat = new ArrayList<>();
        private List<String> sun = new ArrayList<>();
        private List<String> photo = new ArrayList<>();
        private List<Term> term = new ArrayList<>();

        public void setSucid(String sucid) {
            this.sucid = sucid;
        }
        public String getSucid() {
            return sucid;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
        public String getSort() {
            return sort;
        }

        public void setSpid(String spid) {
            this.spid = spid;
        }
        public String getSpid() {
            return spid;
        }

        public void setPlaceid(String placeid) {
            this.placeid = placeid;
        }
        public String getPlaceid() {
            return placeid;
        }

        public void setMmid(String mmid) {
            this.mmid = mmid;
        }
        public String getMmid() {
            return mmid;
        }

        public void setMsid(String msid) {
            this.msid = msid;
        }
        public String getMsid() {
            return msid;
        }

        public void setMscid(String mscid) {
            this.mscid = mscid;
        }
        public String getMscid() {
            return mscid;
        }

        public void setCountryid(String countryid) {
            this.countryid = countryid;
        }
        public String getCountryid() {
            return countryid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }
        public String getCityid() {
            return cityid;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }
        public String getLat() {
            return lat;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }
        public String getLng() {
            return lng;
        }

        public void setPlace(String place) {
            this.place = place;
        }
        public String getPlace() {
            return place;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }
        public String getAddr() {
            return addr;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }
        public String getRating() {
            return rating;
        }

        public void setMon(List<String> mon) {
            this.mon = mon;
        }
        public List<String> getMon() {
            return mon;
        }

        public void setTue(List<String> tue) {
            this.tue = tue;
        }
        public List<String> getTue() {
            return tue;
        }

        public void setWed(List<String> wed) {
            this.wed = wed;
        }
        public List<String> getWed() {
            return wed;
        }

        public void setThu(List<String> thu) {
            this.thu = thu;
        }
        public List<String> getThu() {
            return thu;
        }

        public void setFri(List<String> fri) {
            this.fri = fri;
        }
        public List<String> getFri() {
            return fri;
        }

        public void setSun(List<String> sun) {
            this.sun = sun;
        }
        public List<String> getSun() {
            return sun;
        }

        public void setSat(List<String> sat) {
            this.sat = sat;
        }
        public List<String> getSat() {
            return sat;
        }

        public void setPhoto(List<String> photo) {
            this.photo = photo;
        }
        public List<String> getPhoto() {
            return photo;
        }

        public void setTerm(List<Term> term) {
            this.term = term;
        }
        public List<Term> getTerm() {
            return term;
        }
    }

    public class Term {
        private String mstid = "";
        private String mtid = "";
        private String miid = "";

        public void setMtid(String mtid) {
            this.mtid = mtid;
        }
        public String getMtid() {
            return mtid;
        }

        public void setMiid(String miid) {
            this.miid = miid;
        }
        public String getMiid() {
            return miid;
        }

        public void setMstid(String mstid) {
            this.mstid = mstid;
        }
        public String getMstid() {
            return mstid;
        }
    }
}
