package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class CityBean {
    /**
     * total : 2
     * data : [{"id":510100,"name":"成都市","parentid":510000,"shortname":"成都","leveltype":2,"mergername":"中国,四川省,成都市","lng":"104.065735","lat":"30.659462","pinyin":"Chengdu","state":1},{"id":511100,"name":"乐山市","parentid":510000,"shortname":"乐山","leveltype":2,"mergername":"中国,四川省,乐山市","lng":"103.761263","lat":"29.582024","pinyin":"Leshan","state":1}]
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
         * id : 510100
         * name : 成都市
         * parentid : 510000
         * shortname : 成都
         * leveltype : 2
         * mergername : 中国,四川省,成都市
         * lng : 104.065735
         * lat : 30.659462
         * pinyin : Chengdu
         * state : 1
         */

        private int id;
        private String name;
        private int parentid;
        private String shortname;
        private int leveltype;
        private String mergername;
        private String lng;
        private String lat;
        private String pinyin;
        private int state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParentid() {
            return parentid;
        }

        public void setParentid(int parentid) {
            this.parentid = parentid;
        }

        public String getShortname() {
            return shortname;
        }

        public void setShortname(String shortname) {
            this.shortname = shortname;
        }

        public int getLeveltype() {
            return leveltype;
        }

        public void setLeveltype(int leveltype) {
            this.leveltype = leveltype;
        }

        public String getMergername() {
            return mergername;
        }

        public void setMergername(String mergername) {
            this.mergername = mergername;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getPinyin() {
            return pinyin;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
