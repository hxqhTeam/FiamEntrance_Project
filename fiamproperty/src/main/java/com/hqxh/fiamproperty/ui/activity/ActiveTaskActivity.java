package com.hqxh.fiamproperty.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.base.BaseListActivity;
import com.hqxh.fiamproperty.model.Results;
import com.hqxh.fiamproperty.model.Results.ResultBean;
import com.hqxh.fiamproperty.model.Wfassignment;
import com.hqxh.fiamproperty.ui.adapter.BaseQuickAdapter;
import com.hqxh.fiamproperty.ui.adapter.WfassignmentAdapter;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 待办任务的Activity
 **/
public class ActiveTaskActivity extends BaseListActivity {
    private static final String TAG = "ActiveTaskActivity";

    private List<Wfassignment> wlist;

    private WfassignmentAdapter wfassignmentAdapter;

    private int page=1;

    @Override
    protected String getSubTitle() {
        return getString(R.string.db_task_text);
    }


    /**
     * 获取数据
     **/
    private void getData() {
        Log.i(TAG, "获取数据测试");
        String url = "http://10.60.12.98:9080/maximo/mobile/common/api";
        String data = "{'appid':'WFADMIN','objectname':'WFASSIGNMENT','username':'yanghongwei','curpage':1,'showcount':20,'option':'read','condition':{'ASSIGNSTATUS':'=ACTIVE','ASSIGNCODE':'=YANGHONGWEI'}}";
        Rx2AndroidNetworking.post(url)
                .addQueryParameter("data", data)
                .build()
                .getObjectObservable(Results.class) // 发起获取数据列表的请求，并解析到FootList
                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取数据列表的请求结果
                .doOnNext(new Consumer<Results>() {
                    @Override
                    public void accept(@NonNull Results results) throws Exception {
                        // 先根据获取数据的响应结果做一些操作
                        Log.e(TAG, "accept: doOnNext :" + results);
                    }
                })

                .map(new Function<Results, ResultBean>() {
                    @Override
                    public ResultBean apply(@NonNull Results results) throws Exception {
                        return results.getResult();
                    }
                })
                .map(new Function<ResultBean, List<Wfassignment>>() {
                    @Override
                    public List<Wfassignment> apply(@NonNull ResultBean resultBean) throws Exception {
                        Log.i(TAG,"resultBean.getResultlist()="+resultBean.getResultlist().size());
                        return resultBean.getResultlist();
                    }

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Wfassignment>>() {
                    @Override
                    public void accept(@NonNull List<Wfassignment> wfassignments) throws Exception {

                        mPullLoadMoreRecyclerView.setRefreshing(false);

                        if (wfassignments == null || wfassignments.isEmpty()) {

                        } else {

                            if (wfassignments != null || wfassignments.size() != 0) {
                                if (page == 1) {
                                    initAdapter(new ArrayList<Wfassignment>());
                                }

                                addData(wfassignments);
                            }

                        }
                    }


                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe 失败:" + Thread.currentThread().getName() + "\n");
                        Log.e(TAG, "失败：" + throwable.getMessage() + "\n");
                    }
                });
    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }


    @Override
    protected void fillData() {
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
