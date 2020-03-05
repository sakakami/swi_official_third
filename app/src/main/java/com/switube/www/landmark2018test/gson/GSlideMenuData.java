package com.switube.www.landmark2018test.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GSlideMenuData {
    @SerializedName("Mode")
    private List<Mode> modes;
    @SerializedName("Style")
    private List<Style> styles;
    @SerializedName("Class")
    private List<Class> classes;
    @SerializedName("Term")
    private List<Term> terms;
    @SerializedName("Item")
    private List<Item> items;
    @SerializedName("City")
    private List<City> cities;
    private List<String> key;

    public static class Mode {
        private String mmid;
        private String mmtitle_en;
        private String mmtitle_tw;
        private String mmtitle_ch;
        private String mmtitle_jp;

        public void setMmid(String mmid) {
            this.mmid = mmid;
        }

        public void setMmtitle_en(String mmtitle_en) {
            this.mmtitle_en = mmtitle_en;
        }

        public void setMmtitle_tw(String mmtitle_tw) {
            this.mmtitle_tw = mmtitle_tw;
        }

        public void setMmtitle_ch(String mmtitle_ch) {
            this.mmtitle_ch = mmtitle_ch;
        }

        public void setMmtitle_jp(String mmtitle_jp) {
            this.mmtitle_jp = mmtitle_jp;
        }

        public String getMmid() {
            return mmid;
        }

        public String getMmtitle_en() {
            return mmtitle_en;
        }

        public String getMmtitle_tw() {
            return mmtitle_tw;
        }

        public String getMmtitle_ch() {
            return mmtitle_ch;
        }

        public String getMmtitle_jp() {
            return mmtitle_jp;
        }
    }

    public static class Style {
        private String menuid;
        private String mmspid;
        private String mmid;
        private String msid;
        private String mstitle_en;
        private String mstitle_tw;
        private String mstitle_ch;
        private String mstitle_jp;

        public void setMenuid(String menuid) {
            this.menuid = menuid;
        }

        public void setMmspid(String mmspid) {
            this.mmspid = mmspid;
        }

        public void setMmid(String mmid) {
            this.mmid = mmid;
        }

        public void setMsid(String msid) {
            this.msid = msid;
        }

        public void setMstitle_en(String mstitle_en) {
            this.mstitle_en = mstitle_en;
        }

        public void setMstitle_tw(String mstitle_tw) {
            this.mstitle_tw = mstitle_tw;
        }

        public void setMstitle_ch(String mstitle_ch) {
            this.mstitle_ch = mstitle_ch;
        }

        public void setMstitle_jp(String mstitle_jp) {
            this.mstitle_jp = mstitle_jp;
        }

        public String getMenuid() {
            return menuid;
        }

        public String getMmspid() {
            return mmspid;
        }

        public String getMmid() {
            return mmid;
        }

        public String getMsid() {
            return msid;
        }

        public String getMstitle_en() {
            return mstitle_en;
        }

        public String getMstitle_tw() {
            return mstitle_tw;
        }

        public String getMstitle_ch() {
            return mstitle_ch;
        }

        public String getMstitle_jp() {
            return mstitle_jp;
        }
    }

    public static class Class {
        private String mscid;
        private String msid;
        private String mcid;
        private String mctitle_en;
        private String mctitle_tw;
        private String mctitle_ch;
        private String mctitle_jp;

        public void setMcid(String mcid) {
            this.mcid = mcid;
        }

        public void setMsid(String msid) {
            this.msid = msid;
        }

        public void setMscid(String mscid) {
            this.mscid = mscid;
        }

        public void setMctitle_en(String mctitle_en) {
            this.mctitle_en = mctitle_en;
        }

        public void setMctitle_tw(String mctitle_tw) {
            this.mctitle_tw = mctitle_tw;
        }

        public void setMctitle_ch(String mctitle_ch) {
            this.mctitle_ch = mctitle_ch;
        }

        public void setMctitle_jp(String mctitle_jp) {
            this.mctitle_jp = mctitle_jp;
        }

        public String getMscid() {
            return mscid;
        }

        public String getMsid() {
            return msid;
        }

        public String getMcid() {
            return mcid;
        }

        public String getMctitle_en() {
            return mctitle_en;
        }

        public String getMctitle_tw() {
            return mctitle_tw;
        }

        public String getMctitle_ch() {
            return mctitle_ch;
        }

        public String getMctitle_jp() {
            return mctitle_jp;
        }
    }

    public static class Term {
        private String msid;
        private String mtid;
        private String mttitle_en;
        private String mttitle_tw;
        private String mttitle_ch;
        private String mttitle_jp;

        public void setMsid(String msid) {
            this.msid = msid;
        }

        public void setMtid(String mtid) {
            this.mtid = mtid;
        }

        public void setMttitle_en(String mttitle_en) {
            this.mttitle_en = mttitle_en;
        }

        public void setMttitle_tw(String mttitle_tw) {
            this.mttitle_tw = mttitle_tw;
        }

        public void setMttitle_ch(String mttitle_ch) {
            this.mttitle_ch = mttitle_ch;
        }

        public void setMttitle_jp(String mttitle_jp) {
            this.mttitle_jp = mttitle_jp;
        }

        public String getMsid() {
            return msid;
        }

        public String getMtid() {
            return mtid;
        }

        public String getMttitle_en() {
            return mttitle_en;
        }

        public String getMttitle_tw() {
            return mttitle_tw;
        }

        public String getMttitle_ch() {
            return mttitle_ch;
        }

        public String getMttitle_jp() {
            return mttitle_jp;
        }
    }

    public static class Item {
        private String mstid;
        private String mtid;
        private String miid;
        private String mititle_en;
        private String mititle_tw;
        private String mititle_ch;
        private String mititle_jp;

        public void setMstid(String mstid) {
            this.mstid = mstid;
        }

        public void setMtid(String mtid) {
            this.mtid = mtid;
        }

        public void setMiid(String miid) {
            this.miid = miid;
        }

        public void setMititle_en(String mititle_en) {
            this.mititle_en = mititle_en;
        }

        public void setMititle_tw(String mititle_tw) {
            this.mititle_tw = mititle_tw;
        }

        public void setMititle_ch(String mititle_ch) {
            this.mititle_ch = mititle_ch;
        }

        public void setMititle_jp(String mititle_jp) {
            this.mititle_jp = mititle_jp;
        }

        public String getMstid() {
            return mstid;
        }

        public String getMtid() {
            return mtid;
        }

        public String getMiid() {
            return miid;
        }

        public String getMititle_en() {
            return mititle_en;
        }

        public String getMititle_tw() {
            return mititle_tw;
        }

        public String getMititle_ch() {
            return mititle_ch;
        }

        public String getMititle_jp() {
            return mititle_jp;
        }
    }

    public static class City {
        private String countryid;
        private String cityid;
        private String start;
        private String end;
        private String city_en;
        private String city_ch;
        private String city_tw;
        private String city_jp;

        public void setCity_ch(String city_ch) {
            this.city_ch = city_ch;
        }

        public void setCity_en(String city_en) {
            this.city_en = city_en;
        }

        public void setCity_jp(String city_jp) {
            this.city_jp = city_jp;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public void setCity_tw(String city_tw) {
            this.city_tw = city_tw;
        }

        public void setCountryid(String countryid) {
            this.countryid = countryid;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getStart() {
            return start;
        }

        public String getCity_ch() {
            return city_ch;
        }

        public String getCity_en() {
            return city_en;
        }

        public String getCity_jp() {
            return city_jp;
        }

        public String getCity_tw() {
            return city_tw;
        }

        public String getCityid() {
            return cityid;
        }

        public String getCountryid() {
            return countryid;
        }

        public String getEnd() {
            return end;
        }
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setModes(List<Mode> modes) {
        this.modes = modes;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public void setKey(List<String> key) {
        this.key = key;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Mode> getModes() {
        return modes;
    }

    public List<Style> getStyles() {
        return styles;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public List<String> getKey() {
        return key;
    }
}
