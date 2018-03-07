package com.zyjd.tijia.activity.call;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyjd.tijia.R;
import com.zyjd.tijia.api.ApiClient;
import com.zyjd.tijia.base.ToolbarActivity;
import com.zyjd.tijia.entity.Terminal;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TerminalListActivity extends ToolbarActivity implements BaseQuickAdapter.OnItemChildClickListener, Toolbar.OnMenuItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Adapter adapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_terminal_list;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        toolbar.setOnMenuItemClickListener(this);
        adapter = new Adapter(R.layout.list_item_terminal);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);

        getTerminalList();
    }

    private void getTerminalList() {
        ApiClient.getApiService(this)
                .getTerminalList(new HashMap<String, Object>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Terminal>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Terminal> terminals) {
                        adapter.replaceData(terminals);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_terminal_list, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, VideoCallActivity.class);
        intent.putExtra("username", ((Terminal) adapter.getItem(position)).getHx_id()).putExtra("isComingCall", false);
        startActivity(intent);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        getTerminalList();
        return true;
    }


    private class Adapter extends BaseQuickAdapter<Terminal, BaseViewHolder> {
        public Adapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Terminal item) {
            helper.setText(R.id.terminal_name, item.getName())
                    .addOnClickListener(R.id.iv_video_call);
        }
    }
}
