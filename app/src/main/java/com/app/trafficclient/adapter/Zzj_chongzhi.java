package com.app.trafficclient.adapter;

public class Zzj_chongzhi {

    private String xuhao,chehao,jin_e,riqi;

    public Zzj_chongzhi(String xuhao) {
        this.xuhao = xuhao;
    }

    public Zzj_chongzhi(String xuhao, String chehao, String jin_e, String riqi) {
        this.xuhao = xuhao;
        this.chehao = chehao;
        this.jin_e = jin_e;
        this.riqi = riqi;
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

    public String getJin_e() {
        return jin_e;
    }

    public void setJin_e(String jin_e) {
        this.jin_e = jin_e;
    }

    public String getRiqi() {
        return riqi;
    }

    public void setRiqi(String riqi) {
        this.riqi = riqi;
    }
}
