package com.hqxh.fiamproperty.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.base.BaseTitleActivity;
import com.hqxh.fiamproperty.model.R_Workorder.Workorder;
import com.hqxh.fiamproperty.unit.JsonUnit;

/**
 * 国内出差详情
 **/
public class GnWorkorderActivity extends BaseTitleActivity {

    private static final String TAG = "GnWorkorderActivity";
    /**
     * 信息展示
     **/
    private TextView wonumText; //申请单
    private TextView projectidText; //费用号
    private TextView worktypeText; //类型
    private TextView statusText; //状态
    private TextView udtargstartdateText; //开始时间
    private TextView udtargcompdateText; //结束时间
    private TextView udaddress1Text; //目的地
    private TextView udtransport3Text; //是否飞机
    private TextView udremark1Text; //出差原因
    private TextView udtrvcost1Text; //差旅费用
    private TextView udtrvcost2Text; //其它费用
    private TextView udesttotalcostText; //出差费用预算

    private ImageView ccrText; //出差人
    private LinearLayout ccrLinearLayout;

    private ImageView jbxxImageView;  //基本信息

    private TextView udtrv1Text; //团队负责人
    private TextView rdcheadText; //中心分管领导
    private TextView reportedbyText; //申请人
    private TextView cudeptText; //部门
    private TextView reportdateText; //申请日期
    private TextView phonenumText; //电话

    private ImageView sqjlImageView; //审批记录

    private Workorder workorder;

    private Animation rotate;

    @Override
    protected void beforeInit() {
        super.beforeInit();
        workorder = (Workorder) getIntent().getSerializableExtra("workorder");
        Log.e(TAG, "初始化界面前的准备");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_gn_workorder;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        wonumText = (TextView) findViewById(R.id.requireplannum_text_id);
        projectidText = (TextView) findViewById(R.id.projectid_text_id);
        worktypeText = (TextView) findViewById(R.id.worktype_text_id);
        statusText = (TextView) findViewById(R.id.status_text_id);
        udtargstartdateText = (TextView) findViewById(R.id.udtargstartdate_text_id);
        udtargcompdateText = (TextView) findViewById(R.id.udtargcompdate_text_id);
        udaddress1Text = (TextView) findViewById(R.id.udaddress1_text_id);
        udtransport3Text = (TextView) findViewById(R.id.udtransport3_text_id);
        udremark1Text = (TextView) findViewById(R.id.udremark1_text_id);
        udtrvcost1Text = (TextView) findViewById(R.id.udtrvcost1_text_id);
        udtrvcost2Text = (TextView) findViewById(R.id.udtrvcost2_text_id);
        udesttotalcostText = (TextView) findViewById(R.id.udesttotalcost_text_id);

        ccrText = (ImageView) findViewById(R.id.ccr_imageview_id);
        ccrLinearLayout=(LinearLayout)findViewById(R.id.linearLayout_id);
        jbxxImageView = (ImageView) findViewById(R.id.jbxx_kz_imageview_id);

        udtrv1Text = (TextView) findViewById(R.id.udtrv1_text_id);
        rdcheadText = (TextView) findViewById(R.id.rdchead_text_id);
        reportedbyText = (TextView) findViewById(R.id.reportedby_text_id);
        cudeptText = (TextView) findViewById(R.id.cudept_text_id);
        reportdateText = (TextView) findViewById(R.id.reportdate_text_id);
        phonenumText = (TextView) findViewById(R.id.phonenum_text_id);

        sqjlImageView = (ImageView) findViewById(R.id.sqjl_imageview_id);

        showData();
    }

    //展示界面数据
    private void showData() {
        wonumText.setText(JsonUnit.convertStrToArray(workorder.getWONUM())[0] + "," + JsonUnit.convertStrToArray(workorder.getDESCRIPTION())[0]);
        projectidText.setText(JsonUnit.convertStrToArray(workorder.getPROJECTID())[0] + "," + JsonUnit.convertStrToArray(workorder.getFINCNTRLDESC())[0]);
        worktypeText.setText(JsonUnit.convertStrToArray(workorder.getWORKTYPE())[0]);
        statusText.setText(JsonUnit.convertStrToArray(workorder.getSTATUS())[0]);
        udtargstartdateText.setText(JsonUnit.strToDateString(JsonUnit.convertStrToArray(workorder.getUDTARGSTARTDATE())[0]));
        udtargcompdateText.setText(JsonUnit.strToDateString(JsonUnit.convertStrToArray(workorder.getUDTARGCOMPDATE())[0]));
        udaddress1Text.setText(JsonUnit.convertStrToArray(workorder.getUDADDRESS1())[0]);
        udtransport3Text.setText(JsonUnit.convertStrToArray(workorder.getUDESTDUR3())[0]);
        udremark1Text.setText(JsonUnit.convertStrToArray(workorder.getUDREMARK1())[0]);
        udtrvcost1Text.setText(JsonUnit.convertStrToArray(workorder.getUDTRVCOST1())[0]);
        udtrvcost2Text.setText(JsonUnit.convertStrToArray(workorder.getUDTRVCOST2())[0]);
        udesttotalcostText.setText(JsonUnit.convertStrToArray(workorder.getUDESTTOTALCOST())[0]);

        udtrv1Text.setText(JsonUnit.convertStrToArray(workorder.getTEAMLEADER())[0]);
        rdcheadText.setText(JsonUnit.convertStrToArray(workorder.getRDCHEAD())[0]);
        reportedbyText.setText(JsonUnit.convertStrToArray(workorder.getREPORTEDBY())[0]);
        cudeptText.setText(JsonUnit.convertStrToArray(workorder.getCUDEPT())[0]);
        reportdateText.setText(JsonUnit.convertStrToArray(workorder.getREPORTDATE())[0]);
        phonenumText.setText(JsonUnit.convertStrToArray(workorder.getPHONENUM())[0]);

        rotate = AnimationUtils.loadAnimation(this, R.anim.arrow_rotate);//创建动画

        ccrText.setOnClickListener(ccrTextOnClickListener);
        jbxxImageView.setOnClickListener(jbxxImageViewOnClickListener);


    }

    @Override
    protected String getSubTitle() {
        return getString(R.string.gncc_detail_text);
    }


    private View.OnClickListener ccrTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(GnWorkorderActivity.this, PersonrelationActivity.class);
            intent.putExtra("wonum", JsonUnit.convertStrToArray(workorder.getWONUM())[0]);
            startActivityForResult(intent, 0);
        }
    };


    private View.OnClickListener jbxxImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(startAnaim()){
                ccrLinearLayout.setVisibility(View.GONE);
            }else{
                ccrLinearLayout.setVisibility(View.VISIBLE);
            }

        }
    };


    //启动动画
    private boolean startAnaim() {

        rotate.setInterpolator(new LinearInterpolator());//设置为线性旋转

        Log.e(TAG,"b="+!rotate.getFillAfter());
        rotate.setFillAfter(!rotate.getFillAfter());//每次都取相反值，使得可以不恢复原位的旋转

        jbxxImageView.startAnimation(rotate);
        return rotate.getFillAfter();
    }

}
