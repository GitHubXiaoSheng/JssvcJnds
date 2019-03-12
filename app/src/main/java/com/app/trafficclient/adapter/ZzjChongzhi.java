package com.app.trafficclient.adapter;

public class ZzjChongzhi {

    private String xuhao,chehao,chongzhijine,caozuoren,chongzhishijian;

    public ZzjChongzhi(String xuhao) {
        this.xuhao = xuhao;
    }

    public ZzjChongzhi(String xuhao, String chehao, String chongzhijine, String caozuoren, String chongzhishijian) {
        this.xuhao = xuhao;
        this.chehao = chehao;
        this.chongzhijine = chongzhijine;
        this.caozuoren = caozuoren;
        this.chongzhishijian = chongzhishijian;
    }

    public String getXuhao() {
        return xuhao;
    }

    public void setXuhao(String xuhao) {
        this.xuhao = xuhao;
    }

    public String getChehao() {
        return chehao;
    }

    public void setChehao(String chehao) {
        this.chehao = chehao;
    }

    public String getChongzhijine() {
        return chongzhijine;
    }

    public void setChongzhijine(String chongzhijine) {
        this.chongzhijine = chongzhijine;
    }

    public String getCaozuoren() {
        return caozuoren;
    }

    public void setCaozuoren(String caozuoren) {
        this.caozuoren = caozuoren;
    }

    public String getChongzhishijian() {
        return chongzhishijian;
    }

    public void setChongzhishijian(String chongzhishijian) {
        this.chongzhishijian = chongzhishijian;
    }
}
