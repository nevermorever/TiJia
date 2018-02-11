package com.zyjd.tijia.activity.device_manage;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zyjd.tijia.R;
import com.zyjd.tijia.api.ApiClient;
import com.zyjd.tijia.base.ToolbarActivity;
import com.zyjd.tijia.entity.PaginatedResult;
import com.zyjd.tijia.entity.RepairRecord;
import com.zyjd.tijia.entity.User;


import java.util.HashMap;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RepairRecordActivity extends ToolbarActivity implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.refresher)
    SmartRefreshLayout refresher;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    private int current;
    private String next;

    private Adapter adapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_repair_record;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(RepairRecordActivity.this, AddRepairRecordActivity.class));
                return false;
            }
        });

        adapter = new Adapter(R.layout.list_item_repair_record);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        refresher.setOnRefreshListener(this);
        refresher.setOnLoadmoreListener(this);
        refresher.autoRefresh(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_repair_record, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        ApiClient.getApiService()
                .getRepairRecordList(new HashMap<String, Object>() {
                    {
                        put("page", 1);
                        put("limit", 10);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PaginatedResult<RepairRecord>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PaginatedResult<RepairRecord> repairRecordPaginatedResult) {
                        adapter.replaceData(repairRecordPaginatedResult.getResults());
                        current = 1;
                        next = repairRecordPaginatedResult.getNext();
                        refresher.finishRefresh(0);
                        refresher.resetNoMoreData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        refresher.finishRefresh(0);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        if (null == next) {
            refresher.finishLoadmoreWithNoMoreData();
        } else {

            ApiClient.getApiService()
                    .getRepairRecordList(new HashMap<String, Object>() {
                        {
                            put("page", current + 1);
                            put("limit", 10);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<PaginatedResult<RepairRecord>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(PaginatedResult<RepairRecord> repairRecordPaginatedResult) {
                            adapter.addData(repairRecordPaginatedResult.getResults());
                            current += 1;
                            next = repairRecordPaginatedResult.getNext();
                            refresher.finishLoadmore(0);
                        }

                        @Override
                        public void onError(Throwable e) {
                            refresher.finishLoadmore(0);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    private class Adapter extends BaseQuickAdapter<RepairRecord, BaseViewHolder> {
        public Adapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, RepairRecord item) {
            helper.setText(R.id.tv_elevator_name, item.getElevator_detail().getName());
            String exexutor_name_list = "";
            for (User user : item.getExecutors_detail()) {
                exexutor_name_list += user.getName() + " ";
            }
            helper.setText(R.id.tv_executor, exexutor_name_list);
            helper.setText(R.id.tv_check_content, item.getCheck_content());
            helper.setText(R.id.tv_repair_content, item.getRepair_content());
            helper.setText(R.id.tv_start_time, item.getStart_time());
        }
    }
}
