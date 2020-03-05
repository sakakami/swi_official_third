package com.switube.www.landmark2018test.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GPlayer {
    @SerializedName("Data")
    private List<Data> data = new ArrayList<>();
    @SerializedName("BeatData")
    private List<Beat> beatData = new ArrayList<>();
    @SerializedName("SmartData")
    private Smart smartData;
    @SerializedName("PushData")
    private List<Push> pushData = new ArrayList<>();
    @SerializedName("RAData")
    private List<RaData> raData = new ArrayList<>();

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }

    public void setBeatData(List<Beat> beatData) {
        this.beatData = beatData;
    }
    public List<Beat> getBeatData() {
        return beatData;
    }

    public void setPushData(List<Push> pushData) {
        this.pushData = pushData;
    }
    public List<Push> getPushData() {
        return pushData;
    }

    public void setSmartData(Smart smartData) {
        this.smartData = smartData;
    }
    public Smart getSmartData() {
        return smartData;
    }

    public void setRaData(List<RaData> raData) {
        this.raData = raData;
    }
    public List<RaData> getRaData() {
        return raData;
    }

    public static class Data {
        private String webid = "";
        private String stid = "";
        private String name = "";
        private String xx = "";
        private String yy = "";
        private String atdate = "";
        private String lengths = "";
        private String view = "";

        public void setWebid(String webid) {
            this.webid = webid;
        }
        public String getWebid() {
            return webid;
        }

        public void setStid(String stid) {
            this.stid = stid;
        }
        public String getStid() {
            return stid;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setXx(String xx) {
            this.xx = xx;
        }
        public String getXx() {
            return xx;
        }

        public void setYy(String yy) {
            this.yy = yy;
        }
        public String getYy() {
            return yy;
        }

        public void setAtdate(String atdate) {
            this.atdate = atdate;
        }
        public String getAtdate() {
            return atdate;
        }

        public void setLengths(String lengths) {
            this.lengths = lengths;
        }
        public String getLengths() {
            return lengths;
        }

        public void setView(String view) {
            this.view = view;
        }
        public String getView() {
            return view;
        }
    }

    public static class Beat {
        private String webid = "";
        private String asno = "";
        private String asapp = "";
        private String astype = "";
        private String asname = "";
        private String asname_tw = "";
        private String asname_ch = "";
        private String asname_jp = "";
        private String asurl = "";
        private String asurl_tw = "";
        private String asurl_ch = "";
        private String asurl_jp = "";

        public void setWebid(String webid) {
            this.webid = webid;
        }
        public String getWebid() {
            return webid;
        }

        public void setAsno(String asno) {
            this.asno = asno;
        }
        public String getAsno() {
            return asno;
        }

        public void setAsapp(String asapp) {
            this.asapp = asapp;
        }
        public String getAsapp() {
            return asapp;
        }

        public void setAstype(String astype) {
            this.astype = astype;
        }
        public String getAstype() {
            return astype;
        }

        public void setAsname(String asname) {
            this.asname = asname;
        }
        public String getAsname() {
            return asname;
        }

        public void setAsname_tw(String asname_tw) {
            this.asname_tw = asname_tw;
        }
        public String getAsname_tw() {
            return asname_tw;
        }

        public void setAsname_ch(String asname_ch) {
            this.asname_ch = asname_ch;
        }
        public String getAsname_ch() {
            return asname_ch;
        }

        public void setAsname_jp(String asname_jp) {
            this.asname_jp = asname_jp;
        }
        public String getAsname_jp() {
            return asname_jp;
        }

        public void setAsurl(String asurl) {
            this.asurl = asurl;
        }
        public String getAsurl() {
            return asurl;
        }

        public void setAsurl_tw(String asurl_tw) {
            this.asurl_tw = asurl_tw;
        }
        public String getAsurl_tw() {
            return asurl_tw;
        }

        public void setAsurl_ch(String asurl_ch) {
            this.asurl_ch = asurl_ch;
        }
        public String getAsurl_ch() {
            return asurl_ch;
        }

        public void setAsurl_jp(String asurl_jp) {
            this.asurl_jp = asurl_jp;
        }
        public String getAsurl_jp() {
            return asurl_jp;
        }
    }

    public static class Smart {
        private String wsort = "";
        private String wtype = "";
        private String wsmart = "";
        private String u_en = "";
        private String u_tw = "";
        private String u_ch = "";
        private String u_jp = "";
        private String d_en = "";
        private String d_tw = "";
        private String d_ch = "";
        private String d_jp = "";
        private String l_en = "";
        private String l_tw = "";
        private String l_ch = "";
        private String l_jp = "";
        private String r_en = "";
        private String r_tw = "";
        private String r_ch = "";
        private String r_jp = "";

        public void setWsort(String wsort) {
            this.wsort = wsort;
        }
        public String getWsort() {
            return wsort;
        }

        public void setWtype(String wtype) {
            this.wtype = wtype;
        }
        public String getWtype() {
            return wtype;
        }

        public void setWsmart(String wsmart) {
            this.wsmart = wsmart;
        }
        public String getWsmart() {
            return wsmart;
        }

        public void setU_en(String u_en) {
            this.u_en = u_en;
        }
        public String getU_en() {
            return u_en;
        }

        public void setU_tw(String u_tw) {
            this.u_tw = u_tw;
        }
        public String getU_tw() {
            return u_tw;
        }

        public void setU_ch(String u_ch) {
            this.u_ch = u_ch;
        }
        public String getU_ch() {
            return u_ch;
        }

        public void setU_jp(String u_jp) {
            this.u_jp = u_jp;
        }
        public String getU_jp() {
            return u_jp;
        }

        public void setD_en(String d_en) {
            this.d_en = d_en;
        }
        public String getD_en() {
            return d_en;
        }

        public void setD_tw(String d_tw) {
            this.d_tw = d_tw;
        }
        public String getD_tw() {
            return d_tw;
        }

        public void setD_ch(String d_ch) {
            this.d_ch = d_ch;
        }
        public String getD_ch() {
            return d_ch;
        }

        public void setD_jp(String d_jp) {
            this.d_jp = d_jp;
        }
        public String getD_jp() {
            return d_jp;
        }

        public void setL_en(String l_en) {
            this.l_en = l_en;
        }
        public String getL_en() {
            return l_en;
        }

        public void setL_tw(String l_tw) {
            this.l_tw = l_tw;
        }
        public String getL_tw() {
            return l_tw;
        }

        public void setL_ch(String l_ch) {
            this.l_ch = l_ch;
        }
        public String getL_ch() {
            return l_ch;
        }

        public void setL_jp(String l_jp) {
            this.l_jp = l_jp;
        }
        public String getL_jp() {
            return l_jp;
        }

        public void setR_en(String r_en) {
            this.r_en = r_en;
        }
        public String getR_en() {
            return r_en;
        }

        public void setR_tw(String r_tw) {
            this.r_tw = r_tw;
        }
        public String getR_tw() {
            return r_tw;
        }

        public void setR_ch(String r_ch) {
            this.r_ch = r_ch;
        }
        public String getR_ch() {
            return r_ch;
        }

        public void setR_jp(String r_jp) {
            this.r_jp = r_jp;
        }
        public String getR_jp() {
            return r_jp;
        }
    }

    public static class Push {
        private String webid_push = "";
        private String webid = "";
        private String menuid = "";
        private String wtitle = "";
        private String wtitle_tw = "";
        private String wtitle_ch = "";
        private String wtitle_jp = "";
        private String path_img = "";
        private String phpname = "";
        private String love = "";
        private String count = "";
        private String renew = "";

        public void setWebid_push(String webid_push) {
            this.webid_push = webid_push;
        }
        public String getWebid_push() {
            return webid_push;
        }

        public void setWebid(String webid) {
            this.webid = webid;
        }
        public String getWebid() {
            return webid;
        }

        public void setMenuid(String menuid) {
            this.menuid = menuid;
        }
        public String getMenuid() {
            return menuid;
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

        public void setRenew(String renew) {
            this.renew = renew;
        }
        public String getRenew() {
            return renew;
        }
    }

    public static class RaData {
        private String id = "";
        private String raname = "";
        private String raname_tw = "";
        private String raname_ch = "";
        private String raname_jp = "";
        private List<DataData> data = new ArrayList<>();

        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

        public void setRaname(String raname) {
            this.raname = raname;
        }
        public String getRaname() {
            return raname;
        }

        public void setRaname_tw(String raname_tw) {
            this.raname_tw = raname_tw;
        }
        public String getRaname_tw() {
            return raname_tw;
        }

        public void setRaname_ch(String raname_ch) {
            this.raname_ch = raname_ch;
        }
        public String getRaname_ch() {
            return raname_ch;
        }

        public void setRaname_jp(String raname_jp) {
            this.raname_jp = raname_jp;
        }
        public String getRaname_jp() {
            return raname_jp;
        }

        public void setData(List<DataData> data) {
            this.data = data;
        }
        public List<DataData> getData() {
            return data;
        }
    }

    public static class DataData {
        private String id = "";
        private String riname = "";
        private String riname_tw = "";
        private String riname_ch = "";
        private String riname_jp = "";

        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

        public void setRiname(String riname) {
            this.riname = riname;
        }
        public String getRiname() {
            return riname;
        }

        public void setRiname_tw(String riname_tw) {
            this.riname_tw = riname_tw;
        }
        public String getRiname_tw() {
            return riname_tw;
        }

        public void setRiname_ch(String riname_ch) {
            this.riname_ch = riname_ch;
        }
        public String getRiname_ch() {
            return riname_ch;
        }

        public void setRiname_jp(String riname_jp) {
            this.riname_jp = riname_jp;
        }
        public String getRiname_jp() {
            return riname_jp;
        }
    }
}
