package com.hqxh.fiamproperty.ui.activity;

import android.os.Bundle;


import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.base.BaseTitleActivity;
//物资出门详情

public class GrDetailsActivity extends BaseTitleActivity {
    private static String TAG = "GrDetailsActivity ";


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_grdetails;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected String getSubTitle() {
        return getString(R.string.wzcmxq_text);
    }
}
