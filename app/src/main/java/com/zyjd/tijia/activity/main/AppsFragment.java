package com.zyjd.tijia.activity.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zyjd.tijia.R;
import com.zyjd.tijia.activity.device.DeviceManageActivity;
import com.zyjd.tijia.activity.duty_and_inspection.DutyAndInspectionManageActivity;
import com.zyjd.tijia.activity.maintenance.MaintenancePlanActivity;
import com.zyjd.tijia.activity.monitor.RealtimeDataMonitorActivity;
import com.zyjd.tijia.activity.test.TestDynamicLayoutActivity;
import com.zyjd.tijia.activity.call.TerminalListActivity;
import com.zyjd.tijia.base.BaseFragment;
import com.zyjd.tijia.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class AppsFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner banner;

//    private Adapter adapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_apps;
    }

//    @Override
//    protected void initWidget(View root) {
//        super.initWidget(root);
//
//        adapter = new Adapter(Arrays.asList(Constant.AppModuleItem.values()));
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
//        recyclerView.setAdapter(adapter);
//
//        // 设置头部banner
//        View header = getLayoutInflater().inflate(R.layout.banner_item, recyclerView, false);
//        Banner banner = (Banner) header;
//        banner.setImageLoader(new GlideImageLoader());
//        banner.setImages(BANNER_ITEMS);
//        banner.setDelayTime(5000);
//        banner.setOnBannerListener(new OnBannerListener() {
//            @Override
//            public void OnBannerClick(int position) {
//                new ToastUtil().show(getContext(), "" + position);
//            }
//        });
//        banner.start();
//
//        adapter.addHeaderView(banner);
//        adapter.openLoadAnimation();
//    }

//    private class Adapter extends BaseQuickAdapter<AppModuleItem, BaseViewHolder> {
//        public Adapter(@Nullable List<AppModuleItem> data) {
//            super(R.layout.grid_item_app_module, data);
//        }
//
//        @Override
//        protected void convert(BaseViewHolder helper, AppModuleItem item) {
//            helper.setImageResource(R.id.iv_image, item.icon);
//            helper.setText(R.id.tv_name, item.name);
//        }
//    }


    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        // 设置头部banner
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(BANNER_ITEMS);
        banner.setDelayTime(5000);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                new ToastUtil().show(getContext(), "" + position);
            }
        });
        banner.start();
    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource(((BannerItem) path).pic);
        }
    }

    public static class BannerItem {

        public int pic;
        public String title;

        public BannerItem() {
        }

        public BannerItem(String title, int pic) {
            this.pic = pic;
            this.title = title;
        }
    }

    public static List<BannerItem> BANNER_ITEMS = new ArrayList<BannerItem>() {{
        add(new BannerItem("最后的骑士", R.mipmap.image_movie_header_48621499931969370));
        add(new BannerItem("三生三世十里桃花", R.mipmap.image_movie_header_12981501221820220));
        add(new BannerItem("豆福传", R.mipmap.image_movie_header_12231501221682438));
    }};


    @OnClick(R.id.device_manage)
    void onDeviceManageClick() {
        startActivity(new Intent(getActivity(), DeviceManageActivity.class));
    }

    @OnClick(R.id.ll_video_call)
    void onVideoCallClick() {
        startActivity(new Intent(getActivity(), TerminalListActivity.class));
    }

    @OnClick(R.id.ll_on_duty_and_inspection)
    void onOnDutyAndInspectionClick() {
        startActivity(new Intent(getActivity(), DutyAndInspectionManageActivity.class));
    }

    @OnClick(R.id.ll_maitenance_plan)
    void onMaintenancePlanClick() {
        startActivity(new Intent(getActivity(), MaintenancePlanActivity.class));
    }

    @OnClick(R.id.ll_realtime_monitor)
    void onRealtimeMonitorClick() {
        startActivity(new Intent(getActivity(), RealtimeDataMonitorActivity.class));
    }

    @OnClick(R.id.ll_more)
    void onMoreClick() {
        startActivity(new Intent(getActivity(), TestDynamicLayoutActivity.class));
    }
}
