package com.zyjd.tijia.activity.device;

import android.content.Intent;
import android.view.View;

import com.zyjd.tijia.R;
import com.zyjd.tijia.base.ToolbarActivity;

import butterknife.OnClick;

public class DeviceManageActivity extends ToolbarActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_device_manage;
    }

    @OnClick(R.id.rl_device_list)
    void onDeviceListClick(View view) {
        startActivity(new Intent(this, ElevatorListActivity.class));
    }

    @OnClick(R.id.rl_repair_record)
    void onRepairRecordClick(View view) {
        startActivity(new Intent(this, RepairRecordActivity.class));
    }

    @OnClick(R.id.rl_maintain_record)
    void onMaintainRecordClick(View view) {
        startActivity(new Intent(this, MaintainRecordActivity.class));
    }

    @OnClick(R.id.rl_fault_record)
    void onFaultRecordClick(View view) {
        startActivity(new Intent(this, FaultRecordActivity.class));
    }

}
