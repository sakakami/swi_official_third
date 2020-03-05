package com.switube.www.landmark2018test.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GMusicRadio {
    @SerializedName("TAData")
    private List<TaData> taData = new ArrayList<>();
    @SerializedName("TDData")
    private List<TdData> tdData = new ArrayList<>();
    @SerializedName("RAData")
    private List<RaData> raData = new ArrayList<>();

    public void setTaData(List<TaData> taData) {
        this.taData = taData;
    }
    public List<TaData> getTaData() {
        return taData;
    }

    public void setTdData(List<TdData> tdData) {
        this.tdData = tdData;
    }
    public List<TdData> getTdData() {
        return tdData;
    }

    public void setRaData(List<RaData> raData) {
        this.raData = raData;
    }
    public List<RaData> getRaData() {
        return raData;
    }

    public static class TaData {
        private String mataid = "";
        private String appid = "";
        private String sort = "";
        private String taid = "";
        private String title_en = "";
        private String title_tw = "";
        private String title_ch = "";
        private String title_jp = "";
        private String path_img = "";

        public void setMataid(String mataid) {
            this.mataid = mataid;
        }
        public String getMataid() {
            return mataid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }
        public String getAppid() {
            return appid;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
        public String getSort() {
            return sort;
        }

        public void setTaid(String taid) {
            this.taid = taid;
        }
        public String getTaid() {
            return taid;
        }

        public void setTitle_en(String title_en) {
            this.title_en = title_en;
        }
        public String getTitle_en() {
            return title_en;
        }

        public void setTitle_tw(String title_tw) {
            this.title_tw = title_tw;
        }
        public String getTitle_tw() {
            return title_tw;
        }

        public void setTitle_ch(String title_ch) {
            this.title_ch = title_ch;
        }
        public String getTitle_ch() {
            return title_ch;
        }

        public void setTitle_jp(String title_jp) {
            this.title_jp = title_jp;
        }
        public String getTitle_jp() {
            return title_jp;
        }

        public void setPath_img(String path_img) {
            this.path_img = path_img;
        }
        public String getPath_img() {
            return path_img;
        }
    }

    public static class TdData {
        private String tdid = "";
        private String taid = "";
        private String sort = "";
        private String menuid = "";
        private String is_push = "";
        private String muid = "";
        private String m_winame = "";
        private String m_winame_tw = "";
        private String m_winame_ch = "";
        private String m_winame_jp = "";
        private String istid = "";
        private String wpath = "";

        public void setTdid(String tdid) {
            this.tdid = tdid;
        }
        public String getTdid() {
            return tdid;
        }

        public void setTaid(String taid) {
            this.taid = taid;
        }
        public String getTaid() {
            return taid;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
        public String getSort() {
            return sort;
        }

        public void setMenuid(String menuid) {
            this.menuid = menuid;
        }
        public String getMenuid() {
            return menuid;
        }

        public void setIs_push(String is_push) {
            this.is_push = is_push;
        }
        public String getIs_push() {
            return is_push;
        }

        public void setMuid(String muid) {
            this.muid = muid;
        }
        public String getMuid() {
            return muid;
        }

        public void setM_winame(String m_winame) {
            this.m_winame = m_winame;
        }
        public String getM_winame() {
            return m_winame;
        }

        public void setM_winame_tw(String m_winame_tw) {
            this.m_winame_tw = m_winame_tw;
        }
        public String getM_winame_tw() {
            return m_winame_tw;
        }

        public void setM_winame_ch(String m_winame_ch) {
            this.m_winame_ch = m_winame_ch;
        }
        public String getM_winame_ch() {
            return m_winame_ch;
        }

        public void setM_winame_jp(String m_winame_jp) {
            this.m_winame_jp = m_winame_jp;
        }
        public String getM_winame_jp() {
            return m_winame_jp;
        }

        public void setIstid(String istid) {
            this.istid = istid;
        }
        public String getIstid() {
            return istid;
        }

        public void setWpath(String wpath) {
            this.wpath = wpath;
        }
        public String getWpath() {
            return wpath;
        }
    }

    public static class RaData {
        private String maraid = "";
        private String appid = "";
        private String sort = "";
        private String raid = "";
        private String title_en = "";
        private String title_tw = "";
        private String title_ch = "";
        private String title_jp = "";
        private String path_img = "";

        public void setAppid(String appid) {
            this.appid = appid;
        }
        public String getAppid() {
            return appid;
        }

        public void setMaraid(String maraid) {
            this.maraid = maraid;
        }
        public String getMaraid() {
            return maraid;
        }

        public void setRaid(String raid) {
            this.raid = raid;
        }
        public String getRaid() {
            return raid;
        }

        public void setPath_img(String path_img) {
            this.path_img = path_img;
        }
        public String getPath_img() {
            return path_img;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
        public String getSort() {
            return sort;
        }

        public void setTitle_ch(String title_ch) {
            this.title_ch = title_ch;
        }
        public String getTitle_ch() {
            return title_ch;
        }

        public void setTitle_en(String title_en) {
            this.title_en = title_en;
        }
        public String getTitle_en() {
            return title_en;
        }

        public void setTitle_jp(String title_jp) {
            this.title_jp = title_jp;
        }
        public String getTitle_jp() {
            return title_jp;
        }

        public void setTitle_tw(String title_tw) {
            this.title_tw = title_tw;
        }
        public String getTitle_tw() {
            return title_tw;
        }
    }
}
