package com.app.trafficclient.entry;

public class Zl_Bus {
    private String busNum;
    private String distance;

    public Zl_Bus(String busNum, String distance) {
        this.busNum = busNum;
        this.distance = distance;
    }

    public String getBusNum() {
        return busNum;
    }

    public void setBusNum(String busNum) {
        this.busNum = busNum;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
