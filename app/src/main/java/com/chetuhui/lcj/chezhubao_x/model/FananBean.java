package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class FananBean {
    /**
     * total : 0
     * data : [{"programCode":"11111111","programId":1,"programName":"99元互助方案","content":"保护车辆头部尾部","description":"99元互助方案","mpMoney":0.2,"imgUrl1":"","imgUrl2":"","imgUrl3":"","imgUrl4":"","imgUrl5":"","imgUrl6":"","programTime":12,"helpNum":0,"limitMoney":500}]
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
         * programCode : 11111111
         * programId : 1
         * programName : 99元互助方案
         * content : 保护车辆头部尾部
         * description : 99元互助方案
         * mpMoney : 0.2
         * imgUrl1 :
         * imgUrl2 :
         * imgUrl3 :
         * imgUrl4 :
         * imgUrl5 :
         * imgUrl6 :
         * programTime : 12
         * helpNum : 0
         * limitMoney : 500
         */

        private String programCode;
        private int programId;
        private String programName;
        private String content;
        private String description;
        private String mpMoney;
        private String imgUrl1;
        private String imgUrl2;
        private String imgUrl3;
        private String imgUrl4;
        private String imgUrl5;
        private String imgUrl6;
        private int programTime;
        private int helpNum;
        private int limitMoney;

        public String getProgramCode() {
            return programCode;
        }

        public void setProgramCode(String programCode) {
            this.programCode = programCode;
        }

        public int getProgramId() {
            return programId;
        }

        public void setProgramId(int programId) {
            this.programId = programId;
        }

        public String getProgramName() {
            return programName;
        }

        public void setProgramName(String programName) {
            this.programName = programName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMpMoney() {
            return mpMoney;
        }

        public void setMpMoney(String mpMoney) {
            this.mpMoney = mpMoney;
        }

        public String getImgUrl1() {
            return imgUrl1;
        }

        public void setImgUrl1(String imgUrl1) {
            this.imgUrl1 = imgUrl1;
        }

        public String getImgUrl2() {
            return imgUrl2;
        }

        public void setImgUrl2(String imgUrl2) {
            this.imgUrl2 = imgUrl2;
        }

        public String getImgUrl3() {
            return imgUrl3;
        }

        public void setImgUrl3(String imgUrl3) {
            this.imgUrl3 = imgUrl3;
        }

        public String getImgUrl4() {
            return imgUrl4;
        }

        public void setImgUrl4(String imgUrl4) {
            this.imgUrl4 = imgUrl4;
        }

        public String getImgUrl5() {
            return imgUrl5;
        }

        public void setImgUrl5(String imgUrl5) {
            this.imgUrl5 = imgUrl5;
        }

        public String getImgUrl6() {
            return imgUrl6;
        }

        public void setImgUrl6(String imgUrl6) {
            this.imgUrl6 = imgUrl6;
        }

        public int getProgramTime() {
            return programTime;
        }

        public void setProgramTime(int programTime) {
            this.programTime = programTime;
        }

        public int getHelpNum() {
            return helpNum;
        }

        public void setHelpNum(int helpNum) {
            this.helpNum = helpNum;
        }

        public int getLimitMoney() {
            return limitMoney;
        }

        public void setLimitMoney(int limitMoney) {
            this.limitMoney = limitMoney;
        }
    }
}
