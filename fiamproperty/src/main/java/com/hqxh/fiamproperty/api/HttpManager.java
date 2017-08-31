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
    public static String getWORKORDERUrl(String appid, String objectname, String wonum, String username, int curpage, int showcount) {
        if (wonum.isEmpty()) {
            return "{'appid':'" + appid + "','objectname':'" + objectname + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

        } else {
            return "{'appid':'" + appid + "','objectname':'" + objectname + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WONUM':'=" + wonum + "'}}";

        }

    }

    /**
     * 根据
     * 条件进行搜索
     */
    public static String getSearchWORKORDERUrl(String appid, String objectname, String search, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + objectname + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','reporsearch':{'WONUM':'" + search + "','DESCRIPTION':'" + search + "'}}";


    }

    /**
     * 出差人*
     */
    public static String getR_PERSONRELATIONUrl(String appid, String username, String wonum, int curpage, int showcount) {
        if (appid.equals(GlobalConfig.TRAVEL_APPID) || appid.equals(GlobalConfig.TRAVELS_APPID)) {
            return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PERSONRELATION_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WONUM':'=" + wonum + "'}}";

        } else if (appid.equals(GlobalConfig.EXPENSES_APPID)) {
            return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PERSONRELATION_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'EXPENSENUM':'=" + wonum + "'}}";

        }
        return null;

    }


    /**
     * 出国立项申请-出国人员知识积累拟交付资料清单*
     */
    public static String getKBFILELISTUrl(String appid, String wonum, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.KBFILELIST_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'WONUM':'=" + wonum + "'}}";


    }

    /*
    物资明细/整车明细
    */
    public static String getGRLINEUrl(String appid, String username, String grnum, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.GRLINE_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'GRNUM':'=" + grnum + "'}}";

    }
