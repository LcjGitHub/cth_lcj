package com.chetuhui.lcj.chezhubao.model;

import java.util.List;

public class AuditStationBean {

    /**
     * total : 1
     * data : [{"id":1,"createTime":"2018-11-19T16:18:45.000+0000","createBy":"llm","updateTime":"2018-11-19T16:18:52.000+0000","updateBy":"llm","version":1,"businessCode":"123456","businessName":"环球修理厂","rpName":"罗连明","phone":"15520449937","password":"123456","province":"510000","city":"510100","area":null,"detailAd":"环球中心","cover":null,"bankNo":"中国建设银行","bankName":null,"openBankName":"环球中国建设银行","branchBankName":"华阳支行","state":0,"countFix":100,"salvationCode":990,"auditorId":1,"auditorName":null,"auditorPhone":null}]
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
         * createTime : 2018-11-19T16:18:45.000+0000
         * createBy : llm
         * updateTime : 2018-11-19T16:18:52.000+0000
         * updateBy : llm
         * version : 1
         * businessCode : 123456
         * businessName : 环球修理厂
         * rpName : 罗连明
         * phone : 15520449937
         * password : 123456
         * province : 510000
         * city : 510100
         * area : null
         * detailAd : 环球中心
         * cover : null
         * bankNo : 中国建设银行
         * bankName : null
         * openBankName : 环球中国建设银行
         * branchBankName : 华阳支行
         * state : 0
         * countFix : 100
         * salvationCode : 990
         * auditorId : 1
         * auditorName : null
         * auditorPhone : null
         */

        private int id;
        private String createTime;
        private String createBy;
        private String updateTime;
        private String updateBy;
        private int version;
        private String businessCode;
        private String businessName;
        private String rpName;
        private String phone;
        private String password;
        private String province;
        private String city;
        private Object area;
        private String detailAd;
        private Object cover;
        private String bankNo;
        private Object bankName;
        private String openBankName;
        private String branchBankName;
        private int state;
        private int countFix;
        private int salvationCode;
        private int auditorId;
        private Object auditorName;
        private Object auditorPhone;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getBusinessCode() {
            return businessCode;
        }

        public void setBusinessCode(String businessCode) {
            this.businessCode = businessCode;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getRpName() {
            return rpName;
        }

        public void setRpName(String rpName) {
            this.rpName = rpName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Object getArea() {
            return area;
        }

        public void setArea(Object area) {
            this.area = area;
        }

        public String getDetailAd() {
            return detailAd;
        }

        public void setDetailAd(String detailAd) {
            this.detailAd = detailAd;
        }

        public Object getCover() {
            return cover;
        }

        public void setCover(Object cover) {
            this.cover = cover;
        }

        public String getBankNo() {
            return bankNo;
        }

        public void setBankNo(String bankNo) {
            this.bankNo = bankNo;
        }

        public Object getBankName() {
            return bankName;
        }

        public void setBankName(Object bankName) {
            this.bankName = bankName;
        }

        public String getOpenBankName() {
            return openBankName;
        }

        public void setOpenBankName(String openBankName) {
            this.openBankName = openBankName;
        }

        public String getBranchBankName() {
            return branchBankName;
        }

        public void setBranchBankName(String branchBankName) {
            this.branchBankName = branchBankName;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getCountFix() {
            return countFix;
        }

        public void setCountFix(int countFix) {
            this.countFix = countFix;
        }

        public int getSalvationCode() {
            return salvationCode;
        }

        public void setSalvationCode(int salvationCode) {
            this.salvationCode = salvationCode;
        }

        public int getAuditorId() {
            return auditorId;
        }

        public void setAuditorId(int auditorId) {
            this.auditorId = auditorId;
        }

        public Object getAuditorName() {
            return auditorName;
        }

        public void setAuditorName(Object auditorName) {
            this.auditorName = auditorName;
        }

        public Object getAuditorPhone() {
            return auditorPhone;
        }

        public void setAuditorPhone(Object auditorPhone) {
            this.auditorPhone = auditorPhone;
        }
    }
}
