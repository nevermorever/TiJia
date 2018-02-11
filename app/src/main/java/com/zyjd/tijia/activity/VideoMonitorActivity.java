package com.zyjd.tijia.activity;

import com.zyjd.tijia.R;
import com.zyjd.tijia.base.ToolbarActivity;
import com.zyjd.tijia.widget.calendar.videoplaer.CustomMediaPlayerAssertFolder;
import com.zyjd.tijia.widget.calendar.videoplaer.JZMediaIjkplayer;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoMonitorActivity extends ToolbarActivity {
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_video_monitor;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        JZVideoPlayer.setMediaInterface(new JZMediaIjkplayer());
        JZVideoPlayerStandard jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.video_player);
        jzVideoPlayerStandard.setUp("rtmp://192.168.2.2:1935/live/15"
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛");
    }
    @Override
    public void onResume() {
        super.onResume();
        JZVideoPlayer.setMediaInterface(new CustomMediaPlayerAssertFolder());//进入此页面修改MediaInterface，让此页面的jzvd正常工作
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
