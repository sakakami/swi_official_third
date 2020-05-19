package com.switube.www.landmark2018test.gson;

import java.util.ArrayList;
import java.util.List;

public class GTicket {
    private List<Data> data = new ArrayList<>();

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        private String ticketId = "";
        private String maid = "";
        private String goodId = "";
        private String goodName = "";
        private String goodPrice = "";
        private String goodPhoto = "";
        private String goodUse = "";
        private String storeId = "";
        private String storeName = "";
        private String cDate = "";
        private String uDate = "";

        public String getTicketId() {
            return ticketId;
        }
        public void setTicketId(String ticketId) {
            this.ticketId = ticketId;
        }

        public String getMaid() {
            return maid;
        }
        public void setMaid(String maid) {
            this.maid = maid;
        }

        public String getGoodId() {
            return goodId;
        }
        public void setGoodId(String goodId) {
            this.goodId = goodId;
        }

        public String getGoodName() {
            return goodName;
        }
        public void setGoodName(String goodName) {
            this.goodName = goodName;
        }

        public String getGoodPrice() {
            return goodPrice;
        }
        public void setGoodPrice(String goodPrice) {
            this.goodPrice = goodPrice;
        }

        public String getGoodPhoto() {
            return goodPhoto;
        }
        public void setGoodPhoto(String goodPhoto) {
            this.goodPhoto = goodPhoto;
        }

        public String getGoodUse() {
            return goodUse;
        }
        public void setGoodUse(String goodUse) {
            this.goodUse = goodUse;
        }

        public String getStoreId() {
            return storeId;
        }
        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getStoreName() {
            return storeName;
        }
        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public void setcDate(String cDate) {
            this.cDate = cDate;
        }
        public String getcDate() {
            return cDate;
        }

        public void setuDate(String uDate) {
            this.uDate = uDate;
        }
        public String getuDate() {
            return uDate;
        }
    }

}
