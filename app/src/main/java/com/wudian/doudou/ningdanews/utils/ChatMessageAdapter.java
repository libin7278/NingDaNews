package com.wudian.doudou.ningdanews.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wudian.doudou.ningdanews.R;
import com.wudian.doudou.ningdanews.domain.ChatMessage;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by doudou on 16/2/21.
 */
public class ChatMessageAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ChatMessage> mDatas;

    public ChatMessageAdapter(Context context, List<ChatMessage> mDatas) {
        mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessage = mDatas.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            if (getItemViewType(position) == 0) {
                convertView = mInflater.inflate(R.layout.item_from, parent, false);
                viewHolder = new ViewHolder();

                viewHolder.mData = (TextView) convertView.findViewById(R.id.tv_from_data);
                viewHolder.mMsg = (TextView) convertView.findViewById(R.id.tv_content_from);
            } else {
                convertView = mInflater.inflate(R.layout.item_to, parent, false);
                viewHolder = new ViewHolder();

                viewHolder.mData = (TextView) convertView.findViewById(R.id.tv_to_data);
                viewHolder.mMsg = (TextView) convertView.findViewById(R.id.to_msg_info);
            }

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        viewHolder.mData.setText(df.format(chatMessage.getDate()));

        viewHolder.mMsg.setText(chatMessage.getMsg());
        return convertView;
    }


    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage = mDatas.get(position);
        if (chatMessage.getType() == ChatMessage.Type.INCOMING) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    static class ViewHolder {
        TextView mData;
        TextView mMsg;

    }
}
