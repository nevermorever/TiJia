package com.zyjd.tijia.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class ImageBrowserPagerAdapter extends PagerAdapter {
    private List<String> mList;
    private Context mContext;

    public ImageBrowserPagerAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//必须实现
//        View view = LayoutInflater.from(mContext).inflate(R.layout.image_preview, container, false);
//        ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
//        Glide.with(mContext).load(mList.get(position)).into(imageView);
//        container.addView(view);
//        return view;

        return true;
    }

    /**
     * 删除操作
     */
    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    public void remove(int index) {
        mList.remove(index);
        notifyDataSetChanged();
    }
}
