package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class IhelpBean {
    /**
     * total : 0
     * data : [{"mutualSize":2,"carNum":"川A444"}]
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
         * mutualSize : 2
         * carNum : 川A444
         */

        private int mutualSize;
        private String carNum;

        public int getMutualSize() {
            return mutualSize;
        }

        public void setMutualSize(int mutualSize) {
            this.mutualSize = mutualSize;
        }

        public String getCarNum() {
            return carNum;
        }

        public void setCarNum(String carNum) {
            this.carNum = carNum;
        }
    }
}
