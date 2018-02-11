package com.zyjd.tijia.activity.video_call;


import com.hyphenate.chat.EMClient;

public class Helper {
    private static Helper instance = null;
    public boolean isVoiceCalling;
    public boolean isVideoCalling;

    private Helper() {
    }

    public synchronized static Helper getInstance() {
        if (instance == null) {
            instance = new Helper();
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return EMClient.getInstance().isLoggedInBefore();
    }
}
