package com.hqxh.fiamproperty.api;

import com.hqxh.fiamproperty.constant.GlobalConfig;

/**
 * Created by apple on 17/7/27.
 * 查询条件
 */

public class HttpManager {
    /**
     * 待办任务*
     */
    public static String getWFASSIGNMENTUrl(String search, String username, String assignstatus, int curpage, int showcount) {
        if (null == search || search.equals("")) {
            return "{'appid':'" + GlobalConfig.WFADMIN_APPID + "','objectname':'" + GlobalConfig.WFASSIGNMENT_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ASSIGNSTATUS':'=" + assignstatus + "','ASSIGNCODE':'=" + username + "'}}";

        } else {
            return "{'appid':'" + GlobalConfig.WFADMIN_APPID + "','objectname':'" + GlobalConfig.WFASSIGNMENT_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ASSIGNSTATUS':'=" + assignstatus + "','ASSIGNCODE':'=" + username + "'},'reporsearch':{'UDAPPNAME':'" + search + "','DESCRIPTION':'" + search + "'}}";

        }

    }

    /**
     * 国内出差*
     */
    public static String getWORKORDERUrl(String username, int curpage, int showcount) {
        return "{'appid':'" + GlobalConfig.TRAVEL_APPID + "','objectname':'" + GlobalConfig.WORKORDER_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }

    /**
     * 出差人*
     */
    public static String getR_PERSONRELATIONUrl(String appid, String username, String wonum, int curpage, int showcount) {
        if (appid.equals(GlobalConfig.TRAVEL_APPID)) {
            return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PERSONRELATION_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WONUM':'=" + wonum + "'}}";

        } else if (appid.equals(GlobalConfig.EXPENSES_APPID)) {
            return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PERSONRELATION_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'EXPENSENUM':'=" + wonum + "'}}";

        }
        return null;

    }

    /*
    物资明细
    */
    public static String getGRLINEUrl(String username, String grnum, int curpage, int showcount) {
        return "{'appid':'" + GlobalConfig.GRWZ_APPID + "','objectname':'" + GlobalConfig.GRLINE_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'GRNUM':'=" + grnum + "'}}";

    }
