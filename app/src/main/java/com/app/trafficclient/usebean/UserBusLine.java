package com.app.trafficclient.usebean;

import java.util.List;

public class UserBusLine {

    /**
     * ERRMSG : 成功
     * ROWS_DETAIL : [{"Id":1,"PhoneNumber":"13811112222","StartSite":"大庙村","EndSite":"热河路","BusDate":["2018-04-03","2018-04-16","2018-04-17"],"Ticket":8,"Flag":0},{"Id":2,"PhoneNumber":"13811112222","StartSite":"存在","EndSite":"黄河路","BusDate":["2018-04-03","2018-04-16","2018-04-17","2018-04-03","2018-04-16","2018-04-17"],"Ticket":8,"Flag":0},{"Id":4,"PhoneNumber":"13811112222","StartSite":"大庙村","EndSite":"热河路","BusDate":["2018-04-03","2018-04-16","2018-04-17"],"Ticket":8,"Flag":0},{"Id":5,"PhoneNumber":"13811112222","StartSite":"大庙村","EndSite":"热河路","BusDate":["2018-04-03","2018-04-16","2018-04-17"],"Ticket":8,"Flag":0},{"Id":6,"PhoneNumber":"13811112222","StartSite":"大庙村","EndSite":"热河路","BusDate":["2018-04-03","2018-04-16","2018-04-17"],"Ticket":8,"Flag":0},{"Id":7,"PhoneNumber":"13811112222","StartSite":"大庙村","EndSite":"热河路","BusDate":["2018-04-03","2018-04-16","2018-04-17"],"Ticket":8,"Flag":0},{"Id":8,"PhoneNumber":"13811112222","StartSite":"大庙村","EndSite":"热河路","BusDate":["2018-04-03","2018-04-16","2018-04-17"],"Ticket":8,"Flag":0},{"Id":9,"PhoneNumber":"13811112222","StartSite":"大庙村","EndSite":"热河路","BusDate":["2018-04-03","2018-04-16","2018-04-17"],"Ticket":7,"Flag":1},{"Id":10,"PhoneNumber":"13811112222","StartSite":"大庙村","EndSite":"热河路","BusDate":["2018-04-03","2018-04-16","2018-04-17"],"Ticket":8,"Flag":1},{"Id":11,"PhoneNumber":"13811112222","StartSite":"大庙村","EndSite":"热河路","BusDate":["2018-04-03","2018-04-16","2018-04-19"],"Ticket":8,"Flag":1}]
     * RESULT : S
     */

    private String ERRMSG;
    private String RESULT;
    private List<ROWSDETAILBean> ROWS_DETAIL;

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
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
         * Id : 1
         * PhoneNumber : 13811112222
         * StartSite : 大庙村
         * EndSite : 热河路
         * BusDate : ["2018-04-03","2018-04-16","2018-04-17"]
         * Ticket : 8
         * Flag : 0
         */

        private int Id;
        private String PhoneNumber;
        private String StartSite;
        private String EndSite;
        private int Ticket;
        private int Flag;
        private List<String> BusDate;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(String PhoneNumber) {
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

        public int getTicket() {
            return Ticket;
        }

        public void setTicket(int Ticket) {
            this.Ticket = Ticket;
        }

        public int getFlag() {
            return Flag;
        }

        public void setFlag(int Flag) {
            this.Flag = Flag;
        }

        public List<String> getBusDate() {
            return BusDate;
        }

        public void setBusDate(List<String> BusDate) {
            this.BusDate = BusDate;
        }
    }
}
