package com.zyjd.tijia.base;


import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zyjd.tijia.R;
import com.zyjd.tijia.util.StatusBarUtil;

public abstract class ToolbarActivity extends BaseActivity {

    private Toolbar toolbar;

    // 初始化toolbar
    public void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            // 取消默认的标题
            actionBar.setDisplayShowTitleEnabled(false);
            // 显示返回键
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initWidget() {
        initToolbar();

        // 沉浸式状态栏
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, toolbar);
        super.initWidget();
    }
}

