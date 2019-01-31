package com.chetuhui.lcj.chezhubao_x.utils;

public class NetData {
    //内网
    public static final String N_HTTP ="http://192.168.0.222:8081/";
    //外网  https://cartreasure.chetuhui.com.cn:8085/
//    public static final String N_HTTP ="http://39.104.150.214:8085/";
//    https://cartreasure.chetuhui.com.cn:8095/
//  public static final String N_HTTP ="https://cartreasure.chetuhui.com.cn:8085/";
//  public static final String N_HTTP ="https://cartreasure.chetuhui.com.cn:8095/";
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
    //热门问题
    public static final String N_findQuestions =N_HTTP+"common/findQuestions";
    //有用没用
    public static final String N_clickQuestions =N_HTTP+"common/clickQuestions";
    //公示中已互助
    public static final String N_mutualAidEvent =N_HTTP+"mutualprogram/mutualAidEvent";
    //公式互助数量
    public static final String N_mutualAidEventNum =N_HTTP+"mutualprogram/mutualAidEventNum";
    //互助详情
    public static final String N_eventDetails =N_HTTP+"mutualprogram/eventDetails";
    //账户公示
    public static final String N_acountDisclosure =N_HTTP+"common/acountDisclosure";
    //我的互助单
    public static final String N_findMyMutualbills =N_HTTP+"mutualbill/findMyMutualbills";
    //互助单详情
    public static final String N_findMutualBillDetail =N_HTTP+"mutualbill/findMutualBillDetail";
    //互助记录列表
    public static final String N_applicationRecord =N_HTTP+"mutualbill/applicationRecord";
    //互助记录详情
    public static final String N_findMutualRecord =N_HTTP+"mutualbill/salvageDetails";
    //全部车辆
    public static final String N_allMutualCars =N_HTTP+"mutualbill/allMutualCars";
    //我的车辆
    public static final String N_findMyCars =N_HTTP+"mutualbill/findMyCars";
    //查询具体车辆的互助单
    public static final String N_findMutualbillByCarNum =N_HTTP+"mutualbill/findMutualbillByCarNum";
    //(改)
    public static final String N_findMutualbillListByCarNum =N_HTTP+"mutualbill/findMutualbillListByCarNum";
    //我要救助
    public static final String N_findMutualCar =N_HTTP+"mutualbill/findMutualCar";
    //维修商家
    public static final String N_findRepairMerchants =N_HTTP+"mutualbill/findRepairMerchants";
    //申请救助
    public static final String N_applyHelp =N_HTTP+"mutualbill/applyHelp";
    //调用支付宝支付
    public static final String N_alipayOrders =N_HTTP+"common/alipayOrders";
    //互助方案列表
    public static final String N_findAll =N_HTTP+"mutualprogram/findAll";
    //确认互助方案
    public static final String N_confirmMutualList =N_HTTP+"mutualprogram/confirmMutualList";
    //区域查询
    public static final String N_queryNextlevel =N_HTTP+"common/queryNextlevel";
    //首页数据
    public static final String N_home =N_HTTP+"common/home";
    //意见反馈
    public static final String N_addOpinion =N_HTTP+"common/addOpinion";
    //协议政策
    public static final String N_findClause =N_HTTP+"common/findClause";
    //个人信息
    public static final String N_personalInformation =N_HTTP+"user/personalInformation";
    //修改昵称
    public static final String N_updatePersonalInformation =N_HTTP+"user/updatePersonalInformation";
    //损坏位置
    public static final String N_damageLocation =N_HTTP+"common/damageLocation";
    //选择救助互助单
    public static final String N_chooseBill =N_HTTP+"mutualbill/chooseBill";
    //更新app
    public static final String N_checkAppVersion =N_HTTP+"common/checkAppVersion";
    //微信支付
    public static final String N_weChatFirst =N_HTTP+"common/weChatFirst";
    //选择红包
    public static final String N_findTicket =N_HTTP+"mutualprogram/findTicket";
    //查询用户的所有红包
    public static final String N_findMyTickets =N_HTTP+"mutualprogram/findMyTickets";
    //邀请好友 邀请红包统一接口返回所有数据
    public static final String N_ticketReport =N_HTTP+"user/ticketReport";
    //互助方案选择确认接口--针对特价方案
    public static final String N_spikeMutualprogram =N_HTTP+"mutualprogram/spikeMutualprogram";
    //选择车辆确认接口
    public static final String N_sureCars =N_HTTP+"mutualprogram/sureCars";
    //修改头像
    public static final String N_updateHeardImage =N_HTTP+"user/updateHeardImage";
    //自动定位功能
    public static final String N_findCitysAutomatic =N_HTTP+"common/findCitysAutomatic";
    //上传维修确认
    public static final String N_confirmationCompleted =N_HTTP+"mutualbill/confirmationCompleted";
    //查询已经选了那些损坏位置
    public static final String N_damageLocationGet =N_HTTP+"common/damageLocationGet";
    //上传已经选了的位置
    public static final String N_damageLocationPut =N_HTTP+"common/damageLocationPut";
    //上传视频
    public static final String N_uploadVideo =N_HTTP+"common/uploadVideo";
    //自助审核信息上传接口
    public static final String N_saveSelfAudit =N_HTTP+"mutualprogram/saveSelfAudit";
    //互助账户列表
    public static final String N_mutualHelpAccountList =N_HTTP+"user/mutualHelpAccountList";
    //收银台确认支付接口
    public static final String N_confirmPayment =N_HTTP+"mutualbill/confirmPayment";
    //互助记录接口
    public static final String N_mutualAidRecord =N_HTTP+"mutualbill/mutualAidRecord";
    //验证码登录
    public static final String N_loginByPhoneAndCode =N_HTTP+"user/loginByPhoneAndCode";




    //


    //






}
