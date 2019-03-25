package com.app.trafficclient.entry;

import java.util.List;

public class PlatformEntry {
    private boolean isOpen;
    private String platformNum;
    private List<Bus> busList;

    public PlatformEntry(boolean isOpen, String platformNum, List<Bus> busList) {
        this.isOpen = isOpen;
        this.platformNum = platformNum;
        this.busList = busList;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }

    public String getPlatformNum() {
        return platformNum;
    }

    public void setPlatformNum(String platformNum) {
        this.platformNum = platformNum;
    }

}
