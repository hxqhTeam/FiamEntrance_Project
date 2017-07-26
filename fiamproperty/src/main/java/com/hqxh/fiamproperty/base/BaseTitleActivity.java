package com.hqxh.fiamproperty.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.ui.SystemBarTintManager;


/**
 * Activity标题基类
 * <p>
 * Author: tc
 * Email: 278233503@qq.com
 * Date: 2017-07-24  14:21
 */

public abstract class BaseTitleActivity extends BaseActivity {
    private static final String TAG = "BaseTitleActivity";
    Toolbar mToolbar;
    private TextView mTitleName;


    /**
     * 设置标题文本
     */
    protected abstract String getSubTitle();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        mTitleName=(TextView)findViewById(R.id.title_text);
        if (getContentViewLayoutID() != 0) {
            initToolbar();
        }


    }

    private void initToolbar() {
        if (mToolbar != null) {
            mToolbar.setTitle("");
            mTitleName.setText(getSubTitle());
            if (isShowBack()) {
                showBack();
            }
        }
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack() {
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    protected boolean isShowBack() {
        return true;
    }
}
