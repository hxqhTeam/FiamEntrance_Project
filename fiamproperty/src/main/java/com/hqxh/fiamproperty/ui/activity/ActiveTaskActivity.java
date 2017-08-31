package com.hqxh.fiamproperty.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.api.HttpManager;
import com.hqxh.fiamproperty.base.BaseListActivity;
import com.hqxh.fiamproperty.constant.GlobalConfig;
import com.hqxh.fiamproperty.model.R_Wfassignemt;
import com.hqxh.fiamproperty.model.R_Wfassignemt.ResultBean;
import com.hqxh.fiamproperty.model.R_Wfassignemt.Wfassignment;
import com.hqxh.fiamproperty.ui.adapter.BaseQuickAdapter;
import com.hqxh.fiamproperty.ui.adapter.WfassignmentAdapter;
import com.hqxh.fiamproperty.unit.AccountUtils;
import com.hqxh.fiamproperty.unit.JsonUnit;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 待办任务的Activity
 **/
public class ActiveTaskActivity extends BaseListActivity {
    private static final String TAG = "ActiveTaskActivity";

    public static final int TASK_REQUESTCODE = 1000; //请求码

    public static final int TASK_RESULTCODE = 1001; //返回码


    private WfassignmentAdapter wfassignmentAdapter;

    private int curpage = 1;
    private int showcount = 20;
    private int totalpage;


    private String assignstatus;
    private String title;

    @Override
    protected void beforeInit() {
        super.beforeInit();
        if (getIntent().hasExtra("assignstatus")) {
            assignstatus = getIntent().getExtras().getString("assignstatus");
        }
        if (getIntent().hasExtra("title")) {
            title = getIntent().getExtras().getString("title");
        }
    }

    @Override
    protected String getSubTitle() {


        return title;
    }


    /**
     * 获取数据
     **/
    private void getData() {
        String data = HttpManager.getWFASSIGNMENTUrl("", AccountUtils.getpersonId(this), assignstatus, curpage, showcount);
        Log.e(TAG, "data=" + data);
        Log.e(TAG, "url=" + GlobalConfig.HTTP_URL_SEARCH);
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

                .map(new Function<R_Wfassignemt, ResultBean>() {
                    @Override
                    public ResultBean apply(@NonNull R_Wfassignemt RWfassignemt) throws Exception {

                        return RWfassignemt.getResult();
                    }
                })
                .map(new Function<ResultBean, List<Wfassignment>>() {
                    @Override
                    public List<Wfassignment> apply(@NonNull ResultBean resultBean) throws Exception {
                        totalpage = Integer.valueOf(resultBean.getTotalpage());
                        return resultBean.getResultlist();
                    }

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Wfassignment>>() {
                    @Override
                    public void accept(@NonNull List<Wfassignment> wfassignments) throws Exception {
                        mPullLoadMoreRecyclerView.setRefreshing(false);
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();

                        if (wfassignments == null || wfassignments.isEmpty()) {
                            notLinearLayout.setVisibility(View.VISIBLE);
                        } else {

                            addData(wfassignments);


                        }
                    }


                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        notLinearLayout.setVisibility(View.VISIBLE);
                        mPullLoadMoreRecyclerView.setRefreshing(false);
                    }
                });
    }


    @Override
    public void onRefresh() {
        curpage = 1;
        wfassignmentAdapter.removeAll(wfassignmentAdapter.getData());
        getData();

    }

    @Override
    public void onLoadMore() {
        if (totalpage == curpage) {
            getLoadMore();
            showMiddleToast(ActiveTaskActivity.this, getResources().getString(R.string.all_data_hint));
        } else {
            curpage++;
            getData();
        }


    }


