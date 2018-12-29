package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class GszBean {
    /**
     * total : 6
     * data : [{"id":6,"salvationCode":"a3d5d62dbfb1492694f50b569d294e57","carNum":"川A515223","phone":"15708132060","endOfferMoney":45.36,"userName":"张三","userUrl":"http://pic1.win4000.com/wallpaper/2018-01-11/5a56d7216b099.jpg"}]
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
         * id : 6
         * salvationCode : a3d5d62dbfb1492694f50b569d294e57
         * carNum : 川A515223
         * phone : 15708132060
         * endOfferMoney : 45.36
         * userName : 张三
         * userUrl : http://pic1.win4000.com/wallpaper/2018-01-11/5a56d7216b099.jpg
         */

        private int id;
        private String salvationCode;
        private String carNum;
        private String phone;
        private double endOfferMoney;
        private String userName;
        private String userUrl;

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

        public double getEndOfferMoney() {
            return endOfferMoney;
        }

        public void setEndOfferMoney(double endOfferMoney) {
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
    }
}
