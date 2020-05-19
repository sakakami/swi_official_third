package com.switube.www.landmark2018test.entity;

public class ECashFlow {
    private String type = "";
    private String cash = "";
    private String date = "";
    private String walletId = "";
    private String maid = "";
    private String check = "";
    private String target = "";
    private String targetMaid = "";
    private String ticketId = "";
    private String ticketName = "";
    private String ticketStore = "";
    private String checkin = "";
    private String checkout = "";
    private String edate = "";
    private String udate = "";

    public String getCash() { return cash; }
    public void setCash(String cash) { this.cash = cash; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getWalletId() { return walletId; }
    public void setWalletId(String walletId) { this.walletId = walletId; }

    public String getMaid() { return maid; }
    public void setMaid(String maid) { this.maid = maid; }

    public String getCheck() { return check; }
    public void setCheck(String check) { this.check = check; }

    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }

    public String getTargetMaid() {
        return targetMaid;
    }
    public void setTargetMaid(String targetMaid) {
        this.targetMaid = targetMaid;
    }

    public String getTicketId() {
        return ticketId;
    }
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketName() {
        return ticketName;
    }
    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }
}
