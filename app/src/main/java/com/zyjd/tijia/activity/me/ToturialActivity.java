package com.zyjd.tijia.activity.me;

import android.webkit.WebView;

import com.zyjd.tijia.R;
import com.zyjd.tijia.base.ToolbarActivity;

import butterknife.BindView;

public class ToturialActivity extends ToolbarActivity {

    @BindView(R.id.webview)
    WebView webView;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_toturial;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        webView.loadUrl("http://www.baidu.com");
    }
}
