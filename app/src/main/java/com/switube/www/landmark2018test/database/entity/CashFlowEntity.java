package com.switube.www.landmark2018test.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "cash_flow")
public class CashFlowEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String maid;
    private String name;
    private String cash;
    private String date;
    private String type;
    public CashFlowEntity(String maid, String name, String type, String cash, String date) {
        this.maid = maid;
        this.name = name;
        this.cash = cash;
        this.date = date;
        this.type = type;
    }

    public void setCash(String cash) { this.cash = cash; }
    public String getCash() { return cash; }

    public void setDate(String date) { this.date = date; }
    public String getDate() { return date; }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void setMaid(String maid) { this.maid = maid; }
    public String getMaid() { return maid; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setType(String type) { this.type = type; }
    public String getType() { return type; }
}
