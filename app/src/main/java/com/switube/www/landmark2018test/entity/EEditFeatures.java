package com.switube.www.landmark2018test.entity;

import java.util.List;

public class EEditFeatures {
    private Class mClass;
    private List<Term> mTerm;
    private String spid;

    public void setmClass(Class mClass) {
        this.mClass = mClass;
    }
    public Class getmClass() {
        return mClass;
    }

    public void setmTerm(List<Term> mTerm) {
        this.mTerm = mTerm;
    }
    public List<Term> getmTerm() {
        return mTerm;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }
    public String getSpid() {
        return spid;
    }

    public static class Class {
        private String mscid;
        private String mcid;

        public void setMscid(String mscid) {
            this.mscid = mscid;
        }
        public String getMscid() {
            return mscid;
        }

        public void setMcid(String mcid) {
            this.mcid = mcid;
        }
        public String getMcid() {
            return mcid;
        }
    }

    public static class Term {
        private String mtid;
        private List<Item> item;

        public void setMtid(String mtid) {
            this.mtid = mtid;
        }
        public String getMtid() {
            return mtid;
        }

        public void setItem(List<Item> item) {
            this.item = item;
        }
        public List<Item> getItem() {
            return item;
        }
    }

    public static class Item {
        private String mstid;
        private String miid;

        public void setMstid(String mstid) {
            this.mstid = mstid;
        }
        public String getMstid() {
            return mstid;
        }

        public void setMiid(String miid) {
            this.miid = miid;
        }
        public String getMiid() {
            return miid;
        }
    }
}
