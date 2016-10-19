package tck.cn.communication.model;

/**
 * Description :请求地址：http://v.juhe.cn/weather/index
 请求参数：cityname=%E6%AD%A6%E6%B1%89&dtype=&format=&key=9a05510809407a658a3cbf47f7ed458e
 请求方式：GET
 * <p>
 * Created by tck on 2016/10/19.
 */

public class CityWeather {


    /**
     * resultcode : 200
     * reason : successed!
     * result : {"sk":{"temp":"18","wind_direction":"北风","wind_strength":"1级","humidity":"92%","time":"08:34"},"today":{"temperature":"18℃~24℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"微风","week":"星期三","city":"武汉","date_y":"2016年10月19日","dressing_index":"舒适","dressing_advice":"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。","uv_index":"最弱","comfort_index":"","wash_index":"不宜","travel_index":"较不宜","exercise_index":"较不宜","drying_index":""},"future":{"day_20161019":{"temperature":"18℃~24℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"微风","week":"星期三","date":"20161019"},"day_20161020":{"temperature":"19℃~22℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"微风","week":"星期四","date":"20161020"},"day_20161021":{"temperature":"18℃~25℃","weather":"小雨转阴","weather_id":{"fa":"07","fb":"02"},"wind":"微风","week":"星期五","date":"20161021"},"day_20161022":{"temperature":"15℃~22℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"微风","week":"星期六","date":"20161022"},"day_20161023":{"temperature":"13℃~19℃","weather":"多云转阴","weather_id":{"fa":"01","fb":"02"},"wind":"微风","week":"星期日","date":"20161023"},"day_20161024":{"temperature":"18℃~25℃","weather":"小雨转阴","weather_id":{"fa":"07","fb":"02"},"wind":"微风","week":"星期一","date":"20161024"},"day_20161025":{"temperature":"15℃~22℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"微风","week":"星期二","date":"20161025"}}}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    /**
     * sk : {"temp":"18","wind_direction":"北风","wind_strength":"1级","humidity":"92%","time":"08:34"}
     * today : {"temperature":"18℃~24℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"微风","week":"星期三","city":"武汉","date_y":"2016年10月19日","dressing_index":"舒适","dressing_advice":"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。","uv_index":"最弱","comfort_index":"","wash_index":"不宜","travel_index":"较不宜","exercise_index":"较不宜","drying_index":""}
     * future : {"day_20161019":{"temperature":"18℃~24℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"微风","week":"星期三","date":"20161019"},"day_20161020":{"temperature":"19℃~22℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"微风","week":"星期四","date":"20161020"},"day_20161021":{"temperature":"18℃~25℃","weather":"小雨转阴","weather_id":{"fa":"07","fb":"02"},"wind":"微风","week":"星期五","date":"20161021"},"day_20161022":{"temperature":"15℃~22℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"微风","week":"星期六","date":"20161022"},"day_20161023":{"temperature":"13℃~19℃","weather":"多云转阴","weather_id":{"fa":"01","fb":"02"},"wind":"微风","week":"星期日","date":"20161023"},"day_20161024":{"temperature":"18℃~25℃","weather":"小雨转阴","weather_id":{"fa":"07","fb":"02"},"wind":"微风","week":"星期一","date":"20161024"},"day_20161025":{"temperature":"15℃~22℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"微风","week":"星期二","date":"20161025"}}
     */

    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * temp : 18
         * wind_direction : 北风
         * wind_strength : 1级
         * humidity : 92%
         * time : 08:34
         */

        private SkBean sk;
        /**
         * temperature : 18℃~24℃
         * weather : 小雨
         * weather_id : {"fa":"07","fb":"07"}
         * wind : 微风
         * week : 星期三
         * city : 武汉
         * date_y : 2016年10月19日
         * dressing_index : 舒适
         * dressing_advice : 建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。
         * uv_index : 最弱
         * comfort_index :
         * wash_index : 不宜
         * travel_index : 较不宜
         * exercise_index : 较不宜
         * drying_index :
         */

