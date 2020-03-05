package com.switube.www.landmark2018test.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GSaveList {
    @SerializedName("Data")
    private List<Data> data = new ArrayList<>();

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }

    public static class Data {
        private String urid = "";
        private String raid = "";
        private String title = "";
        private String sort = "";
        private String cdate = "";
        private String edate = "";
        private String cut = "";
        private String city = "";
        private List<String> photo = new ArrayList<>();

        public void setUrid(String urid) {
            this.urid = urid;
        }
        public String getUrid() {
            return urid;
        }

        public void setRaid(String raid) {
            this.raid = raid;
        }
        public String getRaid() {
            return raid;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
        public String getSort() {
            return sort;
        }

        public void setCdate(String cdate) {
            this.cdate = cdate;
        }
        public String getCdate() {
            return cdate;
        }

        public void setEdate(String edate) {
            this.edate = edate;
        }
        public String getEdate() {
            return edate;
        }

        public void setCity(String city) {
            this.city = city;
        }
        public String getCity() {
            return city;
        }

        public void setCut(String cut) {
            this.cut = cut;
        }
        public String getCut() {
            return cut;
        }

        public void setPhoto(List<String> photo) {
            this.photo = photo;
        }
        public List<String> getPhoto() {
            return photo;
        }
    }
}
