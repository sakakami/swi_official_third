package com.switube.www.landmark2018test.gson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GCashFlow {
    private List<Data> data = new ArrayList<>();

    public List<Data> getData() { return data; }
    public void setData(List<Data> data) { this.data = data; }

    public static class Data {
        private String id = "";
        private String target = "";
        private String type = "";
        private String cash = "";
        private String clear = "";
        private String date = "";
        private String maid = "";
        private String check = "";

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getTarget() { return target; }
        public void setTarget(String target) { this.target = target; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public String getCash() { return cash; }
        public void setCash(String cash) { this.cash = cash; }

        public String getClear() { return clear; }
        public void setClear(String clear) { this.clear = clear; }

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }

        public String getMaid() { return maid; }
        public void setMaid(String maid) { this.maid = maid; }

        public String getCheck() { return check; }
        public void setCheck(String check) { this.check = check; }
    }
}
