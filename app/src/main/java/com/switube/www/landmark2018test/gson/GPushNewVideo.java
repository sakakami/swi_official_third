package com.switube.www.landmark2018test.gson;

public class GPushNewVideo {
    private String webid = "";
    private String tubeid = "";
    private String appname = "";
    private String renew = "";

    public void setRenew(String renew) {
        this.renew = renew;
    }
    public String getRenew() {
        return renew;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }
    public String getAppname() {
        return appname;
    }

    public void setTubeid(String tubeid) {
        this.tubeid = tubeid;
    }
    public String getTubeid() {
        return tubeid;
    }

    public void setWebid(String webid) {
        this.webid = webid;
    }
    public String getWebid() {
        return webid;
    }
}
