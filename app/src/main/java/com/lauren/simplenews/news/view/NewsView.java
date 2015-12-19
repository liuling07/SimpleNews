package com.lauren.simplenews.news.view;

import com.lauren.simplenews.beans.NewsBean;

import java.util.List;

/**
 * @Class:
 * @Description:
 * @author: lling(www.liuling123.com)
 * @Date: 2015/12/18
 */
public interface NewsView {

    void showProgress();

    void showNews(List<NewsBean> newsList);

    void addNews(List<NewsBean> newsList);

    void hideProgress();
}
