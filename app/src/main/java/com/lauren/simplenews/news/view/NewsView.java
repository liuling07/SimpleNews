package com.lauren.simplenews.news.view;

import java.util.List;

/**
 * @Class:
 * @Description:
 * @author: lling(www.liuling123.com)
 * @Date: 2015/12/18
 */
public interface NewsView {

    void showProgress();

    void showNews(List newsList);

    void hideProgress();
}
