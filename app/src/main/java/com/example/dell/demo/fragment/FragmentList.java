package com.example.dell.demo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
public abstract class FragmentList<T> extends Fragment implements AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener,
        AdapterView.OnItemClickListener, View.OnTouchListener {
    public int currentPage = 0;//当前页码
    public int pageSize = 10;//页面大小默认是10
    public MagicAdapter<T> adapter = null;
    public ListView mListView = null;
    private SwipeRefreshLayout swipeRefreshLayout = null;
    private static int STA_US_REFRESH = -1;//刷新状态
    private static int STA_US_NORMAL = 0;//正常状态
    private static int STA_US_LOADING = 1;//加载中
    private static int mState = STA_US_NORMAL;//标识当前列表状态
    private ViewGroup headerView = null;//头部视图

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
        setHeaderView();
        mListView.setAdapter(adapter);
        mListView.setOnScrollListener(this);
        mListView.setOnTouchListener(this);
        mListView.setOnItemClickListener(this);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //处理刷新事件
        return mState == STA_US_REFRESH;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (view != adapter.getFooterView()) {
            onItemClicked(parent, view, position, id);
        } else {
            if (mState != STA_US_LOADING) {
                currentPage++;
                mState = STA_US_LOADING;
                requestData(false);
                adapter.setFooterViewLoading();
            }
        }
    }

    private void setHeaderView() {
        ViewGroup view = getHeaderView();
        if (view != null) {
            mListView.addHeaderView(view);
        }
    }

    /**
     * 可以设置listView的header
     * 如果该方法返回@nil则不会添加
     *
     * @return
     */
    public ViewGroup getHeaderView() {
        return null;
    }

    /**
     * 当数据加载完成
     *
     * @param data
     */
    protected void loadingData(List<T> data) {
        int adapterState = MagicAdapter.STA_NO_DATA;
        swipeRefreshLayout.setRefreshing(false);
        if (data == null) {
            data = new ArrayList<T>();
        }

        if ((adapter.getCount() + data.size()) == 0) {
            adapterState = MagicAdapter.STA_NO_DATA;
            ALog.d("数据加载完成:no data");
        } else if (data.size() == 0
                || (data.size() < pageSize && currentPage == 0)) {
            ALog.d("数据加载完成:all data loaded");
            adapterState = MagicAdapter.STA_ALL_LOADED;
        } else {
            ALog.d("数据加载完成:load more data");
            adapterState = MagicAdapter.STA_LOAD_MORE;
        }
        adapter.setStatus(adapterState);
        if (currentPage == 0 || mState == STA_US_REFRESH) {//刷新数据，清除数据
            adapter.removeAll();
            adapter.addData(data);
            currentPage = mState == STA_US_REFRESH ? 0 : currentPage;
        } else {
            adapter.addData(data);
        }
        ALog.d("current page:" + currentPage);
        mState = STA_US_NORMAL;
    }

    /**
     * 加载数据错误（包含数据异常和网络等错误）
     */
    protected void loadError() {
        ALog.d("加载异常");
        mState = STA_US_NORMAL;
        swipeRefreshLayout.setRefreshing(false);
        adapter.setStatus(MagicAdapter.STA_NET_ERROR);
        adapter.setFooterViewText("加载错误点击重试", false);
    }

    @Override
    public void onRefresh() {
        if (mState != STA_US_NORMAL) {
            swipeRefreshLayout.setRefreshing(false);
        } else {
            mState = STA_US_REFRESH;
            requestData(true);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (adapter == null || adapter.getCount() == 0) {
            return;
        }
        // 数据已经全部加载，或数据为空时，或正在加载，不处理滚动事件
        if (adapter.getStatus() == MagicAdapter.STA_NO_DATA || adapter.getStatus() == MagicAdapter.STA_ALL_LOADED
                || adapter.getStatus() == MagicAdapter.STA_LOADING || mState == STA_US_REFRESH) {
            adapter.setFooterViewText("点击加载更多", false);
            return;
        }
        boolean scrollBottom = false;
        try {
            if (view.getPositionForView(adapter.getFooterView()) == view
                    .getLastVisiblePosition())
                scrollBottom = true;
        } catch (Exception e) {
            scrollBottom = false;
        }

        if (scrollBottom && mState != STA_US_LOADING) {
            if (adapter.getStatus() == MagicAdapter.STA_LOAD_MORE || adapter.getStatus() == MagicAdapter.STA_NET_ERROR) {
                currentPage++;
                mState = STA_US_LOADING;
                requestData(false);
                adapter.setFooterViewLoading();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        ALog.d("firstVisibleItem:" + firstVisibleItem + "visibleItemCount:" + visibleItemCount + "totalItemCount:" + totalItemCount);
    }

    /**
     * 数据请求回调
     *
     * @param isRefresh
     */
    protected abstract void requestData(boolean isRefresh);

    /**
     * 处理item点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    public abstract void onItemClicked(AdapterView<?> parent, View view, int position, long id);

    /**
     * 初始化ui操作
     */
    public abstract void withView();

    /**
     * 设置adapter
     *
     * @return
     */
    public abstract MagicAdapter<T> createAdapter();

    /**
     * 获取当前页码
     *
     * @return
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 获取加载条目
     *
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置加载条目
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
