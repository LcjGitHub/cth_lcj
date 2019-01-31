package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class YhzBean {
    /**
     * total : 1
     * data : [{"id":10,"salvationCode":"12424550ae","endOfferMoney":444,"userName":"阿苦","userUrl":"http://app.chetuhui.com.cn/upload/522fa4625e3b3ca568eaae9caa6b39e.png","carNum":"川C51522","phone":"15708132060","publicStall":3.6}]
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
         * id : 10
         * salvationCode : 12424550ae
         * endOfferMoney : 444
         * userName : 阿苦
         * userUrl : http://app.chetuhui.com.cn/upload/522fa4625e3b3ca568eaae9caa6b39e.png
         * carNum : 川C51522
         * phone : 15708132060
         * publicStall : 3.6
         */

        private int id;
        private String salvationCode;
        private int endOfferMoney;
        private String userName;
        private String userUrl;
        private String carNum;
        private String phone;
        private double publicStall;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSalvationCode() {
            return salvationCode;
        }

        public void setSalvationCode(String salvationCode) {
            this.salvationCode = salvationCode;
        }

        public int getEndOfferMoney() {
            return endOfferMoney;
        }

        public void setEndOfferMoney(int endOfferMoney) {
            this.endOfferMoney = endOfferMoney;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserUrl() {
            return userUrl;
        }

        public void setUserUrl(String userUrl) {
            this.userUrl = userUrl;
        }

        public String getCarNum() {
            return carNum;
        }

        public void setCarNum(String carNum) {
            this.carNum = carNum;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public double getPublicStall() {
            return publicStall;
        }

        public void setPublicStall(double publicStall) {
            this.publicStall = publicStall;
        }
    }
}
