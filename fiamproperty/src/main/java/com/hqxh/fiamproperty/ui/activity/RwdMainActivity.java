package com.hqxh.fiamproperty.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.base.BaseActivity;
import com.hqxh.fiamproperty.constant.GlobalConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**任务单**/
public class          RwdMainActivity extends BaseActivity {

    Toolbar mToolbar;
    /**
     * 标题
     **/
    TextView titleTextView;

    /**
     * GridView
     **/
    GridView gridView;

    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    // 图片封装为一个数组
    private int[] icon = null;
    private String[] iconName = null;


    @Override
    protected int getContentViewLayoutID() {

        return R.layout.activity_home;

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        titleTextView = (TextView) findViewById(R.id.title_text);
        gridView = (GridView) findViewById(R.id.gridview_id);

        initToolbar();

        isShowPage();

        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        getData();
        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.item_grid_view, from, to);
        //配置适配器
        gridView.setAdapter(sim_adapter);
        gridView.setOnItemClickListener(gridViewOnItemClickListener);
    }


    private void initToolbar() {
        if (mToolbar != null) {
            mToolbar.setTitle("");
            titleTextView.setText(R.string.rwd_text);
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


    //判断需要显示的页面
    private void isShowPage() {

        icon = new int[]{R.drawable.ic_syrwd, R.drawable.ic_szrwd,
                R.drawable.ic_wzlld, R.drawable.ic_djrwd, R.drawable.ic_rysq,R.drawable.ic_qtrwd};
        iconName = getResources().getStringArray(R.array.rwd_text);

    }

    //设置数据
    public List<Map<String, Object>> getData() {
        //cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }


    private AdapterView.OnItemClickListener gridViewOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = null;
            switch (i) {
                case 0:  //试验任务单
                    intent = new Intent(RwdMainActivity.this, RwdActivity.class);
                    intent.putExtra("titleRec", R.string.syrwd_text);
                    intent.putExtra("appid", GlobalConfig.TOSY_APPID);
                    startActivityForResult(intent, 0);
                    break;
                case 1:  //试制任务单
                    intent = new Intent(RwdMainActivity.this, RwdActivity.class);
                    intent.putExtra("titleRec", R.string.szrwd_text);
                    intent.putExtra("appid", GlobalConfig.TOSZ_APPID);
                    startActivityForResult(intent, 0);
                    break;
                case 2:  //物资领料单
                    intent = new Intent(RwdMainActivity.this, RwdActivity.class);
                    intent.putExtra("titleRec", R.string.wzlld_text);
                    intent.putExtra("appid", GlobalConfig.TOLL_APPID);
                    startActivityForResult(intent, 0);
                    break;
                case 3:  //调件任务单
                    intent = new Intent(RwdMainActivity.this, RwdActivity.class);
                    intent.putExtra("titleRec", R.string.djrwd_text);
                    intent.putExtra("appid", GlobalConfig.TODJ_APPID);
                    startActivityForResult(intent, 0);
                    break;
                case 4:  //燃油申请单
                    intent = new Intent(RwdMainActivity.this, RwdActivity.class);
                    intent.putExtra("titleRec", R.string.rysqd_text);
                    intent.putExtra("appid", GlobalConfig.TOOIL_APPID);
                    startActivityForResult(intent, 0);
                    break;
                case 5:  //其它任务单
                    intent = new Intent(RwdMainActivity.this, RwdActivity.class);
                    intent.putExtra("titleRec", R.string.qtrwd_text);
                    intent.putExtra("appid", GlobalConfig.TOQT_APPID);
                    startActivityForResult(intent, 0);
                    break;

            }

        }
    };
}
