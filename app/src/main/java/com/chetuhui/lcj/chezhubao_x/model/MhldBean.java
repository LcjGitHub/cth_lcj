package com.chetuhui.lcj.chezhubao_x.model;

public class MhldBean {
    /**
     * total : 0
     * data : {"programName":"99元方案","billCode":"1","billState":1,"mutualSize":2,"limitMoney":1000,"mpMoney":0,"buyTime":"2018-11-27","effectiveTime":"2018-05-06","endTime":"2018-12-27","programTime":0,"carNum":"川A51522"}
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
         * programName : 99元方案
         * billCode : 1
         * billState : 1
         * mutualSize : 2
         * limitMoney : 1000
         * mpMoney : 0
         * buyTime : 2018-11-27
         * effectiveTime : 2018-05-06
         * endTime : 2018-12-27
         * programTime : 0
         * carNum : 川A51522
         */

        private String programName;
        private String billCode;
        private int billState;
        private int mutualSize;
        private double limitMoney;
        private double mpMoney;
        private String buyTime;
        private String effectiveTime;
        private String endTime;
        private int programTime;
        private String carNum;

        public String getProgramName() {
            return programName;
        }

        public void setProgramName(String programName) {
            this.programName = programName;
        }

        public String getBillCode() {
            return billCode;
        }

        public void setBillCode(String billCode) {
            this.billCode = billCode;
        }

        public int getBillState() {
            return billState;
        }

        public void setBillState(int billState) {
            this.billState = billState;
        }

        public int getMutualSize() {
            return mutualSize;
        }

        public void setMutualSize(int mutualSize) {
            this.mutualSize = mutualSize;
        }

        public double getLimitMoney() {
            return limitMoney;
        }

        public void setLimitMoney(int limitMoney) {
            this.limitMoney = limitMoney;
        }

        public double getMpMoney() {
            return mpMoney;
        }

        public void setMpMoney(int mpMoney) {
            this.mpMoney = mpMoney;
        }

        public String getBuyTime() {
            return buyTime;
        }

        public void setBuyTime(String buyTime) {
            this.buyTime = buyTime;
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

        public int getProgramTime() {
            return programTime;
        }

        public void setProgramTime(int programTime) {
            this.programTime = programTime;
        }

        public String getCarNum() {
            return carNum;
        }

        public void setCarNum(String carNum) {
            this.carNum = carNum;
        }
    }
}
