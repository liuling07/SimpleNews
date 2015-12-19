package com.lauren.simplenews.news.model;

import com.lauren.simplenews.utils.OkHttpUtils;

import org.junit.Test;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/19
 */
public class NewsModelImplTest {

    @Test
    public void testLoadNews() throws Exception {
        new NewsModelImpl().loadNews("http://c.m.163.com/nc/article/headline/T1348647909107/0-5.html",
                new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {

                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });

    }
}