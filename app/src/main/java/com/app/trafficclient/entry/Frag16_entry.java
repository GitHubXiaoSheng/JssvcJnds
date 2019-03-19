package com.app.trafficclient.entry;

public class Frag16_entry {
    private String time;
    private String rPersonal;
    private String plate;
    private String recharge;
    private String balance;

    public Frag16_entry(String time, String rPersonal, String plate, String recharge, String balance) {
        this.time = time;
        this.rPersonal = rPersonal;
        this.plate = plate;
        this.recharge = recharge;
        this.balance = balance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getrPersonal() {
        return rPersonal;
    }

    public void setrPersonal(String rPersonal) {
        this.rPersonal = rPersonal;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getRecharge() {
        return recharge;
    }

    public void setRecharge(String recharge) {
        this.recharge = recharge;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
