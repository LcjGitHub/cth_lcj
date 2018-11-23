package com.chetuhui.lcj.chezhubao.utils;

public class NetData {
    public static final String N_HTTP ="http://192.168.0.222:8081/";
    //验证码
    public static final String N_SMSCODE =N_HTTP+"common/getSmsCode";
    //注册
    public static final String N_registered =N_HTTP+"user/registered";
    //登录
    public static final String N_login =N_HTTP+"user/login";
    //密码
    public static final String N_resetPasswordSecond =N_HTTP+"user/resetPasswordSecond";
    //广告页
    public static final String N_findAdvertisement =N_HTTP+"adver/findAdvertisement";
    //广告点击次数
    public static final String N_saveHitCount =N_HTTP+"adver/saveHitCount?id=";
    //定位
    public static final String N_findCitys =N_HTTP+"common/findCitys";
    //用户列表
    public static final String N_findUserAllByPage =N_HTTP+"user/findUserAllByPage";
    //系统消息
    public static final String N_findJiGuangMessage =N_HTTP+"common/findJiGuangMessage";
    //系统公告
    public static final String N_findAnnouncement =N_HTTP+"common/findAnnouncement";
    //修改手机号第一步
    public static final String N_updatePhoneFirst =N_HTTP+"user/updatePhoneFirst";
    //修改手机第二步
    public static final String N_updatePhoneSecond =N_HTTP+"user/updatePhoneSecond";
    //修改登录密码
    public static final String N_updatePassword =N_HTTP+"user/updatePassword";

    //解绑
    public static final String N_removeOpenid =N_HTTP+"user/removeOpenid";
    //微信登录
    public static final String N_loginByWx =N_HTTP+"user/loginByWx?code=";
    //微信绑定手机号
    public static final String N_updateOpenid =N_HTTP+"user/updateOpenid";
    //车辆列表
    public static final String N_findCarByCode =N_HTTP+"mutualprogram/findCarByCode";
    //添加车辆
    public static final String N_confirmInformation =N_HTTP+"mutualprogram/confirmInformation";
    //上传图片
    public static final String N_upload =N_HTTP+"common/upload";
    //获取服务站
    public static final String N_findBusinessByCity =N_HTTP+"mutualprogram/findBusinessByCity";

    //






}
