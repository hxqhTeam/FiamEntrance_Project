package com.hqxh.fiamproperty.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.base.BaseTitleActivity;
import com.hqxh.fiamproperty.model.R_GR.GR;
import com.hqxh.fiamproperty.ui.adapter.BaseQuickAdapter;
import com.hqxh.fiamproperty.ui.adapter.GrAdapter;
import com.hqxh.fiamproperty.unit.JsonUnit;

import java.io.Serializable;
import java.util.List;
//物资出门详情

public class GrDetailsActivity extends BaseTitleActivity {
    private static String TAG = "GrDetailsActivity ";


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
    ImageView spjl_text;//审批记录

    private GR gr;


    @Override
    protected void beforeInit() {
        super.beforeInit();
        gr= (GR) getIntent().getExtras().getSerializable("gr");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_grdetails;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        grnum_text=(TextView)findViewById(R.id.grnum_text);
        grnum2_text=(TextView)findViewById(R.id.grnum2_text);
        location_text=(TextView)findViewById(R.id.location_text);
        location2_text=(TextView)findViewById(R.id.location2_text);
        reason_text=(TextView)findViewById(R.id.reason_text);
        type_text=(TextView)findViewById(R.id.type_text);
        schedulardate_text=(TextView)findViewById(R.id.schedulardate_text);
        displayname_text=(TextView)findViewById(R.id.displayname_text);
        phone_text=(TextView) findViewById(R.id.phone_text);
        cudept_text=(TextView)findViewById(R.id.cudept_text);
        cucrew_text=(TextView)findViewById(R.id.cucrew_text);
        description_text=(TextView)findViewById(R.id.description_text);
        enterdate_text=(TextView)findViewById(R.id.enterdate_text);
        wzmx_text=(ImageView)findViewById(R.id.wzmx_text);
        zcmx_text=(ImageView)findViewById(R.id.zcmx_text);
        spjl_text=(ImageView)findViewById(R.id.spjl_text);


        showData();



    }

    private void showData() {
        grnum_text.setText(JsonUnit.convertStrToArray(gr.getGRNUM())[0]);
        grnum2_text.setText(JsonUnit.convertStrToArray(gr.getDESCRIPTION())[0]);
        location_text.setText(JsonUnit.convertStrToArray(gr.getLOCATIONDESCRIPTION())[0]);
        location2_text.setText(JsonUnit.convertStrToArray(gr.getLOCATION2DESCRIPTION())[0]);
        reason_text.setText(JsonUnit.convertStrToArray(gr.getREASON())[0]);
        type_text .setText(JsonUnit.convertStrToArray(gr.getTYPE())[0]);
        schedulardate_text.setText(JsonUnit.convertStrToArray(gr.getSCHEDULARDATE())[0]);
        displayname_text.setText(JsonUnit.convertStrToArray(gr.getENTERBY())[0]);
        phone_text.setText(JsonUnit.convertStrToArray(gr.getPHONE())[0]);
        cudept_text.setText(JsonUnit.convertStrToArray(gr.getCUDEPT())[0]);
        cucrew_text.setText(JsonUnit.convertStrToArray(gr.getCUCREW())[0]);
        description_text.setText(JsonUnit.convertStrToArray(gr.getSTATUSDESC())[0]);
        enterdate_text.setText(JsonUnit.convertStrToArray(gr.getENTERDATE())[0]);


        wzmx_text.setOnClickListener(wzmx_textOnClickListener);
       //zcmx_text.setOnClickListener(zcmx_textOnClickListener);
    //    spjl_text.setOnClickListener(spjl_textOnClickListener);

    }

    @Override
    protected String getSubTitle() {
        return getString(R.string.wzcmxq_text);
    }



    private View.OnClickListener wzmx_textOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(GrDetailsActivity.this, WzmxListActivity.class);
            intent.putExtra("grnum",JsonUnit.convertStrToArray(gr.getGRNUM())[0]);
            startActivityForResult(intent, 0);

        }
    };
    /*private View.OnClickListener zcmx_textOnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

        }
    };*/
  /*  private View.OnClickListener spjl_textOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(GrDetailsActivity.this, spjlListActivity.class);
            startActivityForResult(intent, 0);

        }
    };*/
}

