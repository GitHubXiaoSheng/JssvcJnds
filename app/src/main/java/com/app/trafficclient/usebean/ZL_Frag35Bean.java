package com.app.trafficclient.usebean;

import java.util.List;

public class ZL_Frag35Bean {
    private String ERRMSG;
    private String RESULT;
    private List<SpotInfo> ROWS_DETAIL;

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

    public List<SpotInfo> getROWS_DETAIL() {
        return ROWS_DETAIL;
    }

    public void setROWS_DETAIL(List<SpotInfo> ROWS_DETAIL) {
        this.ROWS_DETAIL = ROWS_DETAIL;
    }

    @Override
    public String toString() {
        return "ZL_Frag35Bean{" +
                "ERRMSG='" + ERRMSG + '\'' +
                ", RESULT='" + RESULT + '\'' +
                ", ROWS_DETAIL=" + ROWS_DETAIL +
                '}';
    }

    public class SpotInfo{
        private int id;
        private String name;
        private int ticket;
        private String img;
        private String info;
        private String tel;
        private int rating;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTicket() {
            return ticket;
        }

        public void setTicket(int ticket) {
            this.ticket = ticket;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        @Override
        public String toString() {
            return "SpotInfo{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", ticket=" + ticket +
                    ", img='" + img + '\'' +
                    ", info='" + info + '\'' +
                    ", tel='" + tel + '\'' +
                    ", rating=" + rating +
                    '}';
        }
    }
}
