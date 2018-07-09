package com.switube.www.swiofficialthird.home.gson;

import java.util.List;

public class MediumMenuEntity {
    private String adkey3;
    private String adkey4;
    private String a_version;
    private List<WData> wdata;

    public void setAdkey3(String adkey3) {
        this.adkey3 = adkey3;
    }

    public void setAdkey4(String adkey4) {
        this.adkey4 = adkey4;
    }

    public void setA_version(String a_version) {
        this.a_version = a_version;
    }

    public void setWdata(List<WData> wdata) {
        this.wdata = wdata;
    }

    public String getAdkey3() {
        return adkey3;
    }

    public String getAdkey4() {
        return adkey4;
    }

    public String getA_version() {
        return a_version;
    }

    public List<WData> getWdata() {
        return wdata;
    }

    public static class WData {
        private String wordid;
        private String word_en;
        private String word_tw;
        private String word_ch;
        private String word_jp;
        private String status;

        public void setWordid(String wordid) {
            this.wordid = wordid;
        }

        public void setWord_en(String word_en) {
            this.word_en = word_en;
        }

        public void setWord_tw(String word_tw) {
            this.word_tw = word_tw;
        }

        public void setWord_ch(String word_ch) {
            this.word_ch = word_ch;
        }

        public void setWord_jp(String word_jp) {
            this.word_jp = word_jp;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getWordid() {
            return wordid;
        }

        public String getWord_en() {
            return word_en;
        }

        public String getWord_tw() {
            return word_tw;
        }

        public String getWord_ch() {
            return word_ch;
        }

        public String getWord_jp() {
            return word_jp;
        }

        public String getStatus() {
            return status;
        }
    }
}
