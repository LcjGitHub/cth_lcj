package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class MmhBean {
    /**
     * total : 15
     * data : [{"programName":"方案一","mutualSize":2,"effectiveTime":"2018-02-03","endTime":"2018-02-03","protectionDays":0,"state":1,"carNum":"川A1234565","billCode":"cc1f825c79a94030aa00bf9978b917bb"},{"programName":null,"mutualSize":2,"effectiveTime":null,"endTime":null,"protectionDays":0,"state":1,"carNum":"川A55647855","billCode":"cc1f825c79a94030aa00bf9978b917bb"}]
     * code : 0
     * msg : 成功
     * pageNum : 0
     */

    private int total;
    private String code;
    private String msg;
    private int pageNum;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * programName : 方案一
         * mutualSize : 2
         * effectiveTime : 2018-02-03
         * endTime : 2018-02-03
         * protectionDays : 0
         * state : 1
         * carNum : 川A1234565
         * billCode : cc1f825c79a94030aa00bf9978b917bb
         */

        private String programName;
        private int mutualSize;
        private String effectiveTime;
        private String endTime;
        private int protectionDays;
        private int state;
        private String carNum;
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

        public String getCarNum() {
            return carNum;
        }

        public void setCarNum(String carNum) {
            this.carNum = carNum;
        }

        public String getBillCode() {
            return billCode;
        }

        public void setBillCode(String billCode) {
            this.billCode = billCode;
        }
    }
}
