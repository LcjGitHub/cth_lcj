package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class ChooseBusinessBean {
    /**
     * total : 3
     * data : [{"businessCode":"1","businessName":"环球修理厂","phone":"15520449937","detailAd":"环球中心","cover":"1","peopleNum":0,"beginWorkTime":"6","endWorkTime":"8"}]
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
         * businessCode : 1
         * businessName : 环球修理厂
         * phone : 15520449937
         * detailAd : 环球中心
         * cover : 1
         * peopleNum : 0
         * beginWorkTime : 6
         * endWorkTime : 8
         */

        private String businessCode;
        private String businessName;
        private String phone;
        private String detailAd;
        private String cover;
        private int peopleNum;
        private String beginWorkTime;
        private String endWorkTime;

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDetailAd() {
            return detailAd;
        }

        public void setDetailAd(String detailAd) {
            this.detailAd = detailAd;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getPeopleNum() {
            return peopleNum;
        }

        public void setPeopleNum(int peopleNum) {
            this.peopleNum = peopleNum;
        }

        public String getBeginWorkTime() {
            return beginWorkTime;
        }

        public void setBeginWorkTime(String beginWorkTime) {
            this.beginWorkTime = beginWorkTime;
        }

        public String getEndWorkTime() {
            return endWorkTime;
        }

        public void setEndWorkTime(String endWorkTime) {
            this.endWorkTime = endWorkTime;
        }
    }
}
