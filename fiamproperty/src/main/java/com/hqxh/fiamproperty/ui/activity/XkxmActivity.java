package com.hqxh.fiamproperty.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.api.HttpManager;
import com.hqxh.fiamproperty.base.BaseListActivity;
import com.hqxh.fiamproperty.constant.GlobalConfig;


import com.hqxh.fiamproperty.model.R_GRLINE;
import com.hqxh.fiamproperty.model.R_PAYPLANPROJECT;
import com.hqxh.fiamproperty.model.R_PAYPLANPROJECT.PAYPLANPROJECT;
import com.hqxh.fiamproperty.ui.adapter.BaseQuickAdapter;

import com.hqxh.fiamproperty.ui.adapter.WzmxAdapter;
import com.hqxh.fiamproperty.ui.adapter.XkmxAdapter;
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
 * 需款项目
 **/
public class XkxmActivity extends BaseListActivity {
    private static final String TAG = "XkxmActivity";



    private XkmxAdapter xkmxadapter;

    private int curpage = 1;
    private int showcount = 20;
    private int totalpage;

    private String payplannum ;;//payplannum


    @Override
    protected void beforeInit() {
        super.beforeInit();
        if(getIntent().hasExtra("payplannum")){
            payplannum=getIntent().getExtras().getString("payplannum");
        }
    }

    @Override
    protected String getSubTitle() {

        return getString(R.string.xkxm_text);
    }


    /**
     * 获取数据
     **/
    private void getData() {
        String data= HttpManager.gePAYPLANPROJECTUrl(payplannum,AccountUtils.getpersonId(this), curpage, showcount);
        Log.e(TAG,"data="+data);
        Rx2AndroidNetworking.post(GlobalConfig.HTTP_URL_SEARCH)
                .addQueryParameter("data", data)
                .build()
                .getObjectObservable(R_PAYPLANPROJECT.class) // 发起获取数据列表的请求，并解析到R_Wfassignemt
                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取数据列表的请求结果
                .doOnNext(new Consumer<R_PAYPLANPROJECT>() {
                    @Override
                    public void accept(@NonNull R_PAYPLANPROJECT r_payplanproject) throws Exception {
                    }
                })

                .map(new Function<R_PAYPLANPROJECT, R_PAYPLANPROJECT.ResultBean>() {
                    @Override
                    public R_PAYPLANPROJECT.ResultBean apply(@NonNull R_PAYPLANPROJECT r_payplanproject) throws Exception {

                        return r_payplanproject.getResult();
                    }
                })
                .map(new Function<R_PAYPLANPROJECT.ResultBean, List<R_PAYPLANPROJECT.PAYPLANPROJECT>>() {
                    @Override
                    public List<R_PAYPLANPROJECT.PAYPLANPROJECT> apply(@NonNull R_PAYPLANPROJECT.ResultBean resultBean) throws Exception {
                        return resultBean.getResultlist();
                    }

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<R_PAYPLANPROJECT.PAYPLANPROJECT>>() {
                    @Override
                    public void accept(@NonNull List<R_PAYPLANPROJECT.PAYPLANPROJECT> payplanproject) throws Exception {
                        mPullLoadMoreRecyclerView.setRefreshing(false);
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();

                        if (payplanproject == null || payplanproject.isEmpty()) {

                        } else {

                            addData(payplanproject);


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
        xkmxadapter.removeAll(xkmxadapter.getData());
        getData();

    }

    @Override
    public void onLoadMore() {
        if (totalpage == curpage) {
            getLoadMore();
            showMiddleToast(XkxmActivity.this,getResources().getString(R.string.all_data_hint));
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
        payplannum=getIntent().getExtras().getString("payplannum");
        initAdapter(new ArrayList<PAYPLANPROJECT>());
        getData();

    }

    @Override
    protected void setOnClick() {

    }


    /**
     * 获取数据*
     */
    private void initAdapter(final List<PAYPLANPROJECT> list) {
        xkmxadapter = new XkmxAdapter(XkxmActivity.this, R.layout.list_xkxm, list);
        mRecyclerView.setAdapter(xkmxadapter);
        xkmxadapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    /**
     * 添加数据*
     */
    private void addData(final List<PAYPLANPROJECT> list) {
        xkmxadapter.addData(list);
    }




}
