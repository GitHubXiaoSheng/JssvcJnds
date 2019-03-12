package com.app.trafficclient.usebean;

import java.util.List;

public class Weather {


    /**
     * ERRMSG : 成功
     * WCurrent : 17
     * ROWS_DETAIL : [{"temperature":"18~23","WData":"2018-05-20","type":"晴"},{"temperature":"13~21","WData":"2018-05-21","type":"晴"},{"temperature":"18~25","WData":"2018-05-22","type":"晴"},{"temperature":"17~24","WData":"2018-05-23","type":"小雨"},{"temperature":"17~22","WData":"2018-05-24","type":"晴"},{"temperature":"19~23","WData":"2018-05-25","type":"小雨"}]
     * RESULT : S
     */

    private String ERRMSG;
    private int WCurrent;
    private String RESULT;
    private List<ROWSDETAILBean> ROWS_DETAIL;

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public int getWCurrent() {
        return WCurrent;
    }

    public void setWCurrent(int WCurrent) {
        this.WCurrent = WCurrent;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public List<ROWSDETAILBean> getROWS_DETAIL() {
        return ROWS_DETAIL;
    }

    public void setROWS_DETAIL(List<ROWSDETAILBean> ROWS_DETAIL) {
        this.ROWS_DETAIL = ROWS_DETAIL;
    }

    public static class ROWSDETAILBean {
        /**
         * temperature : 18~23
         * WData : 2018-05-20
         * type : 晴
         */

        private String temperature;
        private String WData;
        private String type;

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWData() {
            return WData;
        }

        public void setWData(String WData) {
            this.WData = WData;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
