package com.chetuhui.lcj.chezhubao_x.model;

public class ChangePhoneBean {

    /**
     * total : 0
     * data : {"phoneToken":"lg3KiIv2oCvrboCVNYlvK4Hpvs+/Ub8DpX+oMbaoIsM/W1FgD26GkAlQjxTcuuAd","userCode":"4975d3fb7c6943c184159ce786dd7c8a"}
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
         * phoneToken : lg3KiIv2oCvrboCVNYlvK4Hpvs+/Ub8DpX+oMbaoIsM/W1FgD26GkAlQjxTcuuAd
         * userCode : 4975d3fb7c6943c184159ce786dd7c8a
         */

        private String phoneToken;
        private String userCode;

        public String getPhoneToken() {
            return phoneToken;
        }

        public void setPhoneToken(String phoneToken) {
            this.phoneToken = phoneToken;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }
    }
}
