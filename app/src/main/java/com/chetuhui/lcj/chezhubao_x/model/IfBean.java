package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class IfBean {
    /**
     * total : 0
     * data : {"ticketMoney":"5.0","doorsill":"5","beInvitedMoney":"5.0","ruleOfActivity":"-活动规则1、每邀请5位好友成为车助宝新用户，并在7天内登录app，你可获取5元现金红包。具体奖励金额与你邀请的好友数量有关，最多可获得5次现金红包（共计20元）\n2、邀请好友的红包的活动从2019年1月1日起进行\n3、车助宝新用户需满足手机号及设备硬件从未在车助宝下单\n4、5元红包奖励放入【我的-红包卡券】中\n5、如发现任何违规套取奖励行为将视情节严重程度进行判罚：不予发放奖励、封停推荐有奖功能、冻结所有通过推荐有奖渠道获得的奖励、依法追究其法律责任\n6、邀请记录最多保留12个月\n7、如有其它疑问请咨询车助宝官方客服电话028-69186584","inviterNum":"3","inviterVoList":[{"friendPhone":"15708132060","getDate":"","isGetTicket":"1"},{"friendPhone":"18782246289","getDate":"2018-12-27","isGetTicket":"1"},{"friendPhone":"13990044076","getDate":"","isGetTicket":"1"}]}
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
         * ticketMoney : 5.0
         * doorsill : 5
         * beInvitedMoney : 5.0
         * ruleOfActivity : -活动规则1、每邀请5位好友成为车助宝新用户，并在7天内登录app，你可获取5元现金红包。具体奖励金额与你邀请的好友数量有关，最多可获得5次现金红包（共计20元）
         2、邀请好友的红包的活动从2019年1月1日起进行
         3、车助宝新用户需满足手机号及设备硬件从未在车助宝下单
         4、5元红包奖励放入【我的-红包卡券】中
         5、如发现任何违规套取奖励行为将视情节严重程度进行判罚：不予发放奖励、封停推荐有奖功能、冻结所有通过推荐有奖渠道获得的奖励、依法追究其法律责任
         6、邀请记录最多保留12个月
         7、如有其它疑问请咨询车助宝官方客服电话028-69186584
         * inviterNum : 3
         * inviterVoList : [{"friendPhone":"15708132060","getDate":"","isGetTicket":"1"},{"friendPhone":"18782246289","getDate":"2018-12-27","isGetTicket":"1"},{"friendPhone":"13990044076","getDate":"","isGetTicket":"1"}]
         */

        private String ticketMoney;
        private String doorsill;
        private String beInvitedMoney;
        private String ruleOfActivity;
        private String inviterNum;
        private List<InviterVoListBean> inviterVoList;

        public String getTicketMoney() {
            return ticketMoney;
        }

        public void setTicketMoney(String ticketMoney) {
            this.ticketMoney = ticketMoney;
        }

        public String getDoorsill() {
            return doorsill;
        }

        public void setDoorsill(String doorsill) {
            this.doorsill = doorsill;
        }

        public String getBeInvitedMoney() {
            return beInvitedMoney;
        }

        public void setBeInvitedMoney(String beInvitedMoney) {
            this.beInvitedMoney = beInvitedMoney;
        }

        public String getRuleOfActivity() {
            return ruleOfActivity;
        }

        public void setRuleOfActivity(String ruleOfActivity) {
            this.ruleOfActivity = ruleOfActivity;
        }

        public String getInviterNum() {
            return inviterNum;
        }

        public void setInviterNum(String inviterNum) {
            this.inviterNum = inviterNum;
        }

        public List<InviterVoListBean> getInviterVoList() {
            return inviterVoList;
        }

        public void setInviterVoList(List<InviterVoListBean> inviterVoList) {
            this.inviterVoList = inviterVoList;
        }

        public static class InviterVoListBean {
            /**
             * friendPhone : 15708132060
             * getDate :
             * isGetTicket : 1
             */

            private String friendPhone;
            private String getDate;
            private String isGetTicket;

            public String getFriendPhone() {
                return friendPhone;
            }

            public void setFriendPhone(String friendPhone) {
                this.friendPhone = friendPhone;
            }

            public String getGetDate() {
                return getDate;
            }

            public void setGetDate(String getDate) {
                this.getDate = getDate;
            }

            public String getIsGetTicket() {
                return isGetTicket;
            }

            public void setIsGetTicket(String isGetTicket) {
                this.isGetTicket = isGetTicket;
            }
        }
    }
}
