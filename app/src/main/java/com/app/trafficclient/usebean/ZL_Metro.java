package com.app.trafficclient.usebean;

import java.util.List;

public class ZL_Metro {
    private String ERRMSG;
    private List<Metro> ROWS_DETAIL;
    private String RESULT;

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public List<Metro> getROWS_DETAIL() {
        return ROWS_DETAIL;
    }

    public void setROWS_DETAIL(List<Metro> ROWS_DETAIL) {
        this.ROWS_DETAIL = ROWS_DETAIL;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    @Override
    public String toString() {
        return "ZL_Metro{" +
                "ERRMSG='" + ERRMSG + '\'' +
                ", ROWS_DETAIL=" + ROWS_DETAIL +
                ", RESULT='" + RESULT + '\'' +
                '}';
    }

    public class Metro{
        private String id;
        private String name;
        private String map;
        private List<String> sites;
        private List<FirstOrLastTime> time;
        private List<String> transfersites;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMap() {
            return map;
        }

        public void setMap(String map) {
            this.map = map;
        }

        public List<String> getSites() {
            return sites;
        }

        public void setSites(List<String> sites) {
            this.sites = sites;
        }

        public List<FirstOrLastTime> getTime() {
            return time;
        }

        public void setTime(List<FirstOrLastTime> time) {
            this.time = time;
        }

        public List<String> getTransfersites() {
            return transfersites;
        }

        public void setTransfersites(List<String> transfersites) {
            this.transfersites = transfersites;
        }

        @Override
        public String toString() {
            return "Metro{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", map='" + map + '\'' +
                    ", sites=" + sites +
                    ", time=" + time +
                    ", transfersites=" + transfersites +
                    '}';
        }

        public class FirstOrLastTime{
            private String site;
            private String starttime;
            private String endtime;

            public String getSite() {
                return site;
            }

            public void setSite(String site) {
                this.site = site;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            @Override
            public String toString() {
                return "FirstOrLastTime{" +
                        "site='" + site + '\'' +
                        ", starttime='" + starttime + '\'' +
                        ", endtime='" + endtime + '\'' +
                        '}';
            }
        }

    }

}
