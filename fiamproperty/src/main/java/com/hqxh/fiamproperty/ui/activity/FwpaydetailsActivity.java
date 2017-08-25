package com.hqxh.fiamproperty.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.api.HttpManager;
import com.hqxh.fiamproperty.base.BaseTitleActivity;
import com.hqxh.fiamproperty.bean.R_APPROVE;
import com.hqxh.fiamproperty.bean.R_WORKFLOW;
import com.hqxh.fiamproperty.constant.GlobalConfig;
import com.hqxh.fiamproperty.model.R_PR.PR;
import com.hqxh.fiamproperty.model.R_PR;
import com.hqxh.fiamproperty.ui.widget.ConfirmDialog;
import com.hqxh.fiamproperty.unit.AccountUtils;
import com.hqxh.fiamproperty.unit.JsonUnit;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/23.
 * <p>
 * 服务采购
 */

public class FwpaydetailsActivity extends BaseTitleActivity {
    private static final String TAG = "FwpaydetailsActivity";

    private ScrollView scrollView;
    private TextView prnum_text;//申请单号
    private TextView worktype_text;//类型
    private TextView status_text;//状态
    private TextView udremarka_text;//立项原因
    private TextView projectdes1_text;//项目内容
    private TextView udremarks3_text;//项目预期目标
    private TextView pr6_text;//总预算

    private ImageView jbxx_text;//其它信息
    private LinearLayout jbxxlinearlayout;

    private TextView udremark5_text;//适用范围
    private TextView rdchead_text;//中心分管领导
    private TextView ownername_text;//执行人

    private TextView projectid_text;//费用号
    private TextView pm_text;//项目经理
    private TextView sqr_text;//申请人

    private ImageView document_text;//文档
    private ImageView spjl_text;//审批记录

    private Button workflowBtn;//审批按钮
    private RelativeLayout workflowRelativeLayout;//布局

    private PR pr;
    private Animation rotate;

    private int mark = 0;//跳转标识
    private String appid; //appid
    private String ownernum;//ownernum
    private String ownertable;//ownertable

    protected void beforeInit() {
        super.beforeInit();
        if(getIntent().hasExtra("pr")){
            pr = (PR) getIntent().getExtras().getSerializable("pr");
        }
        if (getIntent().hasExtra("mark")) {
            mark = getIntent().getExtras().getInt("mark");
        }
        if (getIntent().hasExtra("appid")) {
            appid = getIntent().getExtras().getString("appid");
        }
        if (getIntent().hasExtra("ownernum")) {
            ownernum = getIntent().getExtras().getString("ownernum");
        }
        if (getIntent().hasExtra("ownertable")) {
            ownertable = getIntent().getExtras().getString("ownertable");
        }

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_fwpaydetails;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        scrollView = (ScrollView) findViewById(R.id.scrollView_id);
        prnum_text = (TextView) findViewById(R.id.prnum_text);
        worktype_text = (TextView) findViewById(R.id.worktype_text);
        status_text = (TextView) findViewById(R.id.status_text);
        udremarka_text = (TextView) findViewById(R.id.udremarka_text);
        projectdes1_text = (TextView) findViewById(R.id.projectdes1_text);
        udremarks3_text = (TextView) findViewById(R.id.udremarks3_text);
        pr6_text = (TextView) findViewById(R.id.pr6_text);
        udremark5_text = (TextView) findViewById(R.id.udremark5_text);
        rdchead_text = (TextView) findViewById(R.id.rdchead_text);
        ownername_text = (TextView) findViewById(R.id.ownername_text);

        projectid_text = (TextView) findViewById(R.id.projectid_text);
        pm_text = (TextView) findViewById(R.id.pm_text);
        sqr_text = (TextView) findViewById(R.id.sqr_text);

        jbxx_text = (ImageView) findViewById(R.id.jbxx_text);
        jbxxlinearlayout = (LinearLayout) findViewById(R.id.jbxx_text_id);

        document_text = (ImageView) findViewById(R.id.document_text);
        spjl_text = (ImageView) findViewById(R.id.spjl_text);

        workflowBtn = (Button) findViewById(R.id.workflow_btn_id);

        workflowRelativeLayout = (RelativeLayout) findViewById(R.id.relavtivelayout_btn_id);
        if (null == appid) {
            showData();
        } else {
            if (mark == HomeActivity.DB_CODE) { //待办任务
                workflowRelativeLayout.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.setMargins(0, 0, 0, getHeight(workflowRelativeLayout));//4个参数按顺序分别是左上右下
                scrollView.setLayoutParams(layoutParams);
            }
            getNetWorkPR();
        }



    }

