package com.hqxh.fiamproperty.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.base.BaseTitleActivity;
import com.hqxh.fiamproperty.bean.R_APPROVE;
import com.hqxh.fiamproperty.bean.R_WORKFLOW;
import com.hqxh.fiamproperty.constant.GlobalConfig;
import com.hqxh.fiamproperty.model.R_GR;
import com.hqxh.fiamproperty.model.R_GR.GR;


import com.hqxh.fiamproperty.ui.widget.ConfirmDialog;
import com.hqxh.fiamproperty.unit.AccountUtils;
import com.hqxh.fiamproperty.unit.JsonUnit;
import com.rx2androidnetworking.Rx2AndroidNetworking;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
//物资出门详情

public class GrDetailsActivity extends BaseTitleActivity {
    private static String TAG = "GrDetailsActivity ";

    private GR gr;



    TextView grnum_text; //编号
    TextView grnum2_text;//物资出门
    TextView location_text;//门岗1
    TextView location2_text;//门岗2
    TextView reason_text;//理由
    TextView type_text;//类型
    TextView schedulardate_text;//计划出门日期
    TextView displayname_text;//申请人
    TextView phone_text;//电话
    TextView cudept_text;//部门
    TextView cucrew_text;//科室
    TextView description_text;//状态
    TextView enterdate_text;//申请日期

    ImageView wzmx_text;//物资明细
    ImageView zcmx_text;//整车明细
    RelativeLayout zcmx_relativeLayout; //整车明细布局
    ImageView spjl_text;//审批记录
    Button sp_btn_id;//审批按钮


    @Override
    protected void beforeInit() {
        super.beforeInit();
        gr = (GR) getIntent().getExtras().getSerializable("gr");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_grdetails;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        grnum_text = (TextView) findViewById(R.id.grnum_text);
        grnum2_text = (TextView) findViewById(R.id.grnum2_text);
        location_text = (TextView) findViewById(R.id.location_text);
        location2_text = (TextView) findViewById(R.id.location2_text);
        reason_text = (TextView) findViewById(R.id.reason_text);
        type_text = (TextView) findViewById(R.id.type_text);
        schedulardate_text = (TextView) findViewById(R.id.schedulardate_text);
        displayname_text = (TextView) findViewById(R.id.displayname_text);
        phone_text = (TextView) findViewById(R.id.phone_text);
        cudept_text = (TextView) findViewById(R.id.cudept_text);
        cucrew_text = (TextView) findViewById(R.id.cucrew_text);
        description_text = (TextView) findViewById(R.id.description_text);
        enterdate_text = (TextView) findViewById(R.id.enterdate_text);
        wzmx_text = (ImageView) findViewById(R.id.wzmx_text);
        zcmx_text = (ImageView) findViewById(R.id.zcmx_text);
        zcmx_relativeLayout = (RelativeLayout) findViewById(R.id.zcmx_relativelayout_id);
        spjl_text = (ImageView) findViewById(R.id.spjl_text);
        sp_btn_id = (Button) findViewById(R.id.spbutton);

        if (JsonUnit.convertStrToArray(gr.getTYPE())[0].equals("物资")) {
            zcmx_relativeLayout.setVisibility(View.GONE);
        }
        showData();


    }

    private void showData() {
        grnum_text.setText(JsonUnit.convertStrToArray(gr.getGRNUM())[0]);
        grnum2_text.setText(JsonUnit.convertStrToArray(gr.getDESCRIPTION())[0]);
        location_text.setText(JsonUnit.convertStrToArray(gr.getLOCATIONDESCRIPTION())[0]);
        location2_text.setText(JsonUnit.convertStrToArray(gr.getLOCATION2DESCRIPTION())[0]);
        reason_text.setText(JsonUnit.convertStrToArray(gr.getREASON())[0]);
        type_text.setText(JsonUnit.convertStrToArray(gr.getTYPE())[0]);
        schedulardate_text.setText(JsonUnit.convertStrToArray(gr.getSCHEDULARDATE())[0]);
        displayname_text.setText(JsonUnit.convertStrToArray(gr.getENTERBY())[0]);
        phone_text.setText(JsonUnit.convertStrToArray(gr.getPHONE())[0]);
        cudept_text.setText(JsonUnit.convertStrToArray(gr.getCUDEPT())[0]);
        cucrew_text.setText(JsonUnit.convertStrToArray(gr.getCUCREW())[0]);
        description_text.setText(JsonUnit.convertStrToArray(gr.getSTATUSDESC())[0]);
        enterdate_text.setText(JsonUnit.convertStrToArray(gr.getENTERDATE())[0]);


        wzmx_text.setOnClickListener(wzmx_textOnClickListener);
        zcmx_text.setOnClickListener(zcmx_textOnClickListener);
        spjl_text.setOnClickListener(sqjlImageViewOnClickListener);
        sp_btn_id.setOnClickListener(spOnClickListener);


    }

    @Override
    protected String getSubTitle() {
        return getString(R.string.wzcmxq_text);
    }


    private View.OnClickListener wzmx_textOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(GrDetailsActivity.this, WzmxListActivity.class);
            intent.putExtra("grnum", JsonUnit.convertStrToArray(gr.getGRNUM())[0]);
            startActivityForResult(intent, 0);

        }
    };
    private View.OnClickListener zcmx_textOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(GrDetailsActivity.this, ZcmxListActivity.class);
            intent.putExtra("grnum", JsonUnit.convertStrToArray(gr.getGRNUM())[0]);
            startActivityForResult(intent, 0);

        }
    };
    //审批记录
    private View.OnClickListener sqjlImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(GrDetailsActivity.this, WftransactionActivity.class);
            intent.putExtra("ownertable", GlobalConfig.GR_NAME);
            intent.putExtra("ownerid", JsonUnit.convertStrToArray(gr.getGRID())[0]);
            startActivityForResult(intent, 0);
        }
    };
/*    审批*/


    private View.OnClickListener spOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            PostStart(GlobalConfig.GR_NAME, JsonUnit.convertStrToArray(gr.getGRID())[0], GlobalConfig.GRWZ_APPID, AccountUtils.getpersonId(GrDetailsActivity.this));
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
                            showMiddleToast(GrDetailsActivity.this, workflow.getErrmsg());
                        }
                    }


                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        showMiddleToast(GrDetailsActivity.this, getString(R.string.spsb_text));
                    }
                });
    }


    //http://10.60.12.98/maximo/mobile/wf/approve?ownertable=GR&ownerid=77128&memo=驳回&selectWhat=0&userid=zhuyinan
    private void PostApprove(String ownertable, String ownerid, String memo, String selectWhat, String userid) {
        Log.e(TAG, "ownertable=" + ownertable + ",ownerid=" + ownerid + ",memo=" + memo + ",selectWhat=" + selectWhat + ",userid=" + userid);
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
                        R_GR workflow = new Gson().fromJson(s, R_GR.class);
                        showMiddleToast(GrDetailsActivity.this, workflow.getErrmsg());
                    }


                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        showMiddleToast(GrDetailsActivity.this, getString(R.string.spsb_text));
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
                        PostApprove(GlobalConfig.GR_NAME, JsonUnit.convertStrToArray(gr.getGRID())[0], memo, result.getIspositive(), AccountUtils.getpersonId(GrDetailsActivity.this));
                    }


                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }

}