/*
    整车明细
*/

    public static String getZCMXUrl(String username, String grnum, int curpage, int showcount) {
//        return "{'appid':'" + GlobalConfig.GRZC_APPID + "','objectname':'" + GlobalConfig.GRLINE_NAME + "','username':'"+username+"','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'GRNUM':'="+grnum+"'}}";
        return "{'appid':'" + GlobalConfig.GRZC_APPID + "','objectname':'" + GlobalConfig.GRLINE_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }


    /**
     * 出国立项*
     */
    public static String getGWWORKORDERUrl(String username, int curpage, int showcount) {
        return "{'appid':'" + GlobalConfig.TRAVELS_APPID + "','objectname':'" + GlobalConfig.WORKORDER_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }

    /**
     * 出门管理*
     */
    public static String getGRUrl(String username, int curpage, int showcount) {
        return "{'appid':'" + GlobalConfig.GRWZ_APPID + "','objectname':'" + GlobalConfig.GR_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }

    /**
     * 采购申请*
     */
    public static String getPRUrl(String appid, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PR_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }

    /**
     * 任务单*
     */
    public static String getRWDUrl(String appid, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.WORKORDER_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }


    /**
     * 明细信息*
     */
    public static String getWPITEMUrl(String appid, String wonum, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.WPITEM_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WONUM':'=" + wonum + "'}}";

    }

    /**
     * 合同*
     */
    public static String getPURCHVIEWUrl(String username, int curpage, int showcount) {
        return "{'appid':'" + GlobalConfig.CONTPURCH_APPID + "','objectname':'" + GlobalConfig.PURCHVIEW_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }

    /**
     * 付款计划*
     */
    public static String getCONTRACTPAYURL(String username, String contractnum, String contractrev, int curpage, int showcount) {
        return "{'appid':'" + GlobalConfig.CONTPURCH_APPID + "','objectname':'" + GlobalConfig.CONTRACTPAY_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'CONTRACTNUM':'=" + contractnum + "','CONTRACTREV':'=" + contractrev + "'}}";

    }

    /**
     * 合同行*
     */
    public static String getCONTRACTLINEURL(String username, String contractnum, String contractrev, int curpage, int showcount) {
        return "{'appid':'" + GlobalConfig.CONTPURCH_APPID + "','objectname':'" + GlobalConfig.CONTRACTLINE_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'CONTRACTNUM':'=" + contractnum + "','REVISIONNUM':'=" + contractrev + "'}}";

    }


    /**
     * 付款验收*
     */
    public static String getPAYCHECKUrl(String username, int curpage, int showcount) {
        return "{'appid':'" + GlobalConfig.PAYCHECK_APPID + "','objectname':'" + GlobalConfig.PAYCHECK_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }

    /**
     * 需款计划*
     */
    public static String getPAYPLANUrl(String username, int curpage, int showcount) {
        return "{'appid':'" + GlobalConfig.PP_APPID + "','objectname':'" + GlobalConfig.PAYPLAN_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }

    /**
     * 差旅报销单／备用金报销*
     */
    public static String getEXPENSEUrl(String appid, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.EXPENSE_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }


    /**
     * 补助明细*
     */
    public static String getSUBSIDIESUrl(String appid, String username,String expensenum, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.SUBSIDIES_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'EXPENSENUM':'=" + expensenum + "'}}";

    }
    /**
     * 补助明细/交通明细*
     */
    public static String getCLMXUrl(String appid, String objectname,String username,String expensenum, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + objectname + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'EXPENSENUM':'=" + expensenum + "'}}";

    }

    /**
     * 借款单*
     */
    public static String getBoUrl(String username, int curpage, int showcount) {
        return "{'appid':'" + GlobalConfig.BO_APPID + "','objectname':'" + GlobalConfig.BO_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }

    /**
     * 审批记录*
     */
    public static String getWFTRANSACTIONUrl(String username, String ownertable, String ownerid, int curpage, int showcount) {
        return "{'appid':'" + GlobalConfig.WFADMIN_APPID + "','objectname':'" + GlobalConfig.WFTRANSACTION_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'OWNERTABLE':'=" + ownertable + "','OWNERID':'=" + ownerid + "'}}";

    }

    /**
     * 执行部门 /科室*
     * GlobalConfig.USER_APPID 部门
     * GlobalConfig.ROLE_APPID 科室
     */
    public static String getCUDEPTUrl(String appid, String deptnum, String username, int curpage, int showcount) {
        if (appid.equals(GlobalConfig.USER_APPID)) {
            return "{'appid':'" + GlobalConfig.USER_APPID + "','objectname':'" + GlobalConfig.CUDEPT_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else if (appid.equals(GlobalConfig.ROLE_APPID)) {
            return "{'appid':'" + GlobalConfig.ROLE_APPID + "','objectname':'" + GlobalConfig.CUDEPT_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PARENT':'=" + deptnum + "'}}";

        }
        return null;
    }


    /**
     * 附件合同*
     */
    public static String getDOCLINKSUrl(String username, String ownertable, String ownerid, int curpage, int showcount) {
        return "{'appid':'" + GlobalConfig.USER_APPID + "','objectname':'" + GlobalConfig.DOCLINKS_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'OWNERTABLE':'=" + ownertable + "','OWNERID':'=" + ownerid + "'}}";

    }


    /**
     * 执行人
     *
     * @param cudept    部门
     * @param cucrew    科室
     * @param username  用户名
     * @param curpage   当前页
     * @param showcount 显示条数
     */
    public static String getPERSONUrl(String appid, String cudept, String cucrew, String username, int curpage, int showcount) {
        if (null == cudept && null == cucrew) {
            return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PERSON_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

        } else {
            return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PERSON_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'CUDEPT':'=" + cudept + "','CUCREW':'=" + cucrew + "'}}";
        }
    }
}
