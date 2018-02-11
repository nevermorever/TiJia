package com.zyjd.tijia.activity.device_manage;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.TextView;

import com.zyjd.tijia.R;
import com.zyjd.tijia.adapter.FragmentViewPagerAdapter;
import com.zyjd.tijia.base.ToolbarActivity;

import butterknife.BindView;

public class ElevatorDetailActivity extends ToolbarActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private FragmentViewPagerAdapter adapter;
    private String elevatorName;
    private int elevatorId;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_elevator_detail;
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        elevatorName = bundle.getString("elevator_name", null);
        elevatorId = bundle.getInt("elevator_id");
        return super.initArgs(bundle);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        title.setText(elevatorName);
        adapter = new FragmentViewPagerAdapter(getSupportFragmentManager());

        // 将电梯id传入各个fragment
        Bundle bundle = new Bundle();
        bundle.putInt("elevator_id", elevatorId);

        ElevatorParamsFragment elevatorParamsFragment = new ElevatorParamsFragment();
        ElevatorRepairRecordFragment elevatorRepairRecordFragment = new ElevatorRepairRecordFragment();
        ElevatorMaintainRecordFragment elevatorMaintainRecordFragment = new ElevatorMaintainRecordFragment();
        ElevatorFaultRecordFragment elevatorFaultRecordFragment = new ElevatorFaultRecordFragment();

        elevatorParamsFragment.setArguments(bundle);
        elevatorRepairRecordFragment.setArguments(bundle);
        elevatorMaintainRecordFragment.setArguments(bundle);
        elevatorFaultRecordFragment.setArguments(bundle);

        adapter.addFragment(elevatorParamsFragment);
        adapter.addFragment(elevatorRepairRecordFragment);
        adapter.addFragment(elevatorMaintainRecordFragment);
        adapter.addFragment(elevatorFaultRecordFragment);

        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);

        // 设置tab标题
        tab.getTabAt(0).setText(getString(R.string.elevator_params));
        tab.getTabAt(1).setText(getString(R.string.repair_record));
        tab.getTabAt(2).setText(getString(R.string.maintain_record));
        tab.getTabAt(3).setText(getString(R.string.fault_record));
    }
}
