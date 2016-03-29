package com.lauren.simplenews.commons;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xusheng on 16/3/29.
 */
public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter{

    private List<T> mDatas = new ArrayList<>();

    public void addData(T t) {
        mDatas.add(t);
    }

    public void addDatas(List<T> list) {
        mDatas.addAll(list);
    }

    public T getData(int position) {
        return mDatas.get(position);
    }

    public List<T> getDatas () {
        return mDatas;
    }

    public void clear() {
        mDatas.clear();
    }

}
