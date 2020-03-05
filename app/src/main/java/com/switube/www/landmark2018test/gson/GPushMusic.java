package com.switube.www.landmark2018test.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GPushMusic {
    @SerializedName("Data")
    private List<pushMusicData> pushMusicData = new ArrayList<>();

    public void setPushMusicData(List<GPushMusic.pushMusicData> pushMusicData) {
        this.pushMusicData = pushMusicData;
    }
    public List<GPushMusic.pushMusicData> getPushMusicData() {
        return pushMusicData;
    }

    public static class pushMusicData {
        private String webid = "";
        private String wtitle = "";
        private String wtitle_tw = "";
        private String wtitle_ch = "";
        private String wtitle_jp = "";
        private String path_img = "";
        private String phpname = "";
        private String love = "";
        private String count = "";

        public void setWebid(String webid) {
            this.webid = webid;
        }
        public String getWebid() {
            return webid;
        }

        public void setWtitle(String wtitle) {
            this.wtitle = wtitle;
        }
        public String getWtitle() {
            return wtitle;
        }

        public void setWtitle_tw(String wtitle_tw) {
            this.wtitle_tw = wtitle_tw;
        }
        public String getWtitle_tw() {
            return wtitle_tw;
        }

        public void setWtitle_ch(String wtitle_ch) {
            this.wtitle_ch = wtitle_ch;
        }
        public String getWtitle_ch() {
            return wtitle_ch;
        }

        public void setWtitle_jp(String wtitle_jp) {
            this.wtitle_jp = wtitle_jp;
        }
        public String getWtitle_jp() {
            return wtitle_jp;
        }

        public void setPath_img(String path_img) {
            this.path_img = path_img;
        }
        public String getPath_img() {
            return path_img;
        }

        public void setPhpname(String phpname) {
            this.phpname = phpname;
        }
        public String getPhpname() {
            return phpname;
        }

        public void setLove(String love) {
            this.love = love;
        }
        public String getLove() {
            return love;
        }

        public void setCount(String count) {
            this.count = count;
        }
        public String getCount() {
            return count;
        }
    }
}
