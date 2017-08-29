package com.hqxh.fiamproperty.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.base.BaseTitleActivity;
import com.hqxh.fiamproperty.bean.R_APPROVE;
import com.hqxh.fiamproperty.bean.R_WORKFLOW;
import com.hqxh.fiamproperty.constant.GlobalConfig;
import com.hqxh.fiamproperty.model.R_PAYPLAN.PAYPLAN;
import com.hqxh.fiamproperty.model.R_PR;
import com.hqxh.fiamproperty.ui.widget.ConfirmDialog;
import com.hqxh.fiamproperty.unit.AccountUtils;
import com.hqxh.fiamproperty.unit.JsonUnit;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
需款计划详情
 */

public class XkplandetailActivity extends BaseTitleActivity{
    private static final String TAG="XkplandetailActivity";

    private PAYPLAN payplan;
    private Animation rotate;


    TextView payplannum_text;//计划单
    TextView month_text;//日期
    TextView type1_text;//需款类型
    TextView totalcost1_text;//需款金额
    TextView status_text;//状态
    TextView contractnum1_text;//合同
    TextView description2_text;//合同描述

    TextView phase_text;//付款阶段
    TextView vendor_text;//供应商
    TextView wonum2_text;//出差申请

    CheckBox isbopayplan_text;//借款需款？

    TextView sqr_text;//申请人
    TextView enterdate_text;//申请时间
    TextView cudept_text;//部门
    TextView cucrew_text;//科室
    LinearLayout jbxxLinearlayout;

    ImageView jbxx_text;//其它信息
    ImageView xkxm_text;//文档
    ImageView spjl_text;//审批记录

    Button sp_btn_id;//审批按钮


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.xkplandetails;
    }
    protected void beforeInit(){
        super.beforeInit();
        payplan = (PAYPLAN) getIntent().getExtras().getSerializable("payplan");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        payplannum_text=(TextView)findViewById(R.id.payplannum_text);
        month_text=(TextView)findViewById(R.id.month_text);
        type1_text=(TextView)findViewById(R.id.type1_text);

        totalcost1_text=(TextView)findViewById(R.id.totalcost1_text);
        status_text=(TextView)findViewById(R.id.status_text);
        contractnum1_text=(TextView)findViewById(R.id.contractnum1_text);
        description2_text=(TextView)findViewById(R.id.description2_text);
        phase_text=(TextView)findViewById(R.id.phase_text);
        vendor_text=(TextView)findViewById(R.id.vendor_text);
        wonum2_text=(TextView)findViewById(R.id.wonum2_text);
        cudept_text=(TextView)findViewById(R.id.cudept_text);
        cucrew_text=(TextView)findViewById(R.id.cucrew_text);


        isbopayplan_text=(CheckBox) findViewById(R.id.isbopayplan_text);

        sqr_text=(TextView)findViewById(R.id.sqr_text);
        enterdate_text=(TextView)findViewById(R.id.enterdate_text);

        jbxxLinearlayout=(LinearLayout) findViewById(R.id.jbxx_text_id);

        jbxx_text=(ImageView)findViewById(R.id.jbxx_text);
        xkxm_text=(ImageView)findViewById(R.id.xkxm_text);
        spjl_text=(ImageView)findViewById(R.id.spjl_text);

        sp_btn_id=(Button)findViewById(R.id.workflow_btn_id);

         showDate();

    }

    private void showDate() {
        payplannum_text.setText(JsonUnit.convertStrToArray(payplan.getPAYPLANNUM())[0]+","+JsonUnit.convertStrToArray(payplan.getDESCRIPTION())[0]);
        month_text.setText(JsonUnit.convertStrToArray(payplan.getMONTH())[0]);
        type1_text.setText(JsonUnit.convertStrToArray(payplan.getTYPE())[0]);
        totalcost1_text.setText(JsonUnit.convertStrToArray(payplan.getTOTALCOST())[0]);
        status_text.setText(JsonUnit.convertStrToArray(payplan.getSTATUSDESC())[0]);
        contractnum1_text.setText(JsonUnit.convertStrToArray(payplan.getCONTRACTNUM())[0]);
        description2_text.setText(JsonUnit.convertStrToArray(payplan.getCONTRACTDESC())[0]);
        phase_text.setText(JsonUnit.convertStrToArray(payplan.getPHASE())[0]);
        vendor_text.setText(JsonUnit.convertStrToArray(payplan.getVENDORNAME())[0]);
        wonum2_text.setText(JsonUnit.convertStrToArray(payplan.getWONUM2())[0]);
        cudept_text.setText(JsonUnit.convertStrToArray(payplan.getDEPARTMENT())[0]);
        cucrew_text.setText(JsonUnit.convertStrToArray(payplan.getCREW())[0]);


        if(JsonUnit.convertStrToArray(payplan.getISBOPAYPLAN())[0].equals("1")){
            isbopayplan_text.setChecked(true);
        }else {
            isbopayplan_text.setChecked(false);
        }
        sqr_text.setText(JsonUnit.convertStrToArray(payplan.getENTERBYNAME())[0]);
        enterdate_text.setText(JsonUnit.convertStrToArray(payplan.getENTERDATE())[0]);

        rotate = AnimationUtils.loadAnimation(this, R.anim.arrow_rotate);//创建动画

        jbxx_text.setOnClickListener(jbxx_textOnClickListener);
        spjl_text.setOnClickListener(sqjlImageViewOnClickListener);
        xkxm_text.setOnClickListener(xkxm_textOnClickListener);
        sp_btn_id.setOnClickListener(sp_btn_idOnClickListener);
    }

    @Override
    protected String getSubTitle() {
        return getString(R.string.xkjh_text);
    }


    //需款项目
    private View.OnClickListener xkxm_textOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(XkplandetailActivity.this, XkxmActivity.class);
            intent.putExtra("payplannum",JsonUnit.convertStrToArray(payplan.getPAYPLANNUM())[0]);
            startActivityForResult(intent, 0);

        }
    };

    /*
审批记录
*/
    private View.OnClickListener sqjlImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(XkplandetailActivity.this, WftransactionActivity.class);
            intent.putExtra("ownertable", GlobalConfig.PAYPLAN_NAME);
            intent.putExtra("ownerid", JsonUnit.convertStrToArray(payplan.getPAYPLANID())[0]);
            startActivityForResult(intent, 0);
        }
    };
    /*
    其它信息
    */
    private View.OnClickListener jbxx_textOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (startAnaim()) {
                jbxxLinearlayout.setVisibility(View.GONE);
            } else {
                jbxxLinearlayout.setVisibility(View.VISIBLE);
            }

        }
    };
    //启动动画
    private boolean startAnaim() {

        rotate.setInterpolator(new LinearInterpolator());//设置为线性旋转

        Log.e(TAG, "b=" + !rotate.getFillAfter());
        rotate.setFillAfter(!rotate.getFillAfter());//每次都取相反值，使得可以不恢复原位的旋转

        jbxx_text.startAnimation(rotate);
        return rotate.getFillAfter();
    }
    //审批
    private View.OnClickListener sp_btn_idOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PostStart(GlobalConfig.PAYPLAN_NAME, JsonUnit.convertStrToArray(payplan.getPAYPLANID())[0], GlobalConfig.PP_APPID, AccountUtils.getpersonId(XkplandetailActivity.this));
        }
    };



    //流程启动
