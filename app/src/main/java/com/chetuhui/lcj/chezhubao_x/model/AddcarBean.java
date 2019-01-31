package com.chetuhui.lcj.chezhubao_x.model;

public class AddcarBean {
    /**
     * log_id : 6631461962791591787
     * direction : 0
     * words_result_num : 10
     * words_result : {"品牌型号":{"words":"海马HMC7161B"},"发证日期":{"words":"20061127"},"使用性质":{"words":"非营运"},"发动机号码":{"words":"TPBEL159600331"},"号牌号码":{"words":"川K90288"},"所有人":{"words":"袁小平"},"住址":{"words":"中国四川省内江市隆昌具石燕桥镇农乐村4036号"},"注册日期":{"words":"20061127"},"车辆识别代号":{"words":"LH17CKF76H018248"},"车辆类型":{"words":"小型轿车"}}
     */

    private long log_id;
    private int direction;
    private int words_result_num;
    private WordsResultBean words_result;

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(int words_result_num) {
        this.words_result_num = words_result_num;
    }

    public WordsResultBean getWords_result() {
        return words_result;
    }

    public void setWords_result(WordsResultBean words_result) {
        this.words_result = words_result;
    }

    public static class WordsResultBean {
        /**
         * 品牌型号 : {"words":"海马HMC7161B"}
         * 发证日期 : {"words":"20061127"}
         * 使用性质 : {"words":"非营运"}
         * 发动机号码 : {"words":"TPBEL159600331"}
         * 号牌号码 : {"words":"川K90288"}
         * 所有人 : {"words":"袁小平"}
         * 住址 : {"words":"中国四川省内江市隆昌具石燕桥镇农乐村4036号"}
         * 注册日期 : {"words":"20061127"}
         * 车辆识别代号 : {"words":"LH17CKF76H018248"}
         * 车辆类型 : {"words":"小型轿车"}
         */

        private 品牌型号Bean 品牌型号;
        private 发证日期Bean 发证日期;
        private 使用性质Bean 使用性质;
        private 发动机号码Bean 发动机号码;
        private 号牌号码Bean 号牌号码;
        private 所有人Bean 所有人;
        private 住址Bean 住址;
        private 注册日期Bean 注册日期;
        private 车辆识别代号Bean 车辆识别代号;
        private 车辆类型Bean 车辆类型;

        public 品牌型号Bean get品牌型号() {
            return 品牌型号;
        }

        public void set品牌型号(品牌型号Bean 品牌型号) {
            this.品牌型号 = 品牌型号;
        }

        public 发证日期Bean get发证日期() {
            return 发证日期;
        }

        public void set发证日期(发证日期Bean 发证日期) {
            this.发证日期 = 发证日期;
        }

        public 使用性质Bean get使用性质() {
            return 使用性质;
        }

        public void set使用性质(使用性质Bean 使用性质) {
            this.使用性质 = 使用性质;
        }

        public 发动机号码Bean get发动机号码() {
            return 发动机号码;
        }

        public void set发动机号码(发动机号码Bean 发动机号码) {
            this.发动机号码 = 发动机号码;
        }

        public 号牌号码Bean get号牌号码() {
            return 号牌号码;
        }

        public void set号牌号码(号牌号码Bean 号牌号码) {
            this.号牌号码 = 号牌号码;
        }

        public 所有人Bean get所有人() {
            return 所有人;
        }

        public void set所有人(所有人Bean 所有人) {
            this.所有人 = 所有人;
        }

        public 住址Bean get住址() {
            return 住址;
        }

        public void set住址(住址Bean 住址) {
            this.住址 = 住址;
        }

        public 注册日期Bean get注册日期() {
            return 注册日期;
        }

        public void set注册日期(注册日期Bean 注册日期) {
            this.注册日期 = 注册日期;
        }

        public 车辆识别代号Bean get车辆识别代号() {
            return 车辆识别代号;
        }

        public void set车辆识别代号(车辆识别代号Bean 车辆识别代号) {
            this.车辆识别代号 = 车辆识别代号;
        }

        public 车辆类型Bean get车辆类型() {
            return 车辆类型;
        }

        public void set车辆类型(车辆类型Bean 车辆类型) {
            this.车辆类型 = 车辆类型;
        }

        public static class 品牌型号Bean {
            /**
             * words : 海马HMC7161B
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 发证日期Bean {
            /**
             * words : 20061127
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 使用性质Bean {
            /**
             * words : 非营运
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 发动机号码Bean {
            /**
             * words : TPBEL159600331
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 号牌号码Bean {
            /**
             * words : 川K90288
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 所有人Bean {
            /**
             * words : 袁小平
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 住址Bean {
            /**
             * words : 中国四川省内江市隆昌具石燕桥镇农乐村4036号
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 注册日期Bean {
            /**
             * words : 20061127
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 车辆识别代号Bean {
            /**
             * words : LH17CKF76H018248
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }

        public static class 车辆类型Bean {
            /**
             * words : 小型轿车
             */

            private String words;

            public String getWords() {
                return words;
            }

            public void setWords(String words) {
                this.words = words;
            }
        }
    }
}
