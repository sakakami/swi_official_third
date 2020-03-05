package com.switube.www.landmark2018test.entity;

public class EEditTime {
    private boolean mon = false;
    private boolean tue = false;
    private boolean wed = false;
    private boolean thu = false;
    private boolean fri = false;
    private boolean sat = false;
    private boolean sun = false;
    private boolean allDay = false;
    private boolean edit = false;
    private String start = "";
    private String end = "";

    public void setMon(boolean mon) {
        this.mon = mon;
    }
    public boolean isMon() {
        return mon;
    }

    public void setTue(boolean tue) {
        this.tue = tue;
    }
    public boolean isTue() {
        return tue;
    }

    public void setWed(boolean wed) {
        this.wed = wed;
    }
    public boolean isWed() {
        return wed;
    }

    public void setThu(boolean thu) {
        this.thu = thu;
    }
    public boolean isThu() {
        return thu;
    }

    public void setFri(boolean fri) {
        this.fri = fri;
    }
    public boolean isFri() {
        return fri;
    }

    public void setSat(boolean sat) {
        this.sat = sat;
    }
    public boolean isSat() {
        return sat;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
    }
    public boolean isSun() {
        return sun;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }
    public boolean isAllDay() {
        return allDay;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    public boolean isEdit() {
        return edit;
    }

    public void setStart(String start) {
        this.start = start;
    }
    public String getStart() {
        return start;
    }

    public void setEnd(String end) {
        this.end = end;
    }
    public String getEnd() {
        return end;
    }
}
