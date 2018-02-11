package com.zyjd.tijia.activity.realtime_monitor;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zyjd.tijia.R;
import com.zyjd.tijia.api.ApiClient;
import com.zyjd.tijia.api.PaginationedResult;
import com.zyjd.tijia.base.ToolbarActivity;
import com.zyjd.tijia.entity.ElevatorRealtimeData;

import java.util.HashMap;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RealtimeMonitorActivity extends ToolbarActivity implements OnLoadmoreListener, OnRefreshListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refresher)
    SmartRefreshLayout refresher;

    private Adapter adapter;
    private int current;
    private String next;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_realtime_monitor;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        adapter = new Adapter(R.layout.list_item_elevator_realtime_monitor);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        refresher.setOnLoadmoreListener(this);
        refresher.setOnRefreshListener(this);
        refresher.autoRefresh(300);

    }

    @Override
    public void onRefresh(final RefreshLayout refreshlayout) {
        ApiClient.getApiService()
                .getElevatorRealtimeDataList(new HashMap<String, Object>() {
                    {
                        put("page", 1);
                        put("limit", 5);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PaginationedResult<ElevatorRealtimeData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PaginationedResult<ElevatorRealtimeData> elevatorRealtimeDataPaginationedResult) {
                        adapter.replaceData(elevatorRealtimeDataPaginationedResult.getResults());
                        current = 1;
                        next = elevatorRealtimeDataPaginationedResult.getNext();
                        refresher.resetNoMoreData();
                        refresher.finishRefresh(300);
                    }

                    @Override
                    public void onError(Throwable e) {
                        refresher.finishRefresh(300);
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
                    .getElevatorRealtimeDataList(new HashMap<String, Object>() {
                        {
                            put("page", current + 1);
                            put("limit", 5);
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<PaginationedResult<ElevatorRealtimeData>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(PaginationedResult<ElevatorRealtimeData> elevatorRealtimeDataPaginationedResult) {
                            adapter.addData(elevatorRealtimeDataPaginationedResult.getResults());
                            current++;
                            next = elevatorRealtimeDataPaginationedResult.getNext();
                            refresher.finishLoadmore(300);
                        }

                        @Override
                        public void onError(Throwable e) {
                            refresher.finishLoadmore(300);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }


    private class Adapter extends BaseQuickAdapter<ElevatorRealtimeData, BaseViewHolder> {
        public Adapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, ElevatorRealtimeData item) {
            helper.setText(R.id.tv_elevator_name, item.getName())
                    .setText(R.id.tv_station, item.getRealtime_data().getStation())
                    .setText(R.id.tv_direction, item.getRealtime_data().getDirection())
                    .setText(R.id.tv_door, item.getRealtime_data().getDoor());
        }
    }
}
