package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class RedBean {
    /**
     * total : 0
     * data : [{"ticketMoney":"5.0","ticketName":"互助单购买抵扣红包","remark":"购买互助单时自动抵扣","beginTime":"2018-11-10","endTime":"2018-12-26"}]
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
         * ticketMoney : 5.0
         * ticketName : 互助单购买抵扣红包
         * remark : 购买互助单时自动抵扣
         * beginTime : 2018-11-10
         * endTime : 2018-12-26
         */

        private String ticketMoney;
        private String ticketName;
        private String remark;
        private String beginTime;
        private String endTime;

        public String getTicketMoney() {
            return ticketMoney;
        }

        public void setTicketMoney(String ticketMoney) {
            this.ticketMoney = ticketMoney;
        }

        public String getTicketName() {
            return ticketName;
        }

        public void setTicketName(String ticketName) {
            this.ticketName = ticketName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }
}
