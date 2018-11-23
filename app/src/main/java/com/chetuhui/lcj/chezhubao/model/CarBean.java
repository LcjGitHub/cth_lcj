package com.chetuhui.lcj.chezhubao.model;

import java.util.List;

public class CarBean {
    /**
     * total : 0
     * data : [{"carBrand":"奔驰","licensePicture":"http://img2.woyaogexing.com/2018/10/01/a8ecf04a1de04dafb0a850f064bf916e!400x400.jpeg","carNum":"川A558976","engineNum":"787878","state":1}]
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
         * carBrand : 奔驰
         * licensePicture : http://img2.woyaogexing.com/2018/10/01/a8ecf04a1de04dafb0a850f064bf916e!400x400.jpeg
         * carNum : 川A558976
         * engineNum : 787878
         * state : 1
         */

        private String carBrand;
        private String licensePicture;
        private String carNum;
        private String engineNum;
        private int state;

        public String getCarBrand() {
            return carBrand;
        }

        public void setCarBrand(String carBrand) {
            this.carBrand = carBrand;
        }

        public String getLicensePicture() {
            return licensePicture;
        }

        public void setLicensePicture(String licensePicture) {
            this.licensePicture = licensePicture;
        }

        public String getCarNum() {
            return carNum;
        }

        public void setCarNum(String carNum) {
            this.carNum = carNum;
        }

        public String getEngineNum() {
            return engineNum;
        }

        public void setEngineNum(String engineNum) {
            this.engineNum = engineNum;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
