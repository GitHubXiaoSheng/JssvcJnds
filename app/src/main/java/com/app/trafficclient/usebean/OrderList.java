package com.app.trafficclient.usebean;

public class OrderList {

    /**
     * UserName : user1
     * Id : 1
     * PhoneNumber : 13811112222
     * StartSite : 大庙村
     * EndSite : 热河路
     * BusDate : 2018-04-03,2018-04-16,2018-04-17
     */

    private String UserName;
    private int Id;
    private long PhoneNumber;
    private String StartSite;
    private String EndSite;
    private String BusDate;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public long getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(long PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getStartSite() {
        return StartSite;
    }

    public void setStartSite(String StartSite) {
        this.StartSite = StartSite;
    }

    public String getEndSite() {
        return EndSite;
    }

    public void setEndSite(String EndSite) {
        this.EndSite = EndSite;
    }

    public String getBusDate() {
        return BusDate;
    }

    public void setBusDate(String BusDate) {
        this.BusDate = BusDate;
    }
}
