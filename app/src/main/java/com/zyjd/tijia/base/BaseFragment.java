package com.zyjd.tijia.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {
    protected View mRoot;
    protected Unbinder mRootUnbinder;

    public BaseFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRoot == null) {
            int layoutId = getContentLayoutId();
            View root = inflater.inflate(layoutId, container, false);
            initWidget(root);
            mRoot = root;
        } else {
            if (mRoot.getParent() != null) {
                // 把当前root从其父控件中移除
                ((ViewGroup) mRoot.getParent()).removeView(mRoot);
            }
        }
        return mRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // View创建完成之后，初始化数据
        initData();
    }

    // 相关参数初始化
    protected void initArgs(Bundle bundle) {

    }

    // 获取资源ID
    protected abstract int getContentLayoutId();

    // 初始化控件
    protected void initWidget(View root) {
        mRootUnbinder = ButterKnife.bind(this, root);
    }

    // 初始化数据
    protected void initData() {

    }

}
