package com.app.trafficclient.adapter;

public class Weizhang {
    private String text;
    private Integer img;

    public Weizhang(String text, Integer img) {
        this.text = text;
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }
}
