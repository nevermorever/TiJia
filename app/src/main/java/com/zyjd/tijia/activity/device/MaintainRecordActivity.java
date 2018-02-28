package com.zyjd.tijia.activity.device;

import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.zyjd.tijia.R;
import com.zyjd.tijia.base.ToolbarActivity;

import butterknife.BindView;

public class MaintainRecordActivity extends ToolbarActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_maintain_record;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
