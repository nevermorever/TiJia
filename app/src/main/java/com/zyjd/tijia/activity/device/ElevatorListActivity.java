package com.zyjd.tijia.activity.device;

import android.content.Intent;
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
import com.zyjd.tijia.base.ToolbarActivity;
import com.zyjd.tijia.entity.Elevator;
import com.zyjd.tijia.entity.PaginatedResult;

import java.util.HashMap;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ElevatorListActivity extends ToolbarActivity implements BaseQuickAdapter.OnItemClickListener, OnLoadmoreListener, OnRefreshListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refresher)
    SmartRefreshLayout refresher;


    private Adapter adapter;
    private int current;
    private String next;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_elevator_list;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        adapter = new Adapter(R.layout.list_item_elevator);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        refresher.setOnRefreshListener(this);
        refresher.setOnLoadmoreListener(this);
        refresher.autoRefresh(0);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        ApiClient.getApiService()
                .getElevatorList(new HashMap<String, Object>() {{
                    put("page", 1);
                    put("limit", 10);
                }})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<PaginatedResult<Elevator>>() {
                    @Override
                    public void onNext(PaginatedResult<Elevator> elevatorPaginatedResult) {
                        adapter.replaceData(elevatorPaginatedResult.getResults());
                        current = 1;
                        next = elevatorPaginatedResult.getNext();
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
        if (next == null) {
            refresher.finishLoadmoreWithNoMoreData();
        } else {
            ApiClient.getApiService()
                    .getElevatorList(new HashMap<String, Object>() {{
                        put("page", current + 1);
                        put("limit", 10);
                    }})
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<PaginatedResult<Elevator>>() {
                        @Override
                        public void onNext(PaginatedResult<Elevator> elevatorPaginatedResult) {
                            adapter.addData(elevatorPaginatedResult.getResults());
                            current += 1;
                            next = elevatorPaginatedResult.getNext();
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


    private class Adapter extends BaseQuickAdapter<Elevator, BaseViewHolder> {
        public Adapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Elevator item) {
            helper.setText(R.id.tv_elevator_name, item.getName())
                    .setText(R.id.tv_belong_org, item.getBelong_org_detail().getName())
                    .setText(R.id.tv_sn, item.getSn())
                    .setText(R.id.tv_area, item.getArea_detail().getName());
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(ElevatorListActivity.this, ElevatorDetailActivity.class);
        intent.putExtra("elevator_name", ((Elevator) adapter.getItem(position)).getName());
        intent.putExtra("elevator_id", ((Elevator) adapter.getItem(position)).getId());
        startActivity(intent);
    }
}
