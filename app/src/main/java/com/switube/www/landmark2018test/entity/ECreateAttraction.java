package com.switube.www.landmark2018test.entity;

import java.util.List;

public class ECreateAttraction {
    private Name place;
    private Address address;
    private Time time;
    private String phone = "";
    private String website = "";
    private String info = "";
    private String info_tw = "";
    private String info_ch = "";
    private String info_jp = "";
    private String lat = "";
    private String lng = "";
    private String rating = "";
    private String place_id = "";
    private String user_email = "";
    private String photoCount = "";
    private String SwiTubeURL = "";
    private String mapurl = "";
    private List<String> defaultPhoto;
    private boolean isCheck;
    private Class mClass;
    private List<Term> mTerm;
    private LocationCity city;
    private Style mStyle;

    public void setPlace(Name place) {
        this.place = place;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setDefaultPhoto(List<String> defaultPhoto) {
        this.defaultPhoto = defaultPhoto;
    }

    public void setmClass(Class mClass) {
        this.mClass = mClass;
    }

    public void setmTerm(List<Term> mTerm) {
        this.mTerm = mTerm;
    }

    public void setmStyle(Style mStyle) {
        this.mStyle = mStyle;
    }

    public void setCity(LocationCity city) {
        this.city = city;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setPhotoCount(String photoCount) {
        this.photoCount = photoCount;
    }

    public void setSwiTubeURL(String swiTubeURL) {
        SwiTubeURL = swiTubeURL;
    }

    public void setMapurl(String mapurl) {
        this.mapurl = mapurl;
    }

    public void setInfo_ch(String info_ch) {
        this.info_ch = info_ch;
    }

    public void setInfo_jp(String info_jp) {
        this.info_jp = info_jp;
    }

    public void setInfo_tw(String info_tw) {
        this.info_tw = info_tw;
    }

    public Name getPlace() {
        return place;
    }

    public Address getAddress() {
        return address;
    }

    public Time getTime() {
        return time;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getInfo() {
        return info;
    }

    public Style getmStyle() {
        return mStyle;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getPlace_id() {
        return place_id;
    }

    public String getRating() {
        return rating;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public List<String> getDefaultPhoto() {
        return defaultPhoto;
    }

    public LocationCity getCity() {
        return city;
    }

    public Class getmClass() {
        return mClass;
    }

    public List<Term> getmTerm() {
        return mTerm;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getPhotoCount() {
        return photoCount;
    }

    public String getSwiTubeURL() {
        return SwiTubeURL;
    }

    public String getMapurl() {
        return mapurl;
    }

    public String getInfo_ch() {
        return info_ch;
    }

    public String getInfo_jp() {
        return info_jp;
    }

    public String getInfo_tw() {
        return info_tw;
    }

    public static class Name {
        private String place_en;
        private String place_ch;
        private String place_tw;
        private String place_jp;

        public Name(String place_en, String place_tw, String place_ch, String place_jp) {
            this.place_en = place_en;
            this.place_ch = place_ch;
            this.place_tw = place_tw;
            this.place_jp = place_jp;
        }

        public void setPlace_ch(String place_ch) {
            this.place_ch = place_ch;
        }

        public void setPlace_en(String place_en) {
            this.place_en = place_en;
        }

        public void setPlace_jp(String place_jp) {
            this.place_jp = place_jp;
        }

        public void setPlace_tw(String place_tw) {
            this.place_tw = place_tw;
        }

        public String getPlace_ch() {
            return place_ch;
        }

        public String getPlace_en() {
            return place_en;
        }

        public String getPlace_jp() {
            return place_jp;
        }

        public String getPlace_tw() {
            return place_tw;
        }
    }

    public static class Address {
        private String address_en;
        private String address_ch;
        private String address_tw;
        private String address_jp;

        public Address(String address_en, String address_tw, String address_ch, String address_jp) {
            this.address_en = address_en;
            this.address_ch = address_ch;
            this.address_tw = address_tw;
            this.address_jp = address_jp;
        }

        public void setAddress_en(String address_en) {
            this.address_en = address_en;
        }

        public void setAddress_ch(String address_ch) {
            this.address_ch = address_ch;
        }

        public void setAddress_tw(String address_tw) {
            this.address_tw = address_tw;
        }

        public void setAddress_jp(String address_jp) {
            this.address_jp = address_jp;
        }

        public String getAddress_en() {
            return address_en;
        }

        public String getAddress_ch() {
            return address_ch;
        }

        public String getAddress_tw() {
            return address_tw;
        }

        public String getAddress_jp() {
            return address_jp;
        }
    }

    public static class Time {
        private String OpenBool;
        private List<String> mon;
        private List<String> tue;
        private List<String> wed;
        private List<String> thu;
        private List<String> fri;
        private List<String> sat;
        private List<String> sun;

        public Time(String OpenBool, List<String> sun, List<String> mon, List<String> tue, List<String> wed, List<String> thu,
                    List<String> fri, List<String> sat) {
            this.OpenBool = OpenBool;
            this.mon = mon;
            this.tue = tue;
            this.wed = wed;
            this.thu = thu;
            this.fri = fri;
            this.sat = sat;
            this.sun = sun;
        }

        public void setOpenBool(String openBool) {
            OpenBool = openBool;
        }

        public void setMon(List<String> mon) {
            this.mon = mon;
        }

        public void setTue(List<String> tue) {
            this.tue = tue;
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

        public void setSat(List<String> sat) {
            this.sat = sat;
        }

        public void setSun(List<String> sun) {
            this.sun = sun;
        }

        public String getOpenBool() {
            return OpenBool;
        }

        public List<String> getMon() {
            return mon;
        }

        public List<String> getTue() {
            return tue;
        }

        public List<String> getWed() {
            return wed;
        }

        public List<String> getThu() {
            return thu;
        }

        public List<String> getFri() {
            return fri;
        }

        public List<String> getSat() {
            return sat;
        }

        public List<String> getSun() {
            return sun;
        }
    }

    public static class Item {
        private String mstid;
        private String miid;

        public Item(String mstid, String miid) {
            this.mstid = mstid;
            this.miid = miid;
        }

        public void setMstid(String mstid) {
            this.mstid = mstid;
        }

        public void setMiid(String miid) {
            this.miid = miid;
        }

        public String getMstid() {
            return mstid;
        }

        public String getMiid() {
            return miid;
        }
    }

    public static class Term {
        private String mtid;
        private List<Item> item;

        public Term(String mtid, List<Item> item) {
            this.mtid = mtid;
            this.item = item;
        }

        public void setItem(List<Item> item) {
            this.item = item;
        }

        public void setMtid(String mtid) {
            this.mtid = mtid;
        }

        public List<Item> getItem() {
            return item;
        }

        public String getMtid() {
            return mtid;
        }
    }

    public static class Style {
        private String mmspid;
        private String mmid;
        private String msid;

        public Style(String mmspid, String mmid, String msid) {
            this.mmspid = mmspid;
            this.mmid = mmid;
            this.msid = msid;
        }

        public void setMsid(String msid) {
            this.msid = msid;
        }

        public void setMmid(String mmid) {
            this.mmid = mmid;
        }

        public void setMmspid(String mmspid) {
            this.mmspid = mmspid;
        }

        public String getMsid() {
            return msid;
        }

        public String getMmspid() {
            return mmspid;
        }

        public String getMmid() {
            return mmid;
        }
    }

    public static class Class {
        private String mscid;
        private String mcid;

        public Class(String mscid, String mcid) {
            this.mscid = mscid;
            this.mcid = mcid;
        }

        public void setMcid(String mcid) {
            this.mcid = mcid;
        }

        public void setMscid(String mscid) {
            this.mscid = mscid;
        }

        public String getMcid() {
            return mcid;
        }

        public String getMscid() {
            return mscid;
        }
    }

    public static class LocationCity {
        private String city_l_en;
        private String city_l_tw;
        private String city_l_ch;
        private String city_l_jp;
        private String city_s_en;
        private String city_s_tw;
        private String city_s_ch;
        private String city_s_jp;
        private String city_id;
        private String country;

        public LocationCity(String city_l_en, String city_s_en, String city_l_tw, String city_s_tw,
                    String city_l_ch, String city_s_ch, String city_l_jp, String city_s_jp) {
            this.city_l_en = city_l_en;
            this.city_s_en = city_s_en;
            this.city_l_tw = city_l_tw;
            this.city_s_tw = city_s_tw;
            this.city_l_ch = city_l_ch;
            this.city_s_ch = city_s_ch;
            this.city_l_jp = city_l_jp;
            this.city_s_jp = city_s_jp;
        }

        public void setCity_l_ch(String city_l_ch) {
            this.city_l_ch = city_l_ch;
        }

        public void setCity_l_en(String city_l_en) {
            this.city_l_en = city_l_en;
        }

        public void setCity_l_jp(String city_l_jp) {
            this.city_l_jp = city_l_jp;
        }

        public void setCity_l_tw(String city_l_tw) {
            this.city_l_tw = city_l_tw;
        }

        public void setCity_s_ch(String city_s_ch) {
            this.city_s_ch = city_s_ch;
        }

        public void setCity_s_en(String city_s_en) {
            this.city_s_en = city_s_en;
        }

        public void setCity_s_jp(String city_s_jp) {
            this.city_s_jp = city_s_jp;
        }

        public void setCity_s_tw(String city_s_tw) {
            this.city_s_tw = city_s_tw;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity_l_ch() {
            return city_l_ch;
        }

        public String getCity_l_en() {
            return city_l_en;
        }

        public String getCity_l_jp() {
            return city_l_jp;
        }

        public String getCity_l_tw() {
            return city_l_tw;
        }

        public String getCity_s_ch() {
            return city_s_ch;
        }

        public String getCity_s_en() {
            return city_s_en;
        }

        public String getCity_s_jp() {
            return city_s_jp;
        }

        public String getCity_s_tw() {
            return city_s_tw;
        }

        public String getCity_id() {
            return city_id;
        }

        public String getCountry() {
            return country;
        }
    }
}
