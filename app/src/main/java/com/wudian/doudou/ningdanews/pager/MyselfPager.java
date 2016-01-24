package com.wudian.doudou.ningdanews.pager;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wudian.doudou.ningdanews.base.BasePager;

/**
 * Created by doudou on 16/1/13.
 */
public class MyselfPager extends BasePager {


    public MyselfPager(Activity activity) {
        super(activity);
    }

    @Override
    protected View getView() {
        TextView textView = new TextView(mActivity);
        textView.setText("我页面的内容页");
        textView.setTextSize(35);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        Log.e("TAG","我页面的内容页被初始化了");
        Toast.makeText(mActivity,"我页面的内容页被初始化了", Toast.LENGTH_SHORT).show();
    }
}
