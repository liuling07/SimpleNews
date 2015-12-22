package com.lauren.simplenews.images.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lauren.simplenews.R;
import com.lauren.simplenews.news.widget.NewsFragment;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/22
 */
public class ImageFragment extends Fragment {
    public static final int IMAGE_TYPE_JINGXUAN = 0;
    public static final int IMAGE_TYPE_QUTU = 1;
    public static final int IMAGE_TYPE_MEITU = 2;
    public static final int IMAGE_TYPE_GUSHI = 3;

    private TabLayout mTablayout;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        mTablayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);
        setupViewPager(mViewPager);
        mTablayout.addTab(mTablayout.newTab().setText(R.string.jingxuan));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.qutu));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.meitu));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.story));
        mTablayout.setupWithViewPager(mViewPager);
        return view;
    }

    private void setupViewPager(ViewPager mViewPager) {
        //Fragment中嵌套使用Fragment一定要使用getChildFragmentManager(),否则会有问题
        NewsFragment.MyPagerAdapter adapter = new NewsFragment.MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(ImageListFragment.newInstance(IMAGE_TYPE_JINGXUAN), getString(R.string.jingxuan));
        adapter.addFragment(ImageListFragment.newInstance(IMAGE_TYPE_QUTU), getString(R.string.qutu));
        adapter.addFragment(ImageListFragment.newInstance(IMAGE_TYPE_MEITU), getString(R.string.meitu));
        adapter.addFragment(ImageListFragment.newInstance(IMAGE_TYPE_GUSHI), getString(R.string.story));
        mViewPager.setAdapter(adapter);
    }
}
