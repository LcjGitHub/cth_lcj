package com.chetuhui.lcj.chezhubao_x.model;

public class LoginBean {
    /**
     * total : 1
     * data : {"user":{"phone":"13990044076","userName":null,"nickName":null,"headimgurl":null,"userCode":"a9f46c27e239404ca9197bdfd92cf73f"},"token":"user751b810afbb1482bb3d60724c83e467c"}
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
         * user : {"phone":"13990044076","userName":null,"nickName":null,"headimgurl":null,"userCode":"a9f46c27e239404ca9197bdfd92cf73f"}
         * token : user751b810afbb1482bb3d60724c83e467c
         */

        private UserBean user;
        private String token;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class UserBean {
            /**
             * phone : 13990044076
             * userName : null
             * nickName : null
             * headimgurl : null
             * userCode : a9f46c27e239404ca9197bdfd92cf73f
             */

            private String phone;
            private String userName;
            private String nickName;
            private String headimgurl;
            private String userCode;

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
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
        }
    }
}
