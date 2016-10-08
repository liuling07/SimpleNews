package com.lauren.simplenews.news.model;

import com.lauren.simplenews.beans.NewsBean;

import java.util.List;

/**
 * Description : 新闻列表加载回调
 * Author : AstroGypsophila
 * Github  : https://github.com/AstroGypsophila
 * Date   : 2016/8/28
 */
public interface OnLoadNewsListListener {

    void onSuccess(List<NewsBean> list);

    void onFailure(String msg, Exception e);
}