/*
    整车明细
*/

    public static String getZCMXUrl(String username, String grnum, int curpage, int showcount) {
        return "{'appid':'" + GlobalConfig.GRZC_APPID + "','objectname':'" + GlobalConfig.GRLINE_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'GRNUM':'=" + grnum + "'}}";

    }


    /**
     * 出门管理*
     */
    public static String getGRUrl(String appid, String objctname, String grnum, String username, int curpage, int showcount) {
        if (grnum.isEmpty()) {
            return "{'appid':'" + appid + "','objectname':'" + objctname + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";
        } else {
            return "{'appid':'" + appid + "','objectname':'" + objctname + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'GRNUM':'=" + grnum + "'}}";

        }
    }

    /**
     * 出门管理-搜索*
     */
    public static String getSearchGRUrl(String appid, String objctname, String search, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + objctname + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','reporsearch':{'GRNUM':'" + search + "','DESCRIPTION':'" + search + "'}}";
    }

    /**
     * 采购申请*
     */
    public static String getPRUrl(String appid, String prnum, String username, int curpage, int showcount) {
        if (prnum.isEmpty()) {
            return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PR_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

        } else {
            return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PR_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PRNUM':'=" + prnum + "'}}";
        }
    }

    /**
     * 采购申请-搜索*
     */
    public static String getSearchPRUrl(String appid, String search, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PR_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','reporsearch':{'PRNUM':'" + search + "','DESCRIPTION':'" + search + "'}}";

    }


    /**
     * 潜在供应商*
     */
    public static String getPRVENDORUrl(String appid, String prnum, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PRVENDOR_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PRNUM':'=" + prnum + "'}}";

    }

    /**
     * 申请明细*
     */
    public static String getPRLINEUrl(String appid, String prnum, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PRLINE_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PRNUM':'=" + prnum + "'}}";

    }

    /**
     * 任务单*
     */
    public static String getRWDUrl(String appid, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.WORKORDER_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }


    /**
     * 任务单-搜索*
     */
    public static String getSearchRWDUrl(String appid, String search, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.WORKORDER_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','reporsearch':{'WONUM':'" + search + "','DESCRIPTION':'" + search + "'}}";

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
    public static String getPURCHVIEWUrl(String appid, String contractnum, String username, int curpage, int showcount) {
        if (contractnum.isEmpty()) {
            return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PURCHVIEW_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

        } else {
            return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PURCHVIEW_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'CONTRACTNUM':'=" + contractnum + "'}}";

        }

    }

    /**
     * 合同-搜索*
     */
    public static String getSearchPURCHVIEWUrl(String appid, String search, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PURCHVIEW_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','reporsearch':{'CONTRACTNUM':'" + search + "','DESCRIPTION':'" + search + "'}}";


    }

    /**
     * 付款计划*
     */
    public static String getCONTRACTPAYURL(String username, String contractnum, String contractrev, int curpage, int showcount) {
        return "{'appid':'" + GlobalConfig.CONTPURCH_APPID + "','objectname':'" + GlobalConfig.CONTRACTPAY_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'CONTRACTNUM':'=" + contractnum + "','CONTRACTREV':'=" + contractrev + "'}}";

    }

    /**
     * 付款验收明细*
     */
    public static String getPAYCHECKLINEUrl(String appid, String paychecknum, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PAYCHECKLINE_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PAYCHECKNUM':'=" + paychecknum + "'}}";

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
    public static String getPAYCHECKUrl(String appid, String paychecknum, String username, int curpage, int showcount) {
        if (paychecknum.isEmpty()) {
            return "{'appid':'" + GlobalConfig.PAYCHECK_APPID + "','objectname':'" + GlobalConfig.PAYCHECK_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

        } else {
            return "{'appid':'" + GlobalConfig.PAYCHECK_APPID + "','objectname':'" + GlobalConfig.PAYCHECK_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PAYCHECKNUM':'=" + paychecknum + "'}}";

        }

    }


    /**
     * 付款验收*
     */
    public static String getSearchPAYCHECKUrl(String appid, String search, String username, int curpage, int showcount) {

        return "{'appid':'" + GlobalConfig.PAYCHECK_APPID + "','objectname':'" + GlobalConfig.PAYCHECK_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','reporsearch':{'PAYCHECKNUM':'" + search + "','DESCRIPTION':'" + search + "'}}";


    }

    /**
     * 需款计划*
     */
    public static String getPAYPLANUrl(String appid, String payplannum, String username, int curpage, int showcount) {
        if (payplannum.isEmpty()) {
            return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PAYPLAN_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

        } else {
            if(appid.equals(GlobalConfig.PPCHANGE_APPID)){  //子需款计划
                return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PAYPLAN_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PARENT':'=" + payplannum + "'}}";

            }
            return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PAYPLAN_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PAYPLANNUM':'=" + payplannum + "'}}";

        }

    }


    /**
     * 需款计划-搜索*
     */
    public static String getSearchPAYPLANUrl(String appid, String search, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PAYPLAN_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','reporsearch':{'PAYPLANNUM':'" + search + "','DESCRIPTION':'" + search + "'}}";


    }


    /**
     * 需款项目
     */
    public static String gePAYPLANPROJECTUrl(String appid,String payplannum, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PAYPLANPROJECT_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'PAYPLANNUM':'=" + payplannum + "'}}";

    }

    /**
     * 差旅报销单／备用金报销*
     */
    public static String getEXPENSEUrl(String appid, String expensenum, String username, int curpage, int showcount) {
        if (expensenum.isEmpty()) {
            return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.EXPENSE_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

        } else {
            return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.EXPENSE_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'EXPENSENUM':'=" + expensenum + "'}}";

        }

    }

    /**
     * 差旅报销单／备用金报销 -搜索*
     */
    public static String getSearchEXPENSEUrl(String appid, String search, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.EXPENSE_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','reporsearch':{'EXPENSENUM':'" + search + "','DESCRIPTION':'" + search + "'}}";


    }


    /**
     * 补助明细*
     */
    public static String getSUBSIDIESUrl(String appid, String username, String expensenum, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.SUBSIDIES_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'EXPENSENUM':'=" + expensenum + "'}}";

    }

    /**
     * 补助明细/交通明细*
     */
    public static String getCLMXUrl(String appid, String objectname, String username, String expensenum, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + objectname + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'EXPENSENUM':'=" + expensenum + "'}}";

    }

    /**
     * 借款单*
     */
    public static String getBoUrl(String appid, String bonum, String username, int curpage, int showcount) {
        if (bonum.isEmpty()) {
            return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.BO_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

        } else {
            return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.BO_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'BONUM':'=" + bonum + "'}}";

        }

    }

    /**
     * 借款单-搜索*
     */
    public static String getSearchBoUrl(String appid, String search, String username, int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.BO_NAME + "','username':'" + username + "','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','reporsearch':{'BONUM':'" + search + "','DESCRIPTION':'" + search + "'}}";


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
