package com.app.trafficclient.entry;

public class ZL_Frag11_TrafficMangEntry {
    private int road;
    private int redTime;
    private int yellowTime;
    private int greenTime;
    private boolean isChoose;

    public ZL_Frag11_TrafficMangEntry(int road, int redTime, int yellowTime, int greenTime, boolean isChoose) {
        this.road = road;
        this.redTime = redTime;
        this.yellowTime = yellowTime;
        this.greenTime = greenTime;
        this.isChoose = isChoose;
    }

    public int getRoad() {
        return road;
    }

    public void setRoad(int road) {
        this.road = road;
    }

    public int getRedTime() {
        return redTime;
    }

    public void setRedTime(int redTime) {
        this.redTime = redTime;
    }

    public int getYellowTime() {
        return yellowTime;
    }

    public void setYellowTime(int yellowTime) {
        this.yellowTime = yellowTime;
    }

    public int getGreenTime() {
        return greenTime;
    }

    public void setGreenTime(int greenTime) {
        this.greenTime = greenTime;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }
}
