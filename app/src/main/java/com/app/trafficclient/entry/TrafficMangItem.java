package com.app.trafficclient.entry;

public class TrafficMangItem {
    private String road;
    private String redTime;
    private String yellowTime;
    private String greenTime;

    public TrafficMangItem(String road, String redTime, String yellowTime, String greenTime) {
        this.road = road;
        this.redTime = redTime;
        this.yellowTime = yellowTime;
        this.greenTime = greenTime;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getRedTime() {
        return redTime;
    }

    public void setRedTime(String redTime) {
        this.redTime = redTime;
    }

    public String getYellowTime() {
        return yellowTime;
    }

    public void setYellowTime(String yellowTime) {
        this.yellowTime = yellowTime;
    }

    public String getGreenTime() {
        return greenTime;
    }

    public void setGreenTime(String greenTime) {
        this.greenTime = greenTime;
    }
}
