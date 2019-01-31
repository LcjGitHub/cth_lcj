package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class AdBean {
    /**
     * total : 0
     * data : [{"month":"2019年1月","rechargeAmount":3.3,"helpSize":1,"amortizedAmountMonth":0.3,"mubillRecordVoList":[{"programeName":"9.9元互助体验","billHelpSize":1,"carNum":"川K90288","amortizedAmount":0.3,"type":1},{"programeName":"9.9元互助体验","billHelpSize":0,"carNum":"川K90288","amortizedAmount":3.3,"type":2}]}]
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
         * month : 2019年1月
         * rechargeAmount : 3.3
         * helpSize : 1
         * amortizedAmountMonth : 0.3
         * mubillRecordVoList : [{"programeName":"9.9元互助体验","billHelpSize":1,"carNum":"川K90288","amortizedAmount":0.3,"type":1},{"programeName":"9.9元互助体验","billHelpSize":0,"carNum":"川K90288","amortizedAmount":3.3,"type":2}]
         */

        private String month;
        private double rechargeAmount;
        private int helpSize;
        private double amortizedAmountMonth;
        private List<MubillRecordVoListBean> mubillRecordVoList;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public double getRechargeAmount() {
            return rechargeAmount;
        }

        public void setRechargeAmount(double rechargeAmount) {
            this.rechargeAmount = rechargeAmount;
        }

        public int getHelpSize() {
            return helpSize;
        }

        public void setHelpSize(int helpSize) {
            this.helpSize = helpSize;
        }

        public double getAmortizedAmountMonth() {
            return amortizedAmountMonth;
        }

        public void setAmortizedAmountMonth(double amortizedAmountMonth) {
            this.amortizedAmountMonth = amortizedAmountMonth;
        }

        public List<MubillRecordVoListBean> getMubillRecordVoList() {
            return mubillRecordVoList;
        }

        public void setMubillRecordVoList(List<MubillRecordVoListBean> mubillRecordVoList) {
            this.mubillRecordVoList = mubillRecordVoList;
        }

        public static class MubillRecordVoListBean {
            /**
             * programeName : 9.9元互助体验
             * billHelpSize : 1
             * carNum : 川K90288
             * amortizedAmount : 0.3
             * type : 1
             */

            private String programeName;
            private int billHelpSize;
            private String carNum;
            private double amortizedAmount;
            private int type;

            public String getProgrameName() {
                return programeName;
            }

            public void setProgrameName(String programeName) {
                this.programeName = programeName;
            }

            public int getBillHelpSize() {
                return billHelpSize;
            }

            public void setBillHelpSize(int billHelpSize) {
                this.billHelpSize = billHelpSize;
            }

            public String getCarNum() {
                return carNum;
            }

            public void setCarNum(String carNum) {
                this.carNum = carNum;
            }

            public double getAmortizedAmount() {
                return amortizedAmount;
            }

            public void setAmortizedAmount(double amortizedAmount) {
                this.amortizedAmount = amortizedAmount;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
