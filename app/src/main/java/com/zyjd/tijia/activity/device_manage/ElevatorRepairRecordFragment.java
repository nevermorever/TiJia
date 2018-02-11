package com.zyjd.tijia.activity.device_manage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zyjd.tijia.R;
import com.zyjd.tijia.api.ApiClient;
import com.zyjd.tijia.base.BaseFragment;
import com.zyjd.tijia.entity.PaginatedResult;
import com.zyjd.tijia.entity.RepairRecord;
import com.zyjd.tijia.entity.User;


import java.util.HashMap;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ElevatorRepairRecordFragment extends BaseFragment implements OnRefreshListener, OnLoadmoreListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refresher)
    SmartRefreshLayout refresher;

    private int elevatorId;
    private Adapter adapter;
    private int current;
    private String next;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_elevator_repair_record;
    }

    @Override
    protected void initArgs(Bundle bundle) {
        elevatorId = bundle.getInt("elevator_id");
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        adapter = new Adapter(R.layout.list_item_repair_record);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

        refresher.setOnRefreshListener(this);
        refresher.setOnLoadmoreListener(this);
        refresher.autoRefresh();

        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        ApiClient
                .getApiService()
                .getRepairRecordList(new HashMap<String, Object>() {{
                    put("page", 1);
                    put("limit", 10);
                    put("elevator", elevatorId);
                }})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PaginatedResult<RepairRecord>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PaginatedResult<RepairRecord> repairRecordPaginatedResult) {
                        adapter.replaceData(repairRecordPaginatedResult.getResults());
                        refresher.finishRefresh();
                        refresher.setEnableLoadmore(true);
                        current = 1;
                        next = repairRecordPaginatedResult.getNext();
                        refresher.resetNoMoreData();

                    }

                    @Override
                    public void onError(Throwable e) {
                        refresher.finishRefresh();
                        refresher.setEnableLoadmore(true);
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
            ApiClient
                    .getApiService()
                    .getRepairRecordList(new HashMap<String, Object>() {
                        {
                            put("page", current + 1);
                            put("limit", 10);
                            put("elevator", elevatorId);
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
                            refresher.finishLoadmore();
                            current++;
                            next = repairRecordPaginatedResult.getNext();
                        }

                        @Override
                        public void onError(Throwable e) {
                            refresher.finishLoadmore();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(getActivity(), RepairRecordDetailActivity.class);
        startActivity(intent);
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
