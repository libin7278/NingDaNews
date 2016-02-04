package com.wudian.doudou.ningdanews.pager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wudian.doudou.ningdanews.R;
import com.wudian.doudou.ningdanews.base.BasePager;
import com.wudian.doudou.ningdanews.pager.myselfdetailpager.OptionRefectActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by doudou on 16/1/13.
 */
public class MyselfPager extends BasePager implements View.OnClickListener {
    @ViewInject(R.id.tv_title_myself_denglu)
    private TextView tv_title_myself_denglu;

    @ViewInject(R.id.tv_myself_wodexiaoxi)
    private TextView tv_myself_wodexiaoxi;

    @ViewInject(R.id.tv_myself_wodeshoucan)
    private TextView tv_myself_wodeshoucan;

    @ViewInject(R.id.tv_myself_qingchuhuancun)
    private TextView tv_myself_qingchuhuancun;

    @ViewInject(R.id.tv_myself_yijianfankui)
    private TextView tv_myself_yijianfankui;

    @ViewInject(R.id.tv_muself_yanfarenyuan)
    private TextView tv_muself_yanfarenyuan;

    @ViewInject(R.id.tv_myself_guanyuwomen)
    private TextView tv_myself_guanyuwomen;

    public MyselfPager(Activity activity) {
        super(activity);
    }

    @Override
    protected View getView() {
        View view = View.inflate(mActivity, R.layout.myself_detail_pager, null);
        x.view().inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        Log.e("TAG", "我页面的内容页被初始化了");
        Toast.makeText(mActivity, "我页面的内容页被初始化了", Toast.LENGTH_SHORT).show();

        initSetOnClickListener();
        tv_muself_yanfarenyuan.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    private void initSetOnClickListener() {
        tv_title_myself_denglu.setOnClickListener(this);
        tv_myself_wodexiaoxi.setOnClickListener(this);
        tv_myself_wodeshoucan.setOnClickListener(this);
        tv_myself_qingchuhuancun.setOnClickListener(this);
        tv_myself_yijianfankui.setOnClickListener(this);
        tv_myself_guanyuwomen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tv_title_myself_denglu) {
            Toast.makeText(mActivity, "登陆", Toast.LENGTH_SHORT).show();
        } else if (v == tv_myself_wodexiaoxi) {
            Toast.makeText(mActivity, "我的消息", Toast.LENGTH_SHORT).show();
        } else if (v == tv_myself_wodeshoucan) {
            Toast.makeText(mActivity, "我的收藏", Toast.LENGTH_SHORT).show();
        } else if (v == tv_myself_qingchuhuancun) {
            Toast.makeText(mActivity, "缓存清除成功", Toast.LENGTH_SHORT).show();
        } else if (v == tv_myself_yijianfankui) {
            Intent intent = new Intent(mActivity, OptionRefectActivity.class);
            mActivity.startActivity(intent);
            //Toast.makeText(mActivity, "意见反馈", Toast.LENGTH_SHORT).show();
        } else if (v == tv_myself_guanyuwomen) {
            Toast.makeText(mActivity, "关于我们", Toast.LENGTH_SHORT).show();
        }
    }
}
