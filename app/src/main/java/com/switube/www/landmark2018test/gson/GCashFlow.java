package com.switube.www.landmark2018test.gson;

import java.util.ArrayList;
import java.util.List;

public class GCashFlow {
    private List<Data> data = new ArrayList<>();

    public List<Data> getData() { return data; }
    public void setData(List<Data> data) { this.data = data; }

    public static class Data {
        private String id = "";
        private String target = "";
        private String targetMaid = "";
        private String type = "";
        private String cash = "";
        private String maid = "";
        private String name = "";
        private String checkin = "";
        private String checkout = "";
        private String ticketId = "";
        private String ticketName = "";
        private String ticketStore = "";
        private String used = "";
        private String cDate = "";
        private String eDate = "";
        private String uDate = "";
        private String check = "";

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getTarget() { return target; }
        public void setTarget(String target) { this.target = target; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public String getCash() { return cash; }
        public void setCash(String cash) { this.cash = cash; }

        public void setMaid(String maid) {
            this.maid = maid;
        }
        public String getMaid() {
            return maid;
        }

        public String getUsed() {
            return used;
        }
        public void setUsed(String used) {
            this.used = used;
        }

        public void setCheckin(String checkin) {
            this.checkin = checkin;
        }
        public String getCheckin() {
            return checkin;
        }

        public void setCheckout(String checkout) {
            this.checkout = checkout;
        }
        public String getCheckout() {
            return checkout;
        }

        public void setTicketId(String ticketId) {
            this.ticketId = ticketId;
        }
        public String getTicketId() {
            return ticketId;
        }

        public void setTicketName(String ticketName) {
            this.ticketName = ticketName;
        }
        public String getTicketName() {
            return ticketName;
        }

        public void setTicketStore(String ticketStore) {
            this.ticketStore = ticketStore;
        }
        public String getTicketStore() {
            return ticketStore;
        }

        public void setCDate(String cDate) {
            this.cDate = cDate;
        }
        public String getCDate() {
            return cDate;
        }

        public void setEDate(String eDate) {
            this.eDate = eDate;
        }
        public String getEDate() {
            return eDate;
        }

        public void setUDate(String uDate) {
            this.uDate = uDate;
        }
        public String getUDate() {
            return uDate;
        }

        public void setCheck(String check) {
            this.check = check;
        }
        public String getCheck() {
            return check;
        }

        public String getTargetMaid() {
            return targetMaid;
        }
        public void setTargetMaid(String targetMaid) {
            this.targetMaid = targetMaid;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }
}
