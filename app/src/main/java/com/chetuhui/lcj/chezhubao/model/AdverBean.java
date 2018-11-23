package com.chetuhui.lcj.chezhubao.model;

public class AdverBean {

    /**
     * total : 1
     * data : {"id":1,"createTime":"2018-11-12T10:36:19.000+0000","createBy":null,"updateTime":null,"updateBy":null,"version":1,"adName":"广告名字","beginTime":"2018-11-12T10:35:36.000+0000","endTime":"2018-11-30T10:35:33.000+0000","state":1,"imgUrl":"https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/06/04/ChMkJ1vbtvuIPRkeAAREcnnMdakAAs54gHN9NEABESK706.jpg","httpUrl":"https://www.baidu.com/?tn=90278658_hao_pg","hitNum":0}
     * code : 0
     * msg : 0
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
         * id : 1
         * createTime : 2018-11-12T10:36:19.000+0000
         * createBy : null
         * updateTime : null
         * updateBy : null
         * version : 1
         * adName : 广告名字
         * beginTime : 2018-11-12T10:35:36.000+0000
         * endTime : 2018-11-30T10:35:33.000+0000
         * state : 1
         * imgUrl : https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/06/04/ChMkJ1vbtvuIPRkeAAREcnnMdakAAs54gHN9NEABESK706.jpg
         * httpUrl : https://www.baidu.com/?tn=90278658_hao_pg
         * hitNum : 0
         */

        private int id;
        private String createTime;
        private Object createBy;
        private Object updateTime;
        private Object updateBy;
        private int version;
        private String adName;
        private String beginTime;
        private String endTime;
        private int state;
        private String imgUrl;
        private String httpUrl;
        private int hitNum;

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

        public String getAdName() {
            return adName;
        }

        public void setAdName(String adName) {
            this.adName = adName;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getHttpUrl() {
            return httpUrl;
        }

        public void setHttpUrl(String httpUrl) {
            this.httpUrl = httpUrl;
        }

        public int getHitNum() {
            return hitNum;
        }

        public void setHitNum(int hitNum) {
            this.hitNum = hitNum;
        }
    }
}
