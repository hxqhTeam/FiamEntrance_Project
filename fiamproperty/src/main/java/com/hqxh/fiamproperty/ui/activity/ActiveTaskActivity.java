package com.hqxh.fiamproperty.ui.activity;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.api.HttpManager;
import com.hqxh.fiamproperty.base.BaseListActivity;
import com.hqxh.fiamproperty.constant.GlobalConfig;
import com.hqxh.fiamproperty.model.R_Wfassignemt;
import com.hqxh.fiamproperty.model.R_Wfassignemt.Wfassignment;
import com.hqxh.fiamproperty.model.R_Wfassignemt.ResultBean;
import com.hqxh.fiamproperty.ui.adapter.BaseQuickAdapter;
import com.hqxh.fiamproperty.ui.adapter.WfassignmentAdapter;
import com.hqxh.fiamproperty.unit.AccountUtils;
import com.hqxh.fiamproperty.unit.JsonUnit;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

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



    private WfassignmentAdapter wfassignmentAdapter;

    private int curpage = 1;
    private int showcount = 20;
    private int totalpage;

    private int mark=0;

    @Override
    protected String getSubTitle() {

        if(mark==HomeActivity.DB_CODE){
            return getString(R.string.db_task_text);
        }else if(mark==HomeActivity.YB_CODE){
            return getString(R.string.yb_task_text);
        }
        return null;
    }


    /**
     * 获取数据
     **/
    private void getData() {
        String data=null;
        if(mark==HomeActivity.DB_CODE){
            data = HttpManager.getWFASSIGNMENTUrl(AccountUtils.getpersonId(this), "ACTIVE", curpage, showcount);
        }else if(mark==HomeActivity.YB_CODE){
            data = HttpManager.getWFASSIGNMENTUrl(AccountUtils.getpersonId(this), "COMPLETE", curpage, showcount);
        }
        Log.i(TAG, "data=" + data);
        Log.i(TAG, "url=" + GlobalConfig.HTTP_URL_SEARCH);
        Rx2AndroidNetworking.post(GlobalConfig.HTTP_URL_SEARCH)
                .addQueryParameter("data", data)
                .build()
                .getObjectObservable(R_Wfassignemt.class) // 发起获取数据列表的请求，并解析到FootList
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
                        Log.e(TAG,"Totalresult="+resultBean.getTotalresult());
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

                        } else {

                            addData(wfassignments);


                        }
                    }


                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

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
            showMiddleToast(ActiveTaskActivity.this,getResources().getString(R.string.all_data_hint));
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
        mark=getIntent().getExtras().getInt("mark");
        initAdapter(new ArrayList<Wfassignment>());
        getData();

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

            }
        });
    }

    /**
     * 添加数据*
     */
    private void addData(final List<Wfassignment> list) {
        wfassignmentAdapter.addData(list);
    }


}
