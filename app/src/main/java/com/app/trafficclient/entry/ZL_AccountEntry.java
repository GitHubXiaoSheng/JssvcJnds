package com.app.trafficclient.entry;

public class ZL_AccountEntry {
    private int id;
    private int imgId;
    private String plate;
    private String owner;
    private int balance;

    public ZL_AccountEntry(int id, int imgId, String plate, String owner, int balance) {
        this.id = id;
        this.imgId = imgId;
        this.plate = plate;
        this.owner = owner;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
