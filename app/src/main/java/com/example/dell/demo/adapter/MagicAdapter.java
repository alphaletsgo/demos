package com.example.dell.demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.dell.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/4/15.
 */
public abstract class MagicAdapter<T> extends BaseAdapter {
    private List<T> data = new ArrayList<T>();
    public static final int STA_NO_DATA = 0;//没有数据
    public static final int STA_LOAD_MORE = 1;//加载更多
    public static final int STA_CLICK_LOAD_MORE = -1;//点击加载更多
    public static final int STA_ALL_LOADED = 2;//数据加载完毕
    public static final int STA_GET_ERROR = -2;//获取数据失败
    public static final int STA_LOADING = 3;//正在加载中
    private final String noData = "没有数据";
    private final String netError = "网络异常";
    private final String loadMore = "加载更多";
    private final String loadMoreByClick = "点击加载更多";
    private final String allLoaded = "已加载全部";
    private final String getError = "数据获取错误";
    private final String loading = "正在加载中";
    protected int mStatus = STA_NO_DATA;
    private LinearLayout footerView = null;


    @Override
    public int getCount() {
        switch (mStatus) {
            case STA_NO_DATA:
                return 1;
            case STA_CLICK_LOAD_MORE:
            case STA_LOAD_MORE:
            case STA_ALL_LOADED:
            case STA_GET_ERROR:
            case STA_LOADING:
                return getSize() + 1;
            default:
                getSize();
                break;
        }
        return getSize();
    }

    @Override
    public Object getItem(int position) {
        return getDataByIndex(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == getSize()) {
            footerView = (LinearLayout) LayoutInflater.from(
                    parent.getContext()).inflate(R.layout.footer_refresh_layout,
                    null);
            ProgressBar progress = (ProgressBar) footerView
                    .findViewById(R.id.progressBar);
            TextView text = (TextView) footerView.findViewById(R.id.state_info);
            switch (mStatus) {
                case STA_NO_DATA://没有数据
                    footerView.setVisibility(View.VISIBLE);
                    progress.setVisibility(View.GONE);
                    text.setVisibility(View.GONE);
                    text.setText(noData);
                    break;
                case STA_LOAD_MORE://加载更多
                    setFooterViewLoading();
                    break;
                case STA_ALL_LOADED://已加载全部
                    footerView.setVisibility(View.VISIBLE);
                    progress.setVisibility(View.GONE);
                    text.setVisibility(View.VISIBLE);
                    text.setText(allLoaded);
                    break;
                case STA_GET_ERROR://获取数据异常
                    footerView.setVisibility(View.VISIBLE);
                    progress.setVisibility(View.GONE);
                    text.setVisibility(View.VISIBLE);
                    text.setText(getError);
                    break;
                case STA_LOADING://正在加载中...
                    footerView.setVisibility(View.VISIBLE);
                    progress.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);
                    text.setText(loading);
                    break;
                case STA_CLICK_LOAD_MORE:
                default://默认不显示
                    footerView.setVisibility(View.VISIBLE);
                    progress.setVisibility(View.GONE);
                    text.setVisibility(View.VISIBLE);
                    text.setText(loadMoreByClick);
                    break;
            }
            return footerView;
        }
        if (position < 0) {
            position = 0;
        }
        return getLoadView(position, convertView, parent);
    }

    public abstract View getLoadView(int position, View convertView, ViewGroup parent);


    //======== 数据操作=======//
    public void addData(List<T> data) {
        if (data == null) return;
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(T obj) {
        if (obj == null) return;
        this.data.add(obj);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.data.clear();
        notifyDataSetChanged();
    }

    public T getDataByIndex(int position) {
        if (data.size() > position) {
            return data.get(position);
        } else {
            return null;
        }
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getSize() {
        return this.data.size();
    }

    public void setStatus(int status) {
        this.mStatus = status;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public View getFooterView() {
        return footerView;
    }

    public void setFooterViewLoadMore() {
        setFooterViewText(loadMore, false);
    }

    public void setFooterViewLoading() {
        setFooterViewText(loading, true);
    }

    public void setFooterViewGetError() {
        setFooterViewText(getError, false);
    }

    public void setFooterViewNetError() {
        setFooterViewText(netError, false);
    }

    /**
     * 自定义底部显示文字
     *
     * @param msg
     * @param running
     */
    public void setFooterViewText(String msg, boolean running) {
        if (footerView == null) return;
        ProgressBar progress = (ProgressBar) footerView
                .findViewById(R.id.progressBar);
        TextView text = (TextView) footerView.findViewById(R.id.state_info);
        footerView.setVisibility(View.VISIBLE);
        progress.setVisibility(running ? View.VISIBLE : View.GONE);
        text.setVisibility(View.VISIBLE);
        text.setText(msg);
    }

}
