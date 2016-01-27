package com.wudian.doudou.ningdanews.base;

import android.app.Activity;
import android.view.View;

/**
 * 页签页面基类
 * Created by doudou on 16/1/26.
 */
public abstract class TabDetailBasePager {

    public View mRootView;
    public Activity mActivity;

    public TabDetailBasePager(Activity activity) {
        this.mActivity = activity;
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
