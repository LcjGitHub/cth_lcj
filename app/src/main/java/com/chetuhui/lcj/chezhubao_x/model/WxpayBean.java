package com.chetuhui.lcj.chezhubao_x.model;

import com.google.gson.annotations.SerializedName;

public class WxpayBean {

    /**
     * total : 0
     * data : {"appid":"4545454","partnerid":"5656566","noncestr":"78784545","timestamp":"78154545","package":"Sign=WXPay","prepayid":"4545","sign":"4545"}
     * code : 0
     * msg : 成功
     * pageNum : 0
     */

    private int total;
    private DataBean data;
    private String code;
    private String msg;
    private int pageNum;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * appid : 4545454
         * partnerid : 5656566
         * noncestr : 78784545
         * timestamp : 78154545
         * package : Sign=WXPay
         * prepayid : 4545
         * sign : 4545
         */

        private String appid;
        private String partnerid;
        private String noncestr;
        private String timestamp;
        @SerializedName("package")
        private String packageX;
        private String prepayid;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
