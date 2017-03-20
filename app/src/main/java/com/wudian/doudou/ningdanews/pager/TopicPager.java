package com.wudian.doudou.ningdanews.pager;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.wudian.doudou.ningdanews.R;
import com.wudian.doudou.ningdanews.base.BasePager;
import com.wudian.doudou.ningdanews.domain.ChatMessage;
import com.wudian.doudou.ningdanews.utils.ChatMessageAdapter;
import com.wudian.doudou.ningdanews.utils.HttpUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by doudou on 16/1/13.
 */
public class TopicPager extends BasePager {

    private ListView mMsgs;
    private List<ChatMessage> mDatas;
    private ChatMessageAdapter madapter;

    private EditText mInputMsg;
    private Button mSendMsg;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ChatMessage fromMessage = (ChatMessage) msg.obj;
            mDatas.add(fromMessage);

            madapter.notifyDataSetChanged();
            mMsgs.setSelection(madapter.getCount());
        }
    };

    public TopicPager(Activity activity) {
        super(activity);
    }

    @Override
    protected View getView() {
        View view = View.inflate(mActivity, R.layout.topic_layout,null);

        mMsgs = (ListView) view.findViewById(R.id.lv_msgs);
        mInputMsg = (EditText) view.findViewById(R.id.et_input_msg);
        mSendMsg = (Button) view.findViewById(R.id.btn_send_msg);

        return view;
    }

    @Override
    public void initData() {


        mDatas = new ArrayList<ChatMessage>();
        mDatas.add(new ChatMessage("您好,欢迎使用智慧宁大,我是Angelababy,很高兴为您服务!", ChatMessage.Type.INCOMING, new Date()));

        madapter = new ChatMessageAdapter(mActivity, mDatas);

        mMsgs.setAdapter(madapter);

        //初始化事件
        initListener();
    }

    private void initListener() {
        mSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String toMsg = mInputMsg.getText().toString();

                if (TextUtils.isEmpty(toMsg)) {
                    Toast.makeText(mActivity, "发送消息不能为空", Toast.LENGTH_SHORT).show();

                    return;
                }

                ChatMessage toMessage = new ChatMessage();
                toMessage.setDate(new Date());
                toMessage.setMsg(toMsg);
                toMessage.setType(ChatMessage.Type.INCOMING.OUTCOMING);
                mDatas.add(toMessage);

                madapter.notifyDataSetChanged();

                mMsgs.setSelection(madapter.getCount());

                mInputMsg.setText("");

                new Thread() {
                    @Override
                    public void run() {
                        ChatMessage fromMessage = HttpUtils.sendMessage(toMsg);

                        Message m = Message.obtain();
                        m.obj=fromMessage;
                        mHandler.sendMessage(m);
                    }
                }.start();

            }

        });
    }
}
