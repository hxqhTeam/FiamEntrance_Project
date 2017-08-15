package com.hqxh.fiamproperty.constant;

/**
 * 全局配置
 *
 */

public final class GlobalConfig {

    /**服务器地址**/
    public final static String HTTP_URL="http://10.60.12.98:9080";
//    public final static String HTTP_URL="http://qmportal1.qm.cn:8090";

    /**服务器登录接口地址**/

    public final static String HTTP_URL_LOGIN=HTTP_URL+"/maximo/mobile/system/portallogin";

    /**服务器通用查询接口地址**/

    public final static String HTTP_URL_SEARCH=HTTP_URL+"/maximo/mobile/common/api";



    ///////////////////////////
    /**项目类型**/
    public final static String TRAVEL="TRAVEL"; //国内出差/出差申请
    public final static String GRWZ="GRWZ"; //物资出门/出门管理
    public final static String SZPR="SZPR"; //试制采购申请/采购
    public final static String TOQT="TOQT"; //其它任务单/任务单










    /**定义appid,objectname**/
    //待办appid
    public static final String WFADMIN_APPID = "WFADMIN" ;

    //待办的表名
    public static final String WFASSIGNMENT_NAME = "WFASSIGNMENT";

    //国内出差appid
    public static final String TRAVEL_APPID = "TRAVEL" ;
    //出差表名/任务单
    public static final String WORKORDER_NAME = "WORKORDER" ;
    //出差人
    public static final String PERSONRELATION_NAME = "PERSONRELATION" ;
    //国外立项appid
    public static final String TRAVELS_APPID = "TRAVELS" ;

    //出门管理appid
    public static final String GRWZ_APPID = "GRWZ" ;
    //出门管理表名
    public static final String GR_APPID = "GR" ;
    //物资明细
    public static final String GRLINE_NAME = "GRLINE" ;

    //技术采购申请appid
    public static final String JSPR_APPID = "JSPR" ;
    //试制采购申请appid
    public static final String SZPR_APPID = "SZPR" ;
    //物资采购申请appid
    public static final String PR_APPID = "PR" ;
    //服务采购申请appid
    public static final String FWPR_APPID = "FWPR" ;
    //外培采购申请appid
    public static final String WPPR_APPID = "WPPR" ;
    //采购申请表名
    public static final String PR_NAME = "PR" ;

    /**任务单**/
    //试验任务单
    public static final String TOSY_APPID = "TOSY" ;
    //试制任务单
    public static final String TOSZ_APPID = "TOSZ" ;
    //物资领料单
    public static final String TOLL_APPID = "TOLL" ;

    //调件任务单
    public static final String TODJ_APPID = "TODJ" ;
    //燃油申请单
    public static final String TOOIL_APPID = "TOOIL" ;
    //其它任务单
    public static final String TOQT_APPID = "TOQT" ;

    /**任务单结束**/

    //合同的AppID
    public static final String CONTPURCH_APPID = "CONTPURCH" ;
    //合同的表名
    public static final String PURCHVIEW_NAME = "PURCHVIEW" ;
    //付款验收Appid
    public static final String PAYCHECK_APPID = "PAYCHECK" ;
    //付款验收表名
    public static final String PAYCHECK_NAME = "PAYCHECK" ;

    //需款计划Appid
    public static final String PP_APPID = "PP" ;
    //需款计划表名
    public static final String PAYPLAN_NAME = "PAYPLAN" ;

    /**报销**/
    //差旅报销单Appid
    public static final String EXPENSES_APPID = "EXPENSES" ;
    //差旅报销单表名／备用金报销
    public static final String EXPENSE_NAME = "EXPENSE" ;

    //备用金报销Appid
    public static final String EXPENSE_APPID = "EXPENSE" ;

    //借款单Appid
    public static final String BO_APPID = "BO" ;
    //借款单表名
    public static final String BO_NAME = "BO" ;
    /**
     * 用户登录表识--开始*
     */
    public static final String LOGINSUCCESS = "USER-S-101"; //登录成功

    public static final String CHANGEIMEI = "USER-S-104"; //登录成功,检测到用户更换手机登录

    public static final String USERNAMEERROR = "USER-E-100";//用户名密码错误

    public static final String GETDATASUCCESS = "GLOBAL-S-0";//获取数据成功



}
