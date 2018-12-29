package com.chetuhui.lcj.chezhubao_x.model;

import java.io.Serializable;
import java.util.List;

public class AmcBean  implements Serializable {
    /**
     * total : 0
     * data : [{"carBrand":"奥迪","licensePicture":"http://sfdfd","carNum":"川A12345","engineNum":"45454545","owner":"王老如","carCode":"7878"}]
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

    public static class DataBean implements Serializable{
        /**
         * carBrand : 奥迪
         * licensePicture : http://sfdfd
         * carNum : 川A12345
         * engineNum : 45454545
         * owner : 王老如
         * carCode : 7878
         */

        private String carBrand;
        private String licensePicture;
        private String carNum;
        private String engineNum;
        private String owner;
        private String carCode;

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

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getCarCode() {
            return carCode;
        }

        public void setCarCode(String carCode) {
            this.carCode = carCode;
        }
    }
}