    private void showData() {
        prnum_text.setText(JsonUnit.convertStrToArray(pr.getPRNUM())[0]);
        worktype_text.setText(JsonUnit.convertStrToArray(pr.getCUTYPE())[0]);
        status_text.setText(JsonUnit.convertStrToArray(pr.getSTATUSDESC())[0]);
        udremarka_text.setText(JsonUnit.convertStrToArray(pr.getUDREMARK1())[0]);
        projectdes1_text.setText(JsonUnit.convertStrToArray(pr.getUDREMARK2())[0]);
        udremarks3_text.setText(JsonUnit.convertStrToArray(pr.getUDREMARK3())[0]);
        pr6_text.setText(JsonUnit.convertStrToArray(pr.getTOTALCOST())[0]);
        udremark5_text.setText(JsonUnit.convertStrToArray(pr.getUDREMARK4())[0]);
        rdchead_text.setText(JsonUnit.convertStrToArray(pr.getRDCHEADNAME())[0]);
        ownername_text.setText(JsonUnit.convertStrToArray(pr.getASSIGNERNAME())[0]);
        projectid_text.setText(JsonUnit.convertStrToArray(pr.getPROJECTID())[0]);
        pm_text.setText(JsonUnit.convertStrToArray(pr.getPM())[0]);
        sqr_text.setText(JsonUnit.convertStrToArray(pr.getREQBYPERSON())[0]);

        jbxx_text.setOnClickListener(jbxx_textOnClickListener);
        document_text.setOnClickListener(document_textOnClickListener);
        spjl_text.setOnClickListener(spjl_textOnClickListener);

        workflowBtn.setOnClickListener(workflowBtnOnClickListener);

        rotate = AnimationUtils.loadAnimation(this, R.anim.arrow_rotate);//创建动画


    }

    @Override
    protected String getSubTitle() {
        return getString(R.string.fwcgxq_text);
    }

    private boolean startAnaim() {

        rotate.setInterpolator(new LinearInterpolator());//设置为线性旋转

        rotate.setFillAfter(!rotate.getFillAfter());//每次都取相反值，使得可以不恢复原位的旋转

        jbxx_text.startAnimation(rotate);
        return rotate.getFillAfter();
    }

    private View.OnClickListener jbxx_textOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (startAnaim()) {
                jbxxlinearlayout.setVisibility(View.GONE);
            } else {
                jbxxlinearlayout.setVisibility(View.VISIBLE);
            }
        }
    };


    //文档
    private View.OnClickListener document_textOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(FwpaydetailsActivity.this, DoclinksActivity.class);
            intent.putExtra("ownertable", GlobalConfig.PR_NAME);
            intent.putExtra("ownerid", JsonUnit.convertStrToArray(pr.getPRID())[0]);
            intent.putExtra("title", getResources().getString(R.string.wd_text));
            startActivityForResult(intent, 0);

        }
    };

    //审批记录
    private View.OnClickListener spjl_textOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(FwpaydetailsActivity.this, WftransactionActivity.class);
            intent.putExtra("ownertable", GlobalConfig.PR_NAME);
            intent.putExtra("ownerid", JsonUnit.convertStrToArray(pr.getPRID())[0]);
            startActivityForResult(intent, 0);

        }
    };
    private View.OnClickListener workflowBtnOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            PostStart(GlobalConfig.PR_NAME, JsonUnit.convertStrToArray(pr.getPRID())[0], GlobalConfig.FWPR_APPID, AccountUtils.getpersonId(FwpaydetailsActivity.this));

        }
    };

    //流程启动
    private void PostStart(String ownertable, String ownerid, String appid, String userid) {
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
                            showMiddleToast(FwpaydetailsActivity.this, workflow.getErrmsg());
                        }
                    }


                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        showMiddleToast(FwpaydetailsActivity.this, getString(R.string.spsb_text));
                    }
                });
    }


    private void PostApprove(String ownertable, String ownerid, String memo, String selectWhat, String userid) {
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
                        R_WORKFLOW workflow = new Gson().fromJson(s, R_WORKFLOW.class);
                        showMiddleToast(FwpaydetailsActivity.this, workflow.getErrmsg());
                    }


                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        showMiddleToast(FwpaydetailsActivity.this, getString(R.string.spsb_text));
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
                        PostApprove(GlobalConfig.PR_NAME, JsonUnit.convertStrToArray(pr.getPRID())[0], memo, result.getIspositive(), AccountUtils.getpersonId(FwpaydetailsActivity.this));
                    }


                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }

    //根据appid,grnum,objctname获取国内出差信息
    private void getNetWorkPR() {
        String data = HttpManager.getPRUrl(appid, ownernum, AccountUtils.getpersonId(this), 1, 10);
        Rx2AndroidNetworking.post(GlobalConfig.HTTP_URL_SEARCH)
                .addBodyParameter("data", data)
                .build()
                .getObjectObservable(R_PR.class) // 发起获取数据列表的请求，并解析到FootList
                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取数据列表的请求结果
                .doOnNext(new Consumer<R_PR>() {
                    @Override
                    public void accept(@NonNull R_PR r_pr) throws Exception {
                    }
                })

                .map(new Function<R_PR, R_PR.ResultBean>() {
                    @Override
                    public R_PR.ResultBean apply(@NonNull R_PR r_pr) throws Exception {

                        return r_pr.getResult();
                    }
                })
                .map(new Function<R_PR.ResultBean, List<R_PR.PR>>() {
                    @Override
                    public List<R_PR.PR> apply(@NonNull R_PR.ResultBean resultBean) throws Exception {
                        return resultBean.getResultlist();
                    }

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<PR>>() {
                    @Override
                    public void accept(@NonNull List<PR> prs) throws Exception {

                        if (prs == null || prs.isEmpty()) {
                        } else {
                            pr = prs.get(0);
                            showData();
                        }
                    }


                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                    }
                });
    }

}
