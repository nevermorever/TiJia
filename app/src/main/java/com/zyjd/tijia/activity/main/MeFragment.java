package com.zyjd.tijia.activity.main;

import android.content.Intent;
import android.view.View;

import com.zyjd.tijia.LoginActivity;
import com.zyjd.tijia.R;
import com.zyjd.tijia.activity.me.AboutActivity;
import com.zyjd.tijia.activity.me.ProfileActivity;
import com.zyjd.tijia.activity.me.SettingActivity;
import com.zyjd.tijia.base.BaseFragment;
import com.zyjd.tijia.util.SPUtil;
import com.zyjd.tijia.util.ToastUtil;

import butterknife.OnClick;

public class MeFragment extends BaseFragment {

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_me;
    }

    @OnClick(R.id.rl_avatar)
    void click1(View view) {
        new ToastUtil().show(getContext(), "1");
    }

    @OnClick(R.id.sign)
    void click2(View view) {
        new ToastUtil().show(getContext(), "2");
    }

    @OnClick(R.id.rl_about)
    void onAboutClick(View view) {
        startActivity(new Intent(getActivity(), AboutActivity.class));
    }

    @OnClick(R.id.ll_profile)
    void onProfileClick(View view) {
        startActivity(new Intent(getActivity(), ProfileActivity.class));
    }

    @OnClick(R.id.rl_setting)
    void onSettingClick(View view) {
        startActivity(new Intent(getActivity(), SettingActivity.class));
    }

    @OnClick(R.id.tv_logout)
    void onLogoutClick(View view) {
        SPUtil.putString(getContext(), "token", null);
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

}
