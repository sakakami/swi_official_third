package com.switube.www.landmark2018test.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GPushStroke {
    @SerializedName("Data")
    private List<PushData> data = new ArrayList<>();

    public void setData(List<PushData> data) {
        this.data = data;
    }
    public List<PushData> getData() {
        return data;
    }

    public class PushData {
        private String urid = "";
        private String raid = "";
        private String maid = "";
        private String maname = "";
        private String maimg = "";
        private String title = "";
        private String cut = "";
        private String city = "";
        private String cdate = "";
        private String edate = "";
        private List<String> photo = new ArrayList<>();

        public void setCut(String cut) {
            this.cut = cut;
        }
        public String getCut() {
            return cut;
        }

        public void setCity(String city) {
            this.city = city;
        }
        public String getCity() {
            return city;
        }

        public void setUrid(String urid) {
            this.urid = urid;
        }
        public String getUrid() {
            return urid;
        }

        public void setEdate(String edate) {
            this.edate = edate;
        }
        public String getEdate() {
            return edate;
        }

        public void setCdate(String cdate) {
            this.cdate = cdate;
        }
        public String getCdate() {
            return cdate;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setRaid(String raid) {
            this.raid = raid;
        }
        public String getRaid() {
            return raid;
        }

        public void setMaid(String maid) {
            this.maid = maid;
        }
        public String getMaid() {
            return maid;
        }

        public void setMaimg(String maimg) {
            this.maimg = maimg;
        }
        public String getMaimg() {
            return maimg;
        }

        public void setManame(String maname) {
            this.maname = maname;
        }
        public String getManame() {
            return maname;
        }

        public void setPhoto(List<String> photo) {
            this.photo = photo;
        }
        public List<String> getPhoto() {
            return photo;
        }
    }
}