//http://10.60.12.98/maximo/mobile/wf/start?ownertable=GR&ownerid=77129&processname=GR-WZMAIN&userid=yanghongwei
    private void PostStart(String ownertable, String ownerid, String appid, String userid) {
        Log.e(TAG, "ownertable=" + ownertable + ",ownerid=" + ownerid + ",appid=" + appid + ",userid=" + userid);
        Rx2AndroidNetworking.post(GlobalConfig.HTTP_URL_START_WORKFLOW)
                .addBodyParameter("ownertable", ownertable)
                .addBodyParameter("ownerid", ownerid)
                .addBodyParameter("appid", appid)
                .addBodyParameter("userid", userid)
                .build().getStringObservable()
                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取数据列表的请求结果
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String string) throws Exception {

                    }
                })


                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.i(TAG, "s=" + s);


                        R_WORKFLOW workflow = new Gson().fromJson(s, R_WORKFLOW.class);
                        if (workflow.getErrcode().equals(GlobalConfig.WORKFLOW_106)) {
                            R_APPROVE r_approve = new Gson().fromJson(s, R_APPROVE.class);
                            for (int i = 0; i < r_approve.getResult().size(); i++) {
                                R_APPROVE.Result result = r_approve.getResult().get(i);
                                Log.e(TAG, "instruction=" + result.getInstruction() + ",ispositive=" + result.getIspositive());
                            }

                            showDialog(r_approve.getResult());
                        } else {
                            showMiddleToast(XkplandetailActivity.this, workflow.getErrmsg());
                        }
                    }


                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        showMiddleToast(XkplandetailActivity.this, getString(R.string.spsb_text));
                    }
                });
    }


    //http://10.60.12.98/maximo/mobile/wf/approve?ownertable=GR&ownerid=77128&memo=驳回&selectWhat=0&userid=zhuyinan
    private void PostApprove(String ownertable, String ownerid, String memo, String selectWhat, String userid) {
        Log.e(TAG, "ownertable=" + ownertable + ",ownerid=" + ownerid + ",memo=" + memo +",selectWhat="+selectWhat+ ",userid=" + userid);
        Rx2AndroidNetworking.post(GlobalConfig.HTTP_URL_APPROVE_WORKFLOW)
                .addBodyParameter("ownertable", ownertable)
                .addBodyParameter("ownerid", ownerid)
                .addBodyParameter("memo", memo)
                .addBodyParameter("selectWhat", selectWhat)
                .addBodyParameter("userid", userid)
                .build().getStringObservable()
                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取数据列表的请求结果
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String string) throws Exception {

                    }
                })


                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.i(TAG, "审批s=" + s);
                        R_WORKFLOW workflow = new Gson().fromJson(s, R_WORKFLOW.class);
                        showMiddleToast(XkplandetailActivity.this, workflow.getErrmsg());
                    }


                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        showMiddleToast(XkplandetailActivity.this, getString(R.string.spsb_text));
                    }
                });
    }


    //弹出对话框
    public void showDialog(List<R_APPROVE.Result> results) {//

        ConfirmDialog.Builder dialog = new ConfirmDialog.Builder(this);
        dialog.setTitle("审批")
                .setData(results)
                .setPositiveButton("确定", new ConfirmDialog.Builder.cOnClickListener() {
                    @Override
                    public void cOnClickListener(DialogInterface dialogInterface, R_APPROVE.Result result, String memo) {
                        dialogInterface.dismiss();
                        Log.e(TAG, "result" + result.getInstruction());
                        PostApprove(GlobalConfig.PAYPLAN_NAME, JsonUnit.convertStrToArray(payplan.getPAYPLANID())[0],memo,result.getIspositive(),AccountUtils.getpersonId(XkplandetailActivity.this));
                    }


                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }
}
