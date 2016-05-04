package com.example.dell.demo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dell.demo.R;
import com.example.dell.demo.adapter.MagicAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.isif.alibs.utils.log.ALog;

/**
 * Created by dell on 2016/4/29.
 */
public abstract class FragmentList<T> extends Fragment implements AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    public int currentPage = 0;//当前页码
    public int pageSize = 10;//页面大小默认是10
    public MagicAdapter<T> adapter = null;
    public ListView mListView = null;
    private SwipeRefreshLayout swipeRefreshLayout = null;
    private static int STA_US_REFRESH = -1;
    private static int STA_US_NORMAL = 0;
    private static int STA_US_LOADING = 1;
    private static int mState = STA_US_NORMAL;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layoutView = loadLayout(inflater);
        initView(layoutView);
        withView();
        return layoutView;
    }

    public View loadLayout(LayoutInflater inflater) {
        return inflater.inflate(R.layout.layout_list, null);
    }

    public void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.list);
        adapter = createAdapter();
        mListView.setAdapter(adapter);
        mListView.setOnScrollListener(this);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public abstract void withView();

    public abstract MagicAdapter<T> createAdapter();


    /**
     * 当数据加载完成
     *
     * @param data
     */
    protected void loadingData(List<T> data) {
        ALog.d("数据加载完成:" + data.size());
        swipeRefreshLayout.setRefreshing(false);
        if (data == null) {
            data = new ArrayList<T>();
        }
        ALog.d("PageSize:" + currentPage);
        if (currentPage == 0 || mState == STA_US_REFRESH) {//刷新数据，清除数据
            ALog.d("清除数据");
            adapter.removeAll();
        }
        int adapterState = MagicAdapter.STA_NO_DATA;
        if ((adapter.getCount() + data.size()) == 0) {
            adapterState = MagicAdapter.STA_NO_DATA;
            ALog.d("数据加载完成:no data");
        } else if (data.size() == 0
                || (data.size() < pageSize && currentPage == 0)) {
            ALog.d("数据加载完成:all data loaded");
            adapterState = MagicAdapter.STA_ALL_LOADED;
            adapter.notifyDataSetChanged();
        } else {
            ALog.d("数据加载完成:load more data");
            adapterState = MagicAdapter.STA_LOAD_MORE;
        }
        adapter.setStatus(adapterState);
        adapter.addData(data);
        // 判断等于是因为最后有一项是listview的状态
        if (adapter.getCount() == 1) {
            adapter.setStatus(MagicAdapter.STA_NO_DATA);
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 加载数据错误（包含数据异常和网络等错误）
     */
    protected void loadError() {
        ALog.d("加载异常");
        mState = STA_US_NORMAL;
        swipeRefreshLayout.setRefreshing(false);
        adapter.setStatus(MagicAdapter.STA_NET_ERROR);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        mState = STA_US_REFRESH;
        requestData(true);
    }

    protected abstract void requestData(boolean isRefresh);


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (adapter == null || adapter.getCount() == 0) {
            return;
        }
        // 数据已经全部加载，或数据为空时，或正在加载，不处理滚动事件
        if (adapter.getStatus() == MagicAdapter.STA_NO_DATA || adapter.getStatus() == MagicAdapter.STA_ALL_LOADED
                || adapter.getStatus() == MagicAdapter.STA_LOADING) {
            return;
        }
        boolean scrollEnd = false;
        try {
            if (view.getPositionForView(adapter.getFooterView()) == view
                    .getLastVisiblePosition())
                ALog.d("滑动到底部");
            scrollEnd = true;
        } catch (Exception e) {
            scrollEnd = false;
        }

        if (scrollEnd) {
            if (adapter.getStatus() == MagicAdapter.STA_LOAD_MORE || adapter.getStatus() == MagicAdapter.STA_NET_ERROR) {
                currentPage++;
                adapter.setStatus(MagicAdapter.STA_LOADING);
                mState = STA_US_LOADING;
                requestData(false);
                adapter.setFooterViewLoading();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    public void showRefreshView(){
        swipeRefreshLayout.setRefreshing(true);
    }
}
