package com.switube.www.landmark2018test.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GSearchAttractionDetail {
    @SerializedName("Data")
    private List<Data> data = new ArrayList<>();

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public static class Data {
        private String spid = "";
        private String placeid = "";
        private String mmid = "";
        private String msid = "";
        private String mmspid = "";
        private String mscid = "";
        private String countryid = "";
        private String cityid = "";
        private String place = "";
        private String lat = "";
        private String lng = "";
        private String info = "";
        private String addr = "";
        private String phone = "";
        private String email = "";
        private String website = "";
        private String rating = "";
        private String urating = "";
        private String coll = "";
        private List<String> mon = new ArrayList<>();
        private List<String> tue = new ArrayList<>();
        private List<String> wed = new ArrayList<>();
        private List<String> thu = new ArrayList<>();
        private List<String> fri = new ArrayList<>();
        private List<String> sat = new ArrayList<>();
        private List<String> sun = new ArrayList<>();
        private List<String> photo = new ArrayList<>();

        public void setSpid(String spid) {
            this.spid = spid;
        }

        public void setMsid(String msid) {
            this.msid = msid;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public void setCountryid(String countryid) {
            this.countryid = countryid;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public void setMscid(String mscid) {
            this.mscid = mscid;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public void setMmspid(String mmspid) {
            this.mmspid = mmspid;
        }

        public void setMmid(String mmid) {
            this.mmid = mmid;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public void setPlaceid(String placeid) {
            this.placeid = placeid;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setColl(String coll) {
            this.coll = coll;
        }

        public void setUrating(String urating) {
            this.urating = urating;
        }

        public void setSat(List<String> sat) {
            this.sat = sat;
        }

        public void setSun(List<String> sun) {
            this.sun = sun;
        }

        public void setMon(List<String> mon) {
            this.mon = mon;
        }

        public void setTue(List<String> tue) {
            this.tue = tue;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setWed(List<String> wed) {
            this.wed = wed;
        }

        public void setThu(List<String> thu) {
            this.thu = thu;
        }

        public void setFri(List<String> fri) {
            this.fri = fri;
        }

        public void setPhoto(List<String> photo) {
            this.photo = photo;
        }

        public String getSpid() {
            return spid;
        }

        public String getMsid() {
            return msid;
        }

        public String getPlace() {
            return place;
        }

        public String getCityid() {
            return cityid;
        }

        public String getLat() {
            return lat;
        }

        public String getLng() {
            return lng;
        }

        public String getCountryid() {
            return countryid;
        }

        public String getInfo() {
            return info;
        }

        public String getMscid() {
            return mscid;
        }

        public String getMmid() {
            return mmid;
        }

        public String getRating() {
            return rating;
        }

        public String getMmspid() {
            return mmspid;
        }

        public String getAddr() {
            return addr;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getPlaceid() {
            return placeid;
        }

        public String getColl() {
            return coll;
        }

        public String getUrating() {
            return urating;
        }

        public List<String> getMon() {
            return mon;
        }

        public List<String> getTue() {
            return tue;
        }

        public List<String> getSun() {
            return sun;
        }

        public List<String> getSat() {
            return sat;
        }

        public String getWebsite() {
            return website;
        }

        public List<String> getThu() {
            return thu;
        }

        public List<String> getWed() {
            return wed;
        }

        public List<String> getFri() {
            return fri;
        }

        public List<String> getPhoto() {
            return photo;
        }
    }
}
