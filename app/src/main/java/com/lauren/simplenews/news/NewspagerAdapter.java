package com.lauren.simplenews.news;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;

import com.lauren.simplenews.news.widget.NewsListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xusheng on 16/3/29.
 */
public class NewspagerAdapter extends FragmentStatePagerAdapter {

    private SimpleArrayMap<String, Integer> mCategories;

    public NewspagerAdapter(FragmentManager fm) {
        super(fm);
        mCategories = new SimpleArrayMap<>();
    }

    @Override
    public Fragment getItem(int position) {
        return NewsListFragment.newInstance(mCategories.valueAt(position));
    }

    @Override
    public int getCount() {
        return mCategories.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCategories.keyAt(position);
    }

    public void addCategories(SimpleArrayMap<String, Integer> categories) {
        mCategories.putAll(categories);
    }
}