        private TodayBean today;
        /**
         * day_20161019 : {"temperature":"18℃~24℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"微风","week":"星期三","date":"20161019"}
         * day_20161020 : {"temperature":"19℃~22℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"微风","week":"星期四","date":"20161020"}
         * day_20161021 : {"temperature":"18℃~25℃","weather":"小雨转阴","weather_id":{"fa":"07","fb":"02"},"wind":"微风","week":"星期五","date":"20161021"}
         * day_20161022 : {"temperature":"15℃~22℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"微风","week":"星期六","date":"20161022"}
         * day_20161023 : {"temperature":"13℃~19℃","weather":"多云转阴","weather_id":{"fa":"01","fb":"02"},"wind":"微风","week":"星期日","date":"20161023"}
         * day_20161024 : {"temperature":"18℃~25℃","weather":"小雨转阴","weather_id":{"fa":"07","fb":"02"},"wind":"微风","week":"星期一","date":"20161024"}
         * day_20161025 : {"temperature":"15℃~22℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"微风","week":"星期二","date":"20161025"}
         */

        private FutureBean future;

        public SkBean getSk() {
            return sk;
        }

        public void setSk(SkBean sk) {
            this.sk = sk;
        }

        public TodayBean getToday() {
            return today;
        }

        public void setToday(TodayBean today) {
            this.today = today;
        }

        public FutureBean getFuture() {
            return future;
        }

        public void setFuture(FutureBean future) {
            this.future = future;
        }

        public static class SkBean {
            private String temp;
            private String wind_direction;
            private String wind_strength;
            private String humidity;
            private String time;

            public String getTemp() {
                return temp;
            }