    private void getLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                });

            }
        }, 1000);

    }


    @Override
    protected void fillData() {
        initAdapter(new ArrayList<Wfassignment>());
        getData();

    }

    @Override
    protected void setOnClick() {
        searchText.setOnClickListener(searchTextOnClickListener);

    }


    /**
     * 获取数据*
     */
    private void initAdapter(final List<Wfassignment> list) {
        wfassignmentAdapter = new WfassignmentAdapter(ActiveTaskActivity.this, R.layout.list_item_task, list);
        mRecyclerView.setAdapter(wfassignmentAdapter);
        wfassignmentAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Wfassignment wfassignment = (Wfassignment) wfassignmentAdapter.getData().get(position);
                String app = JsonUnit.convertStrToArray(wfassignment.getAPP())[0];
                Log.e(TAG, "app=" + app);
                Intent intent = getIntent();
                if (app.equals(GlobalConfig.GRWZ_APPID)) { //出门管理
                    intent.setClass(ActiveTaskActivity.this, GrDetailsActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.TRAVEL_APPID)) {//国内出差
                    intent.setClass(ActiveTaskActivity.this, GnWorkorderActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.TRAVELS_APPID)) {//国外出差
                    intent.setClass(ActiveTaskActivity.this, CgWorkorderActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.JSPR_APPID)) {//技术采购申请
                    intent.setClass(ActiveTaskActivity.this, JsPrActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.SZPR_APPID)) {//试制采购申请
                    intent.setClass(ActiveTaskActivity.this, SzPrActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.PR_APPID)) {//物资采购申请
                    intent.setClass(ActiveTaskActivity.this, WzPrActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.FWPR_APPID)) {//服务采购申请
                    intent.setClass(ActiveTaskActivity.this, FwpaydetailsActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.WPPR_APPID)) {//外培采购申请
                    intent.setClass(ActiveTaskActivity.this, WppaydetailsActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.TOSY_APPID)) {//试验任务单
                    intent.setClass(ActiveTaskActivity.this, SyrwdWorkorderActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.TOSZ_APPID)) {//试制任务单
                    intent.setClass(ActiveTaskActivity.this, SzrwdWorkorderActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.TOLL_APPID)) {//物资领料单
                    intent.setClass(ActiveTaskActivity.this, WzlldWorkorderActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.TODJ_APPID)) {//调件任务单
                    intent.setClass(ActiveTaskActivity.this, DjrwdWorkorderActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.TOOIL_APPID)) {//燃油申请单
                    intent.setClass(ActiveTaskActivity.this, RyrwdWorkorderActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.TOQT_APPID)) {//其它任务单
                    intent.setClass(ActiveTaskActivity.this, QtrwdWorkorderActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.CONTPURCH_APPID)) {//合同
                    intent.setClass(ActiveTaskActivity.this, PurchviewActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.PAYCHECK_APPID)) {//付款验收
                    intent.setClass(ActiveTaskActivity.this, PaycheckActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.PP_APPID)) {//需款计划
                    intent.setClass(ActiveTaskActivity.this, XkplandetailActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.EXPENSES_APPID)) {//差旅报销单
                    intent.setClass(ActiveTaskActivity.this, ExpenseActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.EXPENSE_APPID)) { //备用金报销单
                    intent.setClass(ActiveTaskActivity.this, ByExpenseActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else if (app.equals(GlobalConfig.BO_APPID)) { //报销
                    intent.setClass(ActiveTaskActivity.this, BoActivity.class);
                    intent.putExtra("appid", app);
                    intent.putExtra("ownernum", JsonUnit.convertStrToArray(wfassignment.getOWNERNUM())[0]);
                    intent.putExtra("ownertable", JsonUnit.convertStrToArray(wfassignment.getOWNERTABLE())[0]);
                    startActivityForResult(intent, TASK_REQUESTCODE);
                } else {
                    showMiddleToast1(ActiveTaskActivity.this, getString(R.string.have_not_appove_hint));
                }
            }
        });
    }

    /**
     * 添加数据*
     */
    private void addData(final List<Wfassignment> list) {
        wfassignmentAdapter.addData(list);
    }


    /**
     * 跳转事件
     **/
    private View.OnClickListener searchTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            intent.setClass(ActiveTaskActivity.this, SearchActivity.class);
            startActivityForResult(intent, 0);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TASK_REQUESTCODE:
                if (resultCode == TASK_RESULTCODE) {//刷新界面
                    //显示下拉刷新
                    mPullLoadMoreRecyclerView.setRefreshing(true);
                    wfassignmentAdapter.removeAll(wfassignmentAdapter.getData());
                    if (notLinearLayout.isShown()) {
                        notLinearLayout.setVisibility(View.GONE);
                    }
                    getData();
                }

        }
    }
}
