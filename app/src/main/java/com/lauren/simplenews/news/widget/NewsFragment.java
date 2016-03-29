package com.lauren.simplenews.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lauren.simplenews.R;
import com.lauren.simplenews.news.NewspagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/13
 */
public class NewsFragment extends Fragment {

    public static final int NEWS_TYPE_TOP = 0;
    public static final int NEWS_TYPE_NBA = 1;
    public static final int NEWS_TYPE_CARS = 2;
    public static final int NEWS_TYPE_JOKES = 3;

    private ViewPager mViewPager;
    private TabLayout mTablayout;

    private NewspagerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mTablayout = (TabLayout) view.findViewById(R.id.tab_layout);
        // 使用默认配置
        // mViewPager.setOffscreenPageLimit(3);
//        mViewPager.setAdapter(ne);
        mAdapter = new NewspagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mAdapter);
        setupViewPager(mViewPager);
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
        mTablayout.setupWithViewPager(mViewPager);
        return view;
    }

    private void setupViewPager(ViewPager mViewPager) {
        SimpleArrayMap<String, Integer> categories = new SimpleArrayMap<>();
        categories.put(getString(R.string.top), NEWS_TYPE_TOP);
        categories.put(getString(R.string.nba), NEWS_TYPE_NBA);
        categories.put(getString(R.string.cars), NEWS_TYPE_CARS);
        categories.put(getString(R.string.jokes), NEWS_TYPE_JOKES);
        mAdapter.addCategories(categories);
        //Fragment中嵌套使用Fragment一定要使用getChildFragmentManager(),否则会有问题
//        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
//        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_TOP), getString(R.string.top));
//        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_NBA), getString(R.string.nba));
//        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_CARS), getString(R.string.cars));
//        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_JOKES), getString(R.string.jokes));
//        mViewPager.setAdapter(adapter);
        mAdapter.notifyDataSetChanged();
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
