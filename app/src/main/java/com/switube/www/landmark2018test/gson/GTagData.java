package com.switube.www.landmark2018test.gson;

import java.util.List;

public class GTagData {
    private List<QBD> QBData;
    private List<QBN> QBNData;
    private List<QBA> QBAData;

    public List<QBA> getQBAData() {
        return QBAData;
    }

    public List<QBD> getQBData() {
        return QBData;
    }

    public List<QBN> getQBNData() {
        return QBNData;
    }

    public void setQBAData(List<QBA> QBAData) {
        this.QBAData = QBAData;
    }

    public void setQBData(List<QBD> QBData) {
        this.QBData = QBData;
    }

    public void setQBNData(List<QBN> QBNData) {
        this.QBNData = QBNData;
    }

    public static class QBD {
        private String tqbid;
        private String qbid;
        private String qb_name;

        public String getQbid() {
            return qbid;
        }

        public String getQb_name() {
            return qb_name;
        }

        public String getTqbid() {
            return tqbid;
        }

        public void setQbid(String qbid) {
            this.qbid = qbid;
        }

        public void setQb_name(String qb_name) {
            this.qb_name = qb_name;
        }

        public void setTqbid(String tqbid) {
            this.tqbid = tqbid;
        }
    }

    public static class QBN {
        private String qbid;
        private String qbnid;
        private String norm;

        public String getQbid() {
            return qbid;
        }

        public String getQbnid() {
            return qbnid;
        }

        public String getNorm() {
            return norm;
        }

        public void setQbid(String qbid) {
            this.qbid = qbid;
        }

        public void setQbnid(String qbnid) {
            this.qbnid = qbnid;
        }

        public void setNorm(String norm) {
            this.norm = norm;
        }
    }

    public static class QBA {
        private String naid;
        private String qbnid;
        private String qbaid;
        private String assess;

        public void setQbnid(String qbnid) {
            this.qbnid = qbnid;
        }

        public void setQbaid(String qbaid) {
            this.qbaid = qbaid;
        }

        public void setNaid(String naid) {
            this.naid = naid;
        }

        public void setAssess(String assess) {
            this.assess = assess;
        }

        public String getAssess() {
            return assess;
        }

        public String getQbnid() {
            return qbnid;
        }

        public String getQbaid() {
            return qbaid;
        }

        public String getNaid() {
            return naid;
        }
    }
}
