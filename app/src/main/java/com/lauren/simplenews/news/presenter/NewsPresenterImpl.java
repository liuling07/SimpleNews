package com.lauren.simplenews.news.presenter;

import android.content.Context;

/**
 * @Class:
 * @Description:
 * @author: lling(www.liuling123.com)
 * @Date: 2015/12/18
 */
public class NewsPresenterImpl implements NewsPresenter {

    private Context mContext;

    public NewsPresenterImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void loadNews() {

    }
}
