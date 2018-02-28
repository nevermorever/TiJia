package com.zyjd.tijia.activity.test;

import android.widget.RelativeLayout;

import com.zyjd.tijia.R;
import com.zyjd.tijia.base.BaseActivity;

import butterknife.BindView;

public class TestDynamicLayoutActivity extends BaseActivity {
    @BindView(R.id.root_layout)
    RelativeLayout root;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_test_dynamic_layout;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
    }
}
