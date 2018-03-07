package com.zyjd.tijia.activity.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.zyjd.tijia.R;
import com.zyjd.tijia.activity.SearchActivity;
import com.zyjd.tijia.adapter.FragmentViewPagerAdapter;
import com.zyjd.tijia.base.ToolbarActivity;
import com.zyjd.tijia.websocket.WsManager;

import butterknife.BindView;

public class MainActivity extends ToolbarActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private MenuItem menuItem;
    private FragmentViewPagerAdapter adapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        toolbar.setNavigationIcon(R.drawable.ic_scan_white_24dp);

        // 导航按钮点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CaptureActivity.class));
            }
        });

        // 菜单按钮点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                return true;
            }
        });
        navigation.getMenu().findItem(R.id.navigation_apps).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(this);
        adapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NotificationFragment());
        adapter.addFragment(new AppsFragment());
        adapter.addFragment(new MeFragment());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(1);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_notification:
                title.setText(R.string.title_notification);
                setIconVisible(false);
                viewPager.setCurrentItem(0);

                return true;
            case R.id.navigation_apps:
                title.setText(R.string.title_apps);
                setIconVisible(true);
                viewPager.setCurrentItem(1);
                return true;
            case R.id.navigation_me:
                title.setText(R.string.title_me);
                setIconVisible(false);
                viewPager.setCurrentItem(2);
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (menuItem != null) {
            menuItem.setChecked(false);
        } else {
            navigation.getMenu().getItem(1).setChecked(false);
        }
        menuItem = navigation.getMenu().getItem(position);
        menuItem.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    // 设置toolbar上的图标可见性
    // 只在滑动到主页fragment时可见
    private void setIconVisible(Boolean visible) {
        toolbar.getMenu().findItem(R.id.search).setVisible(visible);
        if (visible) {
            toolbar.setNavigationIcon(R.drawable.ic_scan_white_24dp);
        } else {
            toolbar.setNavigationIcon(null);
        }
    }
}
