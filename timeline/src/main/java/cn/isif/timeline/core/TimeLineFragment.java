package cn.isif.timeline.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


import java.util.ArrayList;
import java.util.List;

import cn.isif.timeline.R;
import cn.isif.timeline.core.entity.ChildStatusEntity;
import cn.isif.timeline.core.entity.GroupStatusEntity;

/**
 * Created by dell on 2016/8/30-10:32.
 */
public class TimeLineFragment extends Fragment {
    private ExpandableListView expandableListView;
    private View contentView;
    StatusExpandAdapter statusAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (contentView==null){
            contentView = inflater.inflate(R.layout.fragment_time_line, container, false);
            expandableListView = (ExpandableListView)contentView.findViewById(R.id.elv);
        }
        ViewGroup parent = (ViewGroup) contentView.getParent();
        if (parent != null) {
            parent.removeView(contentView);
        }
        initExpandListView();
        return contentView;
    }
    /**
     * 初始化可拓展列表
     */
    private void initExpandListView() {
        statusAdapter = new StatusExpandAdapter(getContext(), getListData());
        expandableListView.setAdapter(statusAdapter);
        expandableListView.setGroupIndicator(null); // 去掉默认带的箭头
        expandableListView.setSelection(0);// 设置默认选中项

        // 遍历所有group,将所有项设置成默认展开
        int groupCount = expandableListView.getCount();
        for (int i = 0; i < groupCount; i++) {
            expandableListView.expandGroup(i);
        }

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // TODO Auto-generated method stub
                return true;
            }
        });
    }

    private List<GroupStatusEntity> getListData() {
        List<GroupStatusEntity> groupList;
        String[] strArray = new String[] { "2016-06-11", "2016-07-11", "2016-08-11","2016-09-11" };
        String[][] childTimeArray = new String[][] {
                { "俯卧撑十次", "仰卧起坐二十次" },
                { "亲，快快滴点赞哦~" }, { "没有赞的，赶紧去赞哦~" },{ "大喊我爱紫豪二十次", "每日赞紫豪一次"} };
        groupList = new ArrayList<GroupStatusEntity>();
        for (int i = 0; i < strArray.length; i++) {
            GroupStatusEntity groupStatusEntity = new GroupStatusEntity();
            groupStatusEntity.setGroupName(strArray[i]);

            List<ChildStatusEntity> childList = new ArrayList<ChildStatusEntity>();

            for (int j = 0; j < childTimeArray[i].length; j++) {
                ChildStatusEntity childStatusEntity = new ChildStatusEntity();
                childStatusEntity.setCompleteTime(childTimeArray[i][j]);
                childStatusEntity.setIsfinished(true);
                childList.add(childStatusEntity);
            }

            groupStatusEntity.setChildList(childList);
            groupList.add(groupStatusEntity);
        }
        return groupList;
    }
}
