package com.hqxh.fiamproperty.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.base.BaseActivity;
import com.hqxh.fiamproperty.constant.GlobalConfig;
import com.hqxh.fiamproperty.model.R_PERSONS;
import com.hqxh.fiamproperty.model.R_PERSONS.PERSION;
import com.hqxh.fiamproperty.unit.AccountUtils;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class HomeActivity extends BaseActivity {

    private static final String TAG = "HomeActivity";

    public static final int DB_CODE = 1000;
    public static final int YB_CODE = 1001;
    Toolbar mToolbar;
    /**
     * 标题
     **/
    TextView titleTextView;
    /**搜索**/


    /**
     * GridView
     **/
    GridView gridView;

    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    // 图片封装为一个数组
    private int[] icon = null;
    private String[] iconName = null;
    private String identity;


    @Override
    protected int getContentViewLayoutID() {

        return R.layout.activity_home;

    }

    @Override
    protected void beforeInit() {
        super.beforeInit();
//        identity = JsonUnit.getIdentity(AccountUtils.getPerson(this));
//        if (null == identity) {
//            showMiddleToast(this, "无法识别身份");
//            finish();
//        }
//
//        Login();


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
            titleTextView.setText(R.string.home_title);
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

        icon = new int[]{R.drawable.ic_dbrw, R.drawable.ic_ybrw,
                R.drawable.ic_ccsq, R.drawable.ic_cmgl, R.drawable.ic_cgsq,
                R.drawable.ic_rwd, R.drawable.ic_ht, R.drawable.ic_fkys, R.drawable.ic_xkjh, R.drawable.ic_bx};
        iconName = getResources().getStringArray(R.array.home_text);

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
                case 0:  //待办任务
                    intent = new Intent(HomeActivity.this, ActiveTaskActivity.class);
                    intent.putExtra("mark", DB_CODE);
                    startActivityForResult(intent, 0);
                    break;
                case 1:  //已办任务
                    intent = new Intent(HomeActivity.this, ActiveTaskActivity.class);
                    intent.putExtra("mark", YB_CODE);
                    startActivityForResult(intent, 0);
                    break;
                case 2:  //出差申请
                    intent = new Intent(HomeActivity.this, WorkorderActivity.class);
                    startActivityForResult(intent, 0);
                    break;
                case 3:  //出门管理
                    intent = new Intent(HomeActivity.this, GrActivity.class);
                    startActivityForResult(intent, 0);
                    break;
                case 4:  //采购申请
                    intent = new Intent(HomeActivity.this, PrMainActivity.class);
                    startActivityForResult(intent, 0);
                    break;
                case 5:  //任务单
                    intent = new Intent(HomeActivity.this, RwdMainActivity.class);
                    startActivityForResult(intent, 0);
                    break;
                case 6:  //合同
                    intent = new Intent(HomeActivity.this, HtActivity.class);
                    startActivityForResult(intent, 0);
                    break;
                case 7:  //付款验收
                    intent = new Intent(HomeActivity.this, FkActivity.class);
                    startActivityForResult(intent, 0);
                    break;
                case 8:  //需款计划
                    intent = new Intent(HomeActivity.this, XkjhActivity.class);
                    startActivityForResult(intent, 0);
                    break;
                case 9:  //报销
                    intent = new Intent(HomeActivity.this, BxActivity.class);
                    startActivityForResult(intent, 0);
                    break;

            }

        }
    };


    //连接系统测试
    private void Login() {

        String imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
                .getDeviceId();

        Rx2AndroidNetworking.post(GlobalConfig.HTTP_URL_LOGIN)
                .addQueryParameter("username", identity)
                .addQueryParameter("imei", imei)
                .build()
                .getObjectObservable(R_PERSONS.class) // 发起获取数据列表的请求，并解析到R_Person.class
                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取数据列表的请求结果
                .doOnNext(new Consumer<R_PERSONS>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull R_PERSONS r_PERSON) throws Exception {


                    }
                })

                .map(new Function<R_PERSONS, String>() {
                    @Override
                    public String apply(@io.reactivex.annotations.NonNull R_PERSONS r_PERSON) throws Exception {
                        if (r_PERSON.getErrcode().equals(GlobalConfig.LOGINSUCCESS)) {//登录成功
                        } else if (r_PERSON.getErrcode().equals(GlobalConfig.CHANGEIMEI)) {//登录成功,检测到用户更换手机登录
                        } else if (r_PERSON.getErrcode().equals(GlobalConfig.USERNAMEERROR)) {//用户名密码错误
                        } else {
                        }
                        return r_PERSON.getResult();
                    }

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull String persion) throws Exception {
                        if (null != persion) {
                            PERSION persion1 = new Gson().fromJson(persion, PERSION.class);
                            AccountUtils.setLoginDetails(HomeActivity.this, persion1);
                        } else {
                            finish();
                        }


                    }


                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        showMiddleToast(HomeActivity.this, getString(R.string.unable_to_connect_to_server_login_failed));
                        finish();
                    }
                });


    }


}
