package com.zyjd.tijia.util;

import android.content.Context;
import android.widget.Toast;

// 防止重复点击时，toast重复显示
public class ToastUtil {
    private static Toast toast;

    public void show(Context context, String content) {
        if (null == toast) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
