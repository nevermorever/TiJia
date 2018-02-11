package com.zyjd.tijia.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWindows();

        if (initArgs(getIntent().getExtras())) {
            int layoutId = getContentLayoutId();
            setContentView(layoutId);
            initData();
            initWidget();
        } else {
            finish();
        }
    }

    // 界面未初始化之前调用的初始化窗口
    protected void initWindows() {

    }

    // 相关参数初始化 成功返回true
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    // 获取资源ID
    protected abstract int getContentLayoutId();

    // 初始化控件
    protected void initWidget() {
        ButterKnife.bind(this);
    }


    // 初始化数据
    protected void initData() {

    }
}
