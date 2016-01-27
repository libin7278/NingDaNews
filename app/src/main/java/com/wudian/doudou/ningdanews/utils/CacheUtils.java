package com.wudian.doudou.ningdanews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by doudou on 16/1/11.
 */
public class CacheUtils {

    /**
     * 保存软件参数
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context,String key , boolean value) {
        SharedPreferences sp = context.getSharedPreferences("wudianxinwen", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 读取软件参数
     * @param context
     * @param key
     */
    public static boolean getBoolean(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences("wudianxinwen", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    /**
     * 保存软件缓存数据
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context,String key , String value) {
        SharedPreferences sp = context.getSharedPreferences("wudianxinwen", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    /**
     * 得到软件缓存数据
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences("wudianxinwen", Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    /**
     * 保存软件int缓存数据
     * @param context
     * @param key
     * @param value
     */
    public static void putInt(Context context,String key , int value) {
        SharedPreferences sp = context.getSharedPreferences("wudianxinwen", Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    /**
     * 得到软件int缓存数据
     * @param context
     * @param key
     * @return
     */
    public static int getInt(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences("wudianxinwen", Context.MODE_PRIVATE);
        return sp.getInt(key,0);
    }

}
