package com.lauren.simplenews.news.widget;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lauren.simplenews.R;
import com.lauren.simplenews.beans.NewsBean;
import com.lauren.simplenews.commons.Urls;
import com.lauren.simplenews.news.presenter.NewsPresenter;
import com.lauren.simplenews.news.presenter.NewsPresenterImpl;
import com.lauren.simplenews.news.view.NewsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description : 头条新闻Fragment
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/13
 */
public class NewsListFragment extends Fragment implements NewsView, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private List<NewsBean> mData;
    private NewsPresenter mNewsPresenter;

    private int mType = NewsFragment.NEWS_TYPE_TOP;
    private int pageIndex = 0;

    public static NewsListFragment newInstance(int type) {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsPresenter = new NewsPresenterImpl(getContext(), this);
        mType = getArguments().getInt("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnScrollListener(mOnScrollListener);
        onRefresh();
        return view;
    }

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
                    && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                //加载更多
                mNewsPresenter.loadNews(mType, pageIndex + Urls.PAZE_SIZE);
            }
        }
    };

    @Override
    public void showProgress() {
        mSwipeRefreshWidget.setRefreshing(true);
    }

    @Override
    public void showNews(List<NewsBean> newsList) {
        mData = newsList;
        if(mData == null) {
            mData = new ArrayList<NewsBean>();
        }
        mAdapter.setmDate(mData);
    }

    @Override
    public void addNews(List<NewsBean> newsList) {
        if(mData == null) {
            mData = new ArrayList<NewsBean>();
        }
        mData.addAll(newsList);
        mAdapter.notifyDataSetChanged();
        pageIndex += Urls.PAZE_SIZE;
    }


    @Override
    public void hideProgress() {
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        mNewsPresenter.loadNews(mType, pageIndex);
    }

    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int TYPE_ITEM = 0;
        private static final int TYPE_FOOTER = 1;

        private List<NewsBean> mDate;


        public MyAdapter() {
        }

        public void setmDate(List<NewsBean> mDate) {
            this.mDate = mDate;
            this.notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            // 最后一个item设置为footerView
            if (position + 1 == getItemCount()) {
                return TYPE_FOOTER;
            } else {
                return TYPE_ITEM;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            if(viewType == TYPE_ITEM) {
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_news, parent, false);
                ItemViewHolder vh = new ItemViewHolder(v);
                return vh;
            } else {
                View view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.footer, null);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                return new FooterViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof ItemViewHolder) {

                NewsBean news = mDate.get(position);
                if(news == null) {
                    return;
                }
                ((ItemViewHolder) holder).mTitle.setText(news.getTitle());
                ((ItemViewHolder) holder).mDesc.setText(news.getDigest());
                Uri uri = Uri.parse(news.getImgsrc());
                ((ItemViewHolder) holder).mNewsImg.setImageURI(uri);
            }
        }

        @Override
        public int getItemCount() {
            if(mDate == null) {
                return 1;
            }
            return mDate.size() + 1;
        }

        public class FooterViewHolder extends RecyclerView.ViewHolder {

            public FooterViewHolder(View view) {
                super(view);
            }

        }

        public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public TextView mTitle;
            public TextView mDesc;
            public SimpleDraweeView mNewsImg;

            public ItemViewHolder(View v) {
                super(v);
                mTitle = (TextView) v.findViewById(R.id.tvTitle);
                mDesc = (TextView) v.findViewById(R.id.tvDesc);
                mNewsImg = (SimpleDraweeView) v.findViewById(R.id.ivNews);
                v.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                String text = "I Love " + mTitle.getText() + ".";
                Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
            }
        }

    }
}
