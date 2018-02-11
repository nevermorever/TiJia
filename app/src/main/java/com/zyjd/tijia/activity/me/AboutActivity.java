package com.zyjd.tijia.activity.me;

import android.content.Intent;
import android.view.View;

import com.zyjd.tijia.R;
import com.zyjd.tijia.base.ToolbarActivity;

import butterknife.OnClick;

public class AboutActivity extends ToolbarActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_about;
    }

    @OnClick(R.id.rl_tutorial)
    void onTutorialClick(View view) {
        startActivity(new Intent(this, ToturialActivity.class));
    }
}
