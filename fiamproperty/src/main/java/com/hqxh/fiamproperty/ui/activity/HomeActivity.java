package com.hqxh.fiamproperty.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.api.HttpManager;
import com.hqxh.fiamproperty.base.BaseActivity;
import com.hqxh.fiamproperty.constant.GlobalConfig;
import com.hqxh.fiamproperty.model.R_PERSONS;
import com.hqxh.fiamproperty.model.R_PERSONS.PERSION;
import com.hqxh.fiamproperty.model.R_Wfassignemt;
import com.hqxh.fiamproperty.ui.widget.MaterialBadgeTextView;
import com.hqxh.fiamproperty.unit.AccountUtils;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
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
     * 待办任务
     **/
    private TextView dbBadgeButton;
    private MaterialBadgeTextView badgeText;
    /**
     * 已办任务
     **/
    private TextView ybBadgeButton;
    /**
     * 出差申请
     **/
    private TextView ccsqBadgeButton;
    /**
     * 出门管理
     **/
    private TextView cmglBadgeButton;
    /**
     * 采购申请
     **/
    private TextView cgsqBadgeButton;
    /**
     * 任务单
     **/
    private TextView rwdBadgeButton;
    /**
     * 合同
     **/
    private TextView htBadgeButton;
    /**
     * 付款验收
     **/
    private TextView fkysBadgeButton;
    /**
     * 需款计划
     **/
    private TextView xkjhBadgeButton;
    /**
     * 报销
     **/
    private TextView bxBadgeButton;


    private String identity;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main_home;

    }

    @Override
    protected void beforeInit() {
        super.beforeInit();
        showLoadingDialog(getString(R.string.loading_hint));
//        identity = "_220724199011260619";
//        identity = JsonUnit.getIdentity(AccountUtils.getPerson(this));
//        if (null == identity) {
//            showMiddleToast(this, "无法识别身份");
//            finish();
//        }
//        LoginAndCount();

//        Login();
        getData();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        titleTextView = (TextView) findViewById(R.id.title_text);

        dbBadgeButton = (TextView) findViewById(R.id.db_task_text_id);
        badgeText = (MaterialBadgeTextView) findViewById(R.id.badge_text_id);
        ybBadgeButton = (TextView) findViewById(R.id.yb_task_id);
        ccsqBadgeButton = (TextView) findViewById(R.id.ccsq_text_id);
        cmglBadgeButton = (TextView) findViewById(R.id.cmgl_text_id);
        cgsqBadgeButton = (TextView) findViewById(R.id.cgsq_text_id);
        rwdBadgeButton = (TextView) findViewById(R.id.rwd_text_id);
        htBadgeButton = (TextView) findViewById(R.id.ht_text_id);
        fkysBadgeButton = (TextView) findViewById(R.id.fkys_text_id);
        xkjhBadgeButton = (TextView) findViewById(R.id.xkjh_text_id);
        bxBadgeButton = (TextView) findViewById(R.id.bx_text_id);
        initToolbar();

        dbBadgeButton.setOnClickListener(onClickListener);
        ybBadgeButton.setOnClickListener(onClickListener);
        ccsqBadgeButton.setOnClickListener(onClickListener);
        cmglBadgeButton.setOnClickListener(onClickListener);
        cgsqBadgeButton.setOnClickListener(onClickListener);
        rwdBadgeButton.setOnClickListener(onClickListener);
        htBadgeButton.setOnClickListener(onClickListener);
        fkysBadgeButton.setOnClickListener(onClickListener);
        xkjhBadgeButton.setOnClickListener(onClickListener);
        bxBadgeButton.setOnClickListener(onClickListener);


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


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            int i = view.getId();
            if (i == R.id.db_task_text_id) {
                intent = new Intent(HomeActivity.this, ActiveTaskActivity.class);
                intent.putExtra("assignstatus", "ACTIVE");
                intent.putExtra("mark", DB_CODE);
                intent.putExtra("title", getResources().getString(R.string.db_task_text));
                startActivityForResult(intent, 0);

            } else if (i == R.id.yb_task_id) {
                intent = new Intent(HomeActivity.this, ActiveTaskActivity.class);
                intent.putExtra("assignstatus", "COMPLETE");
                intent.putExtra("title", getResources().getString(R.string.yb_task_text));
                intent.putExtra("mark", YB_CODE);
                startActivityForResult(intent, 0);

            } else if (i == R.id.ccsq_text_id) {
                intent = new Intent(HomeActivity.this, WorkorderActivity.class);
                startActivityForResult(intent, 0);

            } else if (i == R.id.cmgl_text_id) {
                intent = new Intent(HomeActivity.this, GrActivity.class);
                startActivityForResult(intent, 0);

            } else if (i == R.id.cgsq_text_id) {
                intent = new Intent(HomeActivity.this, PrMainActivity.class);
                startActivityForResult(intent, 0);

            } else if (i == R.id.rwd_text_id) {
                intent = new Intent(HomeActivity.this, RwdMainActivity.class);
                startActivityForResult(intent, 0);

            } else if (i == R.id.ht_text_id) {
                intent = new Intent(HomeActivity.this, HtActivity.class);
                startActivityForResult(intent, 0);

            } else if (i == R.id.fkys_text_id) {
                intent = new Intent(HomeActivity.this, FkActivity.class);
                startActivityForResult(intent, 0);

            } else if (i == R.id.xkjh_text_id) {
                intent = new Intent(HomeActivity.this, XkjhActivity.class);
                startActivityForResult(intent, 0);

            } else if (i == R.id.bx_text_id) {
                intent = new Intent(HomeActivity.this, BxActivity.class);
                startActivityForResult(intent, 0);

            }
        }
    };


    //连接系统测试
    private void Login() {

        String imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
                .getDeviceId();

        Rx2AndroidNetworking.post(GlobalConfig.HTTP_URL_LOGIN)
                .addBodyParameter("username", identity)
                .addBodyParameter("imei", imei)
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


    /*＊获取待办任务的数量**/

    /**
     * 获取数据
     **/
    private void getData() {
        String data = HttpManager.getWFASSIGNMENTUrl("", AccountUtils.getpersonId(this), "ACTIVE", 1, 10);
        Rx2AndroidNetworking.post(GlobalConfig.HTTP_URL_SEARCH)
                .addBodyParameter("data", data)
                .build()
                .getObjectObservable(R_Wfassignemt.class) // 发起获取数据列表的请求，并解析到R_Wfassignemt
                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取数据列表的请求结果
                .doOnNext(new Consumer<R_Wfassignemt>() {
                    @Override
                    public void accept(@NonNull R_Wfassignemt RWfassignemt) throws Exception {
                    }
                })

                .map(new Function<R_Wfassignemt, R_Wfassignemt.ResultBean>() {
                    @Override
                    public R_Wfassignemt.ResultBean apply(@NonNull R_Wfassignemt RWfassignemt) throws Exception {

                        return RWfassignemt.getResult();
                    }
                })
                .map(new Function<R_Wfassignemt.ResultBean, String>() {
                    @Override
                    public String apply(@NonNull R_Wfassignemt.ResultBean resultBean) throws Exception {
                        return resultBean.getTotalresult();
                    }

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        dismissLoadingDialog();
                        badgeText.setVisibility(View.VISIBLE);
                        if (s.equals("0")) {
                            badgeText.setBadgeCount(0, true);
                        } else {
                            badgeText.setBadgeCount(Integer.valueOf(s));
                        }


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        dismissLoadingDialog();
                    }
                });
    }


    /**
     * 测试登录与获取任务数
     **/
    private void LoginAndCount() {
        String imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
                .getDeviceId();
        Rx2AndroidNetworking.post(GlobalConfig.HTTP_URL_LOGIN)
                .addBodyParameter("username", identity)
                .addBodyParameter("imei", imei)
                .build()
                .getObjectObservable(R_PERSONS.class) // 发起网络请求，并解析到R_PERSONS
                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取R_PERSONS列表的请求结果
                .doOnNext(new Consumer<R_PERSONS>() {
                    @Override
                    public void accept(@NonNull R_PERSONS r_persons) throws Exception {
                    }
                })
                .observeOn(Schedulers.io()) // 回到 io 线程去处理获取R_Wfassignemt的请求
                .flatMap(new Function<R_PERSONS, Observable<R_Wfassignemt>>() {
                    @Override
                    public Observable<R_Wfassignemt> apply(@NonNull R_PERSONS r_persons) throws Exception {

                        if (r_persons.getErrcode().equals(GlobalConfig.LOGINSUCCESS) || r_persons.getErrcode().equals(GlobalConfig.CHANGEIMEI)) {//登录成功
                            String persion = r_persons.getResult();
                            if (null != persion) {
                                PERSION persion1 = new Gson().fromJson(persion, PERSION.class);
                                AccountUtils.setLoginDetails(HomeActivity.this, persion1);
                                String data = HttpManager.getWFASSIGNMENTUrl("", persion1.getPERSONID(), "ACTIVE", 1, 10);
                                return Rx2AndroidNetworking.post(GlobalConfig.HTTP_URL_SEARCH)
                                        .addBodyParameter("data", data)
                                        .build()
                                        .getObjectObservable(R_Wfassignemt.class);
                            } else {
                                finish();
                            }
                        }
                        return null;
                    }


                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<R_Wfassignemt>() {
                               @Override
                               public void accept(@NonNull R_Wfassignemt r_Wfassignemt) throws Exception {
                                   R_Wfassignemt.ResultBean resultbean = r_Wfassignemt.getResult();
                                   Log.e(TAG, "s=" + resultbean.getTotalresult());
                                   dismissLoadingDialog();
                                   badgeText.setVisibility(View.VISIBLE);
                                   if (resultbean.getTotalresult().equals("0")) {
                                       badgeText.setBadgeCount(0, true);
                                   } else {
                                       badgeText.setBadgeCount(Integer.valueOf(resultbean.getTotalresult()));
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(@NonNull Throwable throwable) throws Exception {
                                   dismissLoadingDialog();
                               }
                           }
                );
    }


    //重新启动
    @Override
    protected void onRestart() {
        super.onRestart();
        getData();
    }
}
