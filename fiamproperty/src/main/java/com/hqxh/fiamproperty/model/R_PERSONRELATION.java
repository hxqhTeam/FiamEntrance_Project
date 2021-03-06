package com.hqxh.fiamproperty.model;

import java.util.List;

/**
 * Created by apple on 17/7/26.
 * 出差人
 */

public class R_PERSONRELATION {
    private String errcode; //返回状态
    private String errmsg; //消息
    private String userid; //用户名
    private ResultBean result;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    /**
     * 结果集
     * curpage
     * resultlist
     * showcount
     * totalpage
     * totalresult
     **/
    public static class ResultBean {
        private String curpage; //当前页
        private String showcount;//显示条数
        private String totalpage;//总共页数
        private String totalresult;//总共条数
        private List<PERSONRELATION> resultlist; //记录

        public String getCurpage() {
            return curpage;
        }

        public void setCurpage(String curpage) {
            this.curpage = curpage;
        }


        public String getShowcount() {
            return showcount;
        }

        public void setShowcount(String showcount) {
            this.showcount = showcount;
        }

        public String getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(String totalpage) {
            this.totalpage = totalpage;
        }

        public String getTotalresult() {
            return totalresult;
        }

        public void setTotalresult(String totalresult) {
            this.totalresult = totalresult;
        }

        public List<PERSONRELATION> getResultlist() {
            return resultlist;
        }

        public void setResultlist(List<PERSONRELATION> resultlist) {
            this.resultlist = resultlist;
        }
    }


    /**
     * 出差人
     **/
    public static class PERSONRELATION extends Entity {

        private String DISPLAYNAME; //姓名
        private String TITLE;//职务或职称
        private String CUDEPT;//部门
        private String CUCREW;//科室
        private String SEX;//性别
        private String POSITION;//岗级
        private String LINECOST;//报销金额
        private String TRVCOST1;//票据金额
        private String TRVCOST2;//补助金额
        private String PERSONNAME;//姓名

        public String getDISPLAYNAME() {
            return DISPLAYNAME;
        }

        public void setDISPLAYNAME(String DISPLAYNAME) {
            this.DISPLAYNAME = DISPLAYNAME;
        }

        public String getTITLE() {
            return TITLE;
        }

        public void setTITLE(String TITLE) {
            this.TITLE = TITLE;
        }

        public String getCUDEPT() {
            return CUDEPT;
        }

        public void setCUDEPT(String CUDEPT) {
            this.CUDEPT = CUDEPT;
        }

        public String getCUCREW() {
            return CUCREW;
        }

        public void setCUCREW(String CUCREW) {
            this.CUCREW = CUCREW;
        }

        public String getSEX() {
            return SEX;
        }

        public void setSEX(String SEX) {
            this.SEX = SEX;
        }

        public String getPOSITION() {
            return POSITION;
        }

        public void setPOSITION(String POSITION) {
            this.POSITION = POSITION;
        }

        public String getLINECOST() {
            return LINECOST;
        }

        public void setLINECOST(String LINECOST) {
            this.LINECOST = LINECOST;
        }

        public String getTRVCOST1() {
            return TRVCOST1;
        }

        public void setTRVCOST1(String TRVCOST1) {
            this.TRVCOST1 = TRVCOST1;
        }

        public String getTRVCOST2() {
            return TRVCOST2;
        }

        public void setTRVCOST2(String TRVCOST2) {
            this.TRVCOST2 = TRVCOST2;
        }

        public String getPERSONNAME() {
            return PERSONNAME;
        }

        public void setPERSONNAME(String PERSONNAME) {
            this.PERSONNAME = PERSONNAME;
        }
    }


}
