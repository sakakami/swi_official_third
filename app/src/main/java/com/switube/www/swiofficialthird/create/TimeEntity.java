package com.switube.www.swiofficialthird.create;

public class TimeEntity {
    private boolean mon;
    private boolean tue;
    private boolean wed;
    private boolean thu;
    private boolean fri;
    private boolean sat;
    private boolean sun;
    private boolean all;
    private String start;
    private String close;

    public TimeEntity(boolean mon, boolean tue, boolean wed, boolean thu, boolean fri, boolean sat,
                      boolean sun, boolean all, String start, String close) {
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
        this.all = all;
        this.start = start;
        this.close = close;
    }

    public boolean isMon() {
        return mon;
    }

    public boolean isTue() {
        return tue;
    }

    public boolean isWed() {
        return wed;
    }

    public boolean isThu() {
        return thu;
    }

    public boolean isFri() {
        return fri;
    }

    public boolean isSat() {
        return sat;
    }

    public boolean isSun() {
        return sun;
    }

    public boolean isAll() {
        return all;
    }

    public String getStart() {
        return start;
    }

    public String getClose() {
        return close;
    }
}
