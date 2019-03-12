package com.app.trafficclient.usebean;

public class TrafficConfig {
    private String RESULT;
    private String ERRMSG;
    private String RedTime;
    private String GreenTime;
    private String YellowTime;

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public String getRedTime() {
        return RedTime;
    }

    public void setRedTime(String redTime) {
        RedTime = redTime;
    }

    public String getGreenTime() {
        return GreenTime;
    }

    public void setGreenTime(String greenTime) {
        GreenTime = greenTime;
    }

    public String getYellowTime() {
        return YellowTime;
    }

    public void setYellowTime(String yellowTime) {
        YellowTime = yellowTime;
    }
}
