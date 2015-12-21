package com.lauren.simplenews.news.model;

import com.lauren.simplenews.utils.OkHttpUtils;

/**
 * Description : 新闻业务处理类
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/19
 */
public class NewsModelImpl implements NewsModel {

    /**
     * 加载新闻列表
     * @param url
     * @param callback
     */
    @Override
    public void loadNews(String url, OkHttpUtils.ResultCallback callback) {
        OkHttpUtils.get(url, callback);
    }

    /**
     * 加载新闻详情
     * @param url
     * @param callback
     */
    @Override
    public void loadNewsDetail(String url, OkHttpUtils.ResultCallback callback) {
        OkHttpUtils.get(url, callback);
    }


}
