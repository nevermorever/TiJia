package com.zyjd.tijia.activity.device;

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
import com.zyjd.tijia.entity.FaultRecord;
import com.zyjd.tijia.entity.PaginatedResult;
import com.zyjd.tijia.entity.User;

import java.util.HashMap;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ElevatorFaultRecordFragment extends BaseFragment implements OnRefreshListener, OnLoadmoreListener, BaseQuickAdapter.OnItemClickListener {
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
        return R.layout.fragment_elevator_fault_record;
    }

    @Override
    protected void initArgs(Bundle bundle) {
        elevatorId = bundle.getInt("elevator_id");
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        adapter = new Adapter(R.layout.list_item_fault_record);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

        refresher.setOnRefreshListener(this);
        refresher.setOnLoadmoreListener(this);
        refresher.autoRefresh();

        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        ApiClient.getApiService()
                .getFaultRecordList(new HashMap<String, Object>() {
                    {
                        put("page", 1);
                        put("limit", 10);
                        put("elevator", elevatorId);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PaginatedResult<FaultRecord>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PaginatedResult<FaultRecord> faultRecordPaginatedResult) {
                        adapter.replaceData(faultRecordPaginatedResult.getResults());
                        refresher.finishRefresh();
                        refresher.setEnableLoadmore(true);
                        current = 1;
                        next = faultRecordPaginatedResult.getNext();
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
            ApiClient.getApiService()
                    .getFaultRecordList(new HashMap<String, Object>() {
                        {
                            put("page", 1);
                            put("limit", 10);
                            put("elevator", elevatorId);
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<PaginatedResult<FaultRecord>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(PaginatedResult<FaultRecord> faultRecordPaginatedResult) {
                            adapter.addData(faultRecordPaginatedResult.getResults());
                            refresher.finishLoadmore();
                            current++;
                            next = faultRecordPaginatedResult.getNext();
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
        Intent intent = new Intent(getActivity(), FaultRecordDetailActivity.class);
        startActivity(intent);
    }

    private class Adapter extends BaseQuickAdapter<FaultRecord, BaseViewHolder> {
        public Adapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, FaultRecord item) {
            String faultType = "";
            String faultCode = item.getFault_code();

            switch (faultCode) {
                case "1":
                    faultType = "系统电源故障";
                    break;
                case "2":
                    faultType = "安全回路故障";
                    break;
                case "3":
                    faultType = "冲顶";
                    break;
                case "4":
                    faultType = "蹲底";
                    break;
                case "5":
                    faultType = "异常停梯";
                    break;
                case "6":
                    faultType = "无法开门";
                    break;
                case "7":
                    faultType = "无法关门";
                    break;
                case "8":
                    faultType = "门联锁回路故障";
                    break;
            }

            String handlePpl = "";
            for (User user : item.getHandle_ppl_detail()) {
                handlePpl += user.getName() + " ";
            }

            helper.setText(R.id.tv_fault_type, faultType)
                    .setText(R.id.tv_elevator_name, item.getElevator_detail().getName())
                    .setText(R.id.tv_occur_time, item.getOccur_time())
                    .setText(R.id.tv_handle_status, item.getHandle_status())
                    .setText(R.id.tv_handle_result, item.getHandle_result())
                    .setText(R.id.tv_handle_ppl, handlePpl);
        }
    }
}
