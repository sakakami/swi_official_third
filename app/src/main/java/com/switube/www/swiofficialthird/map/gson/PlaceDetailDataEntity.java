package com.switube.www.swiofficialthird.map.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceDetailDataEntity {
    @SerializedName("Data")
    private Data data;

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public static class Data {
        private String spid;
        private String placeid;
        private String mmid;
        private String msid;
        private String mmspid;
        private String mscid;
        private String countryid;
        private String cityid;
        private String place_en;
        private String place_tw;
        private String place_ch;
        private String place_jp;
        private String lat;
        private String lng;
        private String info;
        private String addr_en;
        private String addr_tw;
        private String addr_ch;
        private String addr_jp;
        private String phone;
        private String email;
        private String website;
        private String rating;
        private List<String> mon;
        private List<String> tue;
        private List<String> wed;
        private List<String> thu;
        private List<String> fri;
        private List<String> sat;
        private List<String> sun;
        private List<String> photo;

        public void setSpid(String spid) {
            this.spid = spid;
        }

        public void setMsid(String msid) {
            this.msid = msid;
        }

        public void setPlace_ch(String place_ch) {
            this.place_ch = place_ch;
        }

        public void setPlace_en(String place_en) {
            this.place_en = place_en;
        }

        public void setPlace_tw(String place_tw) {
            this.place_tw = place_tw;
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

        public void setPlace_jp(String place_jp) {
            this.place_jp = place_jp;
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

        public void setAddr_ch(String addr_ch) {
            this.addr_ch = addr_ch;
        }

        public void setAddr_en(String addr_en) {
            this.addr_en = addr_en;
        }

        public void setPlaceid(String placeid) {
            this.placeid = placeid;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setSat(List<String> sat) {
            this.sat = sat;
        }

        public void setAddr_jp(String addr_jp) {
            this.addr_jp = addr_jp;
        }

        public void setSun(List<String> sun) {
            this.sun = sun;
        }

        public void setMon(List<String> mon) {
            this.mon = mon;
        }

        public void setAddr_tw(String addr_tw) {
            this.addr_tw = addr_tw;
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

        public String getPlace_ch() {
            return place_ch;
        }

        public String getPlace_en() {
            return place_en;
        }

        public String getPlace_tw() {
            return place_tw;
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

        public String getPlace_jp() {
            return place_jp;
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

        public String getAddr_ch() {
            return addr_ch;
        }

        public String getAddr_en() {
            return addr_en;
        }

        public String getPhone() {
            return phone;
        }

        public String getAddr_tw() {
            return addr_tw;
        }

        public String getAddr_jp() {
            return addr_jp;
        }

        public String getEmail() {
            return email;
        }

        public String getPlaceid() {
            return placeid;
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
