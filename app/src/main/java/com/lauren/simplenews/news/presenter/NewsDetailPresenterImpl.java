package com.lauren.simplenews.news.presenter;

import android.content.Context;

import com.lauren.simplenews.beans.NewsDetailBean;
import com.lauren.simplenews.commons.Urls;
import com.lauren.simplenews.news.NewsJsonUtils;
import com.lauren.simplenews.news.model.NewsModel;
import com.lauren.simplenews.news.model.NewsModelImpl;
import com.lauren.simplenews.news.view.NewsDetailView;
import com.lauren.simplenews.utils.OkHttpUtils;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 2015/12/21
 */
public class NewsDetailPresenterImpl implements NewsDetailPresenter {

    private Context mContent;
    private NewsDetailView mNewsDetailView;
    private NewsModel mNewsModel;

    public NewsDetailPresenterImpl(Context mContent, NewsDetailView mNewsDetailView) {
        this.mContent = mContent;
        this.mNewsDetailView = mNewsDetailView;
        mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNewsDetail(final String docId) {
        mNewsDetailView.showProgress();
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                NewsDetailBean newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response, docId);
                if(newsDetailBean != null) {
                    mNewsDetailView.showNewsDetialContent(newsDetailBean.getBody());
                }
                mNewsDetailView.hideProgress();
            }

            @Override
            public void onFailure(Exception e) {
                mNewsDetailView.hideProgress();
            }
        };
        mNewsModel.loadNewsDetail(getDetailUrl(docId), loadNewsCallback);
    }

    private String getDetailUrl(String docId) {
        StringBuffer sb = new StringBuffer(Urls.NEW_DETAIL);
        sb.append(docId).append(Urls.END_DETAIL_URL);
        return sb.toString();
    }
}
