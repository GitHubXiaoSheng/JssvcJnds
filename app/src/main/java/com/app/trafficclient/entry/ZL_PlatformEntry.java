package com.app.trafficclient.entry;

import java.util.List;

public class ZL_PlatformEntry {
    private boolean isOpen;
    private String platformNum;
    private List<Zl_Bus> zlBusList;

    public ZL_PlatformEntry(boolean isOpen, String platformNum, List<Zl_Bus> zlBusList) {
        this.isOpen = isOpen;
        this.platformNum = platformNum;
        this.zlBusList = zlBusList;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public List<Zl_Bus> getZlBusList() {
        return zlBusList;
    }

    public void setZlBusList(List<Zl_Bus> zlBusList) {
        this.zlBusList = zlBusList;
    }

    public String getPlatformNum() {
        return platformNum;
    }

    public void setPlatformNum(String platformNum) {
        this.platformNum = platformNum;
    }

}
