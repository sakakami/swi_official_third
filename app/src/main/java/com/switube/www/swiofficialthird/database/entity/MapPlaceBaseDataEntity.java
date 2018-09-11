package com.switube.www.swiofficialthird.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "placeBaseData")
public class MapPlaceBaseDataEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String spid;
    private String msid;
    private String lat;
    private String lng;
    private String countryid;
    private String cityid;
    private String place_en;
    private String place_tw;
    private String place_ch;
    private String place_jp;
    private String address_en;
    private String address_tw;
    private String address_ch;
    private String address_jp;
    private String rating;
    private String mon_1;
    private String mon_2;
    private String mon_3;
    private String mon_4;
    private String tue_1;
    private String tue_2;
    private String tue_3;
    private String tue_4;
    private String wed_1;
    private String wed_2;
    private String wed_3;
    private String wed_4;
    private String thu_1;
    private String thu_2;
    private String thu_3;
    private String thu_4;
    private String fri_1;
    private String fri_2;
    private String fri_3;
    private String fri_4;
    private String sat_1;
    private String sat_2;
    private String sat_3;
    private String sat_4;
    private String sun_1;
    private String sun_2;
    private String sun_3;
    private String sun_4;

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setPlace_jp(String place_jp) {
        this.place_jp = place_jp;
    }

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

    public void setPlace_tw(String place_tw) {
        this.place_tw = place_tw;
    }

    public void setPlace_en(String place_en) {
        this.place_en = place_en;
    }

    public void setPlace_ch(String place_ch) {
        this.place_ch = place_ch;
    }

    public void setMsid(String msid) {
        this.msid = msid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMon_1(String mon_1) {
        this.mon_1 = mon_1;
    }

    public void setMon_2(String mon_2) {
        this.mon_2 = mon_2;
    }

    public void setFri_1(String fri_1) {
        this.fri_1 = fri_1;
    }

    public void setFri_2(String fri_2) {
        this.fri_2 = fri_2;
    }

    public void setMon_3(String mon_3) {
        this.mon_3 = mon_3;
    }

    public void setMon_4(String mon_4) {
        this.mon_4 = mon_4;
    }

    public void setTue_1(String tue_1) {
        this.tue_1 = tue_1;
    }

    public void setFri_3(String fri_3) {
        this.fri_3 = fri_3;
    }

    public void setFri_4(String fri_4) {
        this.fri_4 = fri_4;
    }

    public void setTue_2(String tue_2) {
        this.tue_2 = tue_2;
    }

    public void setTue_3(String tue_3) {
        this.tue_3 = tue_3;
    }

    public void setSat_1(String sat_1) {
        this.sat_1 = sat_1;
    }

    public void setSat_2(String sat_2) {
        this.sat_2 = sat_2;
    }

    public void setSat_3(String sat_3) {
        this.sat_3 = sat_3;
    }

    public void setTue_4(String tue_4) {
        this.tue_4 = tue_4;
    }

    public void setSat_4(String sat_4) {
        this.sat_4 = sat_4;
    }

    public void setSun_1(String sun_1) {
        this.sun_1 = sun_1;
    }

    public void setSun_2(String sun_2) {
        this.sun_2 = sun_2;
    }

    public void setSun_3(String sun_3) {
        this.sun_3 = sun_3;
    }

    public void setSun_4(String sun_4) {
        this.sun_4 = sun_4;
    }

    public void setThu_1(String thu_1) {
        this.thu_1 = thu_1;
    }

    public void setThu_2(String thu_2) {
        this.thu_2 = thu_2;
    }

    public void setWed_1(String wed_1) {
        this.wed_1 = wed_1;
    }

    public void setWed_2(String wed_2) {
        this.wed_2 = wed_2;
    }

    public void setThu_3(String thu_3) {
        this.thu_3 = thu_3;
    }

    public void setThu_4(String thu_4) {
        this.thu_4 = thu_4;
    }

    public void setWed_3(String wed_3) {
        this.wed_3 = wed_3;
    }

    public void setWed_4(String wed_4) {
        this.wed_4 = wed_4;
    }

    public void setAddress_tw(String address_tw) {
        this.address_tw = address_tw;
    }

    public void setAddress_jp(String address_jp) {
        this.address_jp = address_jp;
    }

    public void setAddress_en(String address_en) {
        this.address_en = address_en;
    }

    public void setAddress_ch(String address_ch) {
        this.address_ch = address_ch;
    }

    public String getRating() {
        return rating;
    }

    public String getCountryid() {
        return countryid;
    }

    public String getLng() {
        return lng;
    }

    public String getPlace_jp() {
        return place_jp;
    }

    public String getLat() {
        return lat;
    }

    public String getCityid() {
        return cityid;
    }

    public String getPlace_tw() {
        return place_tw;
    }

    public String getPlace_en() {
        return place_en;
    }

    public String getPlace_ch() {
        return place_ch;
    }

    public String getMsid() {
        return msid;
    }

    public String getSpid() {
        return spid;
    }

    public int getId() {
        return id;
    }

    public String getMon_1() {
        return mon_1;
    }

    public String getMon_2() {
        return mon_2;
    }

    public String getMon_3() {
        return mon_3;
    }

    public String getFri_1() {
        return fri_1;
    }

    public String getMon_4() {
        return mon_4;
    }

    public String getFri_2() {
        return fri_2;
    }

    public String getFri_3() {
        return fri_3;
    }

    public String getTue_1() {
        return tue_1;
    }

    public String getTue_2() {
        return tue_2;
    }

    public String getFri_4() {
        return fri_4;
    }

    public String getTue_3() {
        return tue_3;
    }

    public String getTue_4() {
        return tue_4;
    }

    public String getSat_1() {
        return sat_1;
    }

    public String getSat_2() {
        return sat_2;
    }

    public String getSat_3() {
        return sat_3;
    }

    public String getSat_4() {
        return sat_4;
    }

    public String getSun_1() {
        return sun_1;
    }

    public String getSun_2() {
        return sun_2;
    }

    public String getWed_1() {
        return wed_1;
    }

    public String getSun_3() {
        return sun_3;
    }

    public String getSun_4() {
        return sun_4;
    }

    public String getWed_2() {
        return wed_2;
    }

    public String getThu_1() {
        return thu_1;
    }

    public String getThu_2() {
        return thu_2;
    }

    public String getThu_3() {
        return thu_3;
    }

    public String getThu_4() {
        return thu_4;
    }

    public String getWed_3() {
        return wed_3;
    }

    public String getWed_4() {
        return wed_4;
    }

    public String getAddress_jp() {
        return address_jp;
    }

    public String getAddress_tw() {
        return address_tw;
    }

    public String getAddress_en() {
        return address_en;
    }

    public String getAddress_ch() {
        return address_ch;
    }
}
