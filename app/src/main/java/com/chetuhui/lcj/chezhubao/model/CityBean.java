package com.chetuhui.lcj.chezhubao.model;

import android.text.TextUtils;

import java.util.List;
import java.util.Locale;

public class CityBean {

    /**
     * total : 2
     * data : [{"id":1,"createTime":"2018-11-13 17:45:05","createBy":null,"updateTime":null,"updateBy":null,"version":1,"city":"成都","pid":0},{"id":2,"createTime":"2018-11-13 17:45:29","createBy":null,"updateTime":null,"updateBy":null,"version":1,"city":"乐山","pid":0}]
     * code : 0
     * msg : 成功
     */

    private int total;
    private String code;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * createTime : 2018-11-13 17:45:05
         * createBy : null
         * updateTime : null
         * updateBy : null
         * version : 1
         * city : 成都
         * pid : 0
         */

        private int id;
        private String createTime;
        private Object createBy;
        private Object updateTime;
        private Object updateBy;
        private int version;
        private String city;
        private int pid;

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

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }
    }
}
