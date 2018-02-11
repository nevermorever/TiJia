package com.zyjd.tijia.activity.duty_and_inspection;

import android.content.Intent;

import com.zyjd.tijia.R;
import com.zyjd.tijia.base.ToolbarActivity;

import butterknife.OnClick;

public class DutyAndInspectionManageActivity extends ToolbarActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_duty_and_inspection_manage;
    }

    @OnClick(R.id.rl_on_duty_record)
    void onOnDutyRecordClick() {
        startActivity(new Intent(this, CallRecordActivity.class));
    }

    @OnClick(R.id.rl_inspection_record)
    void onInspectionRecordClick() {
        startActivity(new Intent(this, InspectionRecordActivity.class));

    }
}
