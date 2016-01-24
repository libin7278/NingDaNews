package com.wudian.doudou.ningdanews.base;

import android.app.Activity;
import android.view.View;


/**
 * Created by doudou on 16/1/13.
 */
/**
 * 各个页面的基类
 * Created by Jian Chang on 2016-01-11.
 */
public abstract class BasePager {

    public View mRootView;
    public Activity mActivity;

    public BasePager(Activity activity) {
        mActivity = activity;
        mRootView = getView();
    }

    /**
     * 获取当前详情页面视图, 强制子类实现
     */
    protected abstract View getView();

    /**
     * 初始化方法, 留给子类实现, 在页面加载后调用
     */
    public void initData() {
    }

}