            public void setTemp(String temp) {
                this.temp = temp;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public String getWind_strength() {
                return wind_strength;
            }

            public void setWind_strength(String wind_strength) {
                this.wind_strength = wind_strength;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }

        public static class TodayBean {
            private String temperature;
            private String weather;
            /**
             * fa : 07
             * fb : 07
             */

            private WeatherIdBean weather_id;
            private String wind;
            private String week;
            private String city;
            private String date_y;
            private String dressing_index;
            private String dressing_advice;
            private String uv_index;
            private String comfort_index;
            private String wash_index;
            private String travel_index;
            private String exercise_index;
            private String drying_index;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBean getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBean weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDate_y() {
                return date_y;
            }

            public void setDate_y(String date_y) {
                this.date_y = date_y;
            }

            public String getDressing_index() {
                return dressing_index;
            }

            public void setDressing_index(String dressing_index) {
                this.dressing_index = dressing_index;
            }

            public String getDressing_advice() {
                return dressing_advice;
            }

            public void setDressing_advice(String dressing_advice) {
                this.dressing_advice = dressing_advice;
            }

            public String getUv_index() {
                return uv_index;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
            }

            public String getComfort_index() {
                return comfort_index;
            }

            public void setComfort_index(String comfort_index) {
                this.comfort_index = comfort_index;
            }

            public String getWash_index() {
                return wash_index;
            }

            public void setWash_index(String wash_index) {
                this.wash_index = wash_index;
            }

            public String getTravel_index() {
                return travel_index;
            }

            public void setTravel_index(String travel_index) {
                this.travel_index = travel_index;
            }

            public String getExercise_index() {
                return exercise_index;
            }

            public void setExercise_index(String exercise_index) {
                this.exercise_index = exercise_index;
            }

            public String getDrying_index() {
                return drying_index;
            }

            public void setDrying_index(String drying_index) {
                this.drying_index = drying_index;
            }

            public static class WeatherIdBean {
                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }

        public static class FutureBean {
            /**
             * temperature : 18℃~24℃
             * weather : 小雨
             * weather_id : {"fa":"07","fb":"07"}
             * wind : 微风
             * week : 星期三
             * date : 20161019
             */

            private Day20161019Bean day_20161019;
            /**
             * temperature : 19℃~22℃
             * weather : 小雨
             * weather_id : {"fa":"07","fb":"07"}
             * wind : 微风
             * week : 星期四
             * date : 20161020
             */

            private Day20161020Bean day_20161020;
            /**
             * temperature : 18℃~25℃
             * weather : 小雨转阴
             * weather_id : {"fa":"07","fb":"02"}
             * wind : 微风
             * week : 星期五
             * date : 20161021
             */

            private Day20161021Bean day_20161021;
            /**
             * temperature : 15℃~22℃
             * weather : 小雨
             * weather_id : {"fa":"07","fb":"07"}
             * wind : 微风
             * week : 星期六
             * date : 20161022
             */

            private Day20161022Bean day_20161022;
            /**
             * temperature : 13℃~19℃
             * weather : 多云转阴
             * weather_id : {"fa":"01","fb":"02"}
             * wind : 微风
             * week : 星期日
             * date : 20161023
             */

            private Day20161023Bean day_20161023;
            /**
             * temperature : 18℃~25℃
             * weather : 小雨转阴
             * weather_id : {"fa":"07","fb":"02"}
             * wind : 微风
             * week : 星期一
             * date : 20161024
             */

            private Day20161024Bean day_20161024;
            /**
             * temperature : 15℃~22℃
             * weather : 小雨
             * weather_id : {"fa":"07","fb":"07"}
             * wind : 微风
             * week : 星期二
             * date : 20161025
             */

            private Day20161025Bean day_20161025;

            public Day20161019Bean getDay_20161019() {
                return day_20161019;
            }

            public void setDay_20161019(Day20161019Bean day_20161019) {
                this.day_20161019 = day_20161019;
            }

            public Day20161020Bean getDay_20161020() {
                return day_20161020;
            }

            public void setDay_20161020(Day20161020Bean day_20161020) {
                this.day_20161020 = day_20161020;
            }

            public Day20161021Bean getDay_20161021() {
                return day_20161021;
            }

            public void setDay_20161021(Day20161021Bean day_20161021) {
                this.day_20161021 = day_20161021;
            }

            public Day20161022Bean getDay_20161022() {
                return day_20161022;
            }

            public void setDay_20161022(Day20161022Bean day_20161022) {
                this.day_20161022 = day_20161022;
            }

            public Day20161023Bean getDay_20161023() {
                return day_20161023;
            }

            public void setDay_20161023(Day20161023Bean day_20161023) {
                this.day_20161023 = day_20161023;
            }

            public Day20161024Bean getDay_20161024() {
                return day_20161024;
            }

            public void setDay_20161024(Day20161024Bean day_20161024) {
                this.day_20161024 = day_20161024;
            }

            public Day20161025Bean getDay_20161025() {
                return day_20161025;
            }

            public void setDay_20161025(Day20161025Bean day_20161025) {
                this.day_20161025 = day_20161025;
            }

            public static class Day20161019Bean {
                private String temperature;
                private String weather;
                /**
                 * fa : 07
                 * fb : 07
                 */

                private WeatherIdBean weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBean getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBean weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBean {
                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20161020Bean {
                private String temperature;
                private String weather;
                /**
                 * fa : 07
                 * fb : 07
                 */

                private WeatherIdBean weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBean getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBean weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBean {
                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20161021Bean {
                private String temperature;
                private String weather;
                /**
                 * fa : 07
                 * fb : 02
                 */

                private WeatherIdBean weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBean getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBean weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBean {
                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20161022Bean {
                private String temperature;
                private String weather;
                /**
                 * fa : 07
                 * fb : 07
                 */

                private WeatherIdBean weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBean getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBean weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBean {
                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20161023Bean {
                private String temperature;
                private String weather;
                /**
                 * fa : 01
                 * fb : 02
                 */

                private WeatherIdBean weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBean getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBean weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBean {
                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20161024Bean {
                private String temperature;
                private String weather;
                /**
                 * fa : 07
                 * fb : 02
                 */

                private WeatherIdBean weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBean getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBean weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBean {
                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20161025Bean {
                private String temperature;
                private String weather;
                /**
                 * fa : 07
                 * fb : 07
                 */

                private WeatherIdBean weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBean getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBean weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBean {
                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }
        }
    }
}
