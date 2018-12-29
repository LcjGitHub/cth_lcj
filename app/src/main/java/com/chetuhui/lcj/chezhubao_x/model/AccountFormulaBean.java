package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class AccountFormulaBean {

    /**
     * total : 0
     * data : {"expenditure":232323,"balance":5664646,"list":[{"id":2,"createTime":"2018-11-25","createBy":"张三","updateTime":"2018-02-03","updateBy":"张三","version":1,"bankNo":"565656","bankName":"建设银行","money":"7000","comparisonTime":"2018-11","messageState":1,"monthUrl":"http://fsfs.jpg","expenditure":36.36},{"id":1,"createTime":"2018-11-13","createBy":null,"updateTime":null,"updateBy":null,"version":1,"bankNo":"565656","bankName":"建设银行","money":"5000","comparisonTime":"2018-09","messageState":1,"monthUrl":"http://sfdfdfdf.jpg","expenditure":null}]}
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
         * expenditure : 232323
         * balance : 5664646
         * list : [{"id":2,"createTime":"2018-11-25","createBy":"张三","updateTime":"2018-02-03","updateBy":"张三","version":1,"bankNo":"565656","bankName":"建设银行","money":"7000","comparisonTime":"2018-11","messageState":1,"monthUrl":"http://fsfs.jpg","expenditure":36.36},{"id":1,"createTime":"2018-11-13","createBy":null,"updateTime":null,"updateBy":null,"version":1,"bankNo":"565656","bankName":"建设银行","money":"5000","comparisonTime":"2018-09","messageState":1,"monthUrl":"http://sfdfdfdf.jpg","expenditure":null}]
         */

        private double expenditure;
        private double balance;
        private List<ListBean> list;

        public double getExpenditure() {
            return expenditure;
        }

        public void setExpenditure(int expenditure) {
            this.expenditure = expenditure;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2
             * createTime : 2018-11-25
             * createBy : 张三
             * updateTime : 2018-02-03
             * updateBy : 张三
             * version : 1
             * bankNo : 565656
             * bankName : 建设银行
             * money : 7000
             * comparisonTime : 2018-11
             * messageState : 1
             * monthUrl : http://fsfs.jpg
             * expenditure : 36.36
             */

            private int id;
            private String createTime;
            private String createBy;
            private String updateTime;
            private String updateBy;
            private int version;
            private String bankNo;
            private String bankName;
            private String money;
            private String comparisonTime;
            private int messageState;
            private String monthUrl;
            private double expenditure;

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

            public String getBankNo() {
                return bankNo;
            }

            public void setBankNo(String bankNo) {
                this.bankNo = bankNo;
            }

            public String getBankName() {
                return bankName;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getComparisonTime() {
                return comparisonTime;
            }

            public void setComparisonTime(String comparisonTime) {
                this.comparisonTime = comparisonTime;
            }

            public int getMessageState() {
                return messageState;
            }

            public void setMessageState(int messageState) {
                this.messageState = messageState;
            }

            public String getMonthUrl() {
                return monthUrl;
            }

            public void setMonthUrl(String monthUrl) {
                this.monthUrl = monthUrl;
            }

            public double getExpenditure() {
                return expenditure;
            }

            public void setExpenditure(double expenditure) {
                this.expenditure = expenditure;
            }
        }
    }
}
