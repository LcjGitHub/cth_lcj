package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class SelectAidBean {
    /**
     * total : 0
     * data : [{"programName":"测试数据","mutualSize":2,"effectiveTime":"2018-12-10","endTime":"2019-12-11","billCode":"a85706c4a37442a991b20bacb80e0df6"}]
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
         * programName : 测试数据
         * mutualSize : 2
         * effectiveTime : 2018-12-10
         * endTime : 2019-12-11
         * billCode : a85706c4a37442a991b20bacb80e0df6
         */

        private String programName;
        private int mutualSize;
        private String effectiveTime;
        private String endTime;
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

        public String getBillCode() {
            return billCode;
        }

        public void setBillCode(String billCode) {
            this.billCode = billCode;
        }
    }
}
