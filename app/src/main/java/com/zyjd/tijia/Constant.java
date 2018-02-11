package com.zyjd.tijia;

import com.zyjd.tijia.activity.device_manage.DeviceManageActivity;

// 全局常量
public class Constant {
    public static final boolean DEBUG = true;
    public static final String API_BASE_URL = "http://192.168.0.21:8000/api/";
    public static final String WS_REALTIME_DATA_URL = "";
    public static final int REQUEST_FOR_SCAN = 413;

    // app主界面模块配置
    public enum AppModuleItem {
        Device("设备管理", R.drawable.ic_device_manage, DeviceManageActivity.class),
        Device2("设备管理", R.drawable.ic_call, DeviceManageActivity.class),
        Device3("设备管理", R.drawable.ic_device_manage, DeviceManageActivity.class),
        Device4("设备管理", R.drawable.ic_device_manage, DeviceManageActivity.class),
        Device5("设备管理", R.drawable.ic_device_manage, DeviceManageActivity.class),
        Device6("设备管理", R.drawable.ic_device_manage, DeviceManageActivity.class);


        // 名称
        public String name;
        // 图标
        public int icon;
        // activity
        public Class<?> clazz;

        AppModuleItem(String name, int icon, Class<?> clazz) {
            this.name = name;
            this.icon = icon;
            this.clazz = clazz;
        }
    }
}
