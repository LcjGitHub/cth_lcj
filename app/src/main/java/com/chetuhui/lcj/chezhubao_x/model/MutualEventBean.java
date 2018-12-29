package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class MutualEventBean {

    /**
     * total : 2
     * data : [{"id":1,"salvationCode":"566565656","createTime":"2018-05-06","createBy":"张三","updateTime":"2018-02-12","updateBy":"1515545","version":1,"businessCard":"1111","businessName":"万达修车中心","province":1245,"city":1246,"userName":"zhangasn","phone":"136254784512","carNum":"556566","rescueTime":"2018-11-20T00:00:00.000+0000","rescue_money":"454545","userCode":"121212","userUrl":"http://fdfdfdfdfdf"},{"id":2,"salvationCode":"898989","createTime":null,"createBy":null,"updateTime":null,"updateBy":null,"version":1,"businessCard":"2222","businessName":"环球修理中心","province":null,"city":null,"userName":null,"phone":null,"carNum":null,"rescueTime":"2018-09-01T19:47:10.000+0000","rescue_money":null,"userCode":null,"userUrl":null}]
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
         * salvationCode : 566565656
         * createTime : 2018-05-06
         * createBy : 张三
         * updateTime : 2018-02-12
         * updateBy : 1515545
         * version : 1
         * businessCard : 1111
         * businessName : 万达修车中心
         * province : 1245
         * city : 1246
         * userName : zhangasn
         * phone : 136254784512
         * carNum : 556566
         * rescueTime : 2018-11-20T00:00:00.000+0000
         * rescue_money : 454545
         * userCode : 121212
         * userUrl : http://fdfdfdfdfdf
         */

        private int id;
        private String salvationCode;
        private String createTime;
        private String createBy;
        private String updateTime;
        private String updateBy;
        private int version;
        private String businessCard;
        private String businessName;
        private int province;
        private int city;
        private String userName;
        private String phone;
        private String carNum;
        private String rescueTime;
        private String rescue_money;
        private String userCode;
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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getBusinessCard() {
            return businessCard;
        }

        public void setBusinessCard(String businessCard) {
            this.businessCard = businessCard;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCarNum() {
            return carNum;
        }

        public void setCarNum(String carNum) {
            this.carNum = carNum;
        }

        public String getRescueTime() {
            return rescueTime;
        }

        public void setRescueTime(String rescueTime) {
            this.rescueTime = rescueTime;
        }

        public String getRescue_money() {
            return rescue_money;
        }

        public void setRescue_money(String rescue_money) {
            this.rescue_money = rescue_money;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getUserUrl() {
            return userUrl;
        }

        public void setUserUrl(String userUrl) {
            this.userUrl = userUrl;
        }
    }
}
