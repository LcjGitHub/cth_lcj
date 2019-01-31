package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class MhaBean {
    /**
     * total : 0
     * data : {"mutualAccountBalance":3.6,"apportion":5.4,"mubillVoList":[{"carNum":"川K90288","programeName":"9.9元互助体验","billCode":"085ba4a8fc1048bbb53a8ace74d0575d","carBrandImgurl":"http://app.chetuhui.com.cn/upload/ca97ec43c5794d13b7f8733baa112ce7.jpg","state":1,"balance":2.3,"limitMoney":9.9,"endTime":"2019年07月25日"}]}
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
         * mutualAccountBalance : 3.6
         * apportion : 5.4
         * mubillVoList : [{"carNum":"川K90288","programeName":"9.9元互助体验","billCode":"085ba4a8fc1048bbb53a8ace74d0575d","carBrandImgurl":"http://app.chetuhui.com.cn/upload/ca97ec43c5794d13b7f8733baa112ce7.jpg","state":1,"balance":2.3,"limitMoney":9.9,"endTime":"2019年07月25日"}]
         */

        private double mutualAccountBalance;
        private double apportion;
        private List<MubillVoListBean> mubillVoList;

        public double getMutualAccountBalance() {
            return mutualAccountBalance;
        }

        public void setMutualAccountBalance(double mutualAccountBalance) {
            this.mutualAccountBalance = mutualAccountBalance;
        }

        public double getApportion() {
            return apportion;
        }

        public void setApportion(double apportion) {
            this.apportion = apportion;
        }

        public List<MubillVoListBean> getMubillVoList() {
            return mubillVoList;
        }

        public void setMubillVoList(List<MubillVoListBean> mubillVoList) {
            this.mubillVoList = mubillVoList;
        }

        public static class MubillVoListBean {
            /**
             * carNum : 川K90288
             * programeName : 9.9元互助体验
             * billCode : 085ba4a8fc1048bbb53a8ace74d0575d
             * carBrandImgurl : http://app.chetuhui.com.cn/upload/ca97ec43c5794d13b7f8733baa112ce7.jpg
             * state : 1
             * balance : 2.3
             * limitMoney : 9.9
             * endTime : 2019年07月25日
             */

            private String carNum;
            private String programeName;
            private String billCode;
            private String carBrandImgurl;
            private int state;
            private double balance;
            private double limitMoney;
            private String endTime;

            public String getCarNum() {
                return carNum;
            }

            public void setCarNum(String carNum) {
                this.carNum = carNum;
            }

            public String getProgrameName() {
                return programeName;
            }

            public void setProgrameName(String programeName) {
                this.programeName = programeName;
            }

            public String getBillCode() {
                return billCode;
            }

            public void setBillCode(String billCode) {
                this.billCode = billCode;
            }

            public String getCarBrandImgurl() {
                return carBrandImgurl;
            }

            public void setCarBrandImgurl(String carBrandImgurl) {
                this.carBrandImgurl = carBrandImgurl;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public double getLimitMoney() {
                return limitMoney;
            }

            public void setLimitMoney(double limitMoney) {
                this.limitMoney = limitMoney;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }
        }
    }
}
