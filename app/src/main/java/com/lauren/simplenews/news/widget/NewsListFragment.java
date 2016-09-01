package com.lauren.simplenews.news.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lauren.simplenews.R;
import com.lauren.simplenews.beans.NewsBean;
import com.lauren.simplenews.commons.Urls;
import com.lauren.simplenews.news.NewsAdapter;
import com.lauren.simplenews.news.presenter.NewsPresenter;
import com.lauren.simplenews.news.presenter.NewsPresenterImpl;
import com.lauren.simplenews.news.view.NewsView;
import com.lauren.simplenews.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class NewsListFragment extends BaseLazyFragment implements NewsView,SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG = "NewsListFragment";

    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private NewsAdapter mAdapter;
    private List<NewsBean> mData;
    private NewsPresenter mNewsPresenter;

    private int mType = NewsFragment.NEWS_TYPE_TOP;
    private int pageIndex = 0;


    //---------------------abstract method------------------------//

    @Override
    protected void initPrepare() {
        mNewsPresenter = new NewsPresenterImpl(this);
        mType = getArguments().getInt("type");
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {
        onRefresh();
//        mNewsPresenter.loadNews(mType, pageIndex + Urls.PAZE_SIZE);
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newslist, null);

        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        mSwipeRefreshWidget.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewsAdapter(getActivity().getApplicationContext());
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        return view;
    }


    public static NewsListFragment newInstance(int type) {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    //-----------------------------call back----------------------------------//

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mAdapter.getItemCount()
                    && mAdapter.isShowFooter()) {
                //加载更多
                LogUtils.d(TAG, "loading more data");
                mNewsPresenter.loadNews(mType, pageIndex + Urls.PAZE_SIZE);
            }
        }
    };

    private NewsAdapter.OnItemClickListener mOnItemClickListener = new NewsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            NewsBean news = mAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            intent.putExtra("news", news);

            View transitionView = view.findViewById(R.id.ivNews);
            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                            transitionView, getString(R.string.transition_news_img));

            ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
        }
    };

    @Override
    public void showProgress() {
        mSwipeRefreshWidget.setRefreshing(true);
    }

    @Override
    public void addNews(List<NewsBean> newsList) {
        mAdapter.isShowFooter(true);
        if(mData == null) {
            mData = new ArrayList<NewsBean>();
        }
        mData.addAll(newsList);
        if(pageIndex == 0) {
            mAdapter.setmDate(mData);
        } else {
            //如果没有更多数据了,则隐藏footer布局
            if(newsList == null || newsList.size() == 0) {
                mAdapter.isShowFooter(false);
            }
            mAdapter.notifyDataSetChanged();
        }
        pageIndex += Urls.PAZE_SIZE;
    }


    @Override
    public void hideProgress() {
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {
        if(pageIndex == 0) {
            mAdapter.isShowFooter(false);
            mAdapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? mRecyclerView.getRootView() : getActivity().findViewById(R.id.drawer_layout);
        Snackbar.make(view, getString(R.string.load_fail), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        if(mData != null) {
            mData.clear();
        }
        mNewsPresenter.loadNews(mType, pageIndex);
    }

    @Override
    public void onDestroy() {
        mNewsPresenter = null;
        super.onDestroy();
    }
}
