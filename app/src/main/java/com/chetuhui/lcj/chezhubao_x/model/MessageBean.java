package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class MessageBean {
    /**
     * total : 1
     * data : [{"id":1,"createTime":"2018-11-13 23:08:15","createBy":null,"updateTime":null,"updateBy":null,"version":1,"content":"第一条系统通知测试使用","target":"8888888","jumpType":null,"jumpParam":null}]
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
         * id : 1
         * createTime : 2018-11-13 23:08:15
         * createBy : null
         * updateTime : null
         * updateBy : null
         * version : 1
         * content : 第一条系统通知测试使用
         * target : 8888888
         * jumpType : null
         * jumpParam : null
         */

        private int id;
        private String createTime;
        private String createBy;
        private String updateTime;
        private String updateBy;
        private int version;
        private String content;
        private String target;
        private int jumpType;
        private String jumpParam;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public int  getJumpType() {
            return jumpType;
        }

        public void setJumpType(int jumpType) {
            this.jumpType = jumpType;
        }

        public String getJumpParam() {
            return jumpParam;
        }

        public void setJumpParam(String jumpParam) {
            this.jumpParam = jumpParam;
        }
    }
}
