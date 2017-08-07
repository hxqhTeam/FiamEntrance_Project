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
    public static String getWFASSIGNMENTUrl(String username, String assignstatus,int curpage, int showcount) {
            return "{'appid':'" + GlobalConfig.WFADMIN_APPID + "','objectname':'" + GlobalConfig.WFASSIGNMENT_NAME + "','username':'"+username+"','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read','condition':{'ASSIGNSTATUS':'="+assignstatus+"','ASSIGNCODE':'="+username+"'}}";

    }
    /**
     * 国内出差*
     */
    public static String getWORKORDERUrl(String username,int curpage, int showcount) {
            return "{'appid':'" + GlobalConfig.TRAVEL_APPID + "','objectname':'" + GlobalConfig.WORKORDER_NAME + "','username':'"+username+"','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }

    /**
     * 出国立项*
     */
    public static String getGWWORKORDERUrl(String username,int curpage, int showcount) {
        return "{'appid':'" + GlobalConfig.TRAVELS_APPID + "','objectname':'" + GlobalConfig.WORKORDER_NAME + "','username':'"+username+"','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }

    /**
     * 出门管理*
     */
    public static String getGRUrl(String username,int curpage, int showcount) {
        return "{'appid':'" + GlobalConfig.GRWZ_APPID + "','objectname':'" + GlobalConfig.GR_APPID + "','username':'"+username+"','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }

    /**
     * 采购申请*
     */
    public static String getPRUrl(String appid,String username,int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.PR_NAME + "','username':'"+username+"','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }

    /**
     * 任务单*
     */
    public static String getRWDUrl(String appid,String username,int curpage, int showcount) {
        return "{'appid':'" + appid + "','objectname':'" + GlobalConfig.WORKORDER_NAME + "','username':'"+username+"','curpage':" + curpage + ",'showcount':" + showcount + ",'option':'read'}";

    }
}
