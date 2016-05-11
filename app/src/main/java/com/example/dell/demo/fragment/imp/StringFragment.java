package com.example.dell.demo.fragment.imp;


import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;

import com.example.dell.demo.adapter.MagicAdapter;
import com.example.dell.demo.adapter.imp.StringAdapter;
import com.example.dell.demo.fragment.FragmentList;

import java.util.ArrayList;
import java.util.Random;

import cn.isif.alibs.utils.log.ALog;

/**
 * Created by dell on 2016/5/4.
 */
public class StringFragment extends FragmentList {
    public static String[] data = {"one", "two", "three", "four", "five", "six", "sven", "lai", "deo", "fre", "tec", "tep", "oop"};
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    ArrayList<String> datas = (ArrayList<String>) msg.obj;
                    loadingData(datas);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public View getHeaderView() {
        return null;
    }

    @Override
    public void withView() {
        showRefreshView();
        requestData(false);

    }

    @Override
    public MagicAdapter createAdapter() {
        return new StringAdapter(this.getActivity());
    }

    @Override
    protected void requestData(boolean isRefresh) {
        requestData();
    }


    @Override
    public void onItemClicked(AdapterView parent, View view, int position, long id) {
        ALog.d(""+position);
    }

    public static ArrayList<String> getData() {
        ArrayList<String> d = new ArrayList<>();
        for (String s : data) {
            d.add(s);
        }
        return d;
    }

    public void requestData() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep((new Random().nextInt(5)) * 1000);
                    Message message = mHandler.obtainMessage();
                    message.obj = getData();
                    message.what = 1;
                    mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
    }
}
