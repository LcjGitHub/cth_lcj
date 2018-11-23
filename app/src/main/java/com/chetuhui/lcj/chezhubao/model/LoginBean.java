package com.chetuhui.lcj.chezhubao.model;

import java.util.List;

public class LoginBean {

    /**
     * total : 1
     * data : {"user":{"id":13,"createTime":"2018-11-12 15:28:30","createBy":null,"updateTime":null,"updateBy":null,"version":0,"userName":null,"phone":"13990044076","province":null,"city":null,"area":null,"address":null,"positionProvinceId":null,"positionCityId":null,"positionAreaId":null,"positionAddress":null,"sex":0,"state":0,"openId":null,"inviteId":null,"nickName":null,"password":"e10adc3949ba59abbe56e057f20f883e","headimgurl":null,"bankNo":null,"userCard":null,"userCode":"fa0dfcbcef3f424abf8ce5c58ef6d300","email":null,"cars":[],"groupId":1},"token":"4b252a5adf4d48a9a983ec7887d6aa3a"}
     * code : 0
     * msg : 成功
     */

    private int total;
    private DataBean data;
    private String code;
    private String msg;

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

    public static class DataBean {
        /**
         * user : {"id":13,"createTime":"2018-11-12 15:28:30","createBy":null,"updateTime":null,"updateBy":null,"version":0,"userName":null,"phone":"13990044076","province":null,"city":null,"area":null,"address":null,"positionProvinceId":null,"positionCityId":null,"positionAreaId":null,"positionAddress":null,"sex":0,"state":0,"openId":null,"inviteId":null,"nickName":null,"password":"e10adc3949ba59abbe56e057f20f883e","headimgurl":null,"bankNo":null,"userCard":null,"userCode":"fa0dfcbcef3f424abf8ce5c58ef6d300","email":null,"cars":[],"groupId":1}
         * token : 4b252a5adf4d48a9a983ec7887d6aa3a
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
             * id : 13
             * createTime : 2018-11-12 15:28:30
             * createBy : null
             * updateTime : null
             * updateBy : null
             * version : 0
             * userName : null
             * phone : 13990044076
             * province : null
             * city : null
             * area : null
             * address : null
             * positionProvinceId : null
             * positionCityId : null
             * positionAreaId : null
             * positionAddress : null
             * sex : 0
             * state : 0
             * openId : null
             * inviteId : null
             * nickName : null
             * password : e10adc3949ba59abbe56e057f20f883e
             * headimgurl : null
             * bankNo : null
             * userCard : null
             * userCode : fa0dfcbcef3f424abf8ce5c58ef6d300
             * email : null
             * cars : []
             * groupId : 1
             */

            private int id;
            private String createTime;
            private Object createBy;
            private Object updateTime;
            private Object updateBy;
            private int version;
            private Object userName;
            private String phone;
            private Object province;
            private Object city;
            private Object area;
            private Object address;
            private Object positionProvinceId;
            private Object positionCityId;
            private Object positionAreaId;
            private Object positionAddress;
            private int sex;
            private int state;
            private Object openId;
            private Object inviteId;
            private Object nickName;
            private String password;
            private Object headimgurl;
            private Object bankNo;
            private Object userCard;
            private String userCode;
            private Object email;
            private int groupId;
            private List<?> cars;

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

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(Object updateBy) {
                this.updateBy = updateBy;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public Object getUserName() {
                return userName;
            }

            public void setUserName(Object userName) {
                this.userName = userName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public Object getProvince() {
                return province;
            }

            public void setProvince(Object province) {
                this.province = province;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public Object getArea() {
                return area;
            }

            public void setArea(Object area) {
                this.area = area;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getPositionProvinceId() {
                return positionProvinceId;
            }

            public void setPositionProvinceId(Object positionProvinceId) {
                this.positionProvinceId = positionProvinceId;
            }

            public Object getPositionCityId() {
                return positionCityId;
            }

            public void setPositionCityId(Object positionCityId) {
                this.positionCityId = positionCityId;
            }

            public Object getPositionAreaId() {
                return positionAreaId;
            }

            public void setPositionAreaId(Object positionAreaId) {
                this.positionAreaId = positionAreaId;
            }

            public Object getPositionAddress() {
                return positionAddress;
            }

            public void setPositionAddress(Object positionAddress) {
                this.positionAddress = positionAddress;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public Object getOpenId() {
                return openId;
            }

            public void setOpenId(Object openId) {
                this.openId = openId;
            }

            public Object getInviteId() {
                return inviteId;
            }

            public void setInviteId(Object inviteId) {
                this.inviteId = inviteId;
            }

            public Object getNickName() {
                return nickName;
            }

            public void setNickName(Object nickName) {
                this.nickName = nickName;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public Object getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(Object headimgurl) {
                this.headimgurl = headimgurl;
            }

            public Object getBankNo() {
                return bankNo;
            }

            public void setBankNo(Object bankNo) {
                this.bankNo = bankNo;
            }

            public Object getUserCard() {
                return userCard;
            }

            public void setUserCard(Object userCard) {
                this.userCard = userCard;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public int getGroupId() {
                return groupId;
            }

            public void setGroupId(int groupId) {
                this.groupId = groupId;
            }

            public List<?> getCars() {
                return cars;
            }

            public void setCars(List<?> cars) {
                this.cars = cars;
            }
        }
    }
}
