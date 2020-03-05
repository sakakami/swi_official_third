package com.switube.www.landmark2018test.gson;

import java.util.List;

public class GSignInData {
    private String authority = "";
    private List<Info> info_data;
    private List<Device> device_data;

    public void setDevice_data(List<Device> device_data) {
        this.device_data = device_data;
    }

    public void setInfo_data(List<Info> info_data) {
        this.info_data = info_data;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public List<Info> getInfo_data() {
        return info_data;
    }

    public List<Device> getDevice_data() {
        return device_data;
    }

    public String getAuthority() {
        return authority;
    }

    public static class Info {
        private String accid;
        private String maid;
        private String swiid;
        private String accname;
        private String img;
        private String loginid;
        private String is_proof;
        private String wsid;

        public void setMaid(String maid) {
            this.maid = maid;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        public void setAccname(String accname) {
            this.accname = accname;
        }

        public void setIs_proof(String is_proof) {
            this.is_proof = is_proof;
        }

        public void setLoginid(String loginid) {
            this.loginid = loginid;
        }

        public void setSwiid(String swiid) {
            this.swiid = swiid;
        }

        public void setWsid(String wsid) {
            this.wsid = wsid;
        }

        public String getMaid() {
            return maid;
        }

        public String getImg() {
            return img;
        }

        public String getAccid() {
            return accid;
        }

        public String getAccname() {
            return accname;
        }

        public String getIs_proof() {
            return is_proof;
        }

        public String getLoginid() {
            return loginid;
        }

        public String getSwiid() {
            return swiid;
        }

        public String getWsid() {
            return wsid;
        }
    }

    public static class Device {
        private String serialid;
        private String wdid;
        private String pattern;
        private String sdate;

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }

        public void setSdate(String sdate) {
            this.sdate = sdate;
        }

        public void setSerialid(String serialid) {
            this.serialid = serialid;
        }

        public void setWdid(String wdid) {
            this.wdid = wdid;
        }

        public String getPattern() {
            return pattern;
        }

        public String getSdate() {
            return sdate;
        }

        public String getSerialid() {
            return serialid;
        }

        public String getWdid() {
            return wdid;
        }
    }
}
