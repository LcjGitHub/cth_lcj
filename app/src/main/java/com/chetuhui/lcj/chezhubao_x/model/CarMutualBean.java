package com.chetuhui.lcj.chezhubao_x.model;

public class CarMutualBean {
    /**
     * total : 0
     * data : {"programName":"方案一","mutualSize":2,"effectiveTime":"2018-02-03","endTime":"2019-02-03","protectionDays":20,"state":1,"billCode":"cc1f825c79a94030aa00bf9978b917bb"}
     * code : 0
     * msg : 成功
     * pageNum : 0
     */

    private int total;
    private DataBean data;
    private String code;
    private String msg;
    private int pageNum;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public static class DataBean {
        /**
         * programName : 方案一
         * mutualSize : 2
         * effectiveTime : 2018-02-03
         * endTime : 2019-02-03
         * protectionDays : 20
         * state : 1
         * billCode : cc1f825c79a94030aa00bf9978b917bb
         */

        private String programName;
        private int mutualSize;
        private String effectiveTime;
        private String endTime;
        private int protectionDays;
        private int state;
        private String billCode;

        public String getProgramName() {
            return programName;
        }

        public void setProgramName(String programName) {
            this.programName = programName;
        }

        public int getMutualSize() {
            return mutualSize;
        }

        public void setMutualSize(int mutualSize) {
            this.mutualSize = mutualSize;
        }

        public String getEffectiveTime() {
            return effectiveTime;
        }

        public void setEffectiveTime(String effectiveTime) {
            this.effectiveTime = effectiveTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getProtectionDays() {
            return protectionDays;
        }

        public void setProtectionDays(int protectionDays) {
            this.protectionDays = protectionDays;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getBillCode() {
            return billCode;
        }

        public void setBillCode(String billCode) {
            this.billCode = billCode;
        }
    }
}
