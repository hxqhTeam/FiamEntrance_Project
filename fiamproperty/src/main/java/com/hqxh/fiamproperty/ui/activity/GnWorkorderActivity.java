package com.hqxh.fiamproperty.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.base.BaseTitleActivity;
/**国内出差详情**/
public class GnWorkorderActivity extends BaseTitleActivity {


    /**信息展示**/
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

    private TextView ccrText; //出差人

    private ImageView jbxxImageView;  //基本信息


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_gn_workorder;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected String getSubTitle() {
        return getString(R.string.gncc_detail_text);
    }


}
