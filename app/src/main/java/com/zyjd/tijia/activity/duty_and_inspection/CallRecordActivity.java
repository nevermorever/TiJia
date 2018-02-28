package com.zyjd.tijia.activity.duty_and_inspection;


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
import com.zyjd.tijia.activity.device.AddOnDutyRecordActivity;
import com.zyjd.tijia.api.ApiClient;
import com.zyjd.tijia.base.ToolbarActivity;
import com.zyjd.tijia.entity.CallRecord;
import com.zyjd.tijia.entity.PaginatedResult;

import java.util.HashMap;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CallRecordActivity extends ToolbarActivity implements OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.refresher)
    SmartRefreshLayout refresher;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    private Adaopter adaopter;
    private int current;
    private String next;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_call_record;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(CallRecordActivity.this, AddOnDutyRecordActivity.class));
                return false;
            }
        });

        adaopter = new Adaopter(R.layout.list_item_call_record);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adaopter);

        refresher.setOnRefreshListener(this);
        refresher.setOnLoadmoreListener(this);
        refresher.autoRefresh(300);

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        ApiClient.getApiService()
                .getCallRecordList(new HashMap<String, Object>() {
                    {
                        put("page", 1);
                        put("limit", 10);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PaginatedResult<CallRecord>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PaginatedResult<CallRecord> callRecordPaginatedResult) {
                        adaopter.replaceData(callRecordPaginatedResult.getResults());
                        current = 1;
                        next = callRecordPaginatedResult.getNext();
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
                    .getCallRecordList(new HashMap<String, Object>() {
                        {
                            put("page", current + 1);
                            put("limit", 10);
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<PaginatedResult<CallRecord>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(PaginatedResult<CallRecord> callRecordPaginatedResult) {
                            adaopter.addData(callRecordPaginatedResult.getResults());
                            current += 1;
                            next = callRecordPaginatedResult.getNext();
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

    private class Adaopter extends BaseQuickAdapter<CallRecord, BaseViewHolder> {
        public Adaopter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, CallRecord item) {

            helper.setText(R.id.tv_call_time, item.getCall_time())
                    .setText(R.id.tv_call_org, item.getCall_org())
                    .setText(R.id.tv_call_reason, item.getCall_reason())
                    .setText(R.id.tv_call_content, item.getCall_content())
                    .setText(R.id.tv_related_elevator, null != item.getElevator() ? item.getElevator_detail().getName() : getString(R.string.blank))
                    .setText(R.id.tv_handle_status, item.getHandle_status())
                    .setText(R.id.tv_on_duty_ppl, item.getOn_duty_ppl_detail().getName());
        }
    }
}
