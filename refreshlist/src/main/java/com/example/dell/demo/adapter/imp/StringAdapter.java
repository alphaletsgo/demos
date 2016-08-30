package com.example.dell.demo.adapter.imp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.demo.R;
import com.example.dell.demo.adapter.MagicAdapter;

/**
 * Created by dell on 2016/4/29.
 */
public class StringAdapter extends MagicAdapter<String> {
    Context context = null;

    public StringAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getLoadView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null || convertView.getTag() == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_string, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.cellText = (TextView) convertView.findViewById(R.id.cell_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.cellText.setText(getDataByIndex(position));
        return convertView;
    }

    class ViewHolder {
        TextView cellText;
    }
}
