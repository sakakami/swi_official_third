package com.switube.www.landmark2018test.entity;

public class ECarbon {
    private double latitude = 0;
    private double longitude = 0;
    private String dateFull = "";
    private String dateShort = "";
    private String carbonId = "";

    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLatitude() { return latitude; }

    public void setLongitude(double longitude) { this.longitude = longitude; }
    public double getLongitude() { return longitude; }

    public void setDateFull(String dateFull) { this.dateFull = dateFull; }
    public String getDateFull() { return dateFull; }

    public void setDateShort(String dateShort) { this.dateShort = dateShort; }
    public String getDateShort() { return dateShort; }

    public void setCarbonId(String carbonId) { this.carbonId = carbonId; }
    public String getCarbonId() { return carbonId; }
}
