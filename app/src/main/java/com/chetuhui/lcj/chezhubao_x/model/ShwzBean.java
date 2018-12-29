package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class ShwzBean {
    /**
     * total : 0
     * data : [{"id":1,"damageLocation":"左侧前门"},{"id":2,"damageLocation":"右侧前门"},{"id":3,"damageLocation":"左侧后门"},{"id":4,"damageLocation":"右侧后门"},{"id":5,"damageLocation":"左前翼子板"},{"id":6,"damageLocation":"左后翼子板"},{"id":7,"damageLocation":"右前翼子板"},{"id":8,"damageLocation":"右后翼子板"},{"id":9,"damageLocation":"前保险杠"},{"id":10,"damageLocation":"后保险杠"},{"id":11,"damageLocation":"发动机盖"},{"id":12,"damageLocation":"后备箱盖"},{"id":13,"damageLocation":"车顶"}]
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
         * id : 1
         * damageLocation : 左侧前门
         */

        private int id;
        private String damageLocation;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDamageLocation() {
            return damageLocation;
        }

        public void setDamageLocation(String damageLocation) {
            this.damageLocation = damageLocation;
        }
    }
}
