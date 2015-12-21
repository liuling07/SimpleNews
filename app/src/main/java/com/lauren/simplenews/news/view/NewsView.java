package com.lauren.simplenews.news.view;

import com.lauren.simplenews.beans.NewsBean;

import java.util.List;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/18
 */
public interface NewsView {

    void showProgress();

    void showNews(List<NewsBean> newsList);

    void addNews(List<NewsBean> newsList);

    void hideProgress();

    void showLoadFailMsg();
}
