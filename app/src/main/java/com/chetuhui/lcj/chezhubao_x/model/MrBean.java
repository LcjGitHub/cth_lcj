package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class MrBean {
    /**
     * total : 0
     * data : [{"salvationCode":"1","carNum":"川A51522","state":0,"helpState":2,"programName":"100元互助俩次"}]
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
         * salvationCode : 1
         * carNum : 川A51522
         * state : 0
         * helpState : 2
         * programName : 100元互助俩次
         */

        private String salvationCode;
        private String carNum;
        private int state;
        private int helpState;
        private String programName;

        public String getSalvationCode() {
            return salvationCode;
        }

        public void setSalvationCode(String salvationCode) {
            this.salvationCode = salvationCode;
        }

        public String getCarNum() {
            return carNum;
        }

        public void setCarNum(String carNum) {
            this.carNum = carNum;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getHelpState() {
            return helpState;
        }

        public void setHelpState(int helpState) {
            this.helpState = helpState;
        }

        public String getProgramName() {
            return programName;
        }

        public void setProgramName(String programName) {
            this.programName = programName;
        }
    }
}
