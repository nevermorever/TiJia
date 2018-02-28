package com.zyjd.tijia.activity.duty_and_inspection;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.zyjd.tijia.R;
import com.zyjd.tijia.activity.device.AddInspectionRecordActivity;
import com.zyjd.tijia.base.ToolbarActivity;

import butterknife.BindView;

public class InspectionRecordActivity extends ToolbarActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_inspection_record;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(InspectionRecordActivity.this, AddInspectionRecordActivity.class));
                return false;
            }
        });
    }
}
