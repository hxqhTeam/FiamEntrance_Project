package com.hqxh.fiamproperty.ui.activity;import android.app.AlertDialog;import android.content.DialogInterface;import android.content.Intent;import android.graphics.drawable.Drawable;import android.os.Bundle;import android.util.Log;import android.view.Gravity;import android.view.View;import android.view.ViewGroup;import android.view.animation.Animation;import android.view.animation.AnimationUtils;import android.view.animation.LinearInterpolator;import android.widget.Button;import android.widget.ImageView;import android.widget.LinearLayout;import android.widget.TextView;import com.google.gson.Gson;import com.hqxh.fiamproperty.R;import com.hqxh.fiamproperty.base.BaseTitleActivity;import com.hqxh.fiamproperty.bean.R_APPROVE;import com.hqxh.fiamproperty.bean.R_WORKFLOW;import com.hqxh.fiamproperty.constant.GlobalConfig;import com.hqxh.fiamproperty.dateviews.cons.DPMode;import com.hqxh.fiamproperty.model.R_PURCHVIEW.PURCHVIEW;import com.hqxh.fiamproperty.ui.widget.ConfirmDialog;import com.hqxh.fiamproperty.ui.widget.DatePicker;import com.hqxh.fiamproperty.unit.AccountUtils;import com.hqxh.fiamproperty.unit.DataUtils;import com.hqxh.fiamproperty.unit.JsonUnit;import com.rx2androidnetworking.Rx2AndroidNetworking;import java.util.List;import io.reactivex.android.schedulers.AndroidSchedulers;import io.reactivex.annotations.NonNull;import io.reactivex.functions.Consumer;import io.reactivex.schedulers.Schedulers;/** * 合同详情 **/public class PurchviewActivity extends BaseTitleActivity {    private static final String TAG = "PurchviewActivity";    /**     * 信息展示     **/    private TextView contractnumText; //合同编号    private TextView udcontractnumText; //国外合同号    //    private TextView udtooil1Text; //立项类型    private TextView descriptionText; //合同名称    private TextView udclassText; //合同类别    private TextView revisionnumText; //版本    private TextView udtype2Text; //开口/闭口    private TextView statusdescText; //状态    private TextView udcontentText; //主要内容（摘要）    private TextView uddirectionText; //收付方向    private TextView udpayterm1Text; //支付方式    private TextView udiprownerText; //知识产权归属    private TextView signdateText; //签订日期    private TextView startdateText; //开始日期    private TextView enddateText; //结束日期    private ImageView jbxxImageView;  //基本信息    private LinearLayout jbxxLinearLayout;  //基本信息    private View jbxxView;  //基本信息    private TextView totalbasecostText; //合同金额（人民币）    private TextView cutotalcostText; //外币合同额    private TextView cucurrencyText; //外币币种    private TextView ownerText; //合同责任人    private TextView udownerdeptText; //责任部门    private TextView udownercrewText; //责任科室    private TextView cupuragentText; //合同提出人    private TextView udrecorddateText; //提出日期    private TextView udrequestdeptText; //提出部门    private TextView udrequestcrewText; //提出科室    private ImageView fkjhImageView; //付款计划    private ImageView wdImageView; //文档    private ImageView hthImageView; //合同行    private ImageView sqjlImageView; //审批记录    private Button workflowBtn;    private PURCHVIEW purchview;    private Animation rotate;    @Override    protected void beforeInit() {        super.beforeInit();        purchview = (PURCHVIEW) getIntent().getSerializableExtra("purchview");    }    @Override    protected int getContentViewLayoutID() {        return R.layout.activity_purchview_workorder;    }    @Override    protected void initView(Bundle savedInstanceState) {        contractnumText = (TextView) findViewById(R.id.contractnum_text_id);        udcontractnumText = (TextView) findViewById(R.id.udcontractnum_text_id);        descriptionText = (TextView) findViewById(R.id.description_text_id);        udclassText = (TextView) findViewById(R.id.udclass_text_id);        revisionnumText = (TextView) findViewById(R.id.revisionnum_text_id);        udtype2Text = (TextView) findViewById(R.id.udtype2_text_id);        statusdescText = (TextView) findViewById(R.id.statusdesc_text_id);        udcontentText = (TextView) findViewById(R.id.udcontent_text_id);        uddirectionText = (TextView) findViewById(R.id.uddirection_text_id);        udpayterm1Text = (TextView) findViewById(R.id.udpayterm1_text_id);        udiprownerText = (TextView) findViewById(R.id.udiprowner_text_id);        signdateText = (TextView) findViewById(R.id.signdate_text_id);        startdateText = (TextView) findViewById(R.id.startdate_text_id);        enddateText = (TextView) findViewById(R.id.enddate_text_id);        jbxxImageView = (ImageView) findViewById(R.id.jbxx_kz_imageview_id);        jbxxLinearLayout = (LinearLayout) findViewById(R.id.linearLayout_id);        jbxxView = (View) findViewById(R.id.jbxx_view_id);        rotate = AnimationUtils.loadAnimation(this, R.anim.arrow_rotate);//创建动画        totalbasecostText = (TextView) findViewById(R.id.totalbasecost_text_id);        cutotalcostText = (TextView) findViewById(R.id.cutotalcost_text_id);        cucurrencyText = (TextView) findViewById(R.id.cucurrency_text_id);        ownerText = (TextView) findViewById(R.id.owner_text_id);        udownerdeptText = (TextView) findViewById(R.id.udownerdept_text_id);        udownercrewText = (TextView) findViewById(R.id.udownercrew_text_id);        cupuragentText = (TextView) findViewById(R.id.displayname_text_id);        udrecorddateText = (TextView) findViewById(R.id.udrecorddate_text_id);        udrequestdeptText = (TextView) findViewById(R.id.udrequestdept_text_id);        udrequestcrewText = (TextView) findViewById(R.id.udrequestcrew_text_id);        fkjhImageView = (ImageView) findViewById(R.id.fkjh_image_id);        wdImageView = (ImageView) findViewById(R.id.wd_imageview_id);        hthImageView = (ImageView) findViewById(R.id.hth_text_id);        sqjlImageView = (ImageView) findViewById(R.id.sqjl_imageview_id);        workflowBtn = (Button) findViewById(R.id.workflow_btn_id);        showData();    }    //展示界面数据    private void showData() {        contractnumText.setText(JsonUnit.convertStrToArray(purchview.getCONTRACTNUM())[0]);        udcontractnumText.setText(JsonUnit.convertStrToArray(purchview.getUDCONTRACTNUM())[0]);        descriptionText.setText(JsonUnit.convertStrToArray(purchview.getDESCRIPTION())[0]);        udclassText.setText(JsonUnit.convertStrToArray(purchview.getUDCLASS())[0]);        revisionnumText.setText(JsonUnit.convertStrToArray(purchview.getREVISIONNUM())[0]);        udtype2Text.setText(JsonUnit.convertStrToArray(purchview.getUDTYPE2())[0]);        statusdescText.setText(JsonUnit.convertStrToArray(purchview.getSTATUSDESC())[0]);        udcontentText.setText(JsonUnit.convertStrToArray(purchview.getUDCONTENT())[0]);        uddirectionText.setText(JsonUnit.convertStrToArray(purchview.getUDDIRECTION())[0]);        udpayterm1Text.setText((JsonUnit.convertStrToArray(purchview.getUDPAYTERM1())[0]));        udiprownerText.setText(JsonUnit.convertStrToArray(purchview.getUDIPROWNER())[0]);        signdateText.setText(JsonUnit.strToDateString(JsonUnit.convertStrToArray(purchview.getSIGNDATE())[0]));        startdateText.setText(JsonUnit.strToDateString(JsonUnit.convertStrToArray(purchview.getSTARTDATE())[0]));        enddateText.setText(JsonUnit.strToDateString(JsonUnit.convertStrToArray(purchview.getENDDATE())[0]));        totalbasecostText.setText(JsonUnit.convertStrToArray(purchview.getTOTALBASECOST())[0]);        cutotalcostText.setText(JsonUnit.convertStrToArray(purchview.getCUTOTALCOST())[0]);        cucurrencyText.setText(JsonUnit.convertStrToArray(purchview.getCUCURRENCY())[0]);        ownerText.setText(JsonUnit.convertStrToArray(purchview.getOWNER())[0]);        udownerdeptText.setText(JsonUnit.convertStrToArray(purchview.getUDOWNERDEPT())[0]);        udownercrewText.setText(JsonUnit.convertStrToArray(purchview.getUDOWNERCREW())[0]);        cupuragentText.setText(JsonUnit.convertStrToArray(purchview.getCUPURAGENT())[0]);        udrecorddateText.setText(JsonUnit.strToDateString(JsonUnit.convertStrToArray(purchview.getUDRECORDDATE())[0]));        udrequestdeptText.setText(JsonUnit.convertStrToArray(purchview.getUDREQUESTDEPT())[0]);        udrequestcrewText.setText(JsonUnit.convertStrToArray(purchview.getUDREQUESTCREW())[0]);        jbxxImageView.setOnClickListener(jbxxImageViewOnClickListener);        fkjhImageView.setOnClickListener(fkjhImageViewOnClickListener);        wdImageView.setOnClickListener(wdImageViewOnClickListener);        hthImageView.setOnClickListener(hthImageViewOnClickListener);        sqjlImageView.setOnClickListener(sqjlImageViewOnClickListener);        workflowBtn.setOnClickListener(workflowBtnOnClickListener);        onClickListener();    }    //设置事件监听    private void onClickListener() {        if (JsonUnit.convertStrToArray(purchview.getSIGNDATE())[1].equals(GlobalConfig.NOTREADONLY)) {            Drawable nav_up = getResources().getDrawable(R.drawable.ic_data);            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());            signdateText.setCompoundDrawablesWithIntrinsicBounds(null, null, nav_up, null);            signdateText.setOnClickListener(DataOnClickListener);        }        if (JsonUnit.convertStrToArray(purchview.getSTARTDATE())[1].equals(GlobalConfig.NOTREADONLY)) {            Drawable nav_up = getResources().getDrawable(R.drawable.ic_data);            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());            startdateText.setCompoundDrawablesWithIntrinsicBounds(null, null, nav_up, null);            startdateText.setOnClickListener(DataOnClickListener);        }        if (JsonUnit.convertStrToArray(purchview.getENDDATE())[1].equals(GlobalConfig.NOTREADONLY)) {            Drawable nav_up = getResources().getDrawable(R.drawable.ic_data);            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());            enddateText.setCompoundDrawablesWithIntrinsicBounds(null, null, nav_up, null);            enddateText.setOnClickListener(DataOnClickListener);        }    }    private View.OnClickListener DataOnClickListener = new View.OnClickListener() {        @Override        public void onClick(final View view) {            final AlertDialog dialog = new AlertDialog.Builder(PurchviewActivity.this).create();            dialog.show();            DatePicker picker = new DatePicker(PurchviewActivity.this);            picker.setDate(DataUtils.getYear(), DataUtils.getMonths());            picker.setMode(DPMode.SINGLE);            picker.setFestivalDisplay(false);            picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {                @Override                public void onDatePicked(String date) {                    int i = view.getId();                    if (i == R.id.signdate_text_id) { //签订日期                        signdateText.setText(date);                    } else if (i == R.id.startdate_text_id) {//开始日期                        startdateText.setText(date);                    } else if (i == R.id.enddate_text_id) {//结束日期                        enddateText.setText(date);                    }                    dialog.dismiss();                }            });            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);            dialog.getWindow().setContentView(picker, params);            dialog.getWindow().setGravity(Gravity.CENTER);        }    };    //基本信息    private View.OnClickListener jbxxImageViewOnClickListener = new View.OnClickListener() {        @Override        public void onClick(View view) {            if (startAnaim()) {                jbxxLinearLayout.setVisibility(View.GONE);                jbxxView.setVisibility(View.GONE);            } else {                jbxxLinearLayout.setVisibility(View.VISIBLE);                jbxxView.setVisibility(View.VISIBLE);            }        }    };    //启动动画    private boolean startAnaim() {        rotate.setInterpolator(new LinearInterpolator());//设置为线性旋转        Log.e(TAG, "b=" + !rotate.getFillAfter());        rotate.setFillAfter(!rotate.getFillAfter());//每次都取相反值，使得可以不恢复原位的旋转        jbxxImageView.startAnimation(rotate);        return rotate.getFillAfter();    }    @Override    protected String getSubTitle() {        return getString(R.string.htxq_text);    }    //付款计划    private View.OnClickListener fkjhImageViewOnClickListener = new View.OnClickListener() {        @Override        public void onClick(View view) {            Intent intent = new Intent(PurchviewActivity.this, ContractpayActivity.class);            intent.putExtra("contractnum", JsonUnit.convertStrToArray(purchview.getCONTRACTNUM())[0]);            intent.putExtra("contractrev", JsonUnit.convertStrToArray(purchview.getREVISIONNUM())[0]);            intent.putExtra("title", getResources().getString(R.string.fkjh_text));            startActivityForResult(intent, 0);        }    };    //文档    private View.OnClickListener wdImageViewOnClickListener = new View.OnClickListener() {        @Override        public void onClick(View view) {            Intent intent = new Intent(PurchviewActivity.this, DoclinksActivity.class);            intent.putExtra("ownertable", GlobalConfig.PURCHVIEW_NAME);            intent.putExtra("ownerid", JsonUnit.convertStrToArray(purchview.getCONTRACTID())[0]);            intent.putExtra("title", getResources().getString(R.string.wd_text));            startActivityForResult(intent, 0);        }    };    //合同行    private View.OnClickListener hthImageViewOnClickListener = new View.OnClickListener() {        @Override        public void onClick(View view) {            Intent intent = new Intent(PurchviewActivity.this, ContractlineActivity.class);            intent.putExtra("contractnum", JsonUnit.convertStrToArray(purchview.getCONTRACTNUM())[0]);            intent.putExtra("contractrev", JsonUnit.convertStrToArray(purchview.getREVISIONNUM())[0]);            intent.putExtra("title", getResources().getString(R.string.hth_text));            startActivityForResult(intent, 0);        }    };    //审批记录    private View.OnClickListener sqjlImageViewOnClickListener = new View.OnClickListener() {        @Override        public void onClick(View view) {            Intent intent = new Intent(PurchviewActivity.this, WftransactionActivity.class);            intent.putExtra("ownertable", GlobalConfig.PURCHVIEW_NAME);            intent.putExtra("ownerid", JsonUnit.convertStrToArray(purchview.getCONTRACTID())[0]);            startActivityForResult(intent, 0);        }    };    //审批    private View.OnClickListener workflowBtnOnClickListener = new View.OnClickListener() {        @Override        public void onClick(View view) {            update(JsonUnit.djpurchviewData(JsonUnit.convertStrToArray(purchview.getCONTRACTID())[0], signdateText.getText().toString(), startdateText.getText().toString(), enddateText.getText().toString(), udcontractnumText.getText().toString(), AccountUtils.getpersonId(PurchviewActivity.this), GlobalConfig.CONTPURCH_APPID));        }    };    //流程启动    private void PostStart(String ownertable, String ownerid, String appid, String userid) {        Log.e(TAG, "ownertable=" + ownertable + ",ownerid=" + ownerid + ",appid=" + appid + ",userid=" + userid);        Rx2AndroidNetworking.post(GlobalConfig.HTTP_URL_START_WORKFLOW)                .addBodyParameter("ownertable", ownertable)                .addBodyParameter("ownerid", ownerid)                .addBodyParameter("appid", appid)                .addBodyParameter("userid", userid)                .build().getStringObservable()                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取数据列表的请求结果                .doOnNext(new Consumer<String>() {                    @Override                    public void accept(@NonNull String string) throws Exception {                    }                })                .subscribeOn(Schedulers.io())                .observeOn(AndroidSchedulers.mainThread())                .subscribe(new Consumer<String>() {                    @Override                    public void accept(@NonNull String s) throws Exception {                        R_WORKFLOW workflow = new Gson().fromJson(s, R_WORKFLOW.class);                        if (workflow.getErrcode().equals(GlobalConfig.WORKFLOW_106)) {                            R_APPROVE r_approve = new Gson().fromJson(s, R_APPROVE.class);                            showDialog(r_approve.getResult());                        } else {                            showMiddleToast(PurchviewActivity.this, workflow.getErrmsg());                        }                    }                }, new Consumer<Throwable>() {                    @Override                    public void accept(@NonNull Throwable throwable) throws Exception {                        showMiddleToast(PurchviewActivity.this, getString(R.string.spsb_text));                    }                });    }    private void PostApprove(String ownertable, String ownerid, String memo, String selectWhat, String userid) {        Rx2AndroidNetworking                .post(GlobalConfig.HTTP_URL_APPROVE_WORKFLOW)                .addBodyParameter("ownertable", ownertable)                .addBodyParameter("ownerid", ownerid)                .addBodyParameter("memo", memo)                .addBodyParameter("selectWhat", selectWhat)                .addBodyParameter("userid", userid)                .build()                .getStringObservable()                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取数据列表的请求结果                .doOnNext(new Consumer<String>() {                    @Override                    public void accept(@NonNull String string) throws Exception {                    }                })                .subscribeOn(Schedulers.io())                .observeOn(AndroidSchedulers.mainThread())                .subscribe(new Consumer<String>() {                    @Override                    public void accept(@NonNull String s) throws Exception {                        Log.e(TAG, "审批=" + s);                        R_WORKFLOW workflow = new Gson().fromJson(s, R_WORKFLOW.class);                        showMiddleToast(PurchviewActivity.this, workflow.getErrmsg());                    }                }, new Consumer<Throwable>() {                    @Override                    public void accept(@NonNull Throwable throwable) throws Exception {                        showMiddleToast(PurchviewActivity.this, getString(R.string.spsb_text));                    }                });    }    //弹出对话框    public void showDialog(List<R_APPROVE.Result> results) {//        ConfirmDialog.Builder dialog = new ConfirmDialog.Builder(this);        dialog.setTitle("审批")                .setData(results)                .setPositiveButton("确定", new ConfirmDialog.Builder.cOnClickListener() {                    @Override                    public void cOnClickListener(DialogInterface dialogInterface, R_APPROVE.Result result, String memo) {                        dialogInterface.dismiss();                        PostApprove(GlobalConfig.PURCHVIEW_NAME, JsonUnit.convertStrToArray(purchview.getCONTRACTID())[0], memo, result.getIspositive(), AccountUtils.getpersonId(PurchviewActivity.this));                    }                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {            @Override            public void onClick(DialogInterface dialogInterface, int i) {                dialogInterface.dismiss();            }        }).create().show();    }    //更新操作    private void update(String data) {        Log.e(TAG, "data=" + data);        Rx2AndroidNetworking                .post(GlobalConfig.HTTP_URL_SEARCH)                .addBodyParameter("data", data) //数据                .build()                .getStringObservable()                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取数据列表的请求结果                .doOnNext(new Consumer<String>() {                    @Override                    public void accept(@NonNull String string) throws Exception {                    }                })                .subscribeOn(Schedulers.io())                .observeOn(AndroidSchedulers.mainThread())                .subscribe(new Consumer<String>() {                    @Override                    public void accept(@NonNull String s) throws Exception {                        Log.e(TAG, "s=" + s);                        R_WORKFLOW r = new Gson().fromJson(s, R_WORKFLOW.class);                        if (r.getErrcode().equals(GlobalConfig.GETDATASUCCESS)) {                            PostStart(GlobalConfig.PURCHVIEW_NAME, JsonUnit.convertStrToArray(purchview.getCONTRACTID())[0], GlobalConfig.CONTPURCH_APPID, AccountUtils.getpersonId(PurchviewActivity.this));                        } else {                            showMiddleToast(PurchviewActivity.this, r.getErrmsg());                        }                    }                }, new Consumer<Throwable>() {                    @Override                    public void accept(@NonNull Throwable throwable) throws Exception {                        showMiddleToast(PurchviewActivity.this, getString(R.string.spsb_text));                    }                });    }}