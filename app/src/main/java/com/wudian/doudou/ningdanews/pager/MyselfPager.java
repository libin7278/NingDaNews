package com.wudian.doudou.ningdanews.pager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wudian.doudou.ningdanews.R;
import com.wudian.doudou.ningdanews.base.BasePager;
import com.wudian.doudou.ningdanews.pager.myselfdetailpager.AboutUsActivity;
import com.wudian.doudou.ningdanews.pager.myselfdetailpager.LoginLlActivity;
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
            Intent intent = new Intent(mActivity, LoginLlActivity.class);
            mActivity.startActivity(intent);
        } else if (v == tv_myself_wodexiaoxi) {
            Toast.makeText(mActivity, "暂无消息,哦哦...", Toast.LENGTH_SHORT).show();
        } else if (v == tv_myself_wodeshoucan) {
            Toast.makeText(mActivity, "暂无收藏,哦哦...", Toast.LENGTH_SHORT).show();
        } else if (v == tv_myself_qingchuhuancun) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    new AlertDialog.Builder(mActivity)
                            .setTitle("清除缓存")
                            .setMessage("亲,恭喜成功缓存清除...")
                            .setPositiveButton("确定",null)
                            .show();
                }
            },1500);
            Toast.makeText(mActivity, "清理中....请稍后...", Toast.LENGTH_SHORT).show();
        } else if (v == tv_myself_yijianfankui) {
            Intent intent = new Intent(mActivity, OptionRefectActivity.class);
            mActivity.startActivity(intent);
            //Toast.makeText(mActivity, "意见反馈", Toast.LENGTH_SHORT).show();
        } else if (v == tv_myself_guanyuwomen) {
            Intent intent = new Intent(mActivity, AboutUsActivity.class);
            mActivity.startActivity(intent);
        }
    }
}
