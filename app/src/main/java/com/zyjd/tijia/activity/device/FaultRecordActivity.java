package com.zyjd.tijia.activity.device;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.zyjd.tijia.R;
import com.zyjd.tijia.adapter.FragmentViewPagerAdapter;
import com.zyjd.tijia.base.ToolbarActivity;

import butterknife.BindView;

public class FaultRecordActivity extends ToolbarActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private FragmentViewPagerAdapter adapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_fault_record;
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
                startActivity(new Intent(FaultRecordActivity.this, AddFaultRecordActivity.class));
                return false;
            }
        });

        adapter = new FragmentViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new UnhandleFaultRecordFragment());
        adapter.addFragment(new HandlingFaultRecordFragment());
        adapter.addFragment(new ToBeEvaluatedFaultRecordFragment());
        adapter.addFragment(new HandledFaultRecordFragment());

        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);

        // 设置tab标题
        tab.getTabAt(0).setText(getString(R.string.unhandle));
        tab.getTabAt(1).setText(getString(R.string.handling));
        tab.getTabAt(2).setText(getString(R.string.to_be_evaluated));
        tab.getTabAt(3).setText(getString(R.string.handled));

    }
}
