package com.chetuhui.lcj.chezhubao_x.model;

public class APPversionBean {
    /**
     * total : 0
     * data : {"id":2,"createTime":"2018-11-22 00:47:22","createBy":"","updateTime":"","updateBy":"","version":1,"versionNum":"1.14","url":"https://soft.anruan.com/52288/","client":"app","state":1,"isMust":0,"content":"修改第二个bug","type":0}
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
         * id : 2
         * createTime : 2018-11-22 00:47:22
         * createBy :
         * updateTime :
         * updateBy :
         * version : 1
         * versionNum : 1.14
         * url : https://soft.anruan.com/52288/
         * client : app
         * state : 1
         * isMust : 0
         * content : 修改第二个bug
         * type : 0
         */

        private int id;
        private String createTime;
        private String createBy;
        private String updateTime;
        private String updateBy;
        private int version;
        private String versionNum;
        private String url;
        private String client;
        private int state;
        private int isMust;
        private String content;
        private int type;

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

        public String getVersionNum() {
            return versionNum;
        }

        public void setVersionNum(String versionNum) {
            this.versionNum = versionNum;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getIsMust() {
            return isMust;
        }

        public void setIsMust(int isMust) {
            this.isMust = isMust;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
