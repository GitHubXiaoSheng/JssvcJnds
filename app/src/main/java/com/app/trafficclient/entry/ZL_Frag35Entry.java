package com.app.trafficclient.entry;

import android.graphics.Bitmap;

public class ZL_Frag35Entry {
    private int id;
    private Bitmap imgBitmap;
    private String spotName;
    private String ticket;

    public ZL_Frag35Entry(int id, Bitmap imgBitmap, String spotName, String ticket) {
        this.id = id;
        this.imgBitmap = imgBitmap;
        this.spotName = spotName;
        this.ticket = ticket;
    }

    public Bitmap getImgBitmap() {
        return imgBitmap;
    }

    public void setImgBitmap(Bitmap imgBitmap) {
        this.imgBitmap = imgBitmap;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Frag35Entry{" +
                "id=" + id +
                ", imgBitmap=" + imgBitmap +
                ", spotName='" + spotName + '\'' +
                ", ticket='" + ticket + '\'' +
                '}';
    }
}
