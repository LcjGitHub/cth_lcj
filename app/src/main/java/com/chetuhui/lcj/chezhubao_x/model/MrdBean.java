package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class MrdBean {
    /**
     * total : 0
     * data : [{"state":1,"helpState":2,"userName":"林成均","carNum":"川A123456","phone":"18228131219","carBrand":"接近","damageLocation":"车灯","readyFixTime":"2018-11-23T15:12:00.000+0000","readyFixTimeQuantum":"12：:00-45:00","detail":"sfddfdfdfdfdfdf","programName":"99元基础互助","billCode":"cc1f825c79a94030aa00bf9978b917bb","limitMoney":800.36,"mpMoney":99.23,"effectiveTime":"2018-02-03","endTime":"2018-05-06","programTime":1,"businessName":"环球修理厂","businessPhone":"15520449937","detailAd":"中国,四川省,成都市,武侯区环球中心","carHeadimg":"http://fdfdfdfdf","carEndimg":"http://fdfdfdfdf","carInjuredimg":"http://fdfdfdfdf","carDetailimg":"http://fdfdfdfdf","businessReason":"dfdfdfdfdfdfd"},{"state":1,"helpState":0,"userName":"林成均","carNum":"川A123456","phone":"18228131219","carBrand":"接近","damageLocation":null,"readyFixTime":"2018-11-26T15:25:19.000+0000","readyFixTimeQuantum":"8:00-18:00","detail":null,"programName":"99元基础互助","billCode":"cc1f825c79a94030aa00bf9978b917bb","limitMoney":800,"mpMoney":99,"effectiveTime":null,"endTime":null,"programTime":1,"businessName":"环球修理厂","businessPhone":"15520449937","detailAd":"中国,四川省,成都市,武侯区环球中心","carHeadimg":null,"carEndimg":null,"carInjuredimg":null,"carDetailimg":null,"businessReason":null}]
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
         * state : 1
         * helpState : 2
         * userName : 林成均
         * carNum : 川A123456
         * phone : 18228131219
         * carBrand : 接近
         * damageLocation : 车灯
         * readyFixTime : 2018-11-23T15:12:00.000+0000
         * readyFixTimeQuantum : 12：:00-45:00
         * detail : sfddfdfdfdfdfdf
         * programName : 99元基础互助
         * billCode : cc1f825c79a94030aa00bf9978b917bb
         * limitMoney : 800.36
         * mpMoney : 99.23
         * effectiveTime : 2018-02-03
         * endTime : 2018-05-06
         * programTime : 1
         * businessName : 环球修理厂
         * businessPhone : 15520449937
         * detailAd : 中国,四川省,成都市,武侯区环球中心
         * carHeadimg : http://fdfdfdfdf
         * carEndimg : http://fdfdfdfdf
         * carInjuredimg : http://fdfdfdfdf
         * carDetailimg : http://fdfdfdfdf
         * businessReason : dfdfdfdfdfdfd
         */

        private int state;
        private int helpState;
        private String userName;
        private String carNum;
        private String phone;
        private String carBrand;
        private String damageLocation;
        private String readyFixTime;
        private String readyFixTimeQuantum;
        private String detail;
        private String programName;
        private String billCode;
        private double limitMoney;
        private double mpMoney;
        private String effectiveTime;
        private String endTime;
        private int programTime;
        private String businessName;
        private String businessPhone;
        private String detailAd;
        private String carHeadimg;
        private String carEndimg;
        private String carInjuredimg;
        private String carDetailimg;
        private String businessReason;

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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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

        public String getCarBrand() {
            return carBrand;
        }

        public void setCarBrand(String carBrand) {
            this.carBrand = carBrand;
        }

        public String getDamageLocation() {
            return damageLocation;
        }

        public void setDamageLocation(String damageLocation) {
            this.damageLocation = damageLocation;
        }

        public String getReadyFixTime() {
            return readyFixTime;
        }

        public void setReadyFixTime(String readyFixTime) {
            this.readyFixTime = readyFixTime;
        }

        public String getReadyFixTimeQuantum() {
            return readyFixTimeQuantum;
        }

        public void setReadyFixTimeQuantum(String readyFixTimeQuantum) {
            this.readyFixTimeQuantum = readyFixTimeQuantum;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getProgramName() {
            return programName;
        }

        public void setProgramName(String programName) {
            this.programName = programName;
        }

        public String getBillCode() {
            return billCode;
        }

        public void setBillCode(String billCode) {
            this.billCode = billCode;
        }

        public double getLimitMoney() {
            return limitMoney;
        }

        public void setLimitMoney(double limitMoney) {
            this.limitMoney = limitMoney;
        }

        public double getMpMoney() {
            return mpMoney;
        }

        public void setMpMoney(double mpMoney) {
            this.mpMoney = mpMoney;
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

        public int getProgramTime() {
            return programTime;
        }

        public void setProgramTime(int programTime) {
            this.programTime = programTime;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getBusinessPhone() {
            return businessPhone;
        }

        public void setBusinessPhone(String businessPhone) {
            this.businessPhone = businessPhone;
        }

        public String getDetailAd() {
            return detailAd;
        }

        public void setDetailAd(String detailAd) {
            this.detailAd = detailAd;
        }

        public String getCarHeadimg() {
            return carHeadimg;
        }

        public void setCarHeadimg(String carHeadimg) {
            this.carHeadimg = carHeadimg;
        }

        public String getCarEndimg() {
            return carEndimg;
        }

        public void setCarEndimg(String carEndimg) {
            this.carEndimg = carEndimg;
        }

        public String getCarInjuredimg() {
            return carInjuredimg;
        }

        public void setCarInjuredimg(String carInjuredimg) {
            this.carInjuredimg = carInjuredimg;
        }

        public String getCarDetailimg() {
            return carDetailimg;
        }

        public void setCarDetailimg(String carDetailimg) {
            this.carDetailimg = carDetailimg;
        }

        public String getBusinessReason() {
            return businessReason;
        }

        public void setBusinessReason(String businessReason) {
            this.businessReason = businessReason;
        }
    }
}
