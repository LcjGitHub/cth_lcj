package com.chetuhui.lcj.chezhubao_x.model;

import java.util.List;

public class WeizhiBean {
    /**
     * msg : 成功
     * code : 0
     * data : {"randomCode":"70e5c505be9547c1816cf6a6d8e12c7b","list":[{"damageLocation":"左侧前门","id":1,"state":1},{"damageLocation":"右侧前门","id":2,"state":1},{"damageLocation":"左侧后门","id":3,"state":0},{"damageLocation":"右侧后门","id":4,"state":0},{"damageLocation":"左前翼子板","id":5,"state":0},{"damageLocation":"左后翼子板","id":6,"state":0},{"damageLocation":"右前翼子板","id":7,"state":0},{"damageLocation":"右后翼子板","id":8,"state":0},{"damageLocation":"前保险杠","id":9,"state":0},{"damageLocation":"后保险杠","id":10,"state":0},{"damageLocation":"发动机盖","id":11,"state":0},{"damageLocation":"后备箱盖","id":12,"state":0},{"damageLocation":"车顶","id":13,"state":0}]}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * randomCode : 70e5c505be9547c1816cf6a6d8e12c7b
         * list : [{"damageLocation":"左侧前门","id":1,"state":1},{"damageLocation":"右侧前门","id":2,"state":1},{"damageLocation":"左侧后门","id":3,"state":0},{"damageLocation":"右侧后门","id":4,"state":0},{"damageLocation":"左前翼子板","id":5,"state":0},{"damageLocation":"左后翼子板","id":6,"state":0},{"damageLocation":"右前翼子板","id":7,"state":0},{"damageLocation":"右后翼子板","id":8,"state":0},{"damageLocation":"前保险杠","id":9,"state":0},{"damageLocation":"后保险杠","id":10,"state":0},{"damageLocation":"发动机盖","id":11,"state":0},{"damageLocation":"后备箱盖","id":12,"state":0},{"damageLocation":"车顶","id":13,"state":0}]
         */

        private String randomCode;
        private List<ListBean> list;

        public String getRandomCode() {
            return randomCode;
        }

        public void setRandomCode(String randomCode) {
            this.randomCode = randomCode;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * damageLocation : 左侧前门
             * id : 1
             * state : 1
             */

            private String damageLocation;
            private int id;
            private int state;

            public String getDamageLocation() {
                return damageLocation;
            }

            public void setDamageLocation(String damageLocation) {
                this.damageLocation = damageLocation;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }
        }
    }
}
