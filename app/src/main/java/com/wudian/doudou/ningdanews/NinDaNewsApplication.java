package com.wudian.doudou.ningdanews;

import android.app.Application;

import org.xutils.x;

/**
 * Created by doudou on 16/1/23.
 */
public class NinDaNewsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);//是否输出日志
    }
}
