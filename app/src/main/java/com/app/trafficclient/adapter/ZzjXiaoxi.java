package com.app.trafficclient.adapter;

public class ZzjXiaoxi {

    private String xuhao,leixing,yuzhi,dangqianzhi;

    public ZzjXiaoxi(String xuhao) {
        this.xuhao = xuhao;
    }

    public ZzjXiaoxi(String xuhao, String leixing, String yuzhi, String dangqianzhi) {
        this.xuhao = xuhao;
        this.leixing = leixing;
        this.yuzhi = yuzhi;
        this.dangqianzhi = dangqianzhi;
    }

    public String getXuhao() {
        return xuhao;
    }

    public void setXuhao(String xuhao) {
        this.xuhao = xuhao;
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    public String getYuzhi() {
        return yuzhi;
    }

    public void setYuzhi(String yuzhi) {
        this.yuzhi = yuzhi;
    }

    public String getDangqianzhi() {
        return dangqianzhi;
    }

    public void setDangqianzhi(String dangqianzhi) {
        this.dangqianzhi = dangqianzhi;
    }
}
