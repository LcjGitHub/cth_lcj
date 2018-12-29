package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class UserlistBean {
    /**
     * total : 3
     * data : [{"id":3,"userName":null,"phone":null,"nickName":"覃奋","headimgurl":"//img2.woyaogexing.com/2018/10/01/a8ecf04a1de04dafb0a850f064bf916e!400x400.jpeg","userCode":null,"createTime":"2018-11-21 19:51:44","carCode":"川A55897"},{"id":1,"userName":null,"phone":null,"nickName":"黄宏发","headimgurl":"http://img2.woyaogexing.com/2018/10/05/85c06c76cf5d45e6b40bbeef1874fc37!400x400.jpeg","userCode":null,"createTime":"2018-11-12 19:51:36","carCode":""}]
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
         * id : 3
         * userName : null
         * phone : null
         * nickName : 覃奋
         * headimgurl : //img2.woyaogexing.com/2018/10/01/a8ecf04a1de04dafb0a850f064bf916e!400x400.jpeg
         * userCode : null
         * createTime : 2018-11-21 19:51:44
         * carCode : 川A55897
         */

        private int id;
        private String userName;
        private String phone;
        private String nickName;
        private String headimgurl;
        private String userCode;
        private String createTime;
        private String carCode;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCarCode() {
            return carCode;
        }

        public void setCarCode(String carCode) {
            this.carCode = carCode;
        }
    }
}
