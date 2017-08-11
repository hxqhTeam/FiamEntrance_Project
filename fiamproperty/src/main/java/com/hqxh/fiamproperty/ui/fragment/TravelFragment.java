package com.hqxh.fiamproperty.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.api.HttpManager;
import com.hqxh.fiamproperty.base.BaseActivity;
import com.hqxh.fiamproperty.constant.GlobalConfig;
import com.hqxh.fiamproperty.model.R_Workorder;
import com.hqxh.fiamproperty.model.R_Workorder.ResultBean;
import com.hqxh.fiamproperty.model.R_Workorder.Workorder;
import com.hqxh.fiamproperty.ui.activity.GnWorkorderActivity;
import com.hqxh.fiamproperty.ui.adapter.BaseQuickAdapter;
import com.hqxh.fiamproperty.ui.adapter.TravelAdapter;
import com.hqxh.fiamproperty.ui.widget.PullLoadMoreRecyclerView;
import com.hqxh.fiamproperty.unit.AccountUtils;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class TravelFragment extends Fragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    private static final String TAG="TravelFragment";

    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private RecyclerView mRecyclerView;

    private int curpage = 1;
    private int showcount = 20;
    private int totalpage;


    private TravelAdapter travelAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        //获取mRecyclerView对象
        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        //代码设置scrollbar无效？未解决！
        mRecyclerView.setVerticalScrollBarEnabled(true);
        //显示下拉刷新
        mPullLoadMoreRecyclerView.setRefreshing(true);
        mPullLoadMoreRecyclerView.setLinearLayout();

        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        initAdapter(new ArrayList<Workorder>());
        getData();

    }

    private void getData() {
        String data=HttpManager.getWORKORDERUrl(AccountUtils.getpersonId(getActivity()),curpage, showcount);
        Log.i(TAG, "data=" + data);
        Log.i(TAG, "url=" + GlobalConfig.HTTP_URL_SEARCH);
        Rx2AndroidNetworking.post(GlobalConfig.HTTP_URL_SEARCH)
                .addQueryParameter("data", data)
                .build()
                .getObjectObservable(R_Workorder.class) // 发起获取数据列表的请求，并解析到FootList
                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取数据列表的请求结果
                .doOnNext(new Consumer<R_Workorder>() {
                    @Override
                    public void accept(@NonNull R_Workorder r_workorder) throws Exception {
                    }
                })

                .map(new Function<R_Workorder, R_Workorder.ResultBean>() {
                    @Override
                    public R_Workorder.ResultBean apply(@NonNull R_Workorder r_workorder) throws Exception {

                        return r_workorder.getResult();
                    }
                })
                .map(new Function<ResultBean, List<Workorder>>() {
                    @Override
                    public List<Workorder> apply(@NonNull ResultBean resultBean) throws Exception {
                        totalpage = Integer.valueOf(resultBean.getTotalpage());
                        Log.e(TAG,"Totalresult="+resultBean.getTotalresult());
                        return resultBean.getResultlist();
                    }

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Workorder>>() {
                    @Override
                    public void accept(@NonNull List<Workorder> workorder) throws Exception {
                        mPullLoadMoreRecyclerView.setRefreshing(false);
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();

                        if (workorder == null || workorder.isEmpty()) {

                        } else {

                            addData(workorder);


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
        travelAdapter.removeAll(travelAdapter.getData());
        getData();
    }

    @Override
    public void onLoadMore() {
        if (totalpage == curpage) {
            getLoadMore();
            BaseActivity.showMiddleToast(getActivity(),getResources().getString(R.string.all_data_hint));
        } else {
            curpage++;
            getData();
        }
    }

    private void getLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                });

            }
        }, 1000);

    }

    /**
     * 获取数据*
     */
    private void initAdapter(final List<Workorder> list) {
        travelAdapter = new TravelAdapter(getActivity(), R.layout.list_item_travel, list);
        mRecyclerView.setAdapter(travelAdapter);
        travelAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(), GnWorkorderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("workorder", (Serializable) travelAdapter.getData().get(position));
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * 添加数据*
     */
    private void addData(final List<Workorder> list) {
        travelAdapter.addData(list);
    }

}
