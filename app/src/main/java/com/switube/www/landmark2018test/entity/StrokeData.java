package com.switube.www.landmark2018test.entity;

//mode 1 -> walk
//mode 2 -> bike
//mode 3 -> moto
//mode 4 -> car
public class StrokeData {
    private double dis = 0;
    private double speed = 0;
    private double carbon = 0;
    private String title = "";
    private String content = "";
    private int mode = 0;

    public void setDis(double dis) { this.dis = dis; }
    public double getDis() { return dis; }

    public void setSpeed(double speed) { this.speed = speed; }
    public double getSpeed() { return speed; }

    public void setCarbon(double carbon) { this.carbon = carbon; }
    public double getCarbon() { return carbon; }

    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return title; }

    public void setContent(String content) { this.content = content; }
    public String getContent() { return content; }

    public void setMode(int mode) { this.mode = mode; }
    public int getMode() { return mode; }
}
