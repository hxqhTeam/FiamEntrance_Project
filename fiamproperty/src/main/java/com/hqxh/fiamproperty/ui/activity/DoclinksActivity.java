package com.hqxh.fiamproperty.ui.activity;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.hqxh.fiamproperty.R;
import com.hqxh.fiamproperty.api.HttpManager;
import com.hqxh.fiamproperty.base.BaseListActivity;
import com.hqxh.fiamproperty.constant.GlobalConfig;
import com.hqxh.fiamproperty.model.R_DOCLINKS;
import com.hqxh.fiamproperty.model.R_DOCLINKS.DOCLINKS;
import com.hqxh.fiamproperty.model.R_DOCLINKS.ResultBean;
import com.hqxh.fiamproperty.ui.adapter.BaseQuickAdapter;
import com.hqxh.fiamproperty.ui.adapter.DoclinksAdapter;
import com.hqxh.fiamproperty.unit.AccountUtils;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 附件文档的Activity
 **/
public class DoclinksActivity extends BaseListActivity {
    private static final String TAG = "DoclinksActivity";


    private DoclinksAdapter doclinksadapter;

    private int curpage = 1;
    private int showcount = 20;
    private int totalpage;

    private String ownertable;//
    private String ownerid;//
    private String title;//


    @Override
    protected void beforeInit() {
        super.beforeInit();
        if (getIntent().hasExtra("ownertable")) {
            ownertable = getIntent().getExtras().getString("ownertable");
        }
        if (getIntent().hasExtra("ownerid")) {
            ownerid = getIntent().getExtras().getString("ownerid");
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
        String data = HttpManager.getDOCLINKSUrl(AccountUtils.getpersonId(this), ownertable, ownerid, curpage, showcount);
        Log.i(TAG, "data=" + data);
        Log.i(TAG, "url=" + GlobalConfig.HTTP_URL_SEARCH);
        Rx2AndroidNetworking.post(GlobalConfig.HTTP_URL_SEARCH)
                .addQueryParameter("data", data)
                .build()
                .getObjectObservable(R_DOCLINKS.class) // 发起获取数据列表的请求，并解析到FootList
                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取数据列表的请求结果
                .doOnNext(new Consumer<R_DOCLINKS>() {
                    @Override
                    public void accept(@NonNull R_DOCLINKS r_doclinks) throws Exception {
                    }
                })

                .map(new Function<R_DOCLINKS, ResultBean>() {
                    @Override
                    public ResultBean apply(@NonNull R_DOCLINKS r_doclinks) throws Exception {

                        return r_doclinks.getResult();
                    }
                })
                .map(new Function<ResultBean, List<DOCLINKS>>() {
                    @Override
                    public List<DOCLINKS> apply(@NonNull ResultBean resultBean) throws Exception {
                        totalpage = Integer.valueOf(resultBean.getTotalpage());
                        Log.e(TAG, "Totalresult=" + resultBean.getTotalresult());
                        return resultBean.getResultlist();
                    }

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DOCLINKS>>() {
                    @Override
                    public void accept(@NonNull List<DOCLINKS> doclinks) throws Exception {
                        mPullLoadMoreRecyclerView.setRefreshing(false);
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();

                        if (doclinks == null || doclinks.isEmpty()) {

                        } else {

                            addData(doclinks);


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
        doclinksadapter.removeAll(doclinksadapter.getData());
        getData();

    }

    @Override
    public void onLoadMore() {
        if (totalpage == curpage) {
            getLoadMore();
            showMiddleToast(DoclinksActivity.this, getResources().getString(R.string.all_data_hint));
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
        searchText.setVisibility(View.GONE);
        initAdapter(new ArrayList<DOCLINKS>());
        getData();

    }

    @Override
    protected void setOnClick() {

    }


    /**
     * 获取数据*
     */
    private void initAdapter(final List<DOCLINKS> list) {
        doclinksadapter = new DoclinksAdapter(DoclinksActivity.this, R.layout.list_item_doclinks, list);
        mRecyclerView.setAdapter(doclinksadapter);
        doclinksadapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    /**
     * 添加数据*
     */
    private void addData(final List<DOCLINKS> list) {
        doclinksadapter.addData(list);
    }


}
