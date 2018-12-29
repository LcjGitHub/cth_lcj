package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class QuyuBean {
    /**
     * total : 0
     * data : [{"id":510104,"name":"锦江区","parentid":510100,"shortname":"锦江","leveltype":3,"mergername":"中国,四川省,成都市,锦江区","lng":"104.08347","lat":"30.65614","pinyin":"Jinjiang","state":0},{"id":510105,"name":"青羊区","parentid":510100,"shortname":"青羊","leveltype":3,"mergername":"中国,四川省,成都市,青羊区","lng":"104.06151","lat":"30.67387","pinyin":"Qingyang","state":0},{"id":510106,"name":"金牛区","parentid":510100,"shortname":"金牛","leveltype":3,"mergername":"中国,四川省,成都市,金牛区","lng":"104.05114","lat":"30.69126","pinyin":"Jinniu","state":0}]
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
         * id : 510104
         * name : 锦江区
         * parentid : 510100
         * shortname : 锦江
         * leveltype : 3
         * mergername : 中国,四川省,成都市,锦江区
         * lng : 104.08347
         * lat : 30.65614
         * pinyin : Jinjiang
         * state : 0
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
