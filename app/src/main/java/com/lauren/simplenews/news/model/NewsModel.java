package com.lauren.simplenews.news.model;

import com.lauren.simplenews.utils.OkHttpUtils;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/19
 */
public interface NewsModel {

    void loadNews(String url, OkHttpUtils.ResultCallback callback);

}